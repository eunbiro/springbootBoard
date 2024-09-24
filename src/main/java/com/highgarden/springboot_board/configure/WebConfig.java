package com.highgarden.springboot_board.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	/*
	 사진 파일을 저장할 때 저장할 공간을 마련하고 사진파일에 대한 요청이 들어왔을 때 스프링 경로를 탐색할 수 있도록 맵핑 경로 설정
	*/
	
	private String resourcePath = "/upload/**";
	
	private String savePath = "file:///D:/board_tistory/springboot-board/src/main/resources/upload_files/";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(resourcePath)
				.addResourceLocations(savePath);
	}
}
