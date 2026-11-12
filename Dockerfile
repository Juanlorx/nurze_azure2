FROM eclipse-temurin:17-jdk-jammy

ARG GLASSFISH_VERSION=7.0.21
ENV GLASSFISH_HOME=/opt/glassfish7 \
    APP_WAR=/opt/nurse/Nurse.war

FROM eclipse-temurin:17-jdk-jammy

ARG GLASSFISH_VERSION=7.0.21

ENV GLASSFISH_HOME=/opt/glassfish7 \
    APP_WAR=/opt/nurse/Nurse.war

# Instalar dependencias
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
       wget unzip ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Descargar e instalar GlassFish
RUN wget --max-redirect=20 -q \
    "https://download.eclipse.org/ee4j/glassfish/glassfish-${GLASSFISH_VERSION}.zip" \
    -O /tmp/glassfish.zip \
    && unzip -q /tmp/glassfish.zip -d /opt \
    && rm /tmp/glassfish.zip

# Crear usuario y grupo sin privilegios
RUN groupadd --system glassfish \
    && useradd --system \
       --gid glassfish \
       --home-dir /opt/glassfish7 \
       --shell /usr/sbin/nologin \
       glassfish

# Crear directorio de la aplicación
RUN mkdir -p /opt/nurse

# Asignar permisos al usuario GlassFish
RUN chown -R glassfish:glassfish \
    /opt/glassfish7 \
    /opt/nurse

# Copiar aplicación
COPY --chown=glassfish:glassfish target/Nurse.war /opt/nurse/Nurse.war

# Ejecutar como usuario sin privilegios
USER glassfish

# Puerto de GlassFish
EXPOSE 8080 4848

# Conserva aquí tu comando CMD original para iniciar GlassFish
    && unzip -q /tmp/glassfish.zip -d /opt \
    && rm -f /tmp/glassfish.zip \
    && rm -rf /var/lib/apt/lists/*

COPY dist/Nurse.war /opt/nurse/Nurse.war

COPY docker/glassfish-entrypoint.sh /usr/local/bin/glassfish-entrypoint

RUN chmod +x /usr/local/bin/glassfish-entrypoint

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/glassfish-entrypoint"]
COPY docker/glassfish-entrypoint.sh /usr/local/bin/glassfish-entrypoint

RUN chmod +x /usr/local/bin/glassfish-entrypoint

EXPOSE 8080

ENTRYPOINT ["/usr/local/bin/glassfish-entrypoint"]
