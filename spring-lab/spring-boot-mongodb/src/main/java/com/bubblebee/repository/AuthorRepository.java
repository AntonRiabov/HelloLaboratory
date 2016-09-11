package com.bubblebee.repository;

import com.bubblebee.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * Created by paladii on 01.09.2016.
 */
@Service("author-repo")
public interface AuthorRepository extends MongoRepository<Author, String> {
}
