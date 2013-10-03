package unb.mds.controller;

import java.util.List;

import unb.mds.dao.ContatoDAO;
import unb.mds.model.Contato;

public class ContatoController {

	private static ContatoController instance;
	Contato contato;

	public ContatoController() {
		contato = new Contato();
	}

	//Usando o padrão singleton para evitar multiplas instancias de uma classe controladora
	public static ContatoController getInstance() {
		if (instance == null)
			instance = new ContatoController();
		return instance;
	}

	//Metodo que seta os dados na model e envia para a DAO para inserção no banco
	public void adicionarContato(int id, String nome, String telefone,
			String email, String endereco) {

		contato.setId(id);
		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		contato.setEndereco(endereco);

		ContatoDAO.getInstance().insere(contato);
	}

	//Metodo que seta os dados na model e envia para a DAO para remoção no banco
	public void excluirContato(int id) {
		contato.setId(id);

		ContatoDAO.getInstance().remover(contato);
	}

	//Metodo que seta os dados na model e envia para a DAO para alteração no banco
	public void alterarContato(int id, String nome, String telefone,
			String email, String endereco) {
		contato.setId(id);
		contato.setNome(nome);
		contato.setTelefone(telefone);
		contato.setEmail(email);
		contato.setEndereco(endereco);

		ContatoDAO.getInstance().alterar(contato);
	}

	//Método que retorna lista de contatos cadastrados no banco
	public List<Contato> listarContatos() {

		return ContatoDAO.getInstance().listarContatos();

	}

	//Metodo que retorna lista de contatos pesquisados por nome
	public List<Contato> buscarContatosPorNome(String nome) {

		return ContatoDAO.getInstance().buscarContatosPorNome(nome);

	}

	//Metodo que retorna lista de contatos pesquisados por email
	public List<Contato> buscarContatosPorEmail(String email) {

		return ContatoDAO.getInstance().buscarContatosPorEmail(email);

	}

}
