package com.example.produits.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.produits.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	// Recherche par categorie exacte, sans tenir compte de la casse
	List<Produit> findByCategorieIgnoreCase(String categorie);

	// Recherche par morceau de nom : "cla" retrouve "Clavier"
	List<Produit> findByNomContainingIgnoreCase(String nom);

	// Produits dont le stock est inferieur au seuil passe en parametre
	List<Produit> findByQuantiteLessThan(Integer seuil);

	// Produits dont le prix se situe entre deux bornes (bornes incluses)
	List<Produit> findByPrixBetween(BigDecimal min, BigDecimal max);

	// Meme idee que ci-dessus, mais tries du moins cher au plus cher
	List<Produit> findByCategorieIgnoreCaseOrderByPrixAsc(String categorie);

	Optional<Produit> findByNomIgnoreCase(String nom);

	boolean existsByNomIgnoreCase(String nom);

	// Requete JPQL : liste des categories distinctes presentes en base
	@Query("select distinct p.categorie from Produit p order by p.categorie")
	List<String> listerCategories();

	// Requete JPQL avec parametre nomme : valeur totale du stock d'une categorie
	@Query("select coalesce(sum(p.prix * p.quantite), 0) from Produit p"
			+ " where lower(p.categorie) = lower(:categorie)")
	BigDecimal valeurStockParCategorie(@Param("categorie") String categorie);

}
