package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * Created by anton.riabov on 8/31/2016.
 */
@RestController
@RequestMapping("/posts")
public class PostsRestController {
    public static final String POSTS_ROOT_NAME = "posts";
    @Autowired
    PostRepository  postRepository;


    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, Object> getPosts(){
                                   return new HashMap<String, Object>(){
                                       {put(POSTS_ROOT_NAME,postRepository.findAll());}
                                   };
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HashMap<String, Object> getPost(@PathVariable(value = "id") String id ){

        return new HashMap<String, Object>(){
            {put(POSTS_ROOT_NAME, postRepository.findOne(id));}
        };
    }

}
