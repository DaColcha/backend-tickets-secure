# SpringBoot - Tickets API

_Antes de ejecutar el proyecto asegurarse de tener Java 11 o superior y la última versión de Maven._ 

➡️[Descargar Java](https://www.oracle.com/java/technologies/downloads/?er=221886)

➡️[Guía paso a paso para instalar Maven](https://phoenixnap.com/kb/install-maven-windows)

### Iniciemos! ⏩

1. Clona el repositorio 

2. Usa el archivo __.env.template__, modifíca las variables si lo necesitas y renombralo a __.env__

3. Descarga los scripts SQL que se encuentran en el [link](https://epnecuador-my.sharepoint.com/:f:/g/personal/daniela_colcha_epn_edu_ec/EsmDUG8pLfBNpGR-YPOlV2QBMIeqZwkF7PaxjCsNchTC7A?e=WsgykO).

4. Ejecuta el siguiente comando
    ```
    docker-compose up -d
    ```
5. Con la base levantada, utiliza tu gestor de BD favorito para acceder a ella y ejecutar los scripts descargados en el paso 3.
El orden en que deben ser ejecutados es el siguiente: 
   ```
    1. create-statement.sql
    2. initial-values.sql
    3. views-create.sql
   ```

6. Con la base de datos lista, ejecuta los siguientes comandos para iniciar el proyecto en modo desarrollo
    ```
    mvn clean compile
    mvn spring-boot:run
    ```

### Listo! Ahora accede a la api a través del __localhost:8080__
Si necesitas trabajar con un puerto diferente, modificalo en el archivo __resources/application.properties__

### Documentación 📖
Para acceder a la documentación accede a este [enlace⛓️‍💥](https://calm-bay-035f02c10.4.azurestaticapps.net)