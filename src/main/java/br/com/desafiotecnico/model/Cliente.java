package br.com.desafiotecnico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="clientes")
public class Cliente extends GenericEntity{

	private static final long serialVersionUID = 8729550765031837999L;

	@Column
	@NotBlank
	@Length(min=3, max=50)
	private String nome;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}