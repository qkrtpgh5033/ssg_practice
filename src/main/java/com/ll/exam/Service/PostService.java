package com.ll.exam.Service;

import com.ll.exam.Post;
import com.ll.exam.Repository.PostRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PostService {
    private final PostRepository postRepository = new PostRepository();


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
