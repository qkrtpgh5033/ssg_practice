package com.ll.exam.Controller;

import com.ll.exam.Domain.Post;
import com.ll.exam.Request;
import com.ll.exam.Service.PostService;

import java.util.ArrayList;
import java.util.Scanner;

public class PostController {
    private final PostService postService = new PostService();
    private Scanner sc;
    public PostController(Scanner sc) {
        this.sc = sc;
    }

    private Long getParamValue(Request reQuest){
        Long paramId = reQuest.getIntParm("id", 0L);
        if(paramId == 0 ){
            System.out.println("id를 입력 해주세요");
        }
        return paramId;
    }
    public void updatePost(Request reQuest){
        Long paramValue = getParamValue(reQuest);

        if (paramValue != 0) {
            Post findPost = postService.findById(paramValue);

            if(findPost == null){
                System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramValue);
                return;
            }
            System.out.printf("명언(기존) : %s\n", findPost.getContent());
            System.out.print("명언 : ");

            String updateTalk = sc.nextLine();
            findPost.setContent(updateTalk);

            System.out.printf("작가(기존) : %s\n", findPost.getAuthor());
            System.out.print("작가 : ");

            String updateAuthor = sc.nextLine();
            findPost.setAuthor(updateAuthor);

            System.out.printf("%d번 명언이 수정되었습니다.\n", findPost.getId());


        }



    }
    public void printList(){

        ArrayList<Post> list = postService.showList();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i = 0; i < list.size(); i++){
            System.out.printf("%d / %s / %s\n", list.get(i).getId(), list.get(i).getAuthor(), list.get(i).getContent());
        }


    }
    public void removePost(Request reQuest) {

        Long paramId = getParamValue(reQuest);
        if (paramId != 0) {
            Post findPost = postService.findById(paramId);

            if(findPost == null){
                System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramId);
                return;
            }
            postService.removePost(findPost);
            System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
        }
    }

    public void registerPost()  {


        System.out.print("명언 : ");
        String talk = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        Post savePost = new Post(author, talk);
        Post post = postService.registerPost(savePost);
        System.out.println(post.getId()+"번 명언이 등록되었습니다.");



    }


    public void exit(){
        postService.exit();
    }


}
