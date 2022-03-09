package com.example.blog.entity;

import com.example.blog.dto.PostDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Table
@Data
public class PostEntity {

    // id, title, content
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    private String title;

    private String content;

    // for service
    public PostEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // for service_patch
    public void postPatch(PostEntity postEntity){
        if(postEntity.title != null){
            this.title = postEntity.title;
        }
        if(postEntity.content != null){
            this.content = postEntity.content;
        }
    }

    // toDto
    public PostDto toDto(){
        return new PostDto(id,title, content);
    }

}
