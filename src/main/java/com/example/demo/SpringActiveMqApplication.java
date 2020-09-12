package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SpringActiveMqApplication {

	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(SpringActiveMqApplication.class, args);
		Producer producer = app.getBean(Producer.class);
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date));
		String startTime =formatter.format(date);
		for (int i = 0; i < 100000; i++) {
			System.out.println("Counter: "+i);
			producer.sendMessage("inbound.queue", "Test");
		}
		System.out.println(startTime + " --- "+formatter.format(new Date(System.currentTimeMillis())));
		
		
	}

}
