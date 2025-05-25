package com.sbs.basic1.boundedContext.home.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpRequest;

@Controller
// '개발자가 스프링부트한테 해당 클래스는 컨트롤러 클래스이다.'라고 전달
public class HomeController {
  int no;

  public HomeController() {
    no = 0;
  }

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

  @GetMapping("/home/increase")
  @ResponseBody
  public int showIncrease() {
    return no++;
  }

  @GetMapping("/home/plus")
  @ResponseBody
  public int showPlus(@RequestParam(defaultValue = "0") int a, int b) {
    // 쿼리 파라미터와 매개변수 이름은 일치해야 한다.
    // ?a=1&b=2
    // a와 b는 매개변수 이름
    // 1과 2는 쿼리 파라미터 값

    // @RequestParam은 쿼리 파라미터를 매개변수로 전달받는다.
    // @RequestParam은 생략 가능하다.
    // @RequestParam은 생략하면 쿼리 파라미터와 매개변수 이름이 일치해야 한다.

    // 파라미터 값에 기본값을 주는 경우 @RequestParam(defaultValue = "0") 통해 기본값 지정이 가능하다.
    return a + b;
  }

  @GetMapping("/home/gugudan")
  @ResponseBody
  public String showGugudan(
      @RequestParam(defaultValue = "9") int dan,
      @RequestParam(defaultValue = "9") int limit
  ) {

    StringBuilder rs = new StringBuilder();

    for (int i = 1; i <= limit; i++) {
      rs.append(dan)
          .append(" * ")
          .append(i)
          .append(" = ")
          .append(dan * i)
          .append("<br>"); // HTML 줄바꿈 태그
    }
    
    // rs를 string 타입으로 변환
    return rs.toString();
  }

  @GetMapping("/home/gugudan/{dan}/{limit}")
  @ResponseBody
  // @PathVariable : @RequestParam 처럼 직접적인 기본값 설정이 불가능
  public String showGugudanByPathVariable(
      @PathVariable int dan,
      @PathVariable int limit
  ) {

    StringBuilder rs = new StringBuilder();

    for (int i = 1; i <= limit; i++) {
      rs.append(dan)
          .append(" * ")
          .append(i)
          .append(" = ")
          .append(dan * i)
          .append("<br>"); // HTML 줄바꿈 태그
    }

    // rs를 string 타입으로 변환
    return rs.toString();
  }
}
