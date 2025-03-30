FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

ADD ./target/msvc-reporte-ventas-0.0.1-SNAPSHOT.jar msvc-reporte-ventas.jar

ENTRYPOINT [ "java", "-jar", "msvc-reporte-ventas.jar" ]