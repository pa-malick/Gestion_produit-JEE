package com.example.produits.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.produits.dto.ProduitRequest;
import com.example.produits.dto.ProduitResponse;
import com.example.produits.entity.Produit;

// Le mapper fait le passage DTO <-> entite.
// Ecrit a la main, sans MapStruct.
@Component
public class ProduitMapper {

	public Produit versEntite(ProduitRequest requete) {
		Produit produit = new Produit();
		produit.setNom(requete.getNom().trim());
		produit.setCategorie(requete.getCategorie().trim());
		produit.setPrix(requete.getPrix());
		produit.setQuantite(requete.getQuantite());
		return produit;
	}

	// Utilise lors d'une modification : on recopie les champs sur un produit existant
	public void mettreAJour(Produit produit, ProduitRequest requete) {
		produit.setNom(requete.getNom().trim());
		produit.setCategorie(requete.getCategorie().trim());
		produit.setPrix(requete.getPrix());
		produit.setQuantite(requete.getQuantite());
	}

	public ProduitResponse versReponse(Produit produit) {
		ProduitResponse reponse = new ProduitResponse();
		reponse.setId(produit.getId());
		reponse.setNom(produit.getNom());
		reponse.setCategorie(produit.getCategorie());
		reponse.setPrix(produit.getPrix());
		reponse.setQuantite(produit.getQuantite());
		BigDecimal quantite = BigDecimal.valueOf(produit.getQuantite());
		reponse.setValeurStock(produit.getPrix().multiply(quantite));
		return reponse;
	}

	public List<ProduitResponse> versReponses(List<Produit> produits) {
		List<ProduitResponse> reponses = new ArrayList<>();
		for (Produit produit : produits) {
			reponses.add(versReponse(produit));
		}
		return reponses;
	}

}
