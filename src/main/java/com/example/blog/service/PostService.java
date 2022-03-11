package com.example.blog.service;


import com.example.blog.dto.ImgDto;
import com.example.blog.dto.MainPostDto;
import com.example.blog.dto.PostDto;
import com.example.blog.entity.ImgEntity;
import com.example.blog.entity.PostEntity;
import com.example.blog.repository.ImgRepository;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostImgService postImgService;

    private final PostRepository postRepository;
    private final ImgRepository imgRepository;


    public List<MainPostDto> showPost(){
        List<MainPostDto> mainPostDtoList = new ArrayList<>();

        List<PostEntity> postEntities = postRepository.findAll();

        for (PostEntity postEntity : postEntities) {
            String repimgUrl = "";
            List<ImgEntity> imgEntities = imgRepository.findByPostEntityIdOrderByIdAsc(postEntity.getId());

            for (ImgEntity imgEntity : imgEntities) {
                if (imgEntity.getRepimgYn().equals("Y")){
                    repimgUrl = imgEntity.getImgUrl();
                    break;
                }
            }

            MainPostDto mainPostDto = MainPostDto.builder()
                    .id(postEntity.getId())
                    .title(postEntity.getTitle())
                    .content(postEntity.getContent())
                    .imgUrl(repimgUrl)
                    .build();

            mainPostDtoList.add(mainPostDto);
        }

        return mainPostDtoList;
    }

    public Long savePost(PostDto postDto, List<MultipartFile> itemImgFileList) throws Exception{

        // 글 저장
        // dto -> entity -> repository
        PostEntity postEntity = postDto.toEntity();
        postRepository.save(postEntity);


        // 이미지 등록
        for(int i=0; i<itemImgFileList.size();i++){
            // 글 하나에 이미지 5개까지 저장
            ImgEntity imgEntity = new ImgEntity();
            imgEntity.setPostEntity(postEntity);

            if(i == 0){
                imgEntity.setRepimgYn("Y");
            }
            else{
                imgEntity.setRepimgYn("N");
            }
            postImgService.savePostImg(imgEntity, itemImgFileList.get(i));
        }
        return postEntity.getId();
    }

    // 글 + 글이미지 repository -> entity -> dto
    @Transactional(readOnly = true) // 트랜젝션을 readOnly 로 설정할 경우, JPA 가 변경감지(더티체킹)를 수행하지 않아서 성능 향상됨_데이터 수정이 일어나지 않기 때문에
    public PostDto getPostDetail(Long id){

        // 이미지 repository -> entity -> dto
        List<ImgEntity> imgEntityList = imgRepository.findByPostEntityIdOrderByIdAsc(id);
        List<ImgDto> imgDtoList = new ArrayList<>();

        for (ImgEntity imgEntity : imgEntityList){
            ImgDto imgDto = imgEntity.toDto();
            imgDtoList.add(imgDto);
        }

        // 글 repository -> entity -> dto
        PostEntity postEntity = postRepository.findById(id).orElse(null);

        PostDto postDto = postEntity.toDto();
        postDto.setPostImgDtoList(imgDtoList);

        return postDto;
    }


    public Long updatePost(PostDto postDto, List<MultipartFile> itemImgFileList) throws Exception{

        // repository -> entity -> dto

        // 글 수정
        // dto -> entity -> repository
        // postDto postEntity 둘 다 title, content 만 있어서 괜춘함 (id 는 바꿀 필요가없지)
        PostEntity postEntity = postDto.toEntity();
        PostEntity target = postRepository.findById(postDto.getId()).orElse(null);
        target.postPatch(postEntity);
        postRepository.save(target);

        // 이미지 수정
        List<Long> imgIdList = postDto.getPostImgIdList();
        for(int i=0; i<itemImgFileList.size();i++){

            // imgIdList.get(i): 수정할 이미지들의 id, itemImgFileList.get(i): 수정할 이미지 정보
            postImgService.updateImg(imgIdList.get(i), itemImgFileList.get(i));
        }
        return target.getId();
    }

    public void deletePost(Long postDtoId){
        PostEntity postEntity = postRepository.findById(postDtoId).orElse(null);
        postImgService.deleteImg(postDtoId);
        if (postEntity != null){
            postRepository.delete(postEntity);
        }

    }
}
