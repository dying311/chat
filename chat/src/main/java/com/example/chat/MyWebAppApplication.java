package com.example.chat;

import com.example.chat.Service.DatabaseValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyWebAppApplication {


    @Autowired
    private DatabaseValidationService databaseValidationService;

    public static void main(String[] args) {
        SpringApplication.run(MyWebAppApplication.class, args);
    }


    //测试数据库连接
//    @Bean
//    public CommandLineRunner demo() {
//        return (args) -> {
//            // 在应用启动时调用验证方法
//            databaseValidationService.validateDatabaseConnection();
//        };
//    }
}