# RUC API - Victor Cuaresma

API para gestionar consultas de RUC con integración a la API de SUNAT y MongoDB.

## Características

- ✅ Consulta de RUC desde API externa de SUNAT
- ✅ Almacenamiento en MongoDB
- ✅ Borrado lógico (soft delete)
- ✅ Restauración de registros eliminados
- ✅ Listado de RUCs activos
- ✅ Arquitectura reactiva con WebFlux

## Endpoints Disponibles

### 1. Registrar y guardar RUC
```
GET /api/ruc/fetch/{ruc}
```
**Ejemplo:**
```
GET http://localhost:8080/api/ruc/fetch/20131312955
```

### 2. Listar todos los RUC activos
```
GET /api/ruc/list
```
**Ejemplo:**
```
GET http://localhost:8080/api/ruc/list
```

### 3. Borrado lógico (isDelete=true)
```
PATCH /api/ruc/delete/{ruc}
Content-Type: application/json

{
  "isDelete": true
}
```
**Ejemplo:**
```
PATCH http://localhost:8080/api/ruc/delete/20131312955
```

### 4. Restaurar lógico (isDelete=false)
```
PATCH /api/ruc/restore/{ruc}
Content-Type: application/json

{
  "isDelete": false
}
```
**Ejemplo:**
```
PATCH http://localhost:8080/api/ruc/restore/20131312955
```

### 5. Buscar RUC específico
```
GET /api/ruc/{ruc}
```
**Ejemplo:**
```
GET http://localhost:8080/api/ruc/20131312955
```

## Estructura del Proyecto

```
src/main/java/pe/edu/vallegrande/victorcuaresa/
├── VictorCuaresmaApplication.java
├── config/
│   └── WebClientConfig.java
├── model/
│   └── Ruc.java
├── repository/
│   └── RucRepository.java
├── client/
│   └── RucApiClient.java
├── service/
│   └── RucService.java
└── controller/
    └── RucController.java
```

## Configuración

### Variables de Entorno (Opcionales)

```yaml
# MongoDB
MONGO_USER: 
MONGO_PASSWORD: 
MONGO_HOST: cluster0.qgwrum8.mongodb.net
MONGO_DB: sunat

# API Externa
API_RUC_BASE_URL: https://dniruc.apisperu.com/api/v1
API_RUC_TOKEN: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...
```

## Ejecución

1. **Compilar el proyecto:**
```bash
./mvnw clean compile
```

2. **Ejecutar la aplicación:**
```bash
./mvnw spring-boot:run
```

3. **La aplicación estará disponible en:**
```
http://localhost:8080
```

## Ejemplo de Respuesta

### Consulta de RUC exitosa:
```json
{
  "id": "64f8a1b2c3d4e5f6a7b8c9d0",
  "ruc": "20131312955",
  "razonSocial": "SUPERINTENDENCIA NACIONAL DE ADUANAS Y DE ADMINISTRACION TRIBUTARIA - SUNAT",
  "nombreComercial": null,
  "telefonos": [],
  "tipo": null,
  "estado": "ACTIVO",
  "condicion": "HABIDO",
  "direccion": "AV. GARCILASO DE LA VEGA NRO. 1472 LIMA LIMA LIMA",
  "departamento": "LIMA",
  "provincia": "LIMA",
  "distrito": "LIMA",
  "ubigeo": "150101",
  "capital": "LIMA",
  "isDelete": false,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

## Tecnologías Utilizadas

- **Spring Boot 3.5.3**
- **Spring WebFlux** (Programación reactiva)
- **Spring Data MongoDB Reactive**
- **MongoDB** (Base de datos)
- **WebClient** (Cliente HTTP reactivo)
- **Java 17**

## Funcionalidades Implementadas

1. **Integración con API Externa:** Consume la API de RUC de SUNAT
2. **Base de Datos MongoDB:** Almacenamiento persistente con Spring Data
3. **Borrado Lógico:** Los registros no se eliminan físicamente
4. **Programación Reactiva:** Usa WebFlux para mejor rendimiento
5. **Manejo de Errores:** Respuestas HTTP apropiadas
6. **Configuración Flexible:** Variables de entorno y archivo YAML
