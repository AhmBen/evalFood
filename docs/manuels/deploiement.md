# MANUEL DE DEPLOIEMENT


Le projet est pret a l'emploi, il n'y a rien de particulier � installer.

Le projet "Food" est compos� de 5 classes qui utilisent 2 tables.
Il contient �galement dans le repertoire docs plusieurs manuels.

## Tables :

### Food (Table qui contient les diff�rentes caract�ristiques d'un aliment)

 - Id (Cl� primaire)
 - Nom (Nom de l�ingr�dient)
 - Cat�gories (Cl� �trang�re)
 - Energie, cette donn�e doit �tre sup�rieure ou �gale � z�ro
 - Prot�ines, cette donn�e doit �tre sup�rieure ou �gale � z�ro
 - Glucides, cette donn�e doit �tre sup�rieure ou �gale � z�ro
 - Lipides, cette donn�e doit �tre sup�rieure ou �gale � z�ro

### Cats

 - Id (Cl� primaire)
 - Name (Nom de la cat�gorie)

## Classes :

### App

 - Classe principale qui permet de lancer le programme
 - Affichage du menu (en boucle jusqu�� demande de sortie faite par
   l'utilisateur)
 - Lors de la premi�re ex�cution, les 2 tables seront cr��es.
 - La table Cats contiendra une dizaine de valeurs par d�faut.

### Connexion

 - Contient les scripts de connexion/d�connexion � la BDD.
 - Utilise le fichier bdd.properties (contient les param�tres de connexion)

### CreateTable :

 - Contient les requ�tes Sql de cr�ation des tables
 - Contient l'initialisation de la table Cats

### Food

 - Classe qui contient toutes les m�thodes n�cessaires pour intervenir
   sur la table Food

### Cats

 - Classe qui contient toutes les m�thodes n�cessaires pour intervenir
   sur la table Cats

### R�pertoire Docs :

 - Manuel d'utilisation
 - Manuel de d�ploiement (ce manuel)
 - Manuel de d�veloppement
