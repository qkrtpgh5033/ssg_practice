package com.ll.exam;

public class Post {
    private Long id;
    private String author;
    private String content;

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public Post(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString(){
        return "Id : %d, 작가 : %s, 명언 : %s \n".formatted(getId(), getAuthor(), getContent());
    }
}
