
# Usamos una imagen de Maven para construir el proyecto
FROM maven:3.9.9-amazoncorretto-17-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Usamos una imagen más ligera para ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

ARG DB_HOST
ARG DB_USER
ARG DB_PASSWORD

ENV DB_HOST=${DB_HOST}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PASSWORD}

COPY --from=build /app/target/*.jar backend-tickets.jar

ENTRYPOINT ["java", "-jar", "/backend-tickets.jar"]
EXPOSE 8080
