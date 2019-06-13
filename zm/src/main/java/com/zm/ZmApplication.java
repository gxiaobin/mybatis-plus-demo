package com.zm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xx
 * @date 2019/4/15 15:34
 */
// 如果有多个工程，编译或启动无法找到相关的jar包，需要制定扫描路径信息
@SpringBootApplication(scanBasePackages = "com.zm")
//@MapperScan("com.zm.mapper.*")
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@EnableSwagger2
//@EnableFeignClients
public class ZmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZmApplication.class, args);
	}

}
