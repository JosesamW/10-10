package br.com.ifpe.monitoramento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.monitoramento.dao.HistoricoDao;
import br.com.ifpe.monitoramento.dao.UnidadeGestoraDao;

@Controller
public class MenuController {

	@RequestMapping("/FormLogin")
	public String formLogin() {
		return "login";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/listarHistorico")
	public String listarHistorico(String cpfU, String nomeU, String datah, String dataH, String objetoAlterado,
			Integer uGestoraU, String nome, String codigo, Model model) {
		HistoricoDao dao = new HistoricoDao();
		model.addAttribute("historico", dao.listarHistorico(cpfU, nomeU, datah, dataH, objetoAlterado, uGestoraU));
		UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
		model.addAttribute("listarUGestora", dao2.listarUG(nome, codigo));
		return "historico/listarHistorico";
	}
}
