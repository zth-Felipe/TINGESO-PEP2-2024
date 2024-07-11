FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-reparaciones-vehiculos.jar
ENTRYPOINT ["java","-jar","/ms-reparaciones-vehiculos.jar"]