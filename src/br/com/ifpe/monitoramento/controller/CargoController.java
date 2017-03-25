package br.com.ifpe.monitoramento.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.monitoramento.dao.CargoDao;
import br.com.ifpe.monitoramento.dao.KeyDuplicateException;
import br.com.ifpe.monitoramento.entidades.Cargo;

@Controller
public class CargoController {

	@RequestMapping("/formCadastroCargo")
	public String formCadastroCargo() {
		return "cargo/FormCadastroCargo";
	}

	@RequestMapping("/cadastroCargo")
	public String cadastroCargo(@Valid Cargo cargo, BindingResult rs, Model model) {

		if (rs.hasFieldErrors()) {
			return "forward:formCadastroCargo";
		}
		CargoDao dao = new CargoDao();
		try {
			dao.CadastrarCargo(cargo);
			model.addAttribute("msgSucesso", " Cargo inserido com sucesso ! ! !");
			return "sucesso/sucesso";
		} catch (KeyDuplicateException e) {
			model.addAttribute("msgErrorPkCargo", "Cargo ja existe");
			return "cargo/FormCadastroCargo";
		}

	}

	@RequestMapping("/listarCargo")
	public String listarCargo(Cargo cargo, Model model, String nome, String id) {
		CargoDao dao = new CargoDao();
		model.addAttribute("ListarCargo", dao.listarCargo(nome, id));
		return "cargo/ListarCargo";
	}

	@RequestMapping("/exibirCargo")
	public String exibirCargo(Integer id, Model model) {
		CargoDao dao = new CargoDao();
		model.addAttribute("ExibirAlterarCargo", dao.exibirCargo(id));
		return "cargo/ExibirAlterarCargo";
	}

	@RequestMapping("/alterarCargo")
	public String alterarCargo(@Valid Cargo cargo, BindingResult rs, Model model) {
		if (rs.hasFieldErrors()) {
			return "forward:exibirCargo";
		}
		CargoDao dao = new CargoDao();
		dao.alterarCargo(cargo);
		dao.historicoAlteracaoCargo(cargo);
		model.addAttribute("msgSucesso", "Cargo alterado com sucesso ! ! !");
		return "sucesso/sucesso";
	}
}
