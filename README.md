# API REST de Gestión de Pedidos

Proyecto desarrollado como parte de una evaluación académica utilizando Spring Boot y PostgreSQL para gestionar clientes, productos y pedidos de una tienda online.

---

## 📋 Descripción

La aplicación permite administrar el proceso de ventas mediante una API REST, implementando operaciones para:

* Registrar clientes.
* Registrar productos.
* Crear pedidos con múltiples productos.
* Consultar pedidos por ID.
* Listar pedidos por cliente.

El proyecto fue desarrollado siguiendo una arquitectura por capas (Controller - Service - Repository), utilizando DTOs, Mappers, manejo centralizado de excepciones y pruebas unitarias.

---

# 🛠 Tecnologías utilizadas

* Java 21
* Spring Boot 3.5.15
* Spring Web
* Spring Data JPA
* Spring Validation
* PostgreSQL
* Maven
* Lombok
* JUnit 5
* Mockito

---

# 🚀 Requisitos

Antes de ejecutar el proyecto es necesario contar con:

* JDK 21
* Maven
* PostgreSQL

Crear la base de datos:

```sql
CREATE DATABASE db_pedidos;
```

Configurar las credenciales de acceso en:

```
src/main/resources/application.properties
```

---

# ▶️ Ejecución

Compilar el proyecto:

```bash
mvn clean install
```

Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

La API estará disponible en:

```
http://localhost:8080
```

---

# 🔌 Endpoints

## Clientes

| Método | Endpoint           | Descripción           |
| ------ | ------------------ | --------------------- |
| POST   | /api/clientes      | Registrar cliente     |
| GET    | /api/clientes      | Listar clientes       |
| GET    | /api/clientes/{id} | Buscar cliente por ID |

## Productos

| Método | Endpoint       | Descripción        |
| ------ | -------------- | ------------------ |
| POST   | /api/productos | Registrar producto |
| GET    | /api/productos | Listar productos   |

## Pedidos

| Método | Endpoint                         | Descripción                |
| ------ | -------------------------------- | -------------------------- |
| POST   | /api/pedidos                     | Crear pedido               |
| GET    | /api/pedidos/{id}                | Buscar pedido por ID       |
| GET    | /api/pedidos/cliente/{clienteId} | Listar pedidos por cliente |

---

# 📐 Arquitectura

El proyecto sigue una arquitectura por capas (**Controller – Service – Repository**), promoviendo la separación de responsabilidades y facilitando el mantenimiento del código.

Durante el desarrollo se aplicaron principios SOLID, utilizando interfaces para la capa de servicios, inyección de dependencias mediante Spring Boot, DTOs para el intercambio de información, Mappers para la conversión entre entidades y objetos de transferencia, y un manejo centralizado de excepciones mediante `GlobalExceptionHandler`.


---

# 🧪 Pruebas unitarias

Se implementaron pruebas unitarias utilizando **JUnit 5** y **Mockito** para validar las principales reglas de negocio.

### Casos implementados

* ✔ Crear pedido correctamente (Happy Path).
* ✔ Error cuando el stock es insuficiente.
* ✔ Error cuando el pedido no existe.

Ejecutar las pruebas:

```bash
mvn test
```

---

# 📂 Evidencias

El repositorio incluye una carpeta **evidencias/** con capturas del funcionamiento del proyecto:

* Pruebas realizadas en Postman.
* Registros almacenados en PostgreSQL.
* Ejecución de pruebas unitarias.
* Estructura del proyecto.

---

# 👨‍💻 Autor

**Daniel Arana**
