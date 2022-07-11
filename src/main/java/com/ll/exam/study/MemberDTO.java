package com.ll.exam.study;


/**
 * DTO는 데이터 전달만 하면 된다.
 * setter메소드로 객체를 실수로 건드는 일을 방지할 수 있게
 * 불변객체로 만들자.
 */
public class MemberDTO {

    private final long id;
    private final String name;
    private final String nickName;

    public MemberDTO(long id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    public long getId() {return id;}
    public String getNickName() {return nickName;}

    public String getName() {return name;}

}




