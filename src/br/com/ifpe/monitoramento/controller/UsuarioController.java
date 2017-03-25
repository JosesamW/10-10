package br.com.ifpe.monitoramento.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ifpe.monitoramento.dao.CargoDao;
import br.com.ifpe.monitoramento.dao.KeyDuplicateException;
import br.com.ifpe.monitoramento.dao.UnidadeGestoraDao;
import br.com.ifpe.monitoramento.dao.UsuarioDao;
import br.com.ifpe.monitoramento.entidades.Usuario;

@Controller
public class UsuarioController {

	@RequestMapping("/formCadastro")
	public String formCadastro(Model model, String nome, String id, String codigo) {
		
		
		CargoDao dao = new CargoDao();
		model.addAttribute("listarCargoUsuario", dao.listarCargo(nome, id));
		UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
		model.addAttribute("listarUGestora", dao2.listarUG(nome, codigo));
		return "usuario/FormCadastroUsuario";
	
	}
	@RequestMapping("/cadastrarUsuario")
	public String cadastrarUsuario(@Valid Usuario user, BindingResult rs, Model model) {
		if (rs.hasFieldErrors()) {
			return "forward:formCadastro";
		}
		UsuarioDao dao = new UsuarioDao();
		try {
			dao.cadastrarUsuario(user);
			model.addAttribute("msgSucesso", "Usuario cadastado com sucesso!! ! ");
			return "sucesso/sucesso";
		} catch (KeyDuplicateException e) {
			model.addAttribute("msgErrorPkUser", "Cpf ou Email ja existe ! ! !");
			return "usuario/FormCadastroUsuario";
		}

	}

	@RequestMapping("/listarUsuario")
	public String exibirIncluirCargo(Usuario user, Model model, String nomeUser,String cpfUser) {
		UsuarioDao dao = new UsuarioDao();
		model.addAttribute("ListarUsuario", dao.listarUsuario(nomeUser,cpfUser));
		return "usuario/ListarUsuario";
	}

	@RequestMapping("/exibirUsuario")
	public String exibirUsuario(Integer idUser, String id, String nome, String codigo, Model model) {
		UsuarioDao dao0 = new UsuarioDao();
		model.addAttribute("exibirUsuario", dao0.exibir(idUser));
		CargoDao dao = new CargoDao();
		model.addAttribute("listarCargoUsuario", dao.listarCargo(nome, id));
		UnidadeGestoraDao dao2 = new UnidadeGestoraDao();
		model.addAttribute("listarUGestora", dao2.listarUG(nome, codigo));
		return "usuario/ExibirAlterarUsuario";
	}

	@RequestMapping("/AlterarUsuario")
	public String alterarUsuario(@Valid Usuario user, BindingResult rs, Model model) {
		if (rs.hasFieldErrors("nome") || rs.hasFieldErrors("endereco") || rs.hasFieldErrors("dataNascimento")) {
			return "forward:exibirUsuario";
		}
		UsuarioDao dao = new UsuarioDao();
		dao.alterarUsuario(user);
		dao.historicoAlteracaoUsuario(user);
		model.addAttribute("msgSucesso", "Usuario alterado com sucesso ! ! ");
		return "sucesso/sucesso";
	}

	@RequestMapping("/efetuarLogin")
	public String efetuarLogin(Usuario user, HttpSession session, Model model) {
		UsuarioDao dao = new UsuarioDao();
		Usuario login = dao.login(user);
		if (login != null && login.getSituacao().name()=="ATIVO") {
			session.setAttribute("usuarioLogado", login);
			return "index";
		}
		model.addAttribute("failLogon", "Cpf ou senha nao existe.");
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
