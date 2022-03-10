package com.example.blog.controller;

import com.example.blog.dto.ImgDto;
import com.example.blog.dto.PostDto;
import com.example.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 글 상세 view
    // 글 등록 view + post
    // 글 수정 view + post
    // 글 삭제 delete

    // 글 상세 view
    @GetMapping("/post/{id}")
    public String postDetail(Model model, @PathVariable Long id){

        PostDto postDto = postService.getPostDetail(id);
        model.addAttribute("postDto",postDto);
        return "post/detail";
    }

    // 글 등록 view
    @GetMapping("/new")
    public String mainPage(Model model){
        model.addAttribute("postDto",new PostDto());
        return "post/new";
    }

    // 글 등록 post
    @PostMapping("/new")
    public String newPost(@ModelAttribute PostDto postDto, @RequestParam("imgFile") List<MultipartFile> imgFileList,
                            Model model){

        // 글 작성 시 이미지가 없으면 애러발생
        if(imgFileList.get(0).isEmpty() && postDto.getId() == null){
            model.addAttribute("errorMessage","이미지를 넣어주세요");

            // 글 작성 페이지로 이동
            return "post/new";
        }

        // 글 + 이미지 작성
        try{
            postService.savePost(postDto ,imgFileList);

        }catch(Exception e){
            model.addAttribute("errorMessage","에러가 발생했습니다.");
            return "post/new";
        }

        return "redirect:/"; // 메인 페이지로 리다이렉트
    }

    // 글 수정 view
    @GetMapping("/edit/{id}")
    public String editPage(Model model, @PathVariable Long id){

        PostDto postDto = postService.getPostDetail(id);

        model.addAttribute("postDto",postDto);

        return "post/new";
    }

    // 글 수정 post
    @PostMapping("/edit/{id}")
    public String editPost(@ModelAttribute PostDto postDto, @RequestParam("imgFile") List<MultipartFile> imgFileList, Model model){

        // 글 수정 시 첫번째 이미지가 없으면 에러
        if (imgFileList.get(0).isEmpty() && postDto.getId() == null){
            model.addAttribute("errorMessage","첫번째 이미지를 넣어주세요");
            return "post/new";
        }

        // 글 수정
        try{
            postService.updatePost(postDto,imgFileList);
        }catch(Exception e){
            model.addAttribute("errorMessage","에러가 발생했습니다.");
            return "post/new";
        }

        return "redirect:/"; // 메인 페이지로 리다이렉트
    }

    // 글 삭제 delete
    @PostMapping
    public String deletePost(@ModelAttribute PostDto postDto, @RequestParam("imgFile") List<MultipartFile> imgFileList,Model model){

        try{
            postService.deletePost(postDto,imgFileList);
        }catch(Exception e){
            model.addAttribute("errorMessage","에러가 발생했습니다.");
            return "post/new";
        }
        return "redirect:/";
    }
}
