package com.daraz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.daraz.obj.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class DarazApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarazApplication.class, args);
	}

}
