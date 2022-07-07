package com.ll.exam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App {
    ArrayList<Post> list = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String path = "D:\\CodeLion_Study\\ssg_practice\\src\\main\\java\\com\\ll\\exam\\json\\post.json";
    long num = 1;

    public App(){
        jsonRead();
    }
    public void jsonRead(){
        // FileReader 생성
        Reader reader = null;
        try {
            reader = new FileReader(path);
            Gson gson = new Gson();
            JSONArray jsonArray = gson.fromJson(reader, JSONArray.class);

            // Json 파일 읽어서, <Post> List로 변환
            if(jsonArray != null){
                list = new Gson().fromJson(jsonArray.toString(), new TypeToken<List<Post>>() {
                }.getType());

                num = list.get(list.size()-1).num; // 최신번호
                num += 1L; // 다음번호

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void jsonWrite(){

        JSONArray jArray = new JSONArray();
        for(int i = 0; i < list.size(); i++){
            JSONObject sObject = new JSONObject(); // 배열 내에 들어갈 json
            sObject.put("num", list.get(i).num);
            sObject.put("author", list.get(i).author);
            sObject.put("talk", list.get(i).talk);
            jArray.add(sObject);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        try {
            FileWriter fw = new FileWriter(path);
            gson.toJson(jArray,fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePost(int id){
        Post findPost = findById(id);
        if(findPost != null){
            System.out.println(id + "번 명언을 수정합니다.");
            System.out.println("기존 명언 : " + findPost.talk);
            System.out.print("새 명언 : ");

            try {
                String updateTalk = br.readLine();
                findPost.talk = updateTalk;


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(id + "번 명언이 수정되었습니다.");

        }
    }
    public Post findById(int id){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).num == id){
                return list.get(i);
            }
        }
        System.out.println(id+"번 명언은 존재하지 않습니다.");
        return null;
    }

    public void printList(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i = 0; i < list.size(); i++){
            System.out.printf("%d / %s / %s\n", list.get(i).num, list.get(i).author, list.get(i).talk);
        }
    }

    public void run() {


        System.out.println("=== 명원 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            try {
                String cmd = br.readLine().trim();

                switch (cmd){
                    case "종료":
                        jsonWrite();
                        break outer;
                    case "등록":
                        System.out.print("명언 : ");
                        String talk = br.readLine();
                        System.out.print("작가 : ");
                        String author = br.readLine();
                        list.add(new Post(num, author, talk));
                        System.out.println((num++)+"번이 등록되었습니다.");
                        break;
                    case "수정":
                        System.out.print("수정 ID : ");
                        int id = Integer.parseInt(br.readLine());
                        updatePost(id);
                        break;
                    case "목록":
                        printList();
                        break;
                    case "삭제":
                        break;
                    default:
                        System.out.println("올바르게 입력해주세요.");
                        break;


                }

            }catch (IOException e){

            }





        }

    }


}
