package br.com.ifpe.monitoramento.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.monitoramento.dao.ChaveduplicadaException;
import br.com.ifpe.monitoramento.dao.UnidadeGestoraDao;
import br.com.ifpe.monitoramento.entidades.UnidadeGestora;

@Controller
public class UnidadeGestoraController {

	@RequestMapping("/FormCadastroUG")
	public String formCadastroUG() {
		return "UG/FormCadastroUG";
	}

	@RequestMapping("/CadastroUG")
	public String cadastroUG(@Valid UnidadeGestora ug, BindingResult result, Model model) {

		if (result.hasFieldErrors()) {
			return "forward:FormCadastroUG";
		}
		UnidadeGestoraDao dao = new UnidadeGestoraDao();
		try {
			dao.cadastrarUG(ug);
			model.addAttribute("msgSucesso", "UG cadastrada com sucesso ! ! !");
			return "sucesso/sucesso";
		} catch (ChaveduplicadaException e) {
			model.addAttribute("msgErrorPkUG", "O codigo ja existe ! ");
			return "UG/FormCadastroUG";
		}
	}

	@RequestMapping("/ListarUG")
	public String listarUG(Model model, String nome, String codigo) {
		UnidadeGestoraDao dao = new UnidadeGestoraDao();
		model.addAttribute("ListarUG", dao.listarUG(nome, codigo));
		return "UG/ListarUG";

	}

	@RequestMapping("/ExibirAlterarUG")
	public String exibirAlterarUG(int codigo, Model model) {
		UnidadeGestoraDao dao = new UnidadeGestoraDao();
		model.addAttribute("ExibirAlterarUG", dao.exibirUG(codigo));
		return "UG/ExibirAlterarUG";
	}

	@RequestMapping("/AlterarUG")
	public String alterarUG(@Valid UnidadeGestora ug, BindingResult result, Model model) {

		if (result.hasFieldErrors()) {
			return "forward:ExibirAlterarUG";
		}

		UnidadeGestoraDao dao = new UnidadeGestoraDao();
		dao.alterarUG(ug);
		dao.historicoalteracaoUG(ug);
		model.addAttribute("msgSucesso", " UG alterada com sucesso ! ! !");
		return "sucesso/sucesso";
	}

}
