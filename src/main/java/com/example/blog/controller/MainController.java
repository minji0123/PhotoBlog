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

        // example (mini test1)
        mainPostDtoList.add(MainPostDto.builder().id(1L).title("토끼").content("귀여운 토끼")
                .imgUrl("https://w.namu.la/s/fdeb18748079eec1a3b9e27dc1b5bea0e0a52e22d7517f2e405006d19bdb339d8c876f233afc7b6f42564b33846ca98483a44d9abbabcf5f9c89cd9957193c6fd1ce63945e032890389d96e0af55edbfd5184b3504652e37d75aedb413958693")
                .build());

        // example (mini test1)
        mainPostDtoList.add(MainPostDto.builder().id(2L).title("치와와").content("귀여운 치와와")
                .imgUrl("https://ww.namu.la/s/ae38850ca7ec97f3e2928ad2103bb698214a816c7ddfe672bd34e4fdb4faa62fe4715377640e9003b0273eabb4afc7be4006bbc0e6c845f449d6039ac8615e9add4e9926cd6f89d172aefa0ec18e16d4")
                .build());

        // example (mini test2)
        mainPostDtoList.add(MainPostDto.builder().id(3L).title("만화").content("재밌는만화")
                .imgUrl("https://w.namu.la/s/0d9a3344c787ea338a75e8ab65d7ccf73c5cd1b91e1c48d5d88008ab2918e951592c63f642317a58470a47dfa6f86acbac125d3a66bd80ad9987a13378f3c867b4421d50fd2adfcfec0fcb740ceec4788615ce98a6a81882721c8bc5113217dc00931b1e66c362f225b8ce5a5c17a8b3")
                .build());

        // example (mini test2)
        mainPostDtoList.add(MainPostDto.builder().id(4L).title("노트북").content("옛날 노트북")
                .imgUrl("https://w.namu.la/s/6e08850837dcd39ca16c8c9c0a9ce25beb9e0505cfd32c9b77ea254c392d903d8dbdb817e919d66f5bf34b07aded2d9df562520708a6067a4316937af824c4f8d1db0f16ed8f8e3f3c7b7179e7869f0b50d2942c61ff882d30cd5259f99b3e90")
                .build());

        model.addAttribute("mainPostDtoList",mainPostDtoList);
        return "main";
    }
}
