# OAuth2 인증 library
***

Oauth2 인증 libarary 개발

## 요약
Backend에서 해당 library를 통해서 Kakao, Naver 등 기업의 Login API를 간편화 하기 위함.

## 순서

* LB : library
* BE : Backend로, LB를 쓰고 있는 프로젝트
* FE : frontend
* TS : Target Server (kakao, naver 등)
* TE : Template Engine (jsp, thymeleaf 등)

### 1. FE와 BE가 따로 있는 경우
```mermaid
sequenceDiagram
FE ->> TS : FE에서 State 생성 후 로그인 요청
TS ->> TS : 로그인 인증 처리
TS ->> LB : redirect-url로 CallBack 처리
LB ->> TS : CallBack의 인증 code로 TS에 인증 토큰 요청
TS ->> LB : 결과(인증 토큰) 전달
LB ->> BE : Publisher를 통해 LB를 사용하고 있는 프로젝트에 인증 토큰 전달
```

### 2. WAS에 한 번에 있는 경우 
```mermaid
sequenceDiagram
USER ->> BE : 로그인 페이지 호출
BE ->> TE : State생성 후 model에 넣어서 전달
TE ->> TS : 로그인 요청
TS ->> TS : 로그인 인증 처리
TS ->> LB : redirect-tul로 CallBack 처리
LB ->> LB : State 체크
LB ->> TS : CallBack의 인증 code로 TS에 인증 토큰 요청
TS ->> LB : 결과(인증 토큰) 전달
LB ->> BE : Publisher를 통해 LB를 사용하고 있는 프로젝트에 인증 토큰 전달
```
이 경우에 State는 LB의 redis를 통해 관리
