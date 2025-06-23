# 🛡️ Form Validation API (Chain of Responsibility)

Une petite API REST en Java/Spring Boot pour valider progressivement les champs d'un formulaire d'inscription (nom, email, mot de passe), en utilisant le **patron de conception "Chain of Responsibility"**.

---

## 🚀 Fonctionnalité

> POST `/api/validate`

Cette API prend un JSON contenant les champs suivants :
- `firstName`
- `lastName`
- `email`
- `password`

Elle applique une chaîne de validations séquentielles. Si tous les champs sont valides → `"Validation successful"`, sinon → `400 Bad Request` avec le message d’erreur correspondant.

---

## 🧠 Patron de Conception

Le projet met en œuvre le pattern **Chain of Responsibility** :

```text
NameValidator ─▶ EmailValidator ─▶ PasswordValidator

Chaque validateur hérite d'une classe abstraite BaseValidationHandler et peut décider de :

    Traiter la requête

    Déléguer au prochain maillon de la chaîne

    Lever une exception pour interrompre le processus
```

## 📦 Installation

```bash
git clone https://github.com/madani20/form-validator.git
cd form-validator
./mvnw spring-boot:run

ou avec docker:

Création de l'image :
    docker build -t <nom-de-image:tag> . (Ne pas oublier le point dans la commande, puis
vérifier que l'image existe la commande docker ps -a)
Lancement du conteneur :
    docker run -d --name <nom-du-conteneur> -p 8080:8080 <nom-de-image:tag>
    (lance le conteneur en mode détaché sur le port 8080)

```

## Une documentation interactive est disponible via Swagger :
http://localhost:8080/swagger-ui.html
