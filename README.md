# Buscador de Productos con BST

Este proyecto implementa un buscador de productos utilizando un Árbol Binario de Búsqueda (BST) para almacenar y recuperar información de productos eficientemente.




## Descripción

La aplicación permite:
- Buscar productos por su código SKU
- Visualizar todos los productos disponibles en una tabla
- Ver detalles de un producto, incluyendo precio original, precio actual y ahorro

## Estructura del Proyecto

El proyecto está dividido en dos partes principales:

### Backend (Java + Spring Boot)
- Implementación del Árbol Binario de Búsqueda (BST) genérico
- Modelo de datos para los productos
- Carga de datos desde archivo CSV
- API REST para búsqueda por SKU y listado completo

### Frontend (React)
- Interfaz de usuario con estilo Frutiger Aero
- Barra de búsqueda para consultas por SKU
- Tabla de productos con todos los items disponibles
- Vista detallada de productos seleccionados

## Tecnologías Utilizadas

- **Backend**: 
  - Java 11
  - Spring Boot 2.7.5
  - JUnit para pruebas unitarias

- **Frontend**: 
  - React 18
  - Tailwind CSS
  - Axios para llamadas API

## Requisitos

- Java JDK 11 o superior
- Maven
- Node.js y npm

## Configuración e Instalación

### Backend

1. Navega al directorio backend:
   ```
   cd product-search/backend
   ```

2. Compila el proyecto:
   ```
   mvn clean package
   ```

3. Ejecuta la aplicación:
 
   ```
   mvn spring-boot:run
   ```

### Frontend

1. Navega al directorio frontend:
   ```
   cd product-search/frontend
   ```

2. Instala las dependencias:
   ```
   npm install --legacy-peer-deps
   ```

3. Inicia la aplicación:
   ```
   npm start
   ```

4. Accede a la aplicación en tu navegador:
   ```
   http://localhost:3000
   ```

## Implementación del BST

El Árbol Binario de Búsqueda (BST) fue implementado como una estructura de datos genérica que:
- Permite insertar elementos
- Permite buscar elementos por su clave (SKU)
- Mantiene los elementos ordenados para realizar búsquedas eficientes

## Pruebas

Se han implementado pruebas unitarias para los métodos más importantes del BST:
- Inserción de elementos
- Búsqueda de elementos
- Actualización de elementos
 
