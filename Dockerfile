# 1. OpenJDK 23 이미지를 기반으로 설정
FROM openjdk:23-jdk-slim-alpine

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 JAR 파일을 컨테이너의 /app 디렉토리로 복사
COPY target/*.jar apiServer.jar

# 4. 환경 변수 설정 (기본값: test)
ENV SPRING_PROFILES_ACTIVE=test

# 5. 컨테이너 시작 시 실행할 명령어 설정
ENTRYPOINT ["java", "-jar", "/app/apiServer.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
