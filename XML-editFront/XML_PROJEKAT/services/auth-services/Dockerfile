FROM maven:3.6.3-ibmjava-8-alpine AS appBuild

LABEL maintainer="danijelradakovic@uns.ac.rs"

WORKDIR /usr/src/auth_services
COPY . .
RUN mvn package -DskipTests

FROM openjdk:11.0 AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/auth_services/target/auth_services.jar ./
EXPOSE 8080
CMD java -jar auth_services.jar