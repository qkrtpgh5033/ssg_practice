package com.ll.exam;

public class Post {
    private Long id;
    private String author;
    private String talk;

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTalk() {
        return talk;
    }

    public Post(String author, String talk) {
        this.author = author;
        this.talk = talk;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTalk(String talk) {
        this.talk = talk;
    }
}
