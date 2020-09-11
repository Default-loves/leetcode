package com.junyi.handle.form;

/**
 * @time: 2020/8/13 17:46
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
public class Book {

    private Long id;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
