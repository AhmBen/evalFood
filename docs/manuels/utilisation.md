# MANUEL D'UTILISATION

Il existe (lors de la première utilisation), des catégories créées par défaut.
Liste catégories par défaut :
 - fruits
 - légumes
 - conserve
 - boulangerie
 - épices
 - boissons
 - sauce

Une fois l'application lancée, le menu suivant apparaît :

1) Ajouter un aliment en base de données
2) Supprimer un aliment de la base de données
3) Afficher toute la liste
4) Ajouter une catégorie
5) Supprimer une catégorie
6) Afficher toute les catégories
X) Quitter le programme

# 1) Ajouter un aliment en base de données

 Possibilité d'ajouter un aliment dans la table food.
## Règles fonctionnelles :

 - Si un aliment existe déjà, il sera alors mis à jour 
 - Impossible de créer un aliment avec une catégorie qui n'existe pas
	 - L'application bouclera jusqu'a ce qu'on entre une catégorie valide
 - Tous les champs sont obligatoires
 - Les valeurs des champs numériques (énergie,protéines,glucides et
   lipides) doivent être supérieures ou égales à zéro
 - L'application bouclera sur le champs jusqu'a ce qu'une valeur
   numérique (supérieur ou égale à zéro) soit saisie


# 2) Supprimer un aliment de la base de données 
Possibilité de supprimer un aliment de la base de données aliments.
## Règles fonctionnelles :

 - L'aliment doit exister en base

# 3) Afficher toute la liste des aliments
Permet l'affichage de toute la liste formatées des aliments ainsi que des les catégories correspondantes.
Liste triées sur le nom des aliments.
## Règles fonctionnelles :

 - Aucune

# 4) Ajouter une catégorie

Possibilité d'ajouter une catégorie dans la table catégories.
## Règles fonctionnelles :

 - La catégorie ne doit pas déjà exister
 - champs obligatoires.

# 5) Supprimer une catégorie
Possibilité de supprimer une catégorie de la table catégories.
## Règles fonctionnelles :

 - La catégorie doit exister en base
 - Aucune aliment de la table aliment  ne doit être relié à une catégorie à supprimer, sinon supprimer ou mettre à jour la catégories de tous les aliments
 - 
# 6) Afficher toute les catégories
Permet l'affichage de toute la liste des catégories, triée sur le nom
## Règles fonctionnelles :
 - Aucune

# X) Quitter le programme
Cette fonction permet de sortir du programme
## Règles fonctionnelles :

 - La saisie du X est insensible à la casse

**Toute autre valeur entraînera un rejet ainsi qu'un nouvel affichage du menu.**