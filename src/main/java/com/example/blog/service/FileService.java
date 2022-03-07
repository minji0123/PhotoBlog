package com.example.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/*
UUID: Universally Unique Identifier
서로 다른 개체들을 구별하기 위해서 이름을 부여할 때 사용
고유성이 보장되는 id 임
실제로는 중복될 가능성이 거의 없어서 그냥 파일 이름으로 사용하면 됨
 */

@Service
@Slf4j
public class FileService {

    // 파일 업로드
    public String uploadFiles(String uploadPath, String originalFileName, byte[] fileData) throws Exception{

        // 완전 랜덤한 id 값 생성
        UUID uuid = UUID.randomUUID();
        // 원파일이름에서 . 뒤를 잘라버림 (aaa.jpg => jpg)
        String extention = originalFileName.substring(originalFileName.lastIndexOf("."));

        // 랜덤 id 값 + 파일 확장자
        String saveFileName = uuid.toString() + extention;
        // 파일업로드경로 = 업로드경로/랜덤 id 값.파일 확장자
        String fileUploadFullUrl = uploadPath + "/" + saveFileName;

        // 파일이 저장될 위치와 파일 이름을 파라미터로 넣어서 파일에 쓸 파일 출력 스트림 생성
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        // saveFileName (랜덤 id 값 + 파일 확장자) 반환
        return saveFileName;
    }

    // 파일 삭제
    public void deleteFile(String filePath) throws Exception{

        // 파일 저장 경로를 이용해서 파일 객체 생성
        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일 삭제 완료");
        }else{
            log.info("파일이 없습니다.");
        }

    }


}
