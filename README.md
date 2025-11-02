# TareasApp - Fundamentos de Spring Boot (Programación III - UTN)

## Descripción del Proyecto

Este proyecto es el Trabajo Práctico Integrador de la asignatura Programación III de la Tecnicatura Universitaria en Programación a Distancia (UTN). Consiste en el desarrollo de un **Sistema de Gestión de Tareas (To-Do List)** implementado con **Spring Boot**.

El objetivo principal es aplicar los conceptos fundamentales del framework, incluyendo:
* Inyección de Dependencias (por constructor).
* Uso de Estereotipos (`@Service`, `@Repository`).
* Configuración externa mediante `application.properties` e inyección de valores con `@Value`.
* Gestión de diferentes entornos (`dev` y `prod`) utilizando **Profiles** y Beans condicionales.
* Implementación de `CommandLineRunner` para ejecutar la lógica de negocio al inicio.

## Tecnologías Utilizadas

* **Lenguaje:** Java 17+
* **Framework:** Spring Boot 3.x
* **Build Tool:** Maven
* **Utilidades:** Lombok (para reducir código boilerplate)
* **Estructura:** Arquitectura basada en capas (Model, Repository, Service)

## Instrucciones para Clonar y Ejecutar el Proyecto

Sigue estos pasos para obtener una copia funcional del proyecto en tu máquina local.

### Prerrequisitos

* JDK 21 instalado.
* Maven instalado.
* Un IDE compatible con Spring Boot (IntelliJ IDEA, VS Code con extensiones de Java/Spring, etc.).

### Ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone [Link de tu repositorio GitHub]
    cd tareas
    ```

2.  **Compilar el proyecto (opcional):**
    ```bash
    mvn clean install
    ```

3.  **Ejecutar la aplicación con Profiles:**

La aplicación está configurada para iniciar la lógica de negocio a través del método `run()` de `CommandLineRunner` en la clase `TareasApplication.java`.

#### 1. Ejecución en Entorno de Desarrollo (`dev`) 

Este perfil tiene un límite de tareas bajo (`max-tareas=10`) y un *logging* detallado (`DEBUG`).

* **Usando Maven:**
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=dev
    # O simplemente:
    mvn spring-boot:run 
    ```

#### 2. Ejecución en Entorno de Producción (`prod`) 

Este perfil tiene un límite de tareas alto (`max-tareas=1000`), *logging* limitado (`ERROR`), y mensajes de bienvenida/despedida concisos.

* **Usando Maven:**
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=prod
    ```

> **Nota:** Para cambiar el Profile en un IDE (como IntelliJ IDEA), debes configurar el *Program Argument* o *VM Option* en la configuración de ejecución del *main* class a `--spring.profiles.active=prod` o `--spring.profiles.active=dev.

## Conclusiones Personales sobre lo Aprendido

*(Reemplaza este texto con tus propias reflexiones. Enfócate en la importancia de la Inyección de Dependencias, cómo los Profiles facilitan la adaptación a entornos, y la utilidad de los estereotipos de Spring.)*

* **Inyección de Dependencias y Desacoplamiento:** Comprendí que inyectar las dependencias por constructor, como se hizo con `TareaRepository` en `TareaService`, es una práctica fundamental que promueve el bajo acoplamiento y facilita las pruebas unitarias.
* **La Capacidad de los Profiles:** La implementación de `MensajeDevService` y `MensajeProdService` con `@Profile` demostró de manera práctica cómo Spring puede cargar *beans* condicionalmente, permitiendo adaptar la funcionalidad (mensajes, límites, logging) sin modificar el código fuente principal.
* **Estereotipos y Arquitectura:** La clara división de responsabilidades utilizando `@Repository` y `@Service` ayuda a entender el rol de cada componente dentro de la arquitectura de capas y facilita el mantenimiento del código.

## 👤 Información del Estudiante

* **Nombre y Apellido:** Lautaro Joel Ferreria
* **Legajo:** 52643
