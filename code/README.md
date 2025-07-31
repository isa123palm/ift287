# Lancer le projet APCE Web avec Docker

## Étapes pour démarrer le projet

1. **Il faut se placer à la racine du projet (là où il y a le `Dockerfile` et `pom.xml`)** :

```bash
cd TP/
```

2. **Construire l’image Docker** :

```bash
docker build -t apce-web .
```

3. **Lancer le serveur** :

```bash
docker run -p 8080:8080 apce-web
```

4. **Accéder à l'application** dans le navigateur :

http://localhost:8080

---

Aucune installation de Java ou Maven n’est requise localement.
(Tous est dans le Dockerfile)
