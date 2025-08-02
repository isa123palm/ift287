# Lancer le projet APCE Web avec Docker

## Étapes pour démarrer le projet

0. **Pour lancer le main, il suffit de configurer son IDE (s'assurer qu'un dossier out existe, avoir TP/src/main/java comme source root, TP/src/main/ressources comme ressource root, avoir ajuster /lib comme librairie et avoir comme SDK OpenJDK 21 ou +)et de lancer Apce.java** :



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
