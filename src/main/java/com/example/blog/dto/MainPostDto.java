package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 필요한 정보만 출력하기 위해 만든 main dto
@Data
@Builder
@AllArgsConstructor
public class MainPostDto {

    private Long id;
    private String title;
    private String content;
    private String imgUrl;
}
