package com.bubblebee.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 * Created by anton.riabov on 8/31/2016.
 */
@Document(collection = "posts")
@JsonRootName(value = "post")
//@XmlRootElement(name = "post")
public class Post {

    @Id
    private String id;
//    @JsonIgnore
    private String author;
    private String title;
    private String body;
    private Date date;

    public Post() {
    }

    public Post(String author, String title, String body, Date date) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("id='").append(id).append('\'');
        sb.append(", author=").append(author);
        sb.append(", title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
