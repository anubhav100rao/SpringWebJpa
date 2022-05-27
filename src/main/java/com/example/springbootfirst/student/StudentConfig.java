package com.example.springbootfirst.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

                    Student anubhav = new Student(
                            "Anubhav",
                            "anubhav100rao@gmail.com",
                            LocalDate.of(2001, Month.JULY, 9)
                    );
                    Student adarsh = new Student(
                            "Adarsh",
                            "adarsh100rao@gmail.com",
                            LocalDate.of(2003, Month.JUNE, 9)
                    );
                    studentRepository.saveAll(List.of(anubhav, adarsh));
        };
    }

}
