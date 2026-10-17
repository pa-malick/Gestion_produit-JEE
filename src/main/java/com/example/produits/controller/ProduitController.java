package com.example.produits.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.produits.dto.ProduitRequest;
import com.example.produits.dto.ProduitResponse;
import com.example.produits.service.ProduitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

	private final ProduitService produitService;

	public ProduitController(ProduitService produitService) {
		this.produitService = produitService;
	}

	// POST /api/produits
	@PostMapping
	public ResponseEntity<ProduitResponse> creer(@Valid @RequestBody ProduitRequest requete) {
		ProduitResponse cree = produitService.creer(requete);
		URI emplacement = URI.create("/api/produits/" + cree.getId());
		return ResponseEntity.created(emplacement).body(cree);
	}

	// GET /api/produits
	@GetMapping
	public List<ProduitResponse> listerTout() {
		return produitService.listerTout();
	}

	// GET /api/produits/1
	@GetMapping("/{id}")
	public ProduitResponse trouverParId(@PathVariable Long id) {
		return produitService.trouverParId(id);
	}

	// PUT /api/produits/1
	@PutMapping("/{id}")
	public ProduitResponse modifier(@PathVariable Long id,
			@Valid @RequestBody ProduitRequest requete) {
		return produitService.modifier(id, requete);
	}

	// DELETE /api/produits/1
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> supprimer(@PathVariable Long id) {
		produitService.supprimer(id);
		return ResponseEntity.noContent().build();
	}

	// GET /api/produits/categorie/Informatique
	@GetMapping("/categorie/{categorie}")
	public List<ProduitResponse> parCategorie(@PathVariable String categorie) {
		return produitService.rechercherParCategorie(categorie);
	}

	// GET /api/produits/recherche?nom=cla
	@GetMapping("/recherche")
	public List<ProduitResponse> parNom(@RequestParam String nom) {
		return produitService.rechercherParNom(nom);
	}

	// GET /api/produits/stock-faible?seuil=5
	@GetMapping("/stock-faible")
	public List<ProduitResponse> stockFaible(@RequestParam(defaultValue = "5") Integer seuil) {
		return produitService.stockFaible(seuil);
	}

	// GET /api/produits/prix?min=1000&max=50000
	@GetMapping("/prix")
	public List<ProduitResponse> plageDePrix(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {
		return produitService.plageDePrix(min, max);
	}

	// GET /api/produits/categories
	@GetMapping("/categories")
	public List<String> listerCategories() {
		return produitService.listerCategories();
	}

	// GET /api/produits/valeur-stock?categorie=Informatique
	@GetMapping("/valeur-stock")
	public BigDecimal valeurStock(@RequestParam String categorie) {
		return produitService.valeurStockParCategorie(categorie);
	}

}
