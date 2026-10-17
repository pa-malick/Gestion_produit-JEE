package com.example.produits.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// DTO d'entree : ce que le client envoie pour creer ou modifier un produit.
// L'identifiant n'y figure pas, il est genere par la base.
public class ProduitRequest {

	@NotBlank(message = "Le nom est obligatoire")
	@Size(max = 80, message = "Le nom ne doit pas dépasser 80 caractères")
	private String nom;

	@NotBlank(message = "La catégorie est obligatoire")
	@Size(max = 50, message = "La catégorie ne doit pas dépasser 50 caractères")
	private String categorie;

	@NotNull(message = "Le prix est obligatoire")
	@DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
	@Digits(integer = 8, fraction = 2, message = "Le prix admet au plus 2 décimales")
	private BigDecimal prix;

	@NotNull(message = "La quantité est obligatoire")
	@Min(value = 0, message = "La quantité ne peut pas être négative")
	private Integer quantite;

	public ProduitRequest() {
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

}
