package br.com.desafiotecnico.model;

public enum Tipo {
	OURO("Ouro"), PRATA("Prata");
	
	private final String nome;

	private Tipo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
}
