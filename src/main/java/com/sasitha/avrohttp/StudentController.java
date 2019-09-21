package com.sasitha.avrohttp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @GetMapping("/hello")
    public String sayAvroHello(){
        return "Avro Hello";
    }
}
