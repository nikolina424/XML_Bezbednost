FROM maven:3.8.1-ibmjava-8-alpine AS appBuild

WORKDIR /usr/src/picture-video_services
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/picture-video_services/target/picture-video_services.jar ./
EXPOSE 8080
CMD java -jar picture-video_services.jar