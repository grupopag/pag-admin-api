# Use uma imagem base do Java
FROM openjdk:21
ENV TZ=America/Belem
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Defina o diretório de trabalho dentro do container
WORKDIR /app

# Copie o arquivo JAR do projeto para o container
COPY target/*.jar app.jar

# Defina o comando de inicialização do container
ENTRYPOINT ["java", "-jar", "app.jar"]
