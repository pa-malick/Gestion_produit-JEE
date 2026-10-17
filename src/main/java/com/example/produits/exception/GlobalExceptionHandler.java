package com.example.produits.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// Toutes les erreurs de l'application passent par ici.
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> produitIntrouvable(
			ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(corps(HttpStatus.NOT_FOUND, ex.getMessage()));
	}

	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<Map<String, Object>> produitEnDouble(DuplicateResourceException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(corps(HttpStatus.CONFLICT, ex.getMessage()));
	}

	@ExceptionHandler(RequeteInvalideException.class)
	public ResponseEntity<Map<String, Object>> requeteInvalide(RequeteInvalideException ex) {
		return ResponseEntity.badRequest().body(corps(HttpStatus.BAD_REQUEST, ex.getMessage()));
	}

	// Declenchee par @Valid quand le DTO ne respecte pas ses contraintes
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> donneesInvalides(
			MethodArgumentNotValidException ex) {
		Map<String, String> erreurs = new TreeMap<>();
		for (FieldError erreur : ex.getBindingResult().getFieldErrors()) {
			erreurs.put(erreur.getField(), erreur.getDefaultMessage());
		}
		Map<String, Object> reponse = corps(HttpStatus.BAD_REQUEST, "Données invalides");
		reponse.put("erreurs", erreurs);
		return ResponseEntity.badRequest().body(reponse);
	}

	// Exemple : /api/produits/abc alors qu'un identifiant numerique est attendu
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> parametreMalForme(
			MethodArgumentTypeMismatchException ex) {
		String message = "Le paramètre '" + ex.getName() + "' est mal formé";
		return ResponseEntity.badRequest().body(corps(HttpStatus.BAD_REQUEST, message));
	}

	private Map<String, Object> corps(HttpStatus statut, String message) {
		Map<String, Object> reponse = new LinkedHashMap<>();
		reponse.put("timestamp", LocalDateTime.now());
		reponse.put("status", statut.value());
		reponse.put("error", statut.getReasonPhrase());
		reponse.put("message", message);
		return reponse;
	}

}
