# build stage
FROM gradle:jdk17-alpine AS builder

WORKDIR /usr/app/

COPY . .

RUN gradle bootJar

FROM eclipse-temurin:17.0.5_8-jre-alpine

COPY --from=builder /usr/app/build/libs/*.jar /opt/app/application.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD java -jar /opt/app/application.jar