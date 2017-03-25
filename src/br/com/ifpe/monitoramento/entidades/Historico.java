package br.com.ifpe.monitoramento.entidades;

import java.util.Date;

public class Historico {

	private int idAuto;
	private Usuario IdUsuarioAutor;
	private String Campo;
	private Date dataAlteracao;

	public int getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(int idAuto) {
		this.idAuto = idAuto;
	}

	public Usuario getIdUsuarioAutor() {
		return IdUsuarioAutor;
	}

	public void setIdUsuarioAutor(Usuario idUsuarioAutor) {
		IdUsuarioAutor = idUsuarioAutor;
	}

	public String getCampo() {
		return Campo;
	}

	public void setCampo(String campo) {
		Campo = campo;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

}
