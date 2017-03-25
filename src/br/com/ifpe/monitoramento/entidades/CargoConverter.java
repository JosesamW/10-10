package br.com.ifpe.monitoramento.entidades;

import org.springframework.core.convert.converter.Converter;

import br.com.ifpe.monitoramento.dao.CargoDao;

public class CargoConverter implements Converter<String, Cargo> {
	public Cargo convert(String id){
		CargoDao dao = new CargoDao();
		return dao.exibirCargo(Integer.valueOf(id));
	}

}
