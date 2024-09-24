package com.highgarden.springboot_board.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;


@Controller					// 해당 클래스를 BEAN에 등록해두고 클라이언트 요청에 따라 올바른 컨트롤러로 매핑시켜줄 수 있음
@Slf4j						// 로깅을 손쉽게 사용할 수 있게 해주는 어노테이션으로 메서드가 호출되는지 확인하기 위해 사용
public class IndexController {
	
	@GetMapping("/")		// 기본 루트로 localhost:8080을 호출하면 실행된다.
	public String index() {
		log.info("index메서드 call");
		return "index";		// 특정 String을 return 할 경우 스프링이 템플릿 폴더에서 자동으로 index 라는 파일명의 정적 리소스를 검색한다.
	}
}
