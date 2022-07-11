package com.ll.exam.study;

public class TestService {

    Long id = 1L;

    public MemberDTO createMember(){
        String name = "bumseo";
        String nickName = "lion";

        MemberDTO memberDTO = new MemberDTO(id++, name, nickName);


        return memberDTO;
    }



}



