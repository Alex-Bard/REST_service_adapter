package com.example.demo;

import com.example.consumingwebservice.wsdl.Divide;
import com.example.consumingwebservice.wsdl.DivideResponse;
import com.example.demo.soapclient.CalculatorClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	CommandLineRunner lookup(CalculatorClient quoteClient) {
		return args -> {
			int a = 10;
			int b = 54;

			if (args.length > 0) {
				a = Integer.valueOf(args[0]);
				b = Integer.valueOf(args[1]);
			}
			Divide add = new Divide();
			add.setIntA(a);
			add.setIntB(b);
			DivideResponse response = quoteClient.<DivideResponse, Divide>getResponse(add, '/');
			System.out.println("dfsdfsdfsdf");
			System.out.println(response.getDivideResult());
		};
	}

}
