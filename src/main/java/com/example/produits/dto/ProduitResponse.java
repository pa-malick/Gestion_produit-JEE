package com.example.produits.dto;

import java.math.BigDecimal;

// DTO de sortie : ce que l'API renvoie au client.
// On ajoute une donnee calculee (valeurStock) qui n'existe pas dans la table.
public class ProduitResponse {

	private Long id;
	private String nom;
	private String categorie;
	private BigDecimal prix;
	private Integer quantite;
	private BigDecimal valeurStock;

	public ProduitResponse() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getValeurStock() {
		return valeurStock;
	}

	public void setValeurStock(BigDecimal valeurStock) {
		this.valeurStock = valeurStock;
	}

}
