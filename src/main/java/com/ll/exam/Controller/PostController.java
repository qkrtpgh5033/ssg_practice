package com.ll.exam.Controller;

import com.ll.exam.Post;
import com.ll.exam.ReQuest;
import com.ll.exam.Service.PostService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostController {
    private final PostService postService = new PostService();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private int getParamValue(ReQuest reQuest){
        int paramId = reQuest.getIntParm("id", 0);
        if(paramId == 0 ){
            System.out.println("id를 입력 해주세요");
        }
        return paramId;
    }

    public void updatePost(ReQuest reQuest){
        int paramValue = getParamValue(reQuest);
        if (paramValue != 0) {
            postService.updatePost(paramValue);
        }
    }
    public void printList(){
       postService.printList();
    }
    public void removePost(ReQuest reQuest) {

        int paramId = getParamValue(reQuest);
        if (paramId != 0) {
            postService.updatePost(paramId);
        }
        postService.removePost(paramId);
    }

    public void registerPost()  {

        try {

            System.out.print("명언 : ");
            String talk = br.readLine();
            System.out.print("작가 : ");
            String author = br.readLine();

            Post savePost = new Post(talk, author);
            postService.registerPost(savePost);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public void exit(){
        postService.exit();
    }


}
