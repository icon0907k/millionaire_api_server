# 1. OpenJDK 17 이미지를 기반으로 설정
FROM openjdk:17-jdk-slim

# 2. 환경 변수 설정 (기본값: test)
ENV SPRING_PROFILES_ACTIVE=test

# 3. 컨테이너 시작 시 실행할 명령어 설정
ENTRYPOINT ["java", "-jar", "build/libs/afterwork.millionaire-0.1.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
