# Rsupport 서버개발자 사전과제

## Requirement

### 과제
공지사항 웹 어플리케이션 구현

### 기능
- 사용자는 텍스트로 된 공지를 추가할 수 있다.
- 사용자는 공지를 수정/삭제할 수 있다.
- 사용자는 공지목록을 조회할 수 있다.
- 조회시 제목, 작성일, 작성자, 최종수정일, 내용이 조회 가능하다.
- 목록은 페이징 기능이 있다.

### 과제 요구사항
- 웹 어플리케이션으로 개발
- 웹어플리케이션 개발언어는 Java 로 한다.
- 웹 프레임워크는 Spring Framework (또는 SpringBoot) 을 사용한다.
- 프론트엔드 구현방법은 제약 없음
- 데이타베이스는 사용에 제약 없음 (가능하면 In-memory db 사용, 예를들어 H2)
- 테스트 케이스구현은 선택사항
- 로그인 기능은 선택사항
- 제출시 프로젝트 빌드, 실행 방법 명시 (ex.README.md 파일에 설명을 기술)

---

## 구현 기능

* [x] 회원가입, 로그인/로그아웃
* [x] 공지사항 조회(회원/비회원)
* [x] 공지사항 등록(회원)
* [x] 공지사항 수정,삭제(작성자)
* [x] 페이징

---

## Environment
- Java 8
- Gradle
- JUnit 4, logback
- Spring-Boot 2.1.1 RELEASE
- Spring Data JPA/Hibernate
- H2 Database
- Handlebars
- Bootstrap 4.2.1
- jQuery 3.3.1


---
## 프로젝트 빌드, 실행방법

### 프로젝트 빌드

#### 1. 프로젝트 클론
```bash
$ git clone https://github.com/Sehun-Kim/java-notice.git
$ cd java-notice
```

#### 2. 프로젝트 빌드, 실행
- **build jar**
```bash
$ ./gradlew build -x test
$ chmod 755 build/libs/notice-java-1.0.0.jar
$ java -jar build/libs/notice-java-1.0.0.jar
```

- **use gradle**
```bash
$ ./gradlew bootRun
```

#### 3. 프로젝트 접속
- Project url : `http://localhost:8080` 접속
- DB url : `http://localhost:8080/h2-console/` 접속
```
1) JDBC URL 설정 : jdbc:h2:mem://localhost/~/javanotice;
2) Connect 클릭
```

#### 4. 서버 종료
- `ctrl + c`

### Test 코드 실행
> Acceptance Test, UnitTest


1. `application.properties`의 ***profile*** 과 ***import sql*** 주석 해제
```properties
# profile
spring.profiles.active=test

# import sql
spring.jpa.properties.hibernate.hbm2ddl.import_files=test.sql
```

2. logging level setting

logback.xml 설정 변경
> INFO -> DEBUG
```xml
<logger name="com.assignment.rsupport.noticejava" level="DEBUG"/>
<logger name="com.assignment.rsupport.support" level="DEBUG"/>
```


3. IntelliJ 혹은 STS IDE를 사용하여 JUnit 테스트 코드 실행


