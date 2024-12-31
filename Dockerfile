# Gradle 8.11.1 및 JDK 17을 사용하는 빌더 이미지
FROM gradle:8.11.1-jdk17 as builder

# 프로젝트 소스를 복사
COPY . /home/gradle/project

# 작업 디렉토리 설정
WORKDIR /home/gradle/project

# Gradle을 사용하여 Spring Boot JAR 파일 빌드
RUN gradle bootJar --no-daemon

# 실행 이미지를 Slim OpenJDK로 설정
FROM openjdk:17-jdk-slim

# 빌더 단계에서 생성된 JAR 파일을 실행 단계로 복사
COPY --from=builder /home/gradle/project/build/libs/afterwork.millionaire-0.1.jar /app/afterwork.millionaire-0.1.jar

# Spring Boot의 활성화된 프로파일 설정
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Spring Boot 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/afterwork.millionaire-0.1.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
