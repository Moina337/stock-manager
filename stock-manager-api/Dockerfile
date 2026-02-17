# Étape 1 : Image de base avec le JRE (Java Runtime Environment)
FROM eclipse-temurin:17-jre-alpine

# Dossier où l'application va vivre dans le conteneur
WORKDIR /app

# Copie le fichier .jar généré par Maven (ou Gradle) dans le conteneur
# Note : Vérifie que ton fichier jar s'appelle bien comme ça dans le dossier target
COPY target/*.jar app.jar

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
