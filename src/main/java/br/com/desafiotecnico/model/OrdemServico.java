package br.com.desafiotecnico.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.desafiotecnico.model.ordemservico.CalculadorPreco;

@Entity
@Table(name="ordem_servico")
public class OrdemServico extends GenericEntity{

	private static final long serialVersionUID = -6150993008300149589L;

	private static final String PT_BR_DATE = "dd/MM/yyyy";
	
	@Column
	@NotNull
	@DateTimeFormat(pattern=PT_BR_DATE)
	private LocalDate inicio;
	
	@Column
	@NotNull
	@DateTimeFormat(pattern=PT_BR_DATE)
	private LocalDate fim;
	
	@NotNull
	@JoinColumn
	@ManyToOne(fetch=FetchType.LAZY)
	private Servico servico;

	@NotNull
	@JoinColumn
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	
	@Column
	private Boolean pago;

	public LocalDate getInicio() {
		return inicio;
	}

	public LocalDate getFim() {
		return fim;
	}

	public Servico getServico() {
		return servico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Boolean isPago() {
		return pago;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public void setFim(LocalDate fim) {
		this.fim = fim;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotal(){
		return new CalculadorPreco(this).calcular().getValorTotal();
	}

	
}