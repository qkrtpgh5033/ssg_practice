package com.ll.exam.Service;

import com.ll.exam.Post;
import com.ll.exam.ReQuest;
import com.ll.exam.Repository.PostRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void updatePost(int paramValue){
        Post findPost = postRepository.findById(paramValue);
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
    public void showList(){
        ArrayList<Post> list = postRepository.getList();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i = 0; i < list.size(); i++){
            System.out.printf("%d / %s / %s\n", list.get(i).getId(), list.get(i).getAuthor(), list.get(i).getTalk());
        }
    }
    public void removePost(int paramId) {

        Post findPost = postRepository.findById(paramId);
        if(findPost == null){
            System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramId);
            return;
        }
        postRepository.removePost(findPost);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    public void registerPost(Post post)  {
        postRepository.savePost(post);
        System.out.println(post.getId()+"번이 등록되었습니다.");
    }


    public void exit(){
        postRepository.buildPosts();
    }

}
