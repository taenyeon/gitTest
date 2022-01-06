package com.tech.db;

public class bowlBoard {
    private String no;
    private String title;
    private String content;
    private String writer;
    private String date;
    private int hit;

    public String getNo() {return no;}

    public void setNo(String no) {this.no = no;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {return date;}

    public void setDate(String regDate) {this.date = regDate;}

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}
