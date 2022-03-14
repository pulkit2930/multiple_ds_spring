package com.example.multiple.ds.api;

import com.example.multiple.ds.api.book.repository.BookRepository;
import com.example.multiple.ds.api.model.book.Book;
import com.example.multiple.ds.api.model.user.User;
import com.example.multiple.ds.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController

public class SpringBootMultipleDsApplication {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void addData2DB(){
        userRepository.saveAll(Stream.of(new User(744,"John"),new User(455,"Pitter")).collect(Collectors.toList()));
        bookRepository.saveAll(Stream.of(new Book(111,"Java"),new Book(222,"Spring")).collect(Collectors.toList()));
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultipleDsApplication.class, args);
    }

}
