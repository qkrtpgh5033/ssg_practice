package com.ll.exam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ll.exam.Controller.PostController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public void run() {
        PostController postController = new PostController();
        System.out.println("=== 명원 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            try {
                String cmd = br.readLine().trim();
                ReQuest reQuest = new ReQuest(cmd);


                switch (reQuest.getPath()){
                    case "종료":
                        postController.exit();
                        break outer;
                    case "등록":
                        postController.registerPost();
                        break;
                    case "수정":
                        postController.updatePost(reQuest);
                        break;
                    case "목록":
                        postController.printList();
                        break;
                    case "삭제":
                        postController.removePost(reQuest);
                        break;
                    case "빌드":
                        postController.exit();
                        break;
                    default:
                        System.out.println("올바르게 입력해주세요.");
                        break;


                }

            }catch (IOException e){

            }
        }

    }




    public App(){
//        jsonExist();
//        jsonRead();
    }




}
