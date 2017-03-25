package br.com.ifpe.monitoramento.entidades;

import org.springframework.core.convert.converter.Converter;

import br.com.ifpe.monitoramento.dao.UnidadeGestoraDao;

public class UnidadeGestoraConverter implements Converter<String, UnidadeGestora> {
	public UnidadeGestora convert(String codigo){
		UnidadeGestoraDao dao = new UnidadeGestoraDao();
		return  dao.exibirUG(Integer.valueOf(codigo));
	}

}
