<img alt="moodeat_banner" src="https://github.com/user-attachments/assets/f5520069-526c-4d5e-9cbe-244ddb4a836d"/>

# 무드잇 (Mood:Eat)

기분을 담는 서비스 Mood:Eat입니다.  
모두에게 단순한 요리 레시피 제공을 넘어, 사용자의 감정과 경험을 반영한 맞춤형 요리를 제안합니다.

<br/>

## Service Flow
<image src="https://github.com/user-attachments/assets/c6c6ad7f-5221-4fc7-90fc-07f93bf7c688" width="800px" />

<br/>
<br/>
<br/>

## ERD
<image src="https://github.com/user-attachments/assets/e3f2decb-2702-4a11-b697-837e89ed347d" width="750px" />

<br/>
<br/>
<br/>

## API Specification
<image src="https://github.com/user-attachments/assets/68cac79b-8ddb-4d8b-94e2-61fe979ec14c" width="700px" />

<br/>
<br/>
<br/>

## System Architecture
<image src="https://github.com/user-attachments/assets/b602c762-bc1c-407f-b629-5cdf596bd6cf" width="600px" />

<br/>
<br/>
<br/>

## Git Branch Strategy

`main` ← `develop` ← `feature#{issue-no}-{name}`

<br/>
<br/>

## Commit Message Convention

### 형식

```
<type>(<optional scope>): <description>
```

### 타입

```
feat : 새로운 기능 추가
fix : 버그 수정
docs : 문서 변경
style : 코드 스타일 변경 (포맷팅 수정, 세미콜론 추가 등)
refactor : 코드 리팩토링
test : 테스트 코드 추가, 수정
build: 빌드 관련 수정
chore : 기타 사소한 수정 (.gitignore 수정 등)
```

<br/>
<br/>

## Getting Started

1. `src/main/resources/application.yml` 파일 추가
2. `docker-compose build`
3. `docker-compose up -d`
4. `./gradlew build`
5. `java -jar ./build/libs/moodeat-0.0.1-SNAPSHOT.jar`

<br/>
<br/>

## Members

|                                   Backend                                   |                 이름                 | 역할                                                                                                               |
|:---------------------------------------------------------------------------:|:----------------------------------:|:-----------------------------------------------------------------------------------------------------------------|
| <image src="https://avatars.githubusercontent.com/u/57435446" width=100px/> | [최용훈](https://github.com/yh010217) | • 데이터베이스 설계<br/> • 캐릭터/재료/레시피 관련 API 개발<br/> • 인프라 구축 및 SSL 설정<br/> • Docker 및 Github Actions 기반 CI/CD 파이프라인 자동화 |
| <image src="https://avatars.githubusercontent.com/u/78714820" width=100px/> | [문규원](https://github.com/MoonGyu1) | • 데이터베이스 설계<br/> • 추천 레시피 관련 API 개발<br/> • 네이버클라우드 Clova Studio Chat Completions API 연동<br/> • 프롬프트 엔지니어링                          |
