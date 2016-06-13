package br.com.desafiotecnico.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public abstract class GenericEntity implements Serializable{

	private static final long serialVersionUID = 8659435934843377963L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@NotNull
	@CreatedDate
	@Column(name="data_criacao")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Column(name="data_atualizacao")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAtualizacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@PreUpdate
	public void preUpdate(){
		this.dataAtualizacao = LocalDateTime.now();
	}
	
}