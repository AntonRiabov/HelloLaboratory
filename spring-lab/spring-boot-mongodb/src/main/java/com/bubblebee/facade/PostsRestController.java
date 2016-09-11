package com.bubblebee.facade;

import com.bubblebee.model.Post;
import com.bubblebee.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 *
 * Created by anton.riabov on 8/31/2016.
 */
@RestController
@RequestMapping("/posts")
public class PostsRestController {
    public static final String POSTS_ROOT_NAME = "posts";
    public static final String SINGLE_POST_ROOT_NAME = "post";
    @Autowired
    PostRepository postRepository;


    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> getPosts() {
        return Collections.singletonMap(POSTS_ROOT_NAME, postRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Map<String, Object> getPost(@PathVariable(value = "id") String id) {
        return Collections.singletonMap(SINGLE_POST_ROOT_NAME, postRepository.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Map<String, Object> updatePost(@PathVariable(value = "id") String id,
                                          @RequestBody Map<String, Post> payload) {
        Post post = payload.get(SINGLE_POST_ROOT_NAME);
        post.setId(id);
        return Collections.singletonMap(SINGLE_POST_ROOT_NAME, "postRepository.save(post)");
    }

}
