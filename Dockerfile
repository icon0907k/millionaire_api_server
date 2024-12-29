FROM gradle:7.6-jdk17 as builder

COPY . /home/gradle/project

WORKDIR /home/gradle/project
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim

COPY --from=builder /home/gradle/project/build/libs/afterwork.millionaire-0.1.jar /app/afterwork.millionaire-0.1.jar

ENV SPRING_PROFILES_ACTIVE=test

ENTRYPOINT ["java", "-jar", "/app/afterwork.millionaire-0.1.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]
