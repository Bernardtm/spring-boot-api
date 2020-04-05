# Build Stage
FROM maven:3.6.3-jdk-14 as build

COPY . .

RUN mvn install -DskipTests

# Production Ready Stage 
FROM openjdk:14-jdk as release
WORKDIR /app
COPY --from=build /root/.m2/repository/com/bernardtm/octoEvents/0.0.1-SNAPSHOT/octoEvents-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java","-jar","octoEvents-0.0.1-SNAPSHOT.jar"]