package com.ll.exam.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ll.exam.JsonProcessing;
import com.ll.exam.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 데이터 보관 및 찾는 용도
 */
public class PostRepository {

    private ArrayList<Post> list;
    private static long id = 1L;
    private final JsonProcessing jsonProcessing;

    public Long getLastId(){
        return id;
    }

    public PostRepository() {
         list = new ArrayList<>();
         jsonProcessing = new JsonProcessing();
         init();

    }

    private void init(){
        jsonProcessing.jsonExist();
        list = jsonProcessing.jsonRead();
        if(list != null){
            id = list.get(list.size()-1).getId(); // 최신번호
            id += 1L; // 다음번호
        }else{
            list = new ArrayList<>();
        }
    }


    public ArrayList<Post> getList(){
        return this.list;
    }

    public Post savePost(Post post){
        post.setId(id++);
        list.add(post);
        return post;
    }

    public void removePost(Post post){
        list.remove(post);
    }


    public Post findById(Long id){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == id){
                return list.get(i);
            }
        }
        System.out.println(id+"번 명언은 존재하지 않습니다.");
        return null;
    }

    public void buildPosts(){
        jsonProcessing.jsonWrite(list);
    }






}
