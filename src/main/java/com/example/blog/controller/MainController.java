package com.example.blog.controller;

import com.example.blog.dto.MainPostDto;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    // 메인 페이지 view
    @GetMapping("/")
    public String main(Model model){

        List<MainPostDto> mainPostDtoList = postService.showPost();

        // example (mini test)
        mainPostDtoList.add(MainPostDto.builder().id(2L).title("title!!").content("content!!")
                .imgUrl("https://ww.namu.la/s/ae38850ca7ec97f3e2928ad2103bb698214a816c7ddfe672bd34e4fdb4faa62fe4715377640e9003b0273eabb4afc7be4006bbc0e6c845f449d6039ac8615e9add4e9926cd6f89d172aefa0ec18e16d4")
                .build());


        model.addAttribute("mainPostDtoList",mainPostDtoList);
        return "main";
    }
}
