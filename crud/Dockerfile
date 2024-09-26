# Use uma imagem Maven válida com JDK 21
FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Use uma imagem mais leve do JDK 21 para rodar o app
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/crud-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]