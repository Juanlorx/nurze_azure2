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
"${ASADMIN}" list-applications | grep -q '^nurse'
"${ASADMIN}" stop-domain

exec "${ASADMIN}" start-domain --verbose
