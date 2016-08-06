package br.com.desafiotecnico.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="clientes")
public class Cliente extends GenericEntity{

	private static final long serialVersionUID = 8729550765031837999L;
	
	@NotBlank
	@Length(min=3, max=50)
	@Column(nullable=false, length=50)
	private String nome;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<OrdemServico> ordensServico;
	
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

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}
	
}