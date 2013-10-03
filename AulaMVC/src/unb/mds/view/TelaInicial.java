package unb.mds.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import unb.mds.controller.ContatoController;

public class TelaInicial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Variaveis para os componentes
	private JButton btnNovoContato;
	private JButton btnAlterarContato;
	private JButton btnRemoverContato;
	private JButton btnEncontrarNome;
	private JButton btnEncontrarTelefone;

	private ContatoTable tabela;
	private JScrollPane scrollPane;

	//Construtor chamando metodos que iniciam componentes
	public TelaInicial() {

		setTitle("Tela Inicial");

		inicializaComponentes();
		adicionaComponentes();

		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Inicialização de componentes como botões, caixas de textos,etc
	public void inicializaComponentes() {

		tabela = new ContatoTable();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabela);

		btnNovoContato = new JButton("Novo");
		btnNovoContato.setBounds(412, 11, 89, 23);
		btnNovoContato.setMnemonic(KeyEvent.VK_N);
		btnNovoContato.addActionListener(new CadastrarContatoListener());

		btnAlterarContato = new JButton("Alterar");
		btnAlterarContato.setBounds(412, 45, 89, 23);
		btnAlterarContato.setMnemonic(KeyEvent.VK_A);
		btnAlterarContato.addActionListener(new AlterarContatoListener());

		btnRemoverContato = new JButton("Remover");
		btnRemoverContato.setBounds(412, 79, 89, 23);
		btnRemoverContato.setMnemonic(KeyEvent.VK_R);
		btnRemoverContato.addActionListener(new RemoverContatoListener());

		

	}
	//Adicionando componentes ao JPanel
	private void adicionaComponentes() {
		add(scrollPane);
		JPanel panel = new JPanel();
		panel.add(btnNovoContato);
		panel.add(btnAlterarContato);
		panel.add(btnRemoverContato);
		panel.add(btnEncontrarNome);
		panel.add(btnEncontrarTelefone);

		add(panel, BorderLayout.SOUTH);
	}

	//Comportamento responsável por chamar janela de cadastro
	private class CadastrarContatoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			AdicionarContato frame = new AdicionarContato();
			frame.setVisible(true);
			dispose();
		}
	}

	//Comportamento responsável por chamar janela de alteração
	private class AlterarContatoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				
				/*Foi necessário criar um método e passar o valor do id para a busca no banco de dados
				* das informações que facilitarão o uso da nova janela
				*/
				int id = tabela.getContatoSelected().getId();
				AlterarContato frame = new AlterarContato();
				frame.setVisible(true);
				frame.preencherCampos(id);
				dispose();

			} catch (ArrayIndexOutOfBoundsException e1) {
				mostrarMensagemDeErro("Selecione um Contato para Remover");
			}catch(NullPointerException e2){
				mostrarMensagemDeErro("Selecione um Contato para Remover");
			}
			
		}
	}

	//Classe com comportamento responsável por remoção de contato
	private class RemoverContatoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				
				//É capturado o id do objeto na tabela e enviado para a controladora
				int id = tabela.getContatoSelected().getId();

				ContatoController.getInstance().excluirContato(id);
				refreshTable();

			} catch (ArrayIndexOutOfBoundsException e1) {
				mostrarMensagemDeErro("Selecione um Contato para Remover");
			}catch(NullPointerException e2){
				mostrarMensagemDeErro("Selecione um Contato para Remover");
			}
		}
	}

	//Método responsável por mostrar mensagem de erro
	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}

	//Método responsável por atualizar a tabela a cada alteração
	public void refreshTable() {
		tabela.reload();
	}

}
