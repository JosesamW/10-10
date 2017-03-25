package br.com.ifpe.monitoramento.entidades;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

/**
 * 
 * @author Hugo Oliveira
 *
 */
public class UnidadeGestora {

	@NotBlank
	@Size(min = 8, max = 30, message = "O nome deve ter de : {min} a {max} Letras")
	@Pattern(regexp = "^([A-Z,a-zã,Ã,á,Á,à,À,â,Â,ê,Ê,í,Í,ú,Ú,õ,Õ,ó,Ó,é,É,ü,Ü,ç,Ç, ])*", message = "Nome invalido !")
	private String nome;

	@NotNull
	@Min(value = 1, message = "O codigo min e 1")
	@Max(value = 999999999, message = "O codigo max e 999 Milhoes")
	@NumberFormat(pattern = "#########")
	private Integer codigo;
	private int UsuarioId;
	private String campo;
	private Date data;
	private Situacao situacao;

	public String toString() {
		return "Unidade Gestora: Codigo = " + this.codigo + ", Nome = " + this.nome + ", Situação = "
				+ this.situacao.name();
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public int getUsuarioId() {
		return UsuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		UsuarioId = usuarioId;
	}

}
