package com.sbs.basic1.boundedContext.member.controller;

import com.sbs.basic1.base.rq.Rq;
import com.sbs.basic1.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.entiry.Member;
import com.sbs.basic1.boundedContext.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;
  
  // 생성자 주입
  /*
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
  */

  @GetMapping("/member/login")
  @ResponseBody
  public String login() {
    if(rq.isLogined()) {
      return """
          <script>
            alert("이미 로그인 되어있습니다.");
          </script>
          """;
    }

    return """
        <div class="form-container">
        	<h1>🔗 로그인</h1>
        
        	<form id="urlGeneratorForm" method="POST" action="login">
        
        		<div class="form-group">
        			<label for="username">로그인아이디</label>
        			<input type="text" id="username" name="username" placeholder="로그인 아이디를 입력해주세요.">
        		</div>
        
        		<div class="form-group">
        			<label for="password">비밀번호</label>
        			<input type="password" id="password" name="password" placeholder="비밀번호를 입력해주세요.">
        		</div>
        
        		<div class="form-group">
        			<button type="submit">📋 로그인</button>
        		</div>
        	</form>
        </div>
        """;
  }

  @PostMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password, HttpServletRequest req, HttpServletResponse resp) {
    if(username.trim().isEmpty()) {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if(password.trim().isEmpty()) {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if(rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/member/logout")
  @ResponseBody
  public RsData logout(HttpServletRequest req, HttpServletResponse resp) {
    boolean cookieRemoved = rq.removeSession("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("F-1", "이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/member/me")
  @ResponseBody
  public RsData showMe(HttpServletRequest req, HttpServletResponse resp) {
    long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);

    boolean isLogined = loginedMemberId > 0;

    if(!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 '%s' 입니다.".formatted(member.getUsername()));
  }

  @GetMapping("/member/session")
  @ResponseBody
  public String showSession() {
    return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
  }
}
