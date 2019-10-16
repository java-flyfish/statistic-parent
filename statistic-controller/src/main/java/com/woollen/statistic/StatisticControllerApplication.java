package com.woollen.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.woollen.statistic.mapper")
public class StatisticControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticControllerApplication.class, args);
	}

}
