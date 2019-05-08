package com.zm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xx
 * @date 2019/4/15 15:34
 */
@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@EnableSwagger2
@EnableFeignClients
public class ZmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZmApplication.class, args);
	}

}
