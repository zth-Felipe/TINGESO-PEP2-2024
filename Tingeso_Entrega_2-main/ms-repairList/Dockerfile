FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ms-repairList.jar
ENTRYPOINT ["java","-jar","/ms-repairList.jar"]