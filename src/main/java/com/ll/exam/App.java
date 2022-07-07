package com.ll.exam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class App {
    ArrayList<Post> list = new ArrayList<>();
    public void jsonWrite(){
        //            Reader reader = new FileReader("db.json");

        JSONObject obj = new JSONObject();
        JSONArray jArray = new JSONArray();
        for(int i = 0; i < list.size(); i++){
            JSONObject sObject = new JSONObject(); // 배열 내에 들어갈 json
            sObject.put("num", list.get(i).num);
            sObject.put("author", list.get(i).author);
            sObject.put("talk", list.get(i).talk);
            jArray.add(sObject);
        }

        obj.put("Name", "Test");
        obj.put("id", "userId");
        obj.put("item", jArray);

        System.out.println("test");
        System.out.println(obj.toString());
        System.out.println(obj.toJSONString());


        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter fw = new FileWriter("D:\\CodeLion_Study\\ssg_practice\\src\\main\\java\\com\\ll\\exam\\json\\post.json");
            gson.toJson(jArray,fw);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("=== 명원 SSG ===");

        int num = 1;

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




                }

            }catch (IOException e){

            }





        }

    }
}
