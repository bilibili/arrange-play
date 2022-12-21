package com.arrange.play;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.arrange.play.dao.mysql.mapper")
@SpringBootApplication
public class ArrangePlayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ArrangePlayApplication.class, args);
  }

}
