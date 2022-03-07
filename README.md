# Blog Project

이미지 업로드 및 출력을 공부하기 위한    
Photo Blog project 입니다.

## Plan

main page: 블로그 메인화면   
new post: 새 글 작성 페이지   
edit page: 블로그 글 수정 페이지   

main 화면은 작성한 글을 볼 수 있는 페이지   
새 글 작성 버튼을 누르면 블로그 글을 쓸 수 있음    
글 작성 시 이미지를 첨부해야 함   
글 수정 버튼을 누르면 글을 수정할 수 있음   


## package / class
### controller
MainController: 메인화면 view 리턴 (블로그 글 전체 조회)  
PostController: 블로그 글 등록,수정,삭제 view + post(delete) 리턴   

### dto
ImgDto: 이미지 업로드 시 필요한 데이터   
PostDto: 글 업로드 시 필요한 데이터   
PostFormDto: 

### entity
ImgEntity: db 에서 꺼내올 이미지 데이터
PostEntity: db 에서 꺼내올 글 데이터

### repository

### service
FileService: 파일 업로드, 삭제 메소드 구현
PostImgService: 글에다가 이미지 저장, 수정(수정 시 파일 삭제 후 재 업로드)
PostService: 글 (글+이미지) 저장, 수정, 조회, 삭제

FileService 로 파일 업로드/삭제 구현
->
PostImgService 로 이미지를 글에 업로드함
->
PostService 로 글을 업로드함
근데 이미지를 여러 개 등록할 수 있으니까
이미지 하나를 대표이미지로 정하고
나머지는 출력하게??