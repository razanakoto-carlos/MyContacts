# 📋 ContactApp — Carnet de Contacts Android

**ContactApp** est une application Android native développée en **Java** permettant de gérer un répertoire de contacts de manière fluide et persistante grâce à une base de données **SQLite** intégrée.

L'application arbore une interface utilisateur moderne, minimaliste et sombre, inspirée d'une maquette haute fidélité.

---

## ✨ Fonctionnalités

L'application est articulée autour de 3 écrans clés :

1. **📋 Écran de Liste (Read)** :
    * Affichage dynamique de tous les contacts enregistrés à l'aide d'un `RecyclerView`.
    * Lecture optimisée des données via des requêtes SQLite (`Cursor`).
    * Barre de recherche et statistiques en temps réel (nombre total de contacts).

2. **👤 Écran de Détail (Read & Delete)** :
    * Visualisation complète des informations d'un contact (Nom, Téléphone, Email, Note).
    * **Intents Implicites** : Bouton d'action rapide pour lancer un appel téléphonique réel via `Intent.ACTION_CALL`[cite: 2].
    * Suppression du contact de la base de données avec retour automatique à la liste.

3. **✏️ Écran de Formulaire (Create & Update)** :
    * Formulaire unique gérant intelligemment l'ajout et la modification de contact.
    * Validation stricte des champs obligatoires (Nom et Téléphone) avec messages d'erreur clairs.
    * Enregistrement persistant via des requêtes SQL d'insertion et de mise à jour.

---

## 🛠️ Pile Technique

* **Langage** : Java (Android SDK traditionnel)
* **Architecture Graphique** : Android XML Views (basé sur l'approche `Empty Views Activity`)
* **Base de données** : SQLite (via la classe native `SQLiteOpenHelper`)
* **Gestionnaire de dépendances** : Gradle

---

## 🚀 Installation et Lancement

Pour cloner et exécuter ce projet localement sur votre machine :

1. **Cloner le dépôt** :
```bash
   git clone [https://github.com/razanakoto-carlos/MyContacts.git](https://github.com/razanakoto-carlos/MyContacts.git)