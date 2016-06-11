package br.jh.lembre.me.model;

public enum Subscribe {
	JAVA("Java"), 
	FRONT_END("Front End"), 
	NODE("Node"), 
	RUBY("Ruby"), 
	DEVOPS("Devops"), 
	TOOLS("Ferramentas"), 
	JOBS("Oportunidades de Trabalho"), 
	OTHER("Outros");

	private final String label;

	private Subscribe(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}