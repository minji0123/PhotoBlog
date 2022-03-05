package com.example.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgDto {

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

}
