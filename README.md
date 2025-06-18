# 🛡️ Form Validation API (Spring Boot + Chain of Responsibility)

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
