package com.sbs.basic1.boundedContext.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// '개발자가 스프링부트한테 해당 클래스는 컨트롤러 클래스이다.'라고 전달
public class HomeController {
  @GetMapping("/home/main")
  // 개발자가 스프링부트한테
  // 만약에 '/home/main'이라는 요청이 들어오면
  // 아래 메서드를 실행시켜줘!
  @ResponseBody
  // 개발자가 스프링부트한테
  // 이 메서드가 실행된 결과를
  // Body의 응답으로 보내줘!
  public String showHomeMain() {
    return "반갑습니다.";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showHomeMain2() {
    return "어서오세요.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showHomeMain3() {
    return "스프링부트는 좋아요!!";
  }
}
