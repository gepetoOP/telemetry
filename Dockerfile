# Etapa de construção
FROM openjdk:21-jdk-slim AS build

# Instalar o Maven manualmente
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e o código-fonte
COPY pom.xml .
COPY src ./src

# Compilar o projeto
RUN mvn clean package -DskipTests

# Etapa final
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado da etapa de construção
COPY --from=build /app/target/Telemetry-0.1.jar .

# Expor a porta do serviço
EXPOSE 8080

# Comando para rodar o serviço
CMD ["java", "-jar", "Telemetry-0.1.jar"]