package com.sbs.basic1.boundedContext.home.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
// '개발자가 스프링부트한테 해당 클래스는 컨트롤러 클래스이다.'라고 전달
public class HomeController {
  private int no;
  private List<Person> people;

  public HomeController() {
    no = 0;
    people = new ArrayList<>();
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

  @GetMapping("/home/returnBoolean")
  @ResponseBody
  public boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  @ResponseBody
  public double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnArray")
  @ResponseBody
  public int[] showReturnArray() {
    int[] arr = {10, 20, 30};

    return arr;
  }

  @GetMapping("/home/returnList")
  @ResponseBody
  public List<Integer> showReturnList() {
    // List<Integer> list = List.of(10, 20, 30);

    /*
    List<Integer> list = new ArrayList<>(){{
      add(10);
      add(20);
      add(30);
    }};
    */

    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);

    return list;
  }

  @GetMapping("/home/returnMap")
  @ResponseBody
  public Map<String, Object> showReturnMap() {
    /*
    Map<String, Object> map = Map.of(
        "id", 1,
        "subject", "제목1",
        "content", "내용1",
        "writerName", "홍길순",
        "articleNo", new ArrayList<>() {{
          add(1);
          add(2);
          add(3);
        }}
    );
     */

    Map<String, Object> map = new LinkedHashMap<>() {{
      put("id", 1);
      put("subject", "제목1");
      put("content", "내용1");
      put("writerName", "홍길순");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};

    return map;
  }

  @GetMapping("/home/returnArticle")
  @ResponseBody
  public Article showReturnArticle() {
    Article article = new Article(1, "제목1", "내용1", "김철수", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});

    return article;
  }

  @GetMapping("/home/returnArticle2")
  @ResponseBody
  public Article2 showReturnArticle2() {
    Article2 article = new Article2(1, "제목1", "내용1", "김철수", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});

    return article;
  }

  @GetMapping("/home/returnArticleMapList")
  @ResponseBody
  public List<Map<String, Object>> showReturnArticleMapList() {
    Map<String, Object> articleMap1 = new LinkedHashMap<>() {{
      put("id", 1);
      put("subject", "제목1");
      put("content", "내용1");
      put("writerName", "홍길순");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};

    Map<String, Object> articleMap2 = new LinkedHashMap<>() {{
      put("id", 2);
      put("subject", "제목2");
      put("content", "내용2");
      put("writerName", "홍길동");
      put("articleNo", new ArrayList<>() {{
        add(4);
        add(5);
        add(6);
      }});
    }};

    Map<String, Object> articleMap3 = new LinkedHashMap<>() {{
      put("id", 3);
      put("subject", "제목3");
      put("content", "내용3");
      put("writerName", "임꺽정");
      put("articleNo", new ArrayList<>() {{
        add(7);
        add(8);
        add(9);
      }});
    }};

    List<Map<String, Object>> list = new ArrayList<>();
    list.add(articleMap1);
    list.add(articleMap2);
    list.add(articleMap3);

    return list;
  }

  @GetMapping("/home/returnArticleList")
  @ResponseBody
  public List<Article2> showReturnArticleList() {
    Article2 article1 = new Article2(1, "제목1", "내용1", "김철수", new ArrayList<>() {{
      add(1);
      add(2);
      add(3);
    }});

    Article2 article2 = new Article2(2, "제목2", "내용2", "김영희", new ArrayList<>() {{
      add(4);
      add(5);
      add(6);
    }});

    Article2 article3 = new Article2(3, "제목3", "내용3", "최수영", new ArrayList<>() {{
      add(7);
      add(8);
      add(9);
    }});

    List<Article2> list = new ArrayList<>();
    list.add(article1);
    list.add(article2);
    list.add(article3);

    return list;
  }

  @GetMapping("/home/personTestcase")
  @ResponseBody
  public String personTestcase() {
    people.add(new Person("홍길동", 11));
    people.add(new Person("홍길순", 22));
    people.add(new Person("임꺽정", 33));
    
    return "테스트 케이스 추가";
  }

  @GetMapping("/home/addPerson")
  @ResponseBody
  public String addPerson(String name, int age) {
    Person p = new Person(name, age);
    System.out.println("p : " + p);

    people.add(p);

    return "%d번 사람이 추가되었습니다.".formatted(p.getId());
  }

  @GetMapping("/home/removePerson")
  @ResponseBody
  public String removePerson(int id) {
    /*
    // v1
    Person target = null;

    for(Person p : people) {
      if(p.getId() == id) {
        target = p;
        break;
      }
    }

    if(target == null) {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }
    people.remove(target);
    */

    // v2
    /*
    Person p = people.stream()
        .filter(person -> person.getId() == id)
        .findFirst()
        .orElse(null);

    people.remove(p);
    */

    // v3
    boolean removed = people.removeIf(person -> person.getId() == id);
    // 조건에 맞는 걸 찾은 경우 true 반환, 실패한 경우 false 반환

    if(!removed) {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }

    return "%d번 사람이 삭제되었습니다.".formatted(id);
  }

  @GetMapping("/home/removePerson/{id}")
  @ResponseBody
  public String removePersonPathVariable(@PathVariable int id) {
    boolean removed = people.removeIf(person -> person.getId() == id);

    if(!removed) {
      return "%d번 사람이 존재하지 않습니다.".formatted(id);
    }

    return "%d번 사람이 삭제되었습니다.".formatted(id);
  }


  @GetMapping("/home/showPeople")
  @ResponseBody
  public List<Person> showPeople() {
    return people;
  }
}

class Article {
  public int id;
  public String subject;
  public String content;
  public String writerName;
  public List<Integer> articleNo;

  public int getId() {
    return id;
  }

  public String getSubject() {
    return subject;
  }

  public String getContent() {
    return content;
  }

  public String getWriterName() {
    return writerName;
  }

  public List<Integer> getArticleNo() {
    return articleNo;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setWriterName(String writerName) {
    this.writerName = writerName;
  }

  public void setArticleNo(List<Integer> articleNo) {
    this.articleNo = articleNo;
  }

  public Article(int id, String subject, String content, String writerName, List<Integer> articleNo) {
    this.id = id;
    this.subject = subject;
    this.content = content;
    this.writerName = writerName;
    this.articleNo = articleNo;
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", subject='" + subject + '\'' +
        ", content='" + content + '\'' +
        ", writerName='" + writerName + '\'' +
        ", articleNo=" + articleNo +
        '}';
  }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class Article2 {
  private int id;
  private String subject;
  private String content;
  private String writerName;
  private List<Integer> articleNo;
}

@AllArgsConstructor
@Data
class Person {
  private static int lastId;
  private final int id;
  private final String name;
  private final int age;

  static {
    lastId = 0;
  }

  public Person(String name, int age) {
    this(++lastId, name, age);
  }
}