FROM openjdk:17-jdk-slim

WORKDIR /opt/build/
COPY ./build/*.txt /opt/build/
COPY ./build/libs/*.jar /opt/build/

ENTRYPOINT ["java","-jar","ded-msa-customer-1.0.0.jar"]