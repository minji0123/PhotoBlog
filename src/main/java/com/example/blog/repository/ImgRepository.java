package com.example.blog.repository;

import com.example.blog.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImgRepository extends JpaRepository <ImgEntity, Long> {
}
