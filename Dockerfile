# Build Stage
FROM maven:3.6.3-jdk-14 as build

COPY . .

RUN mvn -X install -DskipTests

# Production Ready Stage 
#FROM openjdk:14-jdk as release
#COPY --from=build /artifact /app