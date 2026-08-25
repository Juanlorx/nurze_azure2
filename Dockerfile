FROM eclipse-temurin:17-jdk-jammy

ARG GLASSFISH_VERSION=7.0.21
ENV GLASSFISH_HOME=/opt/glassfish7 \
    APP_WAR=/opt/nurse/Nurse.war

RUN apt-get update \
    && apt-get install -y --no-install-recommends wget unzip ca-certificates \
    && wget -q "https://download.eclipse.org/ee4j/glassfish/glassfish-${GLASSFISH_VERSION}.zip" -O /tmp/glassfish.zip \
    && unzip -q /tmp/glassfish.zip -d /opt \
    && rm -f /tmp/glassfish.zip \
    && rm -rf /var/lib/apt/lists/*

COPY dist/Nurse.war /opt/nurse/Nurse.war
COPY docker/glassfish-entrypoint.sh /usr/local/bin/glassfish-entrypoint

RUN chmod +x /usr/local/bin/glassfish-entrypoint

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/glassfish-entrypoint"]
