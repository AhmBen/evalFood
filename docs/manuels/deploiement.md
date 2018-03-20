# MANUEL DE DEPLOIEMENT


Le projet est pret a l'emploi, il n'y a rien de particulier à installer.

Le projet "Food" est composé de 5 classes qui utilisent 2 tables.
Il contient également dans le repertoire docs plusieurs manuels.

## Tables :

### Food (Table qui contient les différentes caractéristiques d'un aliment)

 - Id (Clé primaire)
 - Nom (Nom de l’ingrédient)
 - Catégories (Clé étrangère)
 - Energie, cette donnée doit être supérieure ou égale à zéro
 - Protéines, cette donnée doit être supérieure ou égale à zéro
 - Glucides, cette donnée doit être supérieure ou égale à zéro
 - Lipides, cette donnée doit être supérieure ou égale à zéro

### Cats

 - Id (Clé primaire)
 - Name (Nom de la catégorie)

## Classes :

### App

 - Classe principale qui permet de lancer le programme
 - Affichage du menu (en boucle jusqu’à demande de sortie faite par
   l'utilisateur)
 - Lors de la première exécution, les 2 tables seront créées.
 - La table Cats contiendra une dizaine de valeurs par défaut.

### Connexion

 - Contient les scripts de connexion/déconnexion à la BDD.
 - Utilise le fichier bdd.properties (contient les paramètres de connexion)

### CreateTable :

 - Contient les requêtes Sql de création des tables
 - Contient l'initialisation de la table Cats

### Food

 - Classe qui contient toutes les méthodes nécessaires pour intervenir
   sur la table Food

### Cats

 - Classe qui contient toutes les méthodes nécessaires pour intervenir
   sur la table Cats

### Répertoire Docs :

 - Manuel d'utilisation
 - Manuel de déploiement (ce manuel)
 - Manuel de développement
