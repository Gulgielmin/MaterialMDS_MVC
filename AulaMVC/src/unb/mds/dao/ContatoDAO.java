package unb.mds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import unb.mds.model.Contato;

public class ContatoDAO {

	private final static String INSERT_CONTATO = "INSERT INTO contato (nome,telefone,email,endereco) VALUES (?,?,?,?)";
	private final static String DELETE_CONTATO = "DELETE FROM contato WHERE id = ?";
	private final static String UPDATE_CONTATO = "UPDATE contato SET nome = ?, telefone = ?, email = ?, endereco = ? WHERE id = ?";
	private final static String GET_ALL_CONTATOS = "SELECT * FROM contato";
	private final static String GET_CONTATO_BY_NAME = "SELECT * FROM contato WHERE nome = ?";
	private final static String GET_CONTATO_BY_EMAIL = "SELECT * FROM contato WHERE email = ?";

	private Connection conexao;
	private static ContatoDAO instance;

	public ContatoDAO() {

		this.conexao = new ConnectionFactory().getConnection();

	}
	
	//Usando padrão singleton para evitar multiplas instancias de uma classe DAO
	public static ContatoDAO getInstance(){
		if(instance == null)
			instance = new ContatoDAO(); 
			return instance;
		}
	

	// Metodo para adicionar um contato no BD
	public void insere(Contato contato) {

		// Criando um statement
		try {
			PreparedStatement stmt = conexao.prepareStatement(INSERT_CONTATO);

			// Adicionando valores ao statement
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());
			stmt.setString(4, contato.getEndereco());

			// Executando o statement
			stmt.execute();

			// Fechando o statement
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Metodo para alterar um contato no BD
	public void alterar(Contato contato) {

		// Criando um statement
		try {
			PreparedStatement stmt = conexao.prepareStatement(UPDATE_CONTATO);

			// Adicionando valores ao statement
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setString(3, contato.getEmail());
			stmt.setString(4, contato.getEndereco());
			stmt.setInt(5, contato.getId());

			// Executando o statement
			stmt.execute();

			// Fechando o statement
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Metodo para deletar um contato no BD
	public void remover(Contato contato) {

		// Criando um statement
		try {
			PreparedStatement stmt = conexao.prepareStatement(DELETE_CONTATO);

			// Adicionando valores ao statement
			stmt.setInt(1, contato.getId());

			// Executando o statement
			stmt.execute();

			// Fechando o statement
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Contato> listarContatos() {
		List<Contato> contatos = new ArrayList<Contato>();

		try {
			// Criando um statement e um set de resultados
			PreparedStatement stmt = conexao.prepareStatement(GET_ALL_CONTATOS);
			ResultSet rs = stmt.executeQuery();

			// Buscando no banco de dados e adicionando a lista
			while (rs.next()) {

				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				contatos.add(contato);
			}

			//fechando acesso ao banco
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contatos;
	}
	
	public List<Contato> buscarContatosPorNome(String nome) {
		List<Contato> contatos = new ArrayList<Contato>();

		try {
			// Criando um statement e um set de resultados
			PreparedStatement stmt = conexao.prepareStatement(GET_CONTATO_BY_NAME);
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();

			// Buscando no banco de dados e adicionando a lista
			while (rs.next()) {

				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				contatos.add(contato);
			}

			//fechando acesso ao banco
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contatos;
	}
	
	public List<Contato> buscarContatosPorEmail(String email) {
		List<Contato> contatos = new ArrayList<Contato>();

		try {
			// Criando um statement e um set de resultados
			PreparedStatement stmt = conexao.prepareStatement(GET_CONTATO_BY_EMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			// Buscando no banco de dados e adicionando a lista
			while (rs.next()) {

				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				contatos.add(contato);
			}
			
			//fechando acesso ao banco
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contatos;
	}
}
