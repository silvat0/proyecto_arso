FROM maven:latest
COPY . .
WORKDIR ./usuarios-pasarela
RUN mvn clean install
WORKDIR ../pasarela
RUN mvn clean install
ENTRYPOINT ["java","-jar","target/pasarela-0.0.1-SNAPSHOT.jar"]
EXPOSE 8090