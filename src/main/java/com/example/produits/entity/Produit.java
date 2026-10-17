package com.example.produits.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produits")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nom", nullable = false, unique = true, length = 80)
	private String nom;

	@Column(name = "categorie", nullable = false, length = 50)
	private String categorie;

	@Column(name = "prix", nullable = false, precision = 10, scale = 2)
	private BigDecimal prix;

	@Column(name = "quantite", nullable = false)
	private Integer quantite;

	public Produit() {
	}

	public Produit(String nom, String categorie, BigDecimal prix, Integer quantite) {
		this.nom = nom;
		this.categorie = categorie;
		this.prix = prix;
		this.quantite = quantite;
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

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", categorie=" + categorie
				+ ", prix=" + prix + ", quantite=" + quantite + "]";
	}

}
