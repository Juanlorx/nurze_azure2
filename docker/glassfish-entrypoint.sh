#!/usr/bin/env bash
set -euo pipefail

ASADMIN="${GLASSFISH_HOME}/bin/asadmin"

# Se despliega en la raíz para que la URL pública de Azure sea simplemente /.
"${ASADMIN}" start-domain
"${ASADMIN}" deploy --force=true --contextroot / "${APP_WAR}"
"${ASADMIN}" stop-domain

exec "${ASADMIN}" start-domain --verbose
