package com.example.notesfordanil.dao;

public class Note {
    private int id ;
    private String notetopic ;
    private String Content ;

    public Note(int id, String notetopic, String content) {
        this.id = id;
        this.notetopic = notetopic;
        Content = content;
    }

    public Note() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotetopic() {
        return notetopic;
    }

    public void setNotetopic(String notetopic) {
        this.notetopic = notetopic;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
