FROM maven:3.6.3-adoptopenjdk-15

ENV TZ=Europe/Moscow

EXPOSE 8080

COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn install
WORKDIR /tmp/target/

CMD ["java", "-jar", "course-model-0.0.1-SNAPSHOT.jar"]