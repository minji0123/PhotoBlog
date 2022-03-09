package com.example.blog.controller;

import com.example.blog.dto.PostDto;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    // 메인 페이지 view
    @GetMapping("/")
    public String main(@RequestParam Long id, Optional<Integer> page, Model model){

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0,8);
//        PostDto postDto = postService.getPostDetail(id);

//        model.addAttribute("post",postDto);
        model.addAttribute("maxPage",5);

        return "main";
    }
}
