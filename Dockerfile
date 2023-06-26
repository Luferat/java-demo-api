FROM eclipse-temurin:17-jdk-alpine
RUN ./mvn clean package -DskipTests
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080