package com.ll.exam.study;

public class Main {

    TestService testService = new TestService();


    public String createMember() {
        MemberDTO member = testService.createMember();
        String nameOfMember = member.getName();
        String nickNameOfMember = member.getNickName();
        long idOfMember = member.getId();

        return "id : " + idOfMember + "이름 : " + nameOfMember + "닉네임 : " + nickNameOfMember;
    }
}
