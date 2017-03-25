package br.com.ifpe.monitoramento.entidades;

import javax.validation.constraints.Pattern;

/**
 * 
 * @author Hugo Oliveira
 *
 */
public class SugestaoDiaria {
	private Cargo cargo;
	private UnidadeGestora ug;
	private Cidade destino;
	private Cidade origem;
	private int idUsuario;
	private String campo;

	@Pattern(regexp = "[0-9.]*", message = "Valor invalido")
	private String valores;

	private Integer idSD;

	public String toString() {
		return "Sugestão Diaria: Codigo = " + this.idSD + ", Cargo = " + this.cargo.getNome() + ", Unidade Gestora = "
				+ this.ug.getNome() + ", Valor = " + this.valores;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public Integer getIdSD() {
		return idSD;
	}

	public void setIdSD(Integer idSD) {
		this.idSD = idSD;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public UnidadeGestora getUg() {
		return ug;
	}

	public void setUg(UnidadeGestora ug) {
		this.ug = ug;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public Cidade getOrigem() {
		return origem;
	}

	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}

	public String getValores() {
		return valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

}
