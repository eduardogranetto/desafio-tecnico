package br.com.desafiotecnico.model;

import static java.time.LocalDate.now;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.desafiotecnico.model.ordemservico.CalculadorPreco;

@Entity
@Table(name="ordem_servico")
public class OrdemServico extends GenericEntity{

	private static final long serialVersionUID = -6150993008300149589L;

	private static final String PT_BR_DATE = "dd/MM/yyyy";
	
	@Column
	@NotNull(message="Data de Início do serviço não pode ser vazia!")
	@DateTimeFormat(pattern=PT_BR_DATE)
	@JsonFormat(pattern = PT_BR_DATE)
	private LocalDate inicio;
	
	@Column
	@NotNull(message="Data de finalização do serviço não pode ser vazia!")
	@DateTimeFormat(pattern=PT_BR_DATE)
	@JsonFormat(pattern = PT_BR_DATE)
	private LocalDate fim;

	@JsonIgnore
	@NotNull(message="Código do serviço não pode ser vazio!")
	@JoinColumn(name="id_servico", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Servico servico;

	@JsonIgnore
	@NotNull(message="Código do cliente não pode ser vazio!")
	@JoinColumn(name="id_cliente", referencedColumnName="id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Cliente cliente;
	
	@Column
	private BigDecimal valorPago;
	
	@Column
	private Boolean pago = Boolean.FALSE;
	
	@Column(name="id_cliente", insertable=false, updatable=false)
	private Long idCliente;
	
	@Column(name="id_servico", insertable=false, updatable=false)
	private Long idServico;
	
	@AssertTrue(message="Data de término do serviço deve ser maior ou igual à data de início!")
	public boolean isInicioPeriodoValido(){
		return fim!=null && inicio != null && (inicio.isBefore(fim) || inicio.isEqual(fim));
	}
	
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
		return isPago() ? valorPago : new CalculadorPreco(this).calcular().getValorTotal();
	}

	public BigDecimal getValorPago(){
		return valorPago;
	}
	
	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getDiasParaTermino(){
		if(now().isBefore(inicio)){
			return inicio.until(fim, ChronoUnit.DAYS);
		}
		Long diasParaTermino = now().until(fim, ChronoUnit.DAYS);
		return diasParaTermino >= 0 ? diasParaTermino : 0;
	}

	public void cancelarPagamento() {
		this.valorPago = BigDecimal.ZERO;
		this.pago = Boolean.FALSE;
	}
	
	public void pagar(){
		this.valorPago = getTotal();
		this.pago = Boolean.TRUE;
	}
	
}