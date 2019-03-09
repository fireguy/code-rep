package top.fireguy.springboot.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.fireguy")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
