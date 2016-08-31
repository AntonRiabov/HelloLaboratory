package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by anton.riabov on 8/31/2016.
 */

public interface PostRepository  extends MongoRepository<Post, String>{
}
