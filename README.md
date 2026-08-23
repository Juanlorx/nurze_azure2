# NURSE

Aplicación JSP/Servlet para la gestión de enfermería, desplegada sobre GlassFish 7 y MySQL.

## Desarrollo local

El proyecto se compila con Ant/NetBeans y se genera en `dist/Nurse.war`.

```powershell
& 'C:\Program Files\NetBeans-16\netbeans\extide\ant\bin\ant.bat' dist
```

## Azure

La configuración de despliegue se encuentra en [azure/README.md](azure/README.md). Incluye el contenedor de GlassFish, configuración de base de datos mediante variables de entorno y un script de publicación para Azure Container Apps.

Nunca agregues contraseñas, archivos `.env` ni respaldos con datos reales al repositorio.
