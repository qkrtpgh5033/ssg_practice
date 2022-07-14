package com.ll.exam.Controller;

import com.ll.exam.Post;
import com.ll.exam.Request;
import com.ll.exam.Service.PostService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostController {
    private final PostService postService = new PostService();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private int getParamValue(Request reQuest){
        int paramId = reQuest.getIntParm("id", 0);
        if(paramId == 0 ){
            System.out.println("id를 입력 해주세요");
        }
        return paramId;
    }
    public void updatePost(Request reQuest){
        int paramValue = getParamValue(reQuest);

        if (paramValue != 0) {
            Post findPost = postService.findById(paramValue);

            if(findPost == null){
                System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramValue);
                return;
            }
            System.out.printf("기존 명언 : %s\n", findPost.getTalk());
            System.out.print("새 명언 : ");

            try {
                String updateTalk = br.readLine();
                findPost.setTalk(updateTalk);
                System.out.printf("%d번 명언이 수정되었습니다.", findPost.getId());

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }



    }
    public void printList(){

        ArrayList<Post> list = postService.showList();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i = 0; i < list.size(); i++){
            System.out.printf("%d / %s / %s\n", list.get(i).getId(), list.get(i).getAuthor(), list.get(i).getTalk());
        }


    }
    public void removePost(Request reQuest) {

        int paramId = getParamValue(reQuest);
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

        try {

            System.out.print("명언 : ");
            String talk = br.readLine();
            System.out.print("작가 : ");
            String author = br.readLine();

            Post savePost = new Post(talk, author);
            Post post = postService.registerPost(savePost);
            System.out.println(post.getId()+"번이 등록되었습니다.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void exit(){
        postService.exit();
    }


}
