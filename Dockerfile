FROM gradle:jdk23-alpine AS dependencies
WORKDIR /opt/app
ENV GRADLE_USER_HOME=/cache
COPY build.gradle settings.gradle.kts gradle.properties ./
RUN gradle :dependencies --no-daemon --stacktrace

FROM gradle:jdk23-alpine AS builder
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME
COPY --from=dependencies /cache /home/gradle/.gradle
COPY --from=dependencies $APP_HOME $APP_HOME
COPY src ./src
RUN gradle :clean :bootJar --no-daemon --stacktrace

FROM eclipse-temurin:23-alpine AS final
ENV APP_HOME=/opt/app SPRING_PROFILES_ACTIVE=local
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]