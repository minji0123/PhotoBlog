package com.example.blog.dto;

import lombok.Data;

@Data
public class PostSearchDto {

    // 현재 시간과 글 등록일을 비교해서 데이터 조회
    private String searchDateType;
    /*
    all: 등록일 전체
    1d/1w/1m/6m: 최근 하루/1주/한달/6개월 동안 쓴 글
    */

    // 글 제목으로 조회
    private String searchBy;

    // 조회할 검색어를 저장할 변수
    private String searchQuery = "";

}

/*
 * 조회 조건이 엄청 복잡함!!
 *
 * 이렇게 조회 조건이 복잡한 화면은 Querydsl 을 이용한다.
 * 1. 조건에 맞는 쿼리를 동적으로 쉽게 생성 가능
 * 2. 비슷한 쿼리 재활용 가능
 * 3. IDE 가 문법 오류 바로 잡아줌 (쿼리쓰면은 db 문법이기 때문에 안잡아줌)
 * */
