package com.ll.exam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {
    ArrayList<Post> list = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String path = "D:\\CodeLion_Study\\ssg_practice\\src\\main\\java\\com\\ll\\exam\\json\\data.json";
    long num = 1;


    public App(){
        jsonExist();
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

                num = list.get(list.size()-1).id; // 최신번호
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
            sObject.put("id", list.get(i).id);
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

    public void jsonExist(){

        File file = new File(path);
        if(!file.exists())
            jsonCreate();
    }

    public void jsonCreate(){
        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
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

    public void updatePost(ReQuest reQuest){
        int paramValue = reQuest.getIntParm("id", 0);

        if(paramValue == 0 ){
            System.out.println("id를 입력 해주세요");
            return;
        }
        Post findPost = findById(paramValue);
        System.out.printf("기존 명언 : %s\n", findPost.talk);
        System.out.print("새 명언 : ");

        try {
            String updateTalk = br.readLine();
            findPost.talk = updateTalk;
            System.out.printf("%d번 명언이 수정되었습니다.", findPost.id);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



    }
    public Post findById(int id){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).id == id){
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
            System.out.printf("%d / %s / %s\n", list.get(i).id, list.get(i).author, list.get(i).talk);
        }
    }

    private void removePost(int id) {
        Post findPost = findById(id);
        list.remove(findPost);
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }


    public void run() {

        int id;
        System.out.println("=== 명원 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            try {
                String cmd = br.readLine().trim();
                ReQuest reQuest = new ReQuest(cmd);


                switch (reQuest.getPath()){
                    case "종료":
                        jsonWrite();
                        break outer;
                    case "등록":
                        registerPost();
                        break;
                    case "수정":
//                        System.out.print("수정 ID : ");
//                        id = Integer.parseInt(br.readLine());
//                        updatePost(id);
                        updatePost(reQuest);
                        break;
                    case "목록":
                        printList();
                        break;
                    case "삭제":
                        removePost2(reQuest);
                        break;
                    case "빌드":
                        jsonWrite();
                        break;
                    default:
                        System.out.println("올바르게 입력해주세요.");
                        break;


                }

            }catch (IOException e){

            }





        }

    }

    private void removePost2(ReQuest reQuest) {

        int paramId = reQuest.getIntParm("id", 0);
        if (paramId == 0){
            System.out.println("id를 입력해주세요");
            return;
        }

        Post findPost = findById(paramId);
        if(findPost == null){
            System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramId);
            return;
        }
        list.remove(findPost);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    private void registerPost()  {

        try {

            System.out.print("명언 : ");
            String talk = br.readLine();
            System.out.print("작가 : ");
            String author = br.readLine();
            list.add(new Post(num, author, talk));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println((num++)+"번이 등록되었습니다.");
    }


}
