package com.devops.dockerization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DockerizationApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DockerizationApplication.class, args);
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(bean-> System.out.println(bean));
	}

}
