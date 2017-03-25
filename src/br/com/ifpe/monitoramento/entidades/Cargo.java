package br.com.ifpe.monitoramento.entidades;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Hugo Oliveira
 *
 */
public class Cargo {

	@NotBlank
	@Size(min = 3, max = 60, message = "o nome deve ter de : {min} a  {max}")
	@Pattern(regexp = "^([A-Z,a-zã,Ã,á,Á,à,À,â,Â,ê,Ê,í,Í,ú,Ú,õ,Õ,ó,Ó,é,É,ü,Ü,ç,Ç, ])*", message = "Nome invalido")
	private String nome;

	private Integer id;
	private Date data_cadastro;
	private Situacao situacao;
	private String campo;
	private Integer idUsuario;

	public String toString() {
		return "Cargo: Codigo = " + this.id + ", nome = " + this.nome + ", Situacao = " + this.situacao.name();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
