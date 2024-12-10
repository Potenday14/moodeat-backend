# MOOD EAT

### 실행 방법

docker-compose.yml , application.yml 에서 필요한 것들 맞춰줍니다.
1. docker-compose.yml 의 db.environment 초기 설정
   * 현재 너무 쉽게 적어놨습니다.
   * 개인적으로 다시 작성하고, 따로 저장해놓으면 한번 실행한 후 지워도 됩니다.
2. docker-compose.yml 의 app.environment 의 마지막. 데이터베이스 이름 맞추기
3. application.yml 의 spring.datasource 맞추기

docker-compose build

docker-compose up -d

실행

디버깅을 위해 인텔리제이에서 실행하고 싶으시면
* 로컬에서 postgres 서버를 돌리고 인텔리제이 스프링 실행
* 도커에서 postgres만 따로 실행시키고 인텔리제이 스프링 실행 (실험 안해봤습니다)
* 도커에서 docker-compose 실행시켜 postgres가 실행되게 하고 인텔리제이 스프링 실행
  * docker-compose의 스프링도 8080, 인텔리제이의 스프링도 8080이어서 포트 충돌이 납니다.
    * docker-compose.yml 의 app.ports -~:8080 과 Dockerfile 의 EXPOSE ~ 을 바꾸거나
    * application.yml 의 server.port=~~ 를 바꾸거나 (이러면 docker-compose.yml 도 바꿔야할 수도 있습니다.)

#### 주의사항

이 방식으로 로컬에서 돌리면 push pull을 해서 돌리는 게 아니기에 NCP에 적용되진 않습니다.
