package br.senac.rj.banco.teste;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.rj.banco.janelas.JanelaContato;

public class TesteSwing {

	public static void apresentarMenu() {
		
		// Define a janela
		JFrame janelaPrincipal = new JFrame("Cadastro de contatos"); // Janela Normal
		janelaPrincipal.setTitle("Agenda de Contatos");
		janelaPrincipal.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(400, 300); // Define tamanho da janela
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");

		// Cria uma barra de menu para a janela principal
		JMenuBar menuBar = new JMenuBar();
		
		// Adiciona a barra de menu ao frame
		janelaPrincipal.setJMenuBar(menuBar);
		
		// Define e adiciona menu na barra de menu
		JMenu menuAtualizar = new JMenu("Agenda");
		menuBar.add(menuAtualizar);
		
		// Cria e adiciona um item simples para o menu
		JMenuItem menuContato = new JMenuItem("Contato");
		menuAtualizar.add(menuContato);
		janelaPrincipal.getContentPane().setLayout(null);
		
		// adiciona texto na pagina principal
		JLabel lblNewLabel = new JLabel("Chamada??");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblNewLabel.setBounds(107, 40, 164, 59);
		janelaPrincipal.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel1 = new JLabel("Chamada??");
		lblNewLabel1.setFont(new Font("Roboto", Font.PLAIN, 30));
		lblNewLabel1.setBounds(107, 80, 164, 59);
		janelaPrincipal.getContentPane().add(lblNewLabel1);
		
		// Criar a janela de atualização de contatos
		JFrame janelaContato = JanelaContato.criarJanelaConta();
		
		// Adiciona ação para o item do menu
		menuContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaContato.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}
