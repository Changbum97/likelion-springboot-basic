# 멋쟁이 사자처럼 Spring Boot (2022/10/26 ~ )

## Day 1 (10/26 수)
- Spring Boot Project 생성 및 서버 실행
- GetController 생성 후 @RestController 적용
- Talend API Tester(크롬 확장프로그램) 사용해 테스트 진행
- URL Mapping 실습
  - @RequestMapping, @GetMapping 등 사용 가능
- @PathVariable 을 사용해 URL의 Path Variable 받아서 출력
<br/><br/>
- 요약
  1. @RestController
  2. @RequestMapping
  3. @GetMapping
  4. @PathVariable
<br/><br/>
## Day 2 (10/27 목)
- @RequestParam 을 사용해 URL의 Query Parameter 받아서 출력
- @PathVariable, @RequestParam 같이 적용 
- Map(Key, Value)으로 Query Parameter 받기
- MemberDto 생성 후 DTO로 Query Parameter 받기
- @PostMapping 을 사용해 HTTP POST 요청 받기
- @RequestBody 를 사용해 HTTP Request Body 값 받기
- Request Body를 DTO로 받기
- ResponseEntity를 사용해 Response return
- Swagger 3.0 적용
  - springfox-boot-starter(3.0.0) 라이브러리 추가
  - springfox-swagger-ui(3.0.0) 라이브러리 추가 
  - application.yml 수정
  - http://localhost:8080/swagger-ui/
<br/><br/>
- 정리
  1. @RequestParam
  2. @PostMapping
  3. @RequestBody
  4. @PutMapping, @DeleteMapping
  5. ResponseEntity
<br/><br/>
## Day3 (10/28 금)
- Controller에 @Slf4j 어노테이션 추가
- log.info를 통해 로그 출력 가능
- 전에 작성했던 [UserDao](https://github.com/Changbum97/Toby-Spring3-Test/blob/master/src/main/java/UserExercise/dao/UserDao_Final.java)를 활용한 CRUD 기능 개발
  1. JdbcTemplate을 활용해 DB와 연결했던 UserDao 불러오기
  2. MySql 연결을 위한 라이브러리 추가
     - spring-boot-starter-jdbc (2.7.5)
     - mysql-connector-java (8.0.30)
  3. application.yml을 통해 DB connection 설정
     - 비밀번호는 환경변수를 통해 설정
  4. User 객체 생성
     - @Getter, @AllArgsConstructor 적용
  5. Request Body를 통해 User을 받음
     - UserRequestDao 생성
  6. UserController 생성
  7. @Autowired를 사용해 UserDao를 주입 받음
  8. URL Mapping과 UserDao의 기능을 사용해 CRUD 구현
     - add, deleteAll, findById, findAll, getCount 기능
<br/><br/>
- 정리
  1. @Slf4j => log.info
  2. application.yml + 환경변수를 통한 DB connection 설정
  3. @Getter, @Setter, @Data
  4. @AllArgsConstructor
  5. @Autowired
<br/><br/>
## Day4 (10/31 월)
- 전국 병의원 빅데이터를 활용한 미니 프로젝트
  1. 전국 병의원 정보를 담고있는 CSV 파일 다운
  2. 필요한 데이터 선택 및 테이블 설계 => MySQL에 Table(nation_wide_hospitals) 생성
  3. Hospital 객체 생성
  4. 전에 작성했던 Parser(interface), ReadLineContext를 가져와 HospitalParser 제작
  5. HospitalParser Test 진행
  6. ReadLineContext에 HostpitalParser을 조립해주는 ParserFactory 생성
  7. ParserFactory로 조립한 hospitalReadLineContext로 파일을 제대로 읽어오는지 Test 진행
<br/><br/>
- 정리
  1. @NoArgsConstructor
  2. @SpringBootTest, @Autowired
<br/><br/>
## Day5 (11/01 화)
- 전국 병의원 빅데이터를 활용한 미니 프로젝트
  8. HospitalDao 생성
  9. JdbcTemplate을 사용해서 Insert 기능 구현
  10. getCount 기능 구현
  11. deleteAll 기능 구현
  12. findById 기능 구현