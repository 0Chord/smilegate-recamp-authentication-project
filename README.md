# Smilegate Re:Camp 개인 프로젝트 - 인증시스템 💻
## 💾 간단 프로젝트 설명

---
- [x] 이번 프로젝트는 스프링 시큐리티를 사용하지 않고 스프링 부트 기본 제공 라이브러리(Interceptor)와 Spring AOP를 활용하여 인증/인가 서비스를 구현하는 프로젝트
- [x] JWT 사용
- [x] 단순 구현이 아닌 지금까지의 학습한 내용을 가지고 코드 설계와 리팩토링을 할 예정
- [x] FE는 React를 사용하여 구성
- [x] 회원가입 시 회원 인증은 문자메시징 시스템을 활용하여 인증처리
## ✏️ 사용 기술

---
- [x] Java 17
- [x] Spring Boot 3.1.6
- [x] Spring Data JPA
- [x] React 
- [x] Docker
- [x] Jenkins
- [x] SonarQube
- [x] JMeter
## 🔎 프로젝트 요구 사항

---
- [x] 가입, 로그인 페이지
- [x] 유저 관리 페이지
- [x] 인증 서버 API
- [x] RDBMS DB 사용 : MySQL, MariaDB, PostgreSQL
- [x] Password Encryption
- [x] E-Mail 인증 (option)
- [x] 비밀번호 찾기 (option)
- [x] 캐시 (option)
## ✅ 예외 예측

---
- [x] 회원 가입
  - [x] 중복된 이메일 사용
  - [x] 휴대폰 인증 시 문자메세지 전송 실패
  - [x] 비밀번호, 검증 비밀번호 불일치
- [x] 로그인
  - [x] 아이디, 비밀번호 불일치
  - [x] 차단된 사용자 로그인 시도
- [x] 기능 사용
  - [x] accessToken 불일치 문제
  - [x] accessToken 만료 문제
  - [x] accessToken 만료 시 refreshToken 만료 문제
## 🎯 구현 기능 목록

---
- [x] Jenkins를 활용한 자동 배포
  - [x] GCP에 VM 생성
  - [x] Docker를 활용 배포
  - [x] SonarQube를 활용한 정적 검사 실행
- [x] Table 작성
  - [x] 개인 프로젝트이므로 Entity를 작성하여 바로 table 생성
- [x] 회원 가입
  - [x] 비밀번호 검증
  - [x] 문자메시지를 통한 전화번호 검증
  - [x] 중복 회원 검증
- [x] 로그인
  - [x] 아이디, 비밀번호 일치 검증
  - [x] 정지된 회원 검증
- [x] 기능 이용
  - [x] accessToken 검증
  - [x] refreshToken 검증
- [x] 매니저
  - [x] 회원 정지
  - [x] 회원 조회