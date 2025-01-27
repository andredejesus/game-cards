# Parte responsavel por gera o build da aplicação
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Parte responsavel por rodar a aplicação
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/cards-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]

