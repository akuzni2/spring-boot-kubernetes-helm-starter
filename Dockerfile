FROM openjdk:8-jre-alpine
WORKDIR /demo-service/
COPY /target/*.jar /demo-service/demo-service.jar
ENTRYPOINT ["java","-jar", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/dumps/oom.bin", "demo-service.jar"]
