package com.example.blog.entity;

import com.example.blog.dto.ImgDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
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

    public ImgEntity(Long id, String imgName, String oriImgName, String imgUrl, String repimgYn) {
        this.id = id;
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
        this.repimgYn = repimgYn;
    }

    // for service
//    public ImgEntity(String imgName, String oriImgName, String imgUrl) {
//        this.imgName = imgName;
//        this.oriImgName = oriImgName;
//        this.imgUrl = imgUrl;
//    }

    // for service_patch_파라미터로 new entity 를 받음
//    public void imgPatch(ImgEntity imgEntity){
//        if(imgEntity.imgName != null){
//            this.imgName = imgEntity.imgName;
//        }
//        if(imgEntity.oriImgName != null){
//            this.oriImgName = imgEntity.oriImgName;
//        }
//        if(imgEntity.imgUrl != null){
//            this.imgUrl = imgEntity.imgUrl;
//        }
//    }

    // postImgService 에서 이미지 수정할 때 사용함
    public void imgUpdate(String imgName, String oriImgName, String imgUrl){
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

    // 글+이미지 찾을 때 사용함
    public ImgDto toDto(){
        return new ImgDto(id,imgName,oriImgName,imgUrl,repimgYn);
    }

}
