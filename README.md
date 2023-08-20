<h2 style="text-align: center">Software de libros</h2>

<h3>Este proyecto utiliza las siguientes dependencias:</h3>

- rxjava
- r2dbc-postgresql
- spring-data-rest-core
- spring-data-r2dbc
- spring-boot-starter-data-r2dbc
- spring-data-rest-core
- spring-boot-devtools
- spring-boot-starter-test
- spring-boot-maven-plugin

<h4>Para poder utilizar esta aplicación debes de crear la base de datos que se 
encuentra en src\main\java\com\idat\learn\database junto con las fn, 
todo esto en postgresql
</h4>

<h4>Para Poder compilar el proyecto debes de tener 
instalado el JDK 17, maven y debes de ejecutar 
los sigueintes comandos
</h4>

```bash 
mvn spring-boot:run
```

### Endpoints Disponibles
___

### Endpoint de libros

___
#### GET /book
Endpoint para obtener la lista de libros.

- **URL:** http://localhost:3000/book
- **Método:** GET
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": [
            {
                "bookid": "148e607c-8ff7-4e16-a5f7-1369b3056078",
                "title": "test",
                "description": "test",
                "categoryid": "fae4d73a-72c5-4f34-9933-938482cc96f1",
                "categoryname": "Historias"
            }
        ]
    }
    ```

___
#### POST /book
Endpoint para crear un libro

- **URL:** http://localhost:3000/book
- **Método:** POST
- **Envio de Datos:**
  ```json
    {
        "title": "test",
        "description": "test",
        "categoryid": "fae4d73a-72c5-4f34-9933-938482cc96f1"
    }
  ```
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": [
            {
                "bookid": "148e607c-8ff7-4e16-a5f7-1369b3056078",
                "title": "test",
                "description": "test",
                "categoryid": "fae4d73a-72c5-4f34-9933-938482cc96f1",
                "categoryname": "Historias"
            }
        ]
    }
    ```
___

#### PUT /book/{id}
Endpoint para actualizar un libro

- **URL**: http://localhost:3000/book/148e607c-8ff7-4e16-a5f7-1369b3056078
- **Método:** PUT
- **Envio de Datos:**
  ```json
    {
        "title": "test222",
        "description": "test222",
        "categoryid": "fae4d73a-72c5-4f34-9933-938482cc96f1"
    }
  ```
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": [
            {
                "bookid": "148e607c-8ff7-4e16-a5f7-1369b3056078",
                "title": "test222",
                "description": "test222",
                "categoryid": "fae4d73a-72c5-4f34-9933-938482cc96f1",
                "categoryname": "Historias"
            }
        ]
    }
    ```
___
#### DELETE /book/{id}
Endpoint para eliminar un libro

- **URL**: http://localhost:3000/book/148e607c-8ff7-4e16-a5f7-1369b3056078
- **Método:** DELETE
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": []
    }
    ```

### Endpoint de Categoría

___
#### GET /category
Endpoint para obtener la lista de libros.

- **URL:** http://localhost:3000/category
- **Método:** GET
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": [
            {
                "categoryId": "148e607c-8ff7-4e16-a5f7-1369b3056078",
                "description": "test"
            }
        ]
    }
    ```

___
#### POST /book
Endpoint para crear un libro

- **URL:** http://localhost:3000/category
- **Método:** POST
- **Envio de Datos:**
  ```json
    {
        "description": "test"
    }
  ```
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": [
            {
                "categoryId": "148e607c-8ff7-4e16-a5f7-1369b3056078",
                "description": "test"
            }
        ]
    }
    ```
___

#### PUT /book/{id}
Endpoint para actualizar un libro

- **URL**: http://localhost:3000/category/148e607c-8ff7-4e16-a5f7-1369b3056078
- **Método:** PUT
- **Envio de Datos:**
  ```json
    {
        "description": "test222"
    }
  ```
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": [
            {
                "bookid": "148e607c-8ff7-4e16-a5f7-1369b3056078",
                "description": "test222"
            }
        ]
    }
    ```
___
#### DELETE /book/{id}
Endpoint para eliminar un libro

- **URL**: http://localhost:3000/category/148e607c-8ff7-4e16-a5f7-1369b3056078
- **Método:** DELETE
- **Formato de Respuesta:**
    ```json
    {
        "statusCode": "200",
        "message": "",
        "body": []
    }
    ```