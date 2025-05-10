
# 🏙️ Smart City Management System – Backend

This repository contains the backend microservices for the **Smart City Management System**, which simulates and manages city-wide functionalities such as traffic, waste, energy, and emergency incidents.

## 📦 Services Overview

| Service            | Port | Description                                 |
|--------------------|------|---------------------------------------------|
| Discovery Server   | 8671 | Service registry using Eureka               |
| API Gateway        | 9090 | Routes requests to appropriate services     |
| Traffic Service    | 8081 | Simulates and manages traffic incidents     |
| Waste Service      | 8082 | Tracks waste disposal incidents             |
| Energy Service     | 8083 | Monitors city-wide energy consumption       |
| Emergency Service  | 8084 | Logs and broadcasts emergency situations    |

---

## 🧠 Technologies Used

- **Spring Boot 3.2**
- **Eureka Service Discovery**
- **Spring Cloud Gateway**
- **H2 In-Memory Database**
- **Spring Data JPA**
- **Actuator for health checks**
- **WebSocket (for Emergency Live Updates)**

---

## 🐳 Docker Setup

Each service includes a Dockerfile.

To build and run the full backend system:

```bash
docker compose up --build
```

Ensure Docker Desktop is running. This will:
- Build all services
- Run them with inter-service communication over Docker network
- Expose ports for local testing

> You can shut everything down cleanly using:
> ```bash
> docker compose down -v --remove-orphans
> ```

---

## 🔁 Live Simulations

- **Traffic and Emergency** services simulate random incidents every 50 seconds using `@Scheduled` tasks.
- **Energy** service includes 3 usage entries from `CommandLineRunner`
- **Waste** service also has preloaded incidents

---

## 📶 API Endpoints

Here are some example endpoints per service (hosted locally):

- Traffic: `http://localhost:8081/traffic/incidents`
- Waste: `http://localhost:8082/waste/incidents`
- Energy: `http://localhost:8083/energy/usage`
- Emergency: `http://localhost:8084/emergency/incidents`
- Eureka Dashboard: `http://localhost:8671`
- API Gateway (e.g. proxied): `http://localhost:9090/traffic/incidents`

---

## 📚 Future Extensions

- Add frontend React app with heatmaps and incident dashboards
- WebSocket UI for emergency broadcast
- Docker Compose full-stack setup
- Deployment to cloud (optional)

---

## 🤝 Contributions

Feel free to fork and extend!

---

## 🧪 Health Check

All services expose actuator endpoints:

```
GET /actuator/health
GET /actuator
```

---

## 🧾 License

MIT License – Free to use and customize.
