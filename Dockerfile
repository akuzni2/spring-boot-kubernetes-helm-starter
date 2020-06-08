FROM openjdk:8-jre-alpine
WORKDIR /demo-service/
COPY /target/*.jar /demo-service/demo-service.jar
ENTRYPOINT ["java","-jar", "demo-service.jar"]
