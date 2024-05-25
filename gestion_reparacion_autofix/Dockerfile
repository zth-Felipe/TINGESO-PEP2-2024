FROM openjdk:17
ARG JAR_FILE=./target/gestion_reparacion_autofix-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} gestion_reparacion_autofix-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","./gestion_reparacion_autofix-0.0.1-SNAPSHOT.jar"]