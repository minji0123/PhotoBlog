package com.example.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class PostController {
    // 메인 페이지 view
    // 글 등록 view + post
    // 글 수정 view + post
    // 글 삭제 delete

    // 메인 페이지 view
    public String mainPage(Model model){
        return "";
    }

}
