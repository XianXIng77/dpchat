package com.example.dpchatapi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@MapperScan("com.example.dpchatapi.mapper")
@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
public class DpchatApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DpchatApiApplication.class, args);
        System.out.println(" ");
        System.out.println("=====================================");
        System.out.println("接口文档:http://localhost:9090/doc.html");
        System.out.println("=====================================");
    }

}
