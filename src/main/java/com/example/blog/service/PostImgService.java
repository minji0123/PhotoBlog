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
    private String imgLocation;

    private final FileService fileService;
    private final ImgRepository imgRepository;

    // 이미지 저장
    // MultipartFile 인터페이스를 이용해서 업로드 한 파일의 이름, 실제 데이터, 파일 크기 등을 구할 수 있다.
    public void savePostImg(ImgEntity imgEntity, MultipartFile imgFile) throws Exception {

        /*
        imgName: 실제 로컬에 저장된 상품 이미지 파일 이름
        oriImgName: 업로드했던 상품 이미지 파일 초기 이름
        imgUrl: 업로드 결과 로컬에 저장된 상품 이미지 파일을 불러올 경로
         */

        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(oriImgName)) {

            // 이미지 이름 = 이미지경로 + 파일이름 + 파일크기
            imgName = fileService.uploadFiles(imgLocation, oriImgName, imgFile.getBytes());
            // 최종 이미지 경로
            imgUrl = "/item/" + imgName;
        }
        imgEntity.setImgName(imgName);
        imgEntity.setOriImgName(oriImgName);
        imgEntity.setImgUrl(imgUrl);

        imgRepository.save(imgEntity);

    }

    // 이미지 수정
    public void updateImg(Long imgId, MultipartFile imgFile) throws Exception{

        // 수정할 이미지 id 조회
        if(!imgFile.isEmpty()){
            ImgEntity imgSaveEntity = imgRepository.findById(imgId).orElse(null);

            // 기존 이미지 파일 삭제
            if (!StringUtils.isEmpty(imgSaveEntity.getImgName())){
                fileService.deleteFile(imgLocation + "/" + imgSaveEntity.getImgName());
            }

            // 수정한 이미지 파일 저장
            String oriImgName = imgFile.getOriginalFilename();
            String imgName = fileService.uploadFiles(imgLocation,oriImgName,imgFile.getBytes());
            String imgUrl =  "/item/" + imgName;

            // 1. setter 로 넣기
            // imgSaveEntity.setOriImgName(oriImgName);
            // imgSaveEntity.setImgName(imgName);
            // imgSaveEntity.setImgUrl(imgUrl);

            // 2. 만들어줬던 메소드로 넣기_파라미터 entity
            // imgSaveEntity.imgPatch(new ImgEntity(imgName, oriImgName,imgUrl));

            // 3. 만들어줬던 메소드로 넣기_파라미터 변수
            imgSaveEntity.imgUpdate(imgName, oriImgName,imgUrl);

        }
    }

    // 이미지 삭제
    public void deleteImg(Long imgId , MultipartFile imgFile) throws Exception{
        if(!imgFile.isEmpty()){
            ImgEntity imgEntity = imgRepository.findById(imgId).orElse(null);

            if(!StringUtils.isEmpty(imgEntity.getImgName())){
                fileService.deleteFile(imgLocation + "/" + imgEntity.getImgName());
            }
        }
    }
}

