FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

EXPOSE 8090

ADD ./target/msvc_gateway_server-0.0.1-SNAPSHOT.jar msvc_gateway_server.jar

ENTRYPOINT [ "java", "-jar", "msvc_gateway_server.jar" ]