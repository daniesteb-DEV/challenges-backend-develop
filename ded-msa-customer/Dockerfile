FROM openjdk:17-jdk-slim

WORKDIR /opt/build/
COPY ./build/*.txt /opt/build/
COPY ./build/libs/*.jar /opt/build/

CMD ["/bin/sh", "/opt/build/init.sh"]