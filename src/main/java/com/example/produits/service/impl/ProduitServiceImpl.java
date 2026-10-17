package com.example.produits.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.produits.dto.ProduitRequest;
import com.example.produits.dto.ProduitResponse;
import com.example.produits.entity.Produit;
import com.example.produits.exception.DuplicateResourceException;
import com.example.produits.exception.RequeteInvalideException;
import com.example.produits.exception.ResourceNotFoundException;
import com.example.produits.mapper.ProduitMapper;
import com.example.produits.repository.ProduitRepository;
import com.example.produits.service.ProduitService;

@Service
@Transactional(readOnly = true)
public class ProduitServiceImpl implements ProduitService {

	private final ProduitRepository produitRepository;
	private final ProduitMapper produitMapper;

	// Injection par constructeur : les deux dependances sont obligatoires
	public ProduitServiceImpl(ProduitRepository produitRepository,
			ProduitMapper produitMapper) {
		this.produitRepository = produitRepository;
		this.produitMapper = produitMapper;
	}

	@Override
	@Transactional
	public ProduitResponse creer(ProduitRequest requete) {
		String nom = requete.getNom().trim();
		if (produitRepository.existsByNomIgnoreCase(nom)) {
			throw new DuplicateResourceException("Un produit nommé '" + nom + "' existe déjà");
		}
		Produit produit = produitMapper.versEntite(requete);
		return produitMapper.versReponse(produitRepository.save(produit));
	}

	@Override
	@Transactional
	public ProduitResponse modifier(Long id, ProduitRequest requete) {
		Produit produit = chercher(id);
		String nom = requete.getNom().trim();
		// Le nom peut rester le meme, mais il ne doit pas etre celui d'un autre produit
		produitRepository.findByNomIgnoreCase(nom).ifPresent(autre -> {
			if (!autre.getId().equals(id)) {
				throw new DuplicateResourceException(
						"Un produit nommé '" + nom + "' existe déjà");
			}
		});
		produitMapper.mettreAJour(produit, requete);
		return produitMapper.versReponse(produitRepository.save(produit));
	}

	@Override
	@Transactional
	public void supprimer(Long id) {
		produitRepository.delete(chercher(id));
	}

	@Override
	public ProduitResponse trouverParId(Long id) {
		return produitMapper.versReponse(chercher(id));
	}

	@Override
	public List<ProduitResponse> listerTout() {
		return produitMapper.versReponses(produitRepository.findAll());
	}

	@Override
	public List<ProduitResponse> rechercherParCategorie(String categorie) {
		return produitMapper.versReponses(
				produitRepository.findByCategorieIgnoreCaseOrderByPrixAsc(categorie.trim()));
	}

	@Override
	public List<ProduitResponse> rechercherParNom(String nom) {
		return produitMapper.versReponses(
				produitRepository.findByNomContainingIgnoreCase(nom.trim()));
	}

	@Override
	public List<ProduitResponse> stockFaible(Integer seuil) {
		if (seuil == null || seuil < 0) {
			throw new RequeteInvalideException("Le seuil doit être un entier positif");
		}
		return produitMapper.versReponses(produitRepository.findByQuantiteLessThan(seuil));
	}

	@Override
	public List<ProduitResponse> plageDePrix(BigDecimal min, BigDecimal max) {
		if (min == null || max == null) {
			throw new RequeteInvalideException("Les deux bornes de prix sont obligatoires");
		}
		if (min.compareTo(max) > 0) {
			throw new RequeteInvalideException(
					"Le prix minimum ne peut pas dépasser le prix maximum");
		}
		return produitMapper.versReponses(produitRepository.findByPrixBetween(min, max));
	}

	@Override
	public List<String> listerCategories() {
		return produitRepository.listerCategories();
	}

	@Override
	public BigDecimal valeurStockParCategorie(String categorie) {
		return produitRepository.valeurStockParCategorie(categorie.trim());
	}

	// Methode utilitaire : evite de repeter le meme controle dans chaque methode
	private Produit chercher(Long id) {
		return produitRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Aucun produit avec l'identifiant " + id));
	}

}
