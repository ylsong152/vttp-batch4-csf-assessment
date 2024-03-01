FROM node:21 AS builder

RUN npm i -g @angular/cli

WORKDIR /builder

COPY client/package*.json .
COPY client/angular.json .
COPY client/tsconfig.* .
COPY client/src src

RUN npm ci && ng build

FROM maven:3-eclipse-temurin-21

WORKDIR /app

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src
COPY --from=builder /app/dist/client/browser/ src/main/resources/static

RUN mvn package -Dmaven.test.skip=true

FROM maven:3-eclipse-temurin-21

WORKDIR /src 

COPY --from=sb-builder /src/target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080 

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar