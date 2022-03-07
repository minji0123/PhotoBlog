package com.example.blog.service;


import com.example.blog.dto.PostDto;
import com.example.blog.entity.ImgEntity;
import com.example.blog.entity.PostEntity;
import com.example.blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostImgService postImgService;
    private final PostRepository postRepository;

    // 파라미터로 String title, content 를 넣으면 너무 많아지는거같은데
    // 그렇다고 dto 에 toEntity 를 넣자니 어떤게 효율적일지 모르겠다.

    public Long savePost(PostDto postDto, List<MultipartFile> itemImgFileList) throws Exception{

        // 글 저장
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

            postImgService.savePostImg(imgEntity,itemImgFileList.get(i));
        }
        return postEntity.getId();
    }
}
