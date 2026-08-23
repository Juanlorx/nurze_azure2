# Despliegue de NURSE en Azure

Esta aplicación se publica como contenedor de GlassFish en **Azure Container Apps** y usa **Azure Database for MySQL Flexible Server**. La aplicación escucha en el puerto `8080` y queda publicada en la raíz del dominio.

## 1. Crear la base de datos

En Azure Portal crea un servidor **Azure Database for MySQL Flexible Server**. Crea la base `nurse`, un usuario de aplicación con permisos sobre esa base y habilita conectividad TLS. Para llevar el esquema actual, desde tu computador ejecuta:

```powershell
& 'C:\xampp\mysql\bin\mysqldump.exe' -u root --no-data nurse | mysql -h TU_SERVIDOR.mysql.database.azure.com -u TU_USUARIO -p --ssl-mode=REQUIRED nurse
```

No exportes usuarios ni contraseñas reales al repositorio. Si deseas trasladar datos de prueba, haz un respaldo separado y revísalo antes de importarlo.

## 2. Compilar y probar el contenedor localmente

```powershell
& 'C:\Program Files\NetBeans-16\netbeans\extide\ant\bin\ant.bat' dist
docker build -t nurse:local .
docker run --rm -p 8080:8080 `
  -e DB_HOST=host.docker.internal -e DB_PORT=3306 -e DB_NAME=nurse `
  -e DB_USER=root -e DB_PASSWORD='' -e DB_SSL_MODE=PREFERRED nurse:local
```

Abre `http://localhost:8080/`.

## 3. Publicar en Azure

Instala Azure CLI, inicia sesión con `az login`, crea primero el servidor MySQL y después ejecuta desde la raíz del proyecto:

```powershell
& 'C:\Program Files\NetBeans-16\netbeans\extide\ant\bin\ant.bat' dist
./azure/deploy.ps1 `
  -ResourceGroup rg-nurse `
  -AppName nurse-app-unico `
  -DbHost TU_SERVIDOR.mysql.database.azure.com `
  -DbUser TU_USUARIO `
  -DbPassword (Read-Host 'Contraseña de MySQL' -AsSecureString)
```

El script crea el registro de contenedores, compila la imagen en Azure Container Registry, crea Container Apps y guarda la contraseña como secreto de la aplicación. Azure MySQL requiere TLS; el despliegue usa `DB_SSL_MODE=REQUIRED`.

Para producción, restringe el acceso de red del MySQL al entorno de Container Apps y sustituye las credenciales administrativas del registro por una identidad administrada.
