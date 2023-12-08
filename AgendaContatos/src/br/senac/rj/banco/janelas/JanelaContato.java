package br.senac.rj.banco.janelas;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import br.senac.rj.banco.modelo.Contato;

public class JanelaContato {
	public static JFrame criarJanelaConta() {
		
		// Define a janela
		JFrame janelaContatos = new JFrame("Atualização de contatos"); // Janela Normal
		janelaContatos.setResizable(false); // A janela não poderá ter o tamanho ajustado
		janelaContatos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janelaContatos.setSize(500, 380); // Define tamanho da janela
		
		// Define o layout da janela
		Container caixa = janelaContatos.getContentPane();
		caixa.setLayout(null);
		
		// Define os labels dos campos
		JLabel labelId = new JLabel("ID: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelEmail = new JLabel("E-mail: ");
		JLabel labelTelefone = new JLabel("Telefone: ");
		
		// Posiciona os labels na janela
		labelId.setBounds(100, 40, 100, 20); // coluna, linha, largura, tamanho
		labelNome.setBounds(100, 80, 150, 20); // coluna, linha, largura, tamanho
		labelEmail.setBounds(100, 120, 100, 20); // coluna, linha, largura, tamanho
		labelTelefone.setBounds(100, 160, 100, 20); // coluna, linha, largura, tamanho
		
		// Define os input box
		JTextField jTextId = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextEmail = new JTextField();
		JTextField jTextTelefone = new JTextField();
		
		// Define se os campos estão habilitados ou não no início
		jTextId.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextEmail.setEnabled(false);
		jTextTelefone.setEnabled(false);
		
		// Posiciona os input box
		jTextId.setBounds(180, 40, 50, 20);
		jTextNome.setBounds(180, 80, 150, 20);
		jTextEmail.setBounds(180, 120, 150, 20);
		jTextTelefone.setBounds(180, 160, 150, 20);
		
		// Adiciona os rótulos e os input box na janela
		janelaContatos.add(labelId);
		janelaContatos.add(labelNome);
		janelaContatos.add(labelEmail);
		janelaContatos.add(labelTelefone);
		janelaContatos.add(jTextId);
		janelaContatos.add(jTextNome);
		janelaContatos.add(jTextEmail);
		janelaContatos.add(jTextTelefone);
		
		// Define botões e a localização deles na janela
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(230, 40, 100, 20);
		janelaContatos.add(botaoConsultar);
		
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(100, 200, 80, 20);
		botaoGravar.setEnabled(false);
		janelaContatos.add(botaoGravar);
		
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(200, 200, 80, 20);
		botaoDeletar.setEnabled(false);		
		janelaContatos.add(botaoDeletar);
		
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(300, 200, 80, 20);
		janelaContatos.add(botaoLimpar);
		
		// Define tabela e a localização dela na janela
	    DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Email");
        tableModel.addColumn("Telefone");
        
        JTable tabela = new JTable(tableModel);
        tabela.setEnabled(false);
        
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 250, 450, 75);
        scrollPane.setVisible(false);
        
		// Define objeto contato para pesquisar no banco de dados
		Contato contato = new Contato();
	
		// Define ações dos botões
		botaoConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int resposta = JOptionPane.showConfirmDialog(janelaContatos, "Deseja consultar o contato?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		    	
		        try {
		            int id = Integer.parseInt(jTextId.getText());		            
		            String nome = "";
		            String email = "";
		            String telefone = "";

		            if (contato.consultarContato(id)) {
		                nome = contato.getNome();
		                email = contato.getEmail();
		                telefone = contato.getTelefone();
		                
		                while (tableModel.getRowCount() > 0) {
		                    tableModel.removeRow(0);
		                }
		                
		                tableModel.addRow(new Object[]{id, nome, email, telefone});
		                scrollPane.setVisible(true);
		                janelaContatos.add(scrollPane);
		                
		                botaoGravar.setEnabled(true);
		                botaoDeletar.setEnabled(true); // Se a conta for encontrada, habilita o botão Deletar
		            } else {
		            	botaoGravar.setEnabled(true);
		                botaoDeletar.setEnabled(false); // Se a conta não for encontrada, desabilita o botão Deletar
		            }

		            jTextNome.setText(nome);
		            jTextEmail.setText(email);
		            jTextTelefone.setText(telefone);
		            jTextId.setEnabled(false);
		            jTextNome.setEnabled(true);
		            jTextEmail.setEnabled(true);
		            jTextTelefone.setEnabled(true);
		            botaoConsultar.setEnabled(false);
		         
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janelaContatos, "Preencha o campo ID do contato corretamente!!");
		        	}
		        }
		    }
		});
		
		botaoGravar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int resposta = JOptionPane.showConfirmDialog(janelaContatos, "Deseja Atualizar o contato?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		            int id = Integer.parseInt(jTextId.getText());
		            String nome = jTextNome.getText();
		            String email = jTextEmail.getText();
		            String telefone = jTextTelefone.getText();

		            // Validar os campos, se necessário
		            // Exemplo:
		            if (nome.length() == 0 || email.length() == 0 || telefone.length() == 0) {
		                JOptionPane.showMessageDialog(janelaContatos, "Preencha todos os campos!");
		                // Aqui você pode focar no primeiro campo em branco para facilitar a correção
		                jTextNome.requestFocus();
		            } else {
		                // Verificar se o contato já existe
		                if (!contato.consultarContato(id)) {
		                    // Se não existe, cadastrar um novo contato
		                    if (!contato.cadastrarContato(id, nome, email, telefone))
		                        JOptionPane.showMessageDialog(janelaContatos, "Erro na inclusão do contato!");
		                    else
		                        JOptionPane.showMessageDialog(janelaContatos, "Contato incluído com sucesso!");
		                    jTextId.setText("");
			                jTextNome.setText("");
			                jTextEmail.setText("");
			                jTextTelefone.setText("");
			                botaoConsultar.setEnabled(true);
			                botaoGravar.setEnabled(false);			                
			                jTextId.setEnabled(true);
		                } else {
		                    // Se existe, atualizar os detalhes do contato
		                	
			                if (!contato.atualizarContato(id, nome, email, telefone)) 
			                        JOptionPane.showMessageDialog(janelaContatos, "Erro na atualização do contato!");
			                     else
			                    	 
			                        JOptionPane.showMessageDialog(janelaContatos, "Contato atualizado com sucesso!"); 
			                   // jTextId.setText("");
				                jTextNome.setText(nome);
				                jTextEmail.setText(email);
				                jTextTelefone.setText(telefone);
				                botaoConsultar.setEnabled(true);
				                botaoGravar.setEnabled(true);
				                botaoDeletar.setEnabled(true);
				                jTextId.setEnabled(false);
				                tableModel.setRowCount(0);
				                tableModel.addRow(new Object[]{id, nome, email, telefone});
					        
		                }
		            }
		        }
		    }
		});

		botaoDeletar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int resposta = JOptionPane.showConfirmDialog(janelaContatos, "Deseja deletar o contato?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
		        if (resposta == JOptionPane.YES_OPTION) {
		        	int id = Integer.parseInt(jTextId.getText());

		            // Chama o método para deletar a conta
		            if (contato.deletarContato(id)) {
		            	
		            	scrollPane.setVisible(false);
		            	janelaContatos.remove(scrollPane);
		            	janelaContatos.revalidate();
		            	janelaContatos.repaint();
		                
		                JOptionPane.showMessageDialog(janelaContatos, "Contato deletado com sucesso!");
		                // Limpa os campos após a exclusão, se necessário
		                jTextId.setText("");
		                jTextNome.setText("");
		                jTextEmail.setText("");
		                jTextTelefone.setText("");
		                botaoConsultar.setEnabled(true);
		                botaoGravar.setEnabled(false);
		                botaoDeletar.setEnabled(false);
		                jTextId.setEnabled(true);
		            } else {
		                JOptionPane.showMessageDialog(janelaContatos, "Erro ao deletar o contato!");
		            }
		        }
		    }
		}); 
		 
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(janelaContatos, "Deseja limpar os dados?", "Confirmação",
		                JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
				jTextId.setText(""); // Limpar campo
				jTextNome.setText(""); // Limpar campo
				jTextEmail.setText(""); // Limpar campo
				jTextTelefone.setText(""); // Limpar campo
				jTextId.setEnabled(true);
				jTextNome.setEnabled(true);
				jTextEmail.setEnabled(true);
				jTextTelefone.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				botaoDeletar.setEnabled(false);				
				jTextId.requestFocus(); // Colocar o foco em um campo
				
				scrollPane.setVisible(false);
				janelaContatos.remove(scrollPane);
				janelaContatos.revalidate();
				janelaContatos.repaint();
				}
			}
		}); 
		return janelaContatos;
	}
}
