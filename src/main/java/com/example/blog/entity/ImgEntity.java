package com.example.blog.entity;

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

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostEntity postEntity;


    // for service
    public ImgEntity(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

    // for service_patch
    public void postPatch(ImgEntity imgEntity){
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

}
