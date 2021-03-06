package com.ll.exam.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ll.exam.Domain.Post;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DB(json) 접근
 */

public class JsonProcessing {
    String path = "D:\\CodeLion_Study\\ssg_practice\\data.json";

    public ArrayList<Post> jsonRead(){
        ArrayList<Post> list = null;
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
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }



    public void jsonWrite(ArrayList<Post> list){

        JSONArray jArray = new JSONArray();
        for(int i = 0; i < list.size(); i++){
            JSONObject sObject = new JSONObject(); // 배열 내에 들어갈 json
            sObject.put("id", list.get(i).getId());
            sObject.put("author", list.get(i).getAuthor());
            sObject.put("content", list.get(i).getContent());
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
}
