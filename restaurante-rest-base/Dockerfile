FROM maven:latest
COPY . .
WORKDIR ./restaurantes
RUN mvn clean install
WORKDIR ../restaurantes-rest
RUN mvn clean install
CMD ["mvn", "jetty:run"]
EXPOSE 8080