package com.example.blog.service;

import com.example.blog.dto.PostDto;
import com.example.blog.entity.ImgEntity;
import com.example.blog.entity.PostEntity;
import com.example.blog.repository.ImgRepository;
import com.example.blog.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ImgRepository imgRepository;

    // 가상으로 이미지들 만들어둠
    List<MultipartFile> createMultipartFiles() throws Exception{

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<5; i++){
            String path = "C:/blog/post";
            String imgName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imgName, "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }

    @Test
    @DisplayName("글 등록 테스트")
    void savePost() throws Exception{

        // 글 생성
        PostDto postDto = new PostDto();
        postDto.setTitle("글제목");
        postDto.setContent("글내용");

        // 이미지 5개 생성
        List<MultipartFile> multipartFileList = createMultipartFiles();

        // 글+이미지 저장 -> 글+이미지 id 반환
        Long postId = postService.savePost(postDto, multipartFileList);

        // 그림 id
//        List<ImgEntity> imgEntityList = imgRepository.findByIdOrderByIdAsc(postId);
        List<ImgEntity> imgEntityList = imgRepository.findByPostEntityIdOrderByIdAsc(postId);

        // 글 id
        PostEntity postEntity = postRepository.findById(postId).orElse(null);

        // 글 비교
        assertEquals(postDto.getTitle(), postEntity.getTitle());
        assertEquals(postDto.getContent(),postEntity.getContent());

        // 이미지 비교 (첫번째 파일의 원본 이미지 == 원본 이미지 파일 이름)
        assertEquals(multipartFileList.get(0).getOriginalFilename(),imgEntityList.get(0).getOriImgName());
    }


}