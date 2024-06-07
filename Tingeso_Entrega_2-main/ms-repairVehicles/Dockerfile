FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-repairVehicles.jar
ENTRYPOINT ["java","-jar","/ms-repairVehicles.jar"]