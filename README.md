# Stock Manager – Projet Fullstack

## 🚀 Description
Application Fullstack de gestion de stock, développée avec une architecture moderne, sécurisée et conteneurisée.

### Stack Technique
- Backend : Spring Boot + Spring Security + JWT
- Documentation API : OpenAPI (Contract First) + Swagger UI
- Mapping DTO : MapStruct
- Frontend : Angular
- Infrastructure : Docker & Docker Compose

### Fonctionnalités principales
- Authentification sécurisée avec JWT
- Gestion des utilisateurs (recherche par nom ou email)
- Gestion des catégories (recherche par nom)
- Gestion des produits (filtrage par catégorie + recherche par nom)
- Déploiement en une seule commande avec Docker Compose

### Objectifs du projet
- Comprendre l'architecture REST
- Sécuriser une API avec JWT
- Séparer Entity / DTO
- Intégrer Backend ↔ Frontend
- Conteneuriser l'application avec Docker

### Structure du projet
```
stock-manager/
│
├── backend/              # API Spring Boot
├── frontend/             # Application Angular
├── docker-compose.yml    # Orchestration des services
└── README.md
```

### Lancement avec Docker
1. Cloner le projet :
```bash
git clone https://github.com/Moina337/stock-manager.git
cd stock-manager
```
2. Lancer l'application :
```bash
docker compose up --build
```

### Accès aux services
- Backend API : http://localhost:8080
- Swagger UI : http://localhost:8080/swagger-ui.html
- Frontend Angular : http://localhost:4200
- Base de données : localhost:3306



### Auteur
Soilihi Amir – Développeur Backend / Fullstack Junior
Ouvert aux opportunités de stage ou junior en développement

### GitHub
https://github.com/Moina337/stock-manager

