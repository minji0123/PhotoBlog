package com.example.blog.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

// 필요한 정보만 출력하기 위해 만든 main dto
@Data
@Builder
public class MainPostDto {

    private Long id;
    private String title;
    private String content;
    private String imgUrl;


    @QueryProjection
    public MainPostDto(Long id, String title, String content, String imgUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
    }
}
