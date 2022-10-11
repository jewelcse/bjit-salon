FROM openjdk:11
EXPOSE 8080
ADD target/config-service.jar config-service.jar
ENTRYPOINT ["java","-jar","/config-service.jar"]