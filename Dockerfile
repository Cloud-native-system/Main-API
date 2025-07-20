FROM eclipse-temurin:24-jdk

WORKDIR /app

# Maven setup
COPY pom.xml mvnw* ./
COPY .mvn/ .mvn/
RUN ./mvnw dependency:go-offline

# Compile the application
COPY src ./src
COPY src/main/resources ./src/main/resources
RUN ./mvnw clean package -DskipTests

EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]