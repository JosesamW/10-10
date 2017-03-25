package br.com.ifpe.monitoramento.entidades;

import org.springframework.core.convert.converter.Converter;

import br.com.ifpe.monitoramento.dao.CidadeDao;

public class CidadeConverter implements Converter<String, Cidade> {
	public Cidade convert(String idCidade) {
		CidadeDao dao = new CidadeDao();
		return dao.buscarCidade(Integer.valueOf(idCidade));
	}

}
