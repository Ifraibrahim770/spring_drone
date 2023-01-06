FROM maven:3.8.4 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package

# For Java 19,
FROM openjdk:19-oracle

ARG JAR_FILE=drone-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app


COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","drone-0.0.1-SNAPSHOT.jar"]
