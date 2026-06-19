README.md para API de Gestión de Pedidos
# API REST de Gestión de Pedidos - Tienda Online

## 📋 Descripción del Proyecto
Este proyecto es una API REST diseñada para la gestión integral de una tienda, permitiendo el registro de clientes, catálogo de productos y la creación de pedidos con detalle de productos asociados. La solución sigue principios de arquitectura limpia, inyección de dependencias y manejo de excepciones centralizado.

## 🛠 Tecnologías Utilizadas
- Java 21
- Spring Boot 3.5.15 (Spring Data JPA, Web, Validation)
- PostgreSQL (Base de datos relacional)
- Maven (Gestor de dependencias)
- Lombok (Reducción de código repetitivo)
- JUnit 5 / Mockito (Pruebas unitarias)

## 🚀 Instrucciones de Ejecución

1. Requisitos previos:
   - Tener instalado JDK 21.
   - Tener instalado PostgreSQL.

2. Configuración de base de datos:
   Ejecuta en tu PostgreSQL:
   CREATE DATABASE db_pedidos;

3. Ejecución del proyecto:
   # Compilar el proyecto
   mvn clean install
   
   # Ejecutar la aplicación
   mvn spring-boot:run

## 🧪 Pruebas Unitarias
El proyecto incluye un conjunto de pruebas unitarias desarrolladas con JUnit 5 y Mockito para garantizar la calidad del código y el correcto manejo de las reglas de negocio (específicamente en la capa de servicios).

- Cobertura: Se han implementado escenarios de éxito ("Happy Path") y manejo de excepciones ("Error Path").
- Cómo ejecutar los tests:
  mvn test
- Resultado: Tras ejecutar el comando, se generará un reporte en la consola indicando el éxito de las pruebas.

## 🔌 Endpoints Principales

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| POST | /api/clientes | Registrar un nuevo cliente. |
| GET | /api/clientes | Listar todos los clientes registrados. |
| POST | /api/productos | Registrar un producto en el catálogo. |
| GET | /api/productos | Listar todos los productos. |
| POST | /api/pedidos | Crear un pedido con una lista de productos. |
| GET | /api/pedidos/{id} | Consultar el detalle de un pedido específico. |
| GET | /api/pedidos/cliente/{clienteId} | Listar todos los pedidos de un cliente específico. |

## 📐 Arquitectura
El proyecto se organiza bajo el patrón Controller-Service-Repository, garantizando la separación de responsabilidades y el cumplimiento de los principios SOLID.

