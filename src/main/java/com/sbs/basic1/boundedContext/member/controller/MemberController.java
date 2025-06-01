package com.sbs.basic1.boundedContext.member.controller;

import com.sbs.basic1.base.rq.Rq;
import com.sbs.basic1.base.rsData.RsData;
import com.sbs.basic1.boundedContext.member.entiry.Member;
import com.sbs.basic1.boundedContext.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  public String login() {
    return "usr/member/login";
  }

  @PostMapping("/member/login")
  @ResponseBody
  public RsData login(String username, String password) {
    if (username.trim().isEmpty()) {
      return RsData.of("F-1", "아이디를 입력해주세요.");
    }

    if (password.trim().isEmpty()) {
      return RsData.of("F-2", "비밀번호를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if (rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/member/logout")
  @ResponseBody
  public RsData logout() {
    boolean cookieRemoved = rq.removeSession("loginedMemberId");

    if (!cookieRemoved) {
      return RsData.of("F-1", "이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/member/me")
  public String showMe(Model model) {
    long loginedMemberId = rq.getLoginedMember();

    Member member = memberService.findById(loginedMemberId);

    model.addAttribute("member", member);

    return "usr/member/me";
  }

  @GetMapping("/member/session")
  @ResponseBody
  public String showSession() {
    return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
  }
}
