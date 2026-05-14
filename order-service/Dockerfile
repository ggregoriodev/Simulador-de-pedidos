# Usa uma imagem com Java 17 + JDK instalado
# "AS build" dá o nome "build" para esta etapa
FROM eclipse-temurin:17-jdk AS build

# Define a pasta de trabalho dentro do container
WORKDIR /app

# Copia o arquivo mvnw para dentro do container
COPY mvnw .

# Copia a pasta .mvn
COPY .mvn .mvn

# Copia o pom.xml
COPY pom.xml .

# Dá permissão de execução ao mvnw
RUN chmod +x mvnw

# Baixa as dependências do Maven
# Isso ajuda a usar cache e acelerar builds futuros
RUN ./mvnw dependency:go-offline

# Copia a pasta src (código-fonte da aplicação)
COPY src src

# Limpa builds antigos e gera o .jar
# -DskipTests pula os testes
RUN ./mvnw clean package -DskipTests

# ---------------------------
# Segunda etapa da build
# ---------------------------

# Usa uma imagem menor, apenas com Java Runtime
FROM eclipse-temurin:17-jre

# Define pasta de trabalho
WORKDIR /app

# Copia o .jar gerado na etapa "build"
# e renomeia para app.jar
COPY --from=build /app/target/*.jar app.jar

# Informa que a aplicação usa a porta 8080
EXPOSE 8080

# Comando executado ao iniciar o container
ENTRYPOINT ["java", "-jar", "app.jar"]