# Use a imagem oficial do OpenJDK 24 como base
FROM eclipse-temurin:24-jdk

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml e o wrapper do Maven
COPY pom.xml mvnw* ./
COPY .mvn/ .mvn/

# Baixa as dependências do Maven (cache de dependências)
RUN ./mvnw dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src
COPY src/main/resources ./src/main/resources

# Compila o projeto
RUN ./mvnw clean package -DskipTests

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["./mvnw", "spring-boot:run"]
