package com.bubblebee;

import com.bubblebee.model.Author;
import com.bubblebee.repository.AuthorRepository;
import com.bubblebee.repository.CustomerRepository;
import com.bubblebee.model.Customer;
import com.bubblebee.model.Post;
import com.bubblebee.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/*import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;*/
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by paladii on 24.08.2016.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

/*

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {

        return new RepositoryRestConfigurerAdapter() {

            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/api");
                config.setDefaultMediaType(MediaType.APPLICATION_JSON);

            }
        };
    }
*/

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//        builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE)
//                .featuresToEnable(SerializationFeature.ARR)
        ; // enables wrapping for root elements
        return builder;
    }


    @Resource(name = "customer-repo")
    private CustomerRepository repository;

    @Resource(name = "post-repo")
    PostRepository postRepository;

    @Resource(name = "author-repo")
    AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        repository.deleteAll();

//         save a couple of customers
//        repository.save(new Customer("Alice", "Smith"));
//        repository.save(new Customer("Bob", "Smith"));
        authorRepository.deleteAll();
        postRepository.deleteAll();

        Author author = authorRepository.save(new Author("Anton"));

        Post post = postRepository.save(new Post(author.getId(), "title", "body", new Date()));
        Post post1 = postRepository.save(new Post(author.getId(), "title1", "body1", new Date()));

        author.setPosts(Arrays.asList(post.getId(), post1.getId()));

        authorRepository.save(author);

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

    }
}
