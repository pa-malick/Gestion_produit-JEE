package com.example.produits.exception;

// Levee quand les parametres d'une recherche n'ont pas de sens
// (par exemple un prix minimum superieur au prix maximum).
public class RequeteInvalideException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequeteInvalideException(String message) {
		super(message);
	}

}
