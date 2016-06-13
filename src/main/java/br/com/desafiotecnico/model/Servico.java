package br.com.desafiotecnico.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="servicos")
public class Servico extends GenericEntity{

	private static final long serialVersionUID = 8729550765031837999L;

	@NotBlank
	@Length(min=3, max=50)
	@Column(nullable=false, length=50)
	private String nome;
	
	@NotNull
	@Column(nullable=false, length=12, scale=2)
	private BigDecimal valor;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}