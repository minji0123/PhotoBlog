package com.example.blog.service;

import com.example.blog.entity.ImgEntity;
import com.example.blog.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

// 글에 이미지 업로드
@Service
@RequiredArgsConstructor
@Transactional
public class PostImgService {

    @Value("${itemImgLocation}") // .properties 의 itemImgLocation 값을 itemImgLocation 변수에 넣어줌
    private String itemImgLocation;

    private final FileService fileService;
    private final ImgRepository imgRepository;

    // 이미지 저장
    // MultipartFile 인터페이스를 이용해서 업로드 한 파일의 이름, 실제 데이터, 파일 크기 등을 구할 수 있다.
    public void savePostImg(ImgEntity imgEntity, MultipartFile itemImgFile) throws Exception{

        /*
        imgName: 실제 로컬에 저장된 상품 이미지 파일 이름
        oriImgName: 업로드했던 상품 이미지 파일 초기 이름
        imgUrl: 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러올 경로
         */

        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(oriImgName)){

            // 이미지 이름 = 이미지경로 + 파일이름 + 파일크기
            imgName = fileService.uploadFiles(itemImgLocation, oriImgName, itemImgFile.getBytes());
            // 최종 이미지 경로
            imgUrl = "/images/item/" + imgName;
        }

        imgRepository.save(new ImgEntity(imgName,oriImgName,imgUrl));

    }
}
