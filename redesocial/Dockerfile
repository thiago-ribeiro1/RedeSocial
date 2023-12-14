FROM maven:3.8.6-amazoncorretto-18 as build
WORKDIR /redesocial
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:18-ea-10-jdk-slim
WORKDIR /redesocial
COPY --from=build ./redesocial/target/*.jar ./redesocial.jar
ENTRYPOINT java -jar redesocial.jar