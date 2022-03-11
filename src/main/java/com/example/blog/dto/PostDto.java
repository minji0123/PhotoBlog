package com.example.blog.dto;

import com.example.blog.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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

    public PostDto(Long id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // 수정할 때 글 이미지 아이디를 저장하는 리스트
    private List<Long> postImgIdList = new ArrayList<>();

    // 수정할 때 글 안의 이미지 정보를 저장하는 리스트
    private List<ImgDto> postImgDtoList = new ArrayList<>();

    public PostEntity toEntity (){
        // 글 수정할 때 사용함
        return new PostEntity(id,title,content);
    }

}
