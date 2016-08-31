package hello;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by anton.riabov on 8/25/2016.
 */
@Document(collection = "authors")
public class Author {

    @Id
    private String id;
    private List<String> posts;

    public Author() {
    }

    public Author(String id, List<String> posts) {
        this.id = id;
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void setPosts(List<String> posts) {
        this.posts = posts;
    }
}
