# challenges-backend-develop

## Introducción
Este proyecto es un backend desarrollado para gestionar diversos desafíos. Incluye funcionalidades para la gestión de usuarios, movimientos de cuentas y clientes.

## Contenido del Proyecto
El proyecto está dividido en varios módulos:
- **ded-msa-account-movement**: Gestión de movimientos de cuentas.
- **ded-msa-customer**: Gestión de información de clientes.

## Funcionalidades
- Gestión de usuarios.
- Gestión de movimientos de cuentas.
- Gestión de información de clientes.
- Manejo de excepciones y errores.

## Arquitectura
El proyecto sigue una arquitectura de microservicios, donde cada módulo es un servicio independiente que se comunica con los demás a través de APIs RESTful. Los servicios están contenedorizados usando Docker y se pueden orquestar con Docker Compose.

## Requisitos
- Java 11
- Gradle
- Docker
- Docker Compose

## Instalación
1. Clonar el repositorio:
    ```sh
    git clone <URL_DEL_REPOSITORIO>
    ```
2. Navegar al directorio del proyecto:
    ```sh
    cd challenges-backend-develop
    ```

## Compilación y Ejecución
1. Compilar el proyecto:
    ```sh
    ./gradlew build
    ```
2. Construir y levantar los contenedores Docker:
    ```sh
    docker-compose up --build
    ```
