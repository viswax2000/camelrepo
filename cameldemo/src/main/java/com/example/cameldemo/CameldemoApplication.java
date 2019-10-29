package com.example.cameldemo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CameldemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CameldemoApplication.class, args);
		CamelContext context = new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					from("file:src/main/resources/data/inbox").log(" ... Transferring files to outbox ... ").to("file:src/main/resources/data/outbox");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.start();
		Thread.sleep(10000);
		context.stop();
		
	}

}
