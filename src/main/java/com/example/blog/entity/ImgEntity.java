package com.example.blog.entity;

import com.example.blog.dto.ImgDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
public class ImgEntity {

    @Id
    @GeneratedValue
    @Column(name="img_id")
    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn; // 대표이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private PostEntity postEntity;


    // for service
    public ImgEntity(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

    // for service_patch_파라미터로 new entity 를 받음
    public void imgPatch(ImgEntity imgEntity){
        if(imgEntity.imgName != null){
            this.imgName = imgEntity.imgName;
        }
        if(imgEntity.oriImgName != null){
            this.oriImgName = imgEntity.oriImgName;
        }
        if(imgEntity.imgUrl != null){
            this.imgUrl = imgEntity.imgUrl;
        }
    }

    public void imgUpdate(String imgName, String oriImgName, String imgUrl){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

    public ImgDto toDto(){
        return new ImgDto(imgName,oriImgName,imgUrl);
    }

}
