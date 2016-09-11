package com.bubblebee.repository;

import com.bubblebee.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * Created by anton.riabov on 8/31/2016.
 */
@Service("post-repo")
public interface PostRepository  extends MongoRepository<Post, String>{
}
