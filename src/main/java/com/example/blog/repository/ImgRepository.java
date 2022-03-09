package com.example.blog.repository;

import com.example.blog.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository <ImgEntity, Long> {

    // 글 수정 시 사용할 쿼리
    List<ImgEntity> findByIdOrderByIdAsc(Long id);

    List<ImgEntity> findByPostEntityIdOrderByIdAsc(Long id);

    // 블로그 글 대표 이미지 찾는 쿼리
    ImgEntity findByIdAndRepimgYn(Long id, String repimgYn);
}
