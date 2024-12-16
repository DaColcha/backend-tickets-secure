# SpringBoot - Tickets API

1. Clona el repositorio 

2. Usa el archivo __.env.template__, modifíca las variables si lo necesitas y renombralo a __.env__

3. Agrega la carpeta de la base de datos que se encuentra en el [link](https://epnecuador-my.sharepoint.com/:f:/g/personal/daniela_colcha_epn_edu_ec/EsmDUG8pLfBNpGR-YPOlV2QBMIeqZwkF7PaxjCsNchTC7A?e=WsgykO).
   
   El árbol de archivos ser debería verse de la siguiente manera:
   ```
   backend/
    ├── postgresql/
    ├── src/
    ├── ...
   ```
4. Ejecuta el siguiente comando
    ```
    docker-compose up -d
    ```
5. Ejecuta el siguiente comando para iniciar el proyecto en modo desarrollo
    ```
    mvn spring-boot:run
    ```
_Antes de ejecutar el proyecto asegurarse de tener Java 11 o superior y la última versión de Maven._


Listo! Ahora accede a la api a través del __localhost:8080__
Si necesitas trabajar con un puerto diferente, modificalo en el archivo __resources/application.properties__
