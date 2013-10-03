package unb.mds.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;


import unb.mds.controller.ContatoController;

public class AdicionarContato extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6214015642047409415L;
	//Variaveis para os componentes
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnLimpar;

	private JLabel lblNome;
	private JLabel lblEmail;
	private JLabel lblTelefone;
	private JLabel lblEndereco;
	private JTextField textNome;
	private JTextField textTelefone;
	private JTextField textEmail;
	private JTextField textEndereco;
	private JLabel lblId;
	private JTextField textId;

	//Construtor chamando metodos que iniciam componentes
	public AdicionarContato() {

		setTitle("Cadastrar");

		inicializaComponentes();
		adicionaComponentes();

		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	//Inicialização de componentes como botões, caixas de textos,etc
	public void inicializaComponentes() {

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(10, 201, 104, 23);
		btnSalvar.addActionListener(new SalvarContatoListener());
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(148, 201, 111, 23);
		btnCancelar.addActionListener(new CancelarListener());
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(288, 201, 104, 23);
		btnLimpar.addActionListener(new LimparListener());
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 58, 46, 14);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 114, 46, 14);

		lblEndereco = new JLabel("Endereco");
		lblEndereco.setBounds(10, 148, 70, 14);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 89, 70, 14);
		
		lblId = new JLabel("Id");
		lblId.setBounds(10, 27, 46, 14);

		textNome = new JTextField();
		textNome.setBounds(79, 55, 164, 20);
		textNome.setColumns(10);

		textTelefone = new JTextField();
		textTelefone.setBounds(79, 86, 164, 20);
		textTelefone.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(79, 114, 164, 20);
		textEmail.setColumns(10);

		textEndereco = new JTextField();
		textEndereco.setBounds(79, 145, 290, 20);
		textEndereco.setColumns(10);
		
		textId = new JTextField();
		textId.setBounds(79, 24, 164, 20);
		textId.setColumns(10);
	}

	//Adicionando componentes ao JPanel
	private void adicionaComponentes() {
		JPanel panel = new JPanel();

		setBounds(100, 100, 445, 264);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);

		panel.add(btnSalvar);
		panel.add(btnCancelar);
		panel.add(btnLimpar);
		panel.add(lblEmail);
		panel.add(lblNome);
		panel.add(lblTelefone);
		panel.add(lblEndereco);
		panel.add(textNome);
		panel.add(textTelefone);
		panel.add(textEmail);
		panel.add(textEndereco);
		panel.add(lblId);
		panel.add(textId);
		
		
		getContentPane().add(panel);
		
		
	}
	
	//Classe responsável pelo comportamento de salvar um contato
	private class SalvarContatoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try{
			//É necessário converter o id, pois a caixa de texto trabalha apenas com strings
			int id = Integer.parseInt(textId.getText());
			
			//Chamando Controladora passando as informações necessárias para o cadastro
			ContatoController.getInstance().adicionarContato(id, 
					textNome.getText(), 
					textTelefone.getText(), 
					textEmail.getText(), 
					textEndereco.getText());
			
			//Mostrando mensagem de sucesso e voltando a tela anterior
			mostrarMensagem("Usuário cadastrado com sucesso");
			
			TelaInicial frame =  new TelaInicial();
			frame.setVisible(true);
			dispose();
			}catch(NumberFormatException e2){
				mostrarMensagem("O campo Id deve ser um numero inteiro");
			}
			
		}
	}
	//Classe responsável pelo compotamento do botão cancelar
	private class CancelarListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TelaInicial frame = new TelaInicial();
			frame.setVisible(true);
			dispose();
		}
	}
	
	//Classe responsável pelo comportamento de limpar os campos
	private class LimparListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			textId.setText(" ");
			textNome.setText(" ");
			textTelefone.setText(" ");
			textEmail.setText(" ");
			textEndereco.setText(" ");
		}
	}
	
	//Método que mostra mensagem dada uma string
	private void mostrarMensagem(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
