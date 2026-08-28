#!/usr/bin/env bash
set -euo pipefail

ASADMIN="${GLASSFISH_HOME}/bin/asadmin"

# Azure Container Apps publica el puerto tan pronto como GlassFish inicia. Por
# eso la aplicación debe quedar registrada antes del arranque en primer plano:
# de otro modo se muestra la página predeterminada de GlassFish.
"${ASADMIN}" start-domain
"${ASADMIN}" deploy --force=true --name nurse --contextroot /nurse "${APP_WAR}"
"${ASADMIN}" set \
  configs.config.server-config.http-service.virtual-server.server.default-web-module=nurse
"${ASADMIN}" delete-jvm-options \
  '-Djavax.net.ssl.trustStore=${com.sun.aas.instanceRoot}/config/keystore.jks' || true
"${ASADMIN}" delete-jvm-options \
  '-Djavax.net.ssl.keyStore=${com.sun.aas.instanceRoot}/config/keystore.jks' || true
"${ASADMIN}" create-jvm-options \
  "-Djavax.net.ssl.trustStore=/opt/java/openjdk/lib/security/cacerts"
"${ASADMIN}" create-jvm-options \
  "-Djavax.net.ssl.trustStorePassword=changeit"
"${ASADMIN}" create-jvm-options \
  "-Djavax.net.ssl.keyStore=/opt/java/openjdk/lib/security/cacerts"
"${ASADMIN}" create-jvm-options \
  "-Djavax.net.ssl.keyStorePassword=changeit"
"${ASADMIN}" list-applications | grep -q '^nurse'
"${ASADMIN}" stop-domain

exec "${ASADMIN}" start-domain --verbose
