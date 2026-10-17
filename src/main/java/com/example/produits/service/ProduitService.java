package com.example.produits.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.produits.dto.ProduitRequest;
import com.example.produits.dto.ProduitResponse;

// L'interface decrit ce que le service sait faire.
// Le controleur ne connait que cette interface, pas l'implementation.
public interface ProduitService {

	ProduitResponse creer(ProduitRequest requete);

	ProduitResponse modifier(Long id, ProduitRequest requete);

	void supprimer(Long id);

	ProduitResponse trouverParId(Long id);

	List<ProduitResponse> listerTout();

	List<ProduitResponse> rechercherParCategorie(String categorie);

	List<ProduitResponse> rechercherParNom(String nom);

	List<ProduitResponse> stockFaible(Integer seuil);

	List<ProduitResponse> plageDePrix(BigDecimal min, BigDecimal max);

	List<String> listerCategories();

	BigDecimal valeurStockParCategorie(String categorie);

}
