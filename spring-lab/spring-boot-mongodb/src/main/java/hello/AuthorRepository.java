package hello;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by paladii on 01.09.2016.
 */
public interface AuthorRepository extends MongoRepository<Author, String> {
}
