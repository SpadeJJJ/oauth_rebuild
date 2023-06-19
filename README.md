# OAuth2 인증 library


Oauth2 인증 library 개발

## 요약
Backend에서 해당 library를 통해서 Kakao, Naver 등 기업의 Login API를 간편화 하기 위함.

## 순서도

아래의 순서도를 가정으로 library를 설계

* LB : library
* BE : Backend로, LB를 쓰고 있는 프로젝트
* FE : frontend
* TS : Target Server (kakao, naver 등)
* TE : Template Engine (jsp, thymeleaf 등)

***

### 흐름
```mermaid
sequenceDiagram
USER ->> BE : 로그인 페이지 호출
BE ->> LB : State 생성 요청
LB ->> BE : State 생성 및 Redis 저장 후 전달
BE ->> TE : State model에 넣어서 전달
TE ->> TS : 로그인 요청
TS ->> TS : 로그인 인증 처리
TS ->> LB : redirect-url로 CallBack 처리 (Filter에서 처리)
LB ->> LB : State 체크
LB ->> TS : CallBack의 인증 code로 TS에 인증 토큰 요청
TS ->> LB : 결과(인증 토큰) 전달
LB ->> BE : Publisher를 통해 LB를 사용하고 있는 프로젝트에 인증 토큰 전달
```
이 경우에 LB에서 Redis를 통해 State를 처리.
FE가 따로 있는 경우에는 State를 LB에서 처리하지 않음.

***

## library 요약
* library 흐름
    1. 기업의 API 서버에서 CallBack url을 호출하면 Filter를 통해서 해당 요청을 받음.
    2. 상황에 따라 State를 검증함.
    3. Filter에서 url에 따라 기관을 분류하고, 기관별 인증 로직을 수행함.
    4. 수행한 결과(인증 토큰)를 Event Publisher <--> Event Listener를 통해 전달

## 서비스 정보 등록
* 설정 위치 : resources/config
* 형식 : *.properties

설정 정보
oauth2.authorize-info.naver.request-token-url=https://nid.naver.com/oauth2.0/token
oauth2.authorize-info.naver.client-id=UMKT4dIs6FAj23xkYaC1
oauth2.authorize-info.naver.secret-key=fwBz2BIrLy
oauth2.authorize-info.naver.call-back-uri=/naver/callback
oauth2.authorize-info.naver.call-back-host=http://localhost:8080
oauth2.authorize-info.naver.grant-type=authorization_code
oauth2.authorize-info.naver.authorize-url=https://nid.naver.com/oauth2.0/authorize


