# **PizzApp MP** - Application de commande de pizzas multiplateforme

Ce projet, développé en binôme par **Hajar BAKAROU** et **Zahira ED-DENDANE**, est une évolution de l’application **PizzApp** du TD2, transformée en une **application multiplateforme** grâce à l’utilisation de **Kotlin Multiplatform Mobile (KMP)** et **Jetpack Compose**. L’objectif était de rendre l’application compatible avec plusieurs plateformes : **Android**, **Navigateur Web**, et **Desktop** .

---

## **Vidéo de démonstration**
🔗 [Regarder la démonstration](https://drive.google.com/file/d/1eb71C3SxK72X6VcsEZRoo7fxTvhU6Fix/view?usp=sharing)

---

## **Description générale du projet**
L’application permet de commander des pizzas personnalisées et de gérer un panier, avec la possibilité de consulter l’historique des commandes passées. Le projet met en œuvre les **principes du Modern Android Development (MAD)** tels que :
- **Architecture propre** avec séparation du code commun et spécifique à chaque plateforme.
- **Jetpack Compose** pour une interface utilisateur déclarative.
- **Base de données Room et SQLite (via JDBC pour Desktop)** pour la persistance locale.
- **Navigation fluide** entre les écrans grâce à Jetpack Navigation.

---

## **Fonctionnalités principales**
1. **Écran d’accueil :** Introduction à l’application avec un message de bienvenue et navigation vers les fonctionnalités principales.
2. **Menu des pizzas :** Affichage des pizzas disponibles avec leur image, description, et prix.
3. **Détails de la pizza :** Personnalisation des commandes avec choix de la quantité et du fromage supplémentaire.
4. **Panier :** Consultation des articles sélectionnés avec possibilité de modification ou suppression.
5. **Paiement sécurisé :** Entrée des informations de paiement et confirmation de la commande.
6. **Historique des commandes :** Affichage des commandes précédentes avec détails des pizzas et prix.

---

## **Stack technique utilisée**
- **Kotlin Multiplatform (KMP) :** Partage de la logique métier entre les plateformes.
- **Jetpack Compose :** Interface utilisateur moderne et déclarative.
- **Room (Android) et SQLite (Desktop) :** Base de données locale pour la persistance des commandes.
- **ViewModel et StateFlow :** Gestion de l’état et des données dynamiques.
- **Coroutine Flow :** Mise à jour en temps réel des informations.
- **Jetpack Navigation :** Navigation fluide entre les écrans.
- **Material 3 :** Respect des principes de design modernes.

---

## **Difficultés rencontrées et solutions apportées**
### 1. **Intégration de Room (Android)**
- **Problème :** Conflits de versions entre Room et le plugin KSP (Kotlin Symbol Processing).
- **Solution :** Nous avons aligné les versions de Kotlin, Room, et KSP dans les fichiers Gradle. La documentation officielle et les issues GitHub ont été d’une grande aide pour résoudre les erreurs de compilation.

### 2. **Gestion de la persistance sur Desktop**
- **Problème :** Room n’étant pas supporté sur Desktop, nous avons dû utiliser **SQLite via JDBC** pour cette plateforme.
- **Solution :** Nous avons intégré la dépendance `org.xerial:sqlite-jdbc` et adapté les requêtes de persistance au contexte Desktop, tout en conservant la même structure de base de données.

### 3. **Navigation multiplateforme**
- **Problème :** La navigation sur Android fonctionne bien via Jetpack Navigation, mais elle n’est pas directement disponible sur Desktop et Web.
- **Solution :** Nous avons créé un contrôleur de navigation personnalisé pour gérer les transitions entre les écrans sur chaque plateforme de manière cohérente.

### 4. **Gestion des ressources partagées**
- **Problème :** Les ressources (images, textes) doivent être correctement accessibles par chaque plateforme.
- **Solution :** Organisation rigoureuse des ressources dans le dossier `commonMain` avec un mappage correct vers les modules spécifiques à Android, Desktop, et Web.

---

## **Structure des modules**
- **commonMain :** Contient la logique métier partagée (modèles, gestion de la base de données, ViewModel).
- **androidMain :** Code spécifique à la plateforme Android (intégration de Room, UI).
- **desktopMain :** Code spécifique pour la version Desktop (JDBC, UI via Compose Desktop).
- **webMain :** Code pour la version Web avec Compose for Web.


## 👩‍💻 Développé par

- Hajar BAKAROU

- Zahira ED-DENDANE

## Université Côte d'Azur - DS4H - Master MIAGE NumRes
## Cours de Programmation Mobile Moderne - Leo Donati