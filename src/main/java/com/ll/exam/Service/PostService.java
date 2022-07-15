package com.ll.exam.Service;

import com.ll.exam.Post;
import com.ll.exam.Repository.PostRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostService {
    private final PostRepository postRepository = new PostRepository();

//    public void updatePost(int paramValue){
//        Post findPost = postRepository.findById(paramValue);
//        System.out.printf("기존 명언 : %s\n", findPost.getTalk());
//        System.out.print("새 명언 : ");
//
//        try {
//            String updateTalk = br.readLine();
//            findPost.setTalk(updateTalk);
//            System.out.printf("%d번 명언이 수정되었습니다.", findPost.getId());
//
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public Post findById(Long id){
        return postRepository.findById(id);
    }

    public ArrayList<Post> showList(){
        return postRepository.getList();
    }
    public void removePost(Post findPost) {
        postRepository.removePost(findPost);
    }

    public Post registerPost(Post post)  {
        return postRepository.savePost(post);
    }


    public void exit(){
        postRepository.buildPosts();
    }

}
