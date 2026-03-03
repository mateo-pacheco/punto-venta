# 🛒 Sistema de Punto de Venta — Microservicios con Spring Cloud

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2024.0.0-blue?logo=spring)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?logo=docker)
![MySQL](https://img.shields.io/badge/MySQL-9.2.0-blue?logo=mysql)
![License](https://img.shields.io/badge/license-MIT-green)

Sistema de gestión empresarial desarrollado con arquitectura de microservicios usando Spring Boot y Spring Cloud. Incluye manejo de clientes, inventario, ventas, reportes y seguridad con OAuth2.

---

## 📐 Arquitectura

```
                        ┌─────────────────────┐
                        │   API Gateway :8090  │
                        │  (Spring Cloud GW)   │
                        └──────────┬──────────┘
                                   │
              ┌────────────────────┼────────────────────┐
              │                    │                    │
   ┌──────────▼──────┐  ┌──────────▼──────┐  ┌─────────▼───────┐
   │  msvc-clientes  │  │ msvc-inventario │  │msvc-punto-venta │
   └─────────────────┘  └─────────────────┘  └─────────────────┘
              │                    │                    │
   ┌──────────▼──────┐  ┌──────────▼──────┐  ┌─────────▼───────┐
   │msvc-reg-ventas  │  │msvc-rep-ventas  │  │   msvc-users    │
   └─────────────────┘  └─────────────────┘  └─────────────────┘
                                   │
                        ┌──────────▼──────────┐
                        │     msvc-oauth      │
                        │  (Auth Server :9000) │
                        └──────────┬──────────┘
                                   │
                        ┌──────────▼──────────┐
                        │   Eureka Server     │
                        │   (Registry :8761)  │
                        └─────────────────────┘
```

Todos los servicios se registran en **Eureka**, se comunican mediante **load balancing** y están protegidos por **OAuth2 + JWT**.

---

## 🧩 Microservicios

| Servicio | Puerto | Base de Datos | Descripción |
|---|---|---|---|
| `eureka-server` | 8761 | — | Registro y descubrimiento de servicios |
| `msvc-gateway-server` | 8090 | — | API Gateway con autenticación OAuth2 |
| `msvc-oauth` | 9000 | — | Servidor de autorización OAuth2 (JWT) |
| `msvc-users` | dinámico | `users_db` | Gestión de usuarios y roles |
| `msvc-clientes` | dinámico | `clientes_db` | CRUD de clientes |
| `msvc-inventario` | dinámico | `inventario_db` | Productos, categorías, proveedores y órdenes |
| `msvc-registro-ventas` | dinámico | `registro_ventas_db` | Registro y consulta de ventas |
| `msvc-reporte-ventas` | dinámico | `reporte_ventas_db` | Reportes por ventas, cliente y producto |
| `msvc-punto-venta` | dinámico | — | Orquestador de ventas (sin BD propia) |

---

## 🔐 Seguridad

La autenticación se gestiona mediante **Spring Authorization Server** con flujo **Authorization Code**:

- El cliente (`gateway-app`) redirige al servidor OAuth en el puerto `9000`
- Se emite un **JWT** con los roles del usuario (`ROLE_USER`, `ROLE_ADMIN`)
- El gateway valida el token y aplica reglas de autorización por ruta:

| Ruta | Rol requerido |
|---|---|
| `/api/admin/**` | `ROLE_ADMIN` |
| `/api/usuario/**` | `ROLE_ADMIN` o `ROLE_USER` |
| `/api/inventario/**` | `ROLE_ADMIN` |
| `/api/registro-ventas/**` | `ROLE_ADMIN` |
| `/api/reporte-ventas/**` | `ROLE_ADMIN` |
| `/api/punto-venta/**` | `ROLE_ADMIN` o `ROLE_USER` |

---

## 🛡️ Resiliencia

- **Circuit Breaker** con Resilience4j en el gateway y en `msvc-reporte-ventas`
- **Time Limiter** configurado con timeout de 6 segundos
- **Fallback methods** con datos por defecto ante fallos de servicios dependientes
- **Load Balancing** con Eureka para distribución de tráfico entre instancias

---

## 🗄️ Modelo de Datos

### msvc-inventario
```
Suppliers ──< Products >── Category
Suppliers ──< Orders
```

### msvc-registro-ventas
```
ventas (sale_id, name_products, product_id, quantity_products,
        unit_price, total_price, client_name, client_id, date_sale)
```

### msvc-reporte-ventas
```
sales_report | detailed_report | client_report
```

### msvc-users
```
users >──< roles  (tabla intermedia: users_roles)
```

---

## 🚀 Despliegue con Docker Compose

### Requisitos previos

- Docker >= 24.x
- Docker Compose >= 2.x

### Levantar todo el sistema

```bash
cd docker-compose
docker compose up -d
```

### Verificar servicios activos

```bash
docker compose ps
```

Accede al dashboard de Eureka: [http://localhost:8761](http://localhost:8761)

### Detener servicios

```bash
docker compose down
```

---

## 🔧 Variables de Entorno

| Variable | Valor por defecto | Descripción |
|---|---|---|
| `MYSQL_URL` | `mysql` | Host de MySQL |
| `MYSQL_DATABASE` | *(según servicio)* | Nombre de la base de datos |
| `MYSQL_USER` | `root` | Usuario de MySQL |
| `MYSQL_PASSWORD` | `Fazt12345` | Contraseña de MySQL |
| `PORT` | `0` (aleatorio) | Puerto del servicio |
| `IP_ADDR` | `http://127.0.0.1:9000` | URL del servidor OAuth (solo gateway) |

> ⚠️ **Importante:** Cambia las credenciales por defecto en entornos de producción.

---

## 📡 Endpoints principales

### Inventario (`/api/inventario/...`)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/products/getAllProducts` | Listar todos los productos |
| GET | `/products/getByProductId/{id}` | Obtener producto por ID |
| GET | `/products/getAllByLessStock/{stock}` | Productos con stock menor al valor |
| POST | `/products/saveProduct` | Crear producto |
| PUT | `/products/updateProduct` | Actualizar producto |
| PUT | `/products/updateStock/{productId}/{quantity}` | Actualizar stock de un producto |
| DELETE | `/products/deleteProduct/{id}` | Eliminar producto |
| GET | `/suppliers/getAllSuppliers` | Listar proveedores |
| GET | `/categorys/getAllCategorys` | Listar categorías |
| GET | `/orders/getAllOrders` | Listar órdenes de compra |
| PUT | `/orders/updateOrder/{stock}` | Actualizar orden (repone stock si está aprobada) |

### Punto de Venta (`/api/punto-venta/...`)
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/sales/save` | Registrar venta (descuenta stock automáticamente) |

**Body de ejemplo:**
```json
{
  "clientId": "CLIENT-001",
  "products": {
    "PROD-001": 2,
    "PROD-002": 1
  }
}
```

### Reportes (`/api/reporte-ventas/...`)
| Método | Ruta | Descripción |
|---|---|---|
| POST | `/salesReport/saveReport` | Generar reporte general de ventas |
| GET | `/salesReport/getAllRepots` | Listar reportes generados |
| POST | `/clientReport/save/{clientId}` | Reporte de ingresos por cliente |
| POST | `/detailedReport/save/{productId}` | Reporte detallado por producto |

### Usuarios (`/api/admin/...`)
| Método | Ruta | Descripción |
|---|---|---|
| GET | `/users/getAll` | Listar usuarios |
| GET | `/users/getByUserName/{userName}` | Buscar usuario por nombre |
| POST | `/users/save` | Crear usuario |
| PUT | `/users/update` | Actualizar usuario |
| DELETE | `/users/delete/{id}` | Eliminar usuario |

---

## 🔑 Autenticación OAuth2

### Flujo de autenticación

1. Navega a: `http://localhost:8090/oauth2/authorization/client-app`
2. Inicia sesión con tus credenciales
3. Serás redirigido a `/authorized?code=...`
4. Intercambia el `code` por un `access_token` en `http://localhost:9000/oauth2/token`
5. Incluye el token en cada petición: `Authorization: Bearer <token>`

**Credenciales del cliente OAuth:**
```
client-id:     gateway-app
client-secret: 1234
```

---

## 🏗️ Compilación local (sin Docker)

### Requisitos

- Java 21
- Maven 3.9+
- MySQL 9.x corriendo localmente

### Compilar y ejecutar un servicio

```bash
cd eureka-server
./mvnw clean package -DskipTests
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar
```

### Orden de arranque recomendado

1. `eureka-server`
2. MySQL (local o Docker)
3. `msvc-users`
4. `msvc-oauth`
5. `msvc-clientes`, `msvc-inventario`, `msvc-registro-ventas`, `msvc-reporte-ventas`, `msvc-punto-venta`
6. `msvc-gateway-server`

---

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.4.4 | Framework base |
| Spring Cloud Netflix Eureka | 2024.0.0 | Service registry y discovery |
| Spring Cloud Gateway | 2024.0.0 | API Gateway reactivo |
| Spring Authorization Server | — | Servidor OAuth2 + JWT |
| Spring Security OAuth2 | — | Resource server y cliente OAuth2 |
| Resilience4j | — | Circuit Breaker y Time Limiter |
| MapStruct | 1.6.3 | Mapeo de DTOs |
| Lombok | 1.18.30 | Reducción de boilerplate |
| MySQL | 9.2.0 | Base de datos relacional |
| Docker Compose | — | Orquestación de contenedores |
| Amazon Corretto | 21 | JDK base para imágenes Docker |

---

## 📦 Imágenes Docker

Todas las imágenes están publicadas en Docker Hub bajo el usuario `mateo158`:

```
mateo158/eureka-server:latest
mateo158/msvc-clientes:latest
mateo158/msvc-inventario:latest
mateo158/msvc-registro-ventas:latest
mateo158/msvc-reporte-ventas:latest
mateo158/msvc-punto-venta:latest
mateo158/msvc-users:latest
mateo158/msvc-oauth:latest
mateo158/msvc_gateway_server:latest
```

---

## 📁 Estructura del Proyecto

```
.
├── docker-compose/
│   └── docker-compose.yml
├── eureka-server/
├── msvc_gateway_server/
├── msvc-oauth/
├── msvc-users/
├── msvc-clientes/
├── msvc-inventario/
│   └── src/main/java/.../
│       ├── Domain/         # Modelos, servicios e interfaces de dominio
│       ├── Persistence/    # Entidades JPA, mappers y repositorios
│       └── Web/            # Controllers REST
├── msvc-registro-ventas/
├── msvc-reporte-ventas/
└── msvc-punto-venta/
```

Cada microservicio sigue una **arquitectura de capas limpia**:
- **Domain** → lógica de negocio desacoplada de frameworks
- **Persistence** → implementación JPA con entidades y mappers (MapStruct)
- **Web** → controladores REST expuestos al exterior

---

## 🤝 Contribuciones

1. Haz un fork del repositorio
2. Crea una rama: `git checkout -b feature/nueva-funcionalidad`
3. Realiza tus cambios y haz commit: `git commit -m "feat: descripción del cambio"`
4. Abre un Pull Request describiendo los cambios realizados

---

## 📄 Licencia

Este proyecto está bajo la licencia **MIT**. Consulta el archivo `LICENSE` para más detalles.
