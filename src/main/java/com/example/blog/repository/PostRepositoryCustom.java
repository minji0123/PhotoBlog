package com.example.blog.repository;

import com.example.blog.dto.MainPostDto;
import com.example.blog.dto.PostSearchDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

/*
 * Querydsl 을 SpringDataJpa 와 함께 사용하기 위해서는 사용자 정의 repository 를 작성해야 함
 * 사용자 정의 repository 는 총 3단계임
 * 1. 사용자 정의 인터페이스 작성 ***
 * 2. 사용자 정의 인터페이스 구현
 * 3. Spring Data Jpa repository 에서 사용자 정의 인터페이스 상속 (ItemRepository)
 * */
public interface PostRepositoryCustom {

    // 메인 페이지에서 보여줄 글 리스트
//    Page<MainPostDto> getMainPostPage(PostSearchDto postSearchDto, Pageable pageable);
}
