package com.zhaoshaung.excelprocess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zhaoshaung")
public class ExcelProcessApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelProcessApplication.class, args);
    }

}
