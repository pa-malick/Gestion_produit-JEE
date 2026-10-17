# Mini-projet JEE : gestion des produits

API REST de gestion de produits (id, nom, catégorie, prix, quantité) écrite avec
Spring Boot, Spring Data JPA et PostgreSQL.

## Prérequis

- JDK 17 ou plus (testé avec le JDK 23)
- Maven 3.9
- PostgreSQL 16, ici sur le port **5433**

## Mise en route

```
createdb -U postgres -p 5433 produitsdb
mvn clean package
java -jar target/gestion-produits-0.0.1-SNAPSHOT.jar
```

Hibernate crée la table `produits` au premier démarrage, puis `data.sql` insère
huit produits de démonstration. L'application écoute sur le port 8080.

La connexion se règle dans `src/main/resources/application.properties`.

## Points d'entrée

| Méthode | URL | Rôle |
| --- | --- | --- |
| POST | /api/produits | Créer un produit |
| GET | /api/produits | Lister tous les produits |
| GET | /api/produits/{id} | Consulter un produit |
| PUT | /api/produits/{id} | Modifier un produit |
| DELETE | /api/produits/{id} | Supprimer un produit |
| GET | /api/produits/categorie/{categorie} | Produits d'une catégorie |
| GET | /api/produits/recherche?nom= | Recherche par nom |
| GET | /api/produits/stock-faible?seuil=5 | Produits en stock faible |
| GET | /api/produits/prix?min=&max= | Produits dans une plage de prix |
| GET | /api/produits/categories | Liste des catégories |
| GET | /api/produits/valeur-stock?categorie= | Valeur du stock d'une catégorie |

## Organisation du code

```
entity/          Produit           entité JPA
repository/      ProduitRepository query methods et requêtes JPQL
dto/             ProduitRequest    données reçues, avec validation
                 ProduitResponse   données renvoyées, avec valeurStock
mapper/          ProduitMapper     conversion DTO <-> entité
service/         ProduitService    interface
service/impl/    ProduitServiceImpl règles métier
controller/      ProduitController points d'entrée HTTP
exception/       GlobalExceptionHandler et trois exceptions métier
```

## Tests Postman

Importer `postman/Gestion-Produits.postman_collection.json` dans Postman, puis
lancer la collection avec le Runner. Les quinze requêtes portent vingt-cinq
assertions.

La variable `baseUrl` est définie dans la collection. L'environnement
`postman/Local.postman_environment.json` est optionnel : il ne contient que
`baseUrl`.

En ligne de commande :

```
newman run postman/Gestion-Produits.postman_collection.json
```

## Codes de retour

| Situation | Code |
| --- | --- |
| Création réussie | 201 |
| Suppression réussie | 204 |
| Produit introuvable | 404 |
| Nom déjà utilisé | 409 |
| Données invalides ou paramètre incohérent | 400 |
