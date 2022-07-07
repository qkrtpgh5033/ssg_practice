package com.ll.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("=== 명원 SSG ===");

        outer:
        while(true){
            System.out.print("명령) ");
            try {
                String cmd = br.readLine().trim();

                switch (cmd){
                    case "종료":
                        break outer;
                }

            }catch (IOException e){

            }



        }

    }
}
