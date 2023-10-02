# Build Angualar application
FROM node:14 as angular-builder
WORKDIR /app
COPY angular-app /app
RUN npm install
RUN npm run build --prod

# Build Spring boot application
FROM openjdk:17-jdk-alpine3.12
WORKDIR /app
COPY target/MartialArtCMS-0.0.1-SNAPSHOT.jar MartialArtCMS-0.0.1-SNAPSHOT.jar
COPY --from=angular-builder /app/dist /app/dist
COPY src /app/src
COPY pom.xml /app

# Run the Spring Boot application built with Nginx
FROM nginx
COPY --from=spring-boot-builder /app/target/MartialArtCMS-0.0.1-SNAPSHOT.jar /app/MartialArtCMS-0.0.1-SNAPSHOT.jar
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=spring-boot-builder /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["java", "-jar", "/app/M2LANDCMS-0.0.1-SNAPSHOT.jar"]

ENV SPRING_PROFILES_ACTIVE=dev
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/martial_arts_meta_dev
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=Metagame@2023
ENV REDIS_HOST=localhost
ENV REDIS_PORT=6379

ENTRYPOINT ["java", "-jar","db:3306","--", "target/M2LANDCMS-0.0.1-SNAPSHOT.jar"]
