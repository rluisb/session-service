FROM openjdk:11-jre-slim
ADD session-service.jar /
EXPOSE 8081
ENV JAVA_OPTS -Xmx1024m -Xms1024m -Djava.security.egd=file:/dev/./urandom -Duser.timezone=America/Sao_Paulo
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/session-service.jar"]
