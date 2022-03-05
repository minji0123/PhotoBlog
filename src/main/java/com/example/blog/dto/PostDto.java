package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Long id;

    @NotBlank(message = "제목을 입력해 주세요!")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요!")
    private String content;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    // 상품의 이미지 아이디를 저장하는 리스트
    // 상품 등록 전에는 이미지가 없으니까 비어있음(이미지도 공백, 아이디도 공백!)
    // 그냥 수정할 때 이미지 아이디 저장해둘 용도
    private List<Long> itemImgIdList = new ArrayList<>();

}
