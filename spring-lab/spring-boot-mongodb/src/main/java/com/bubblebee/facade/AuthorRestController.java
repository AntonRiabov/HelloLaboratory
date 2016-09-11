package com.bubblebee.facade;

import com.bubblebee.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 *
 * Created by paladii on 01.09.2016.
 */
@RestController
@RequestMapping("/authors")
public class AuthorRestController {
    public static final String AUTHORS_ROOT_NAME = "authors";
    @Autowired
    AuthorRepository authorRepository;


    @RequestMapping(method = RequestMethod.GET)
    public HashMap<String, Object> getAuthors() {
        return new HashMap<String, Object>() {
            {
                put(AUTHORS_ROOT_NAME, authorRepository.findAll());
            }
        };
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HashMap<String, Object> getAuthor(@PathVariable(value = "id") String id) {

        return new HashMap<String, Object>() {
            {
                put(AUTHORS_ROOT_NAME, authorRepository.findOne(id));
            }
        };
    }

}
