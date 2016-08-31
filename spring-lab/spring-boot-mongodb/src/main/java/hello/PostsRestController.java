package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 *
 * Created by anton.riabov on 8/31/2016.
 */
@RestController
@RequestMapping("/posts")
public class PostsRestController {
    @Autowired
    PostRepository  postRepository;


    @RequestMapping(method = RequestMethod.GET)
    public Collection<Post> getPosts(){
                                   return postRepository.findAll();
    }

}
