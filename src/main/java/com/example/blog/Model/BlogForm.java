package com.example.blog.Model;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {
    private int id;
    private String name;
    private String text;
    private String thumbnail;
    private String typeBlog;
    private MultipartFile img;

    public BlogForm(int id, String name, String text, MultipartFile img) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.img = img;
    }

    public BlogForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getTypeBlog() {
        return typeBlog;
    }

    public void setTypeBlog(String typeBlog) {
        this.typeBlog = typeBlog;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
