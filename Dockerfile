# Imagem base para o JDK 17
FROM openjdk:17-jdk-slim

# Instala o Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho
WORKDIR /app

# Copia o código fonte para o contêiner
COPY . .

# Executa o build do projeto usando Maven
RUN mvn clean package

# Expõe a porta da aplicação
EXPOSE 8080

# Define o entrypoint para rodar a aplicação
ENTRYPOINT ["java", "-jar", "target/security-vault-0.0.1-SNAPSHOT.jar"]
