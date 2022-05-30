FROM openjdk:8-jdk-alpine

COPY build/libs/hbp-0.0.1.jar hbp-0.0.1.jar

ENTRYPOINT ["java","-jar","/hbp-0.0.1.jar"]