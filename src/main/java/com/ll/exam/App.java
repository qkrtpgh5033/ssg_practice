package com.ll.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
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
                        break outer;
                    case "등록":
                        System.out.print("명언 : ");
                        String talk = br.readLine();
                        System.out.print("작가 : ");
                        String author = br.readLine();
                        System.out.println((num++)+"번이 등록되었습니다.");

                }

            }catch (IOException e){

            }



        }

    }
}
