package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import model.Dado;
import model.DadoTableModel;
import controller.ControleDados;
import excessao.CpfInvalidoException;
import excessao.CpfNaoEncontrado;
import excessao.NomeInvalidoException;

public class TelaCPF {

	private JFrame frmCadastroDeCpf;
	private JFrame janelaRelatorio;
	private JTextField NomeField;
	private JTextField cpfField;
	private JTextField nomeRelatorioField;
	private JTable jtableDado;
	private DadoTableModel dadoTable;
	
	private ControleDados control = new ControleDados();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCPF window = new TelaCPF();
					window.frmCadastroDeCpf.setVisible(true);
					window.frmCadastroDeCpf.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCPF() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeCpf = new JFrame();
		frmCadastroDeCpf.setTitle("CADASTRO DE CPF");
		frmCadastroDeCpf.setBounds(100, 100, 388, 248);
		frmCadastroDeCpf.setIconImage(new ImageIcon("Imagem/icone.jpg").getImage());
		frmCadastroDeCpf.setLocationRelativeTo(null);
		frmCadastroDeCpf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		
		JPanel panel = new JPanel();
		frmCadastroDeCpf.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("NOME :");
		lblNome.setBounds(30, 44, 55, 14);
		panel.add(lblNome);
		
		NomeField = new JTextField();
		NomeField.setBounds(95, 41, 227, 20);
		panel.add(NomeField);
		NomeField.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF :");
		lblCpf.setBounds(30, 88, 55, 14);
		panel.add(lblCpf);
		
		cpfField = new JTextField();
		cpfField.setBounds(95, 85, 227, 20);
		panel.add(cpfField);
		cpfField.setColumns(10);
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.setBounds(69, 149, 90, 23);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dado  d = new Dado();
				d.setNome(NomeField.getText());
				d.setCpf(cpfField.getText());
				try {
					control.insere(d);
					control.AtualizarArvore();
					JOptionPane.showMessageDialog(null,"Cadastrado com sucesso","Mensagem", JOptionPane.INFORMATION_MESSAGE);
				}catch (NomeInvalidoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Mensagem", JOptionPane.INFORMATION_MESSAGE); 		
				}catch (CpfInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		panel.add(btnSalvar);
		
		JButton btnSair = new JButton("SAIR");
		btnSair.setBounds(233, 149, 89, 23);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);			
			}
		});
		panel.add(btnSair);
		
		JMenuBar menuBar = new JMenuBar();
		frmCadastroDeCpf.setJMenuBar(menuBar);
		
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				janelaRelatorio = new JFrame("Relatório");
				JPanel panelRelatorioNorte = new JPanel();
				JPanel panelRelatorioCentro = new JPanel();
				JPanel panelRelatorioSul = new JPanel();
				nomeRelatorioField = new JTextField(30);
				JLabel nomeRelatorioLabel = new JLabel("Informe CPF a pesquisar");
				
				dadoTable = new DadoTableModel(ControleDados.list);
				jtableDado = new JTable(dadoTable);
				JScrollPane jscrollPane = new JScrollPane(jtableDado);
				jscrollPane.setPreferredSize(new Dimension(300,200));
				
				JButton btCancelar = new JButton("Cancelar");
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						janelaRelatorio.dispose();
					}
				});
				
				JButton btpesquisar = new JButton("Pesquisar");
				btpesquisar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dado dado = new Dado();
						dado.setCpf(nomeRelatorioField.getText());
						System.out.println(control.formatarString(dado.getCpf()));
								try {
										if(control.validarCPF(dado.getCpf())){
											try {
												dado = (Dado) control.procurarDado(dado);
												dadoTable.clearTable();
												dadoTable.addRow(dado);	
											} catch (CpfNaoEncontrado e1) {
												JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
											}

										}else
											JOptionPane.showMessageDialog(null, "Valor do cpf invalido", "Mensagem", JOptionPane.INFORMATION_MESSAGE);	
								}catch (CpfInvalidoException e1) {
									JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);	
								}
							
									
														}	
						
				});
				
				panelRelatorioNorte.add(nomeRelatorioLabel);
				panelRelatorioNorte.add(nomeRelatorioField);
				panelRelatorioCentro.add(jscrollPane);
				panelRelatorioSul.add(btpesquisar);
				panelRelatorioSul.add(btCancelar);
				
				janelaRelatorio.getContentPane().add(panelRelatorioNorte, BorderLayout.NORTH);
				janelaRelatorio.getContentPane().add(panelRelatorioSul, BorderLayout.SOUTH);
				janelaRelatorio.getContentPane().add(panelRelatorioCentro, BorderLayout.CENTER);
				
				janelaRelatorio.setIconImage(new ImageIcon("Imagem/icone.jpg").getImage());
				janelaRelatorio.setResizable(false);
				janelaRelatorio.setSize(500, 300);
				janelaRelatorio.setLocationRelativeTo(null);
				janelaRelatorio.setVisible(true);
			}
		});
		mnOpes.add(mntmBuscar);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {

				String numeroCpf; 
				UIManager.put("OptionPane.cancelButtonText", "Cancelar");
				UIManager.put("OptionPane.okButtonText", "Sim");
				numeroCpf = (String) JOptionPane.showInputDialog(frmCadastroDeCpf, "Digite CPF a ser excluido", "Excluir CPF", 0, null, null, "");
				if(numeroCpf!= null){
					Dado d = new Dado();
					d.setCpf(numeroCpf);
					try {
						control.remover(d);
						control.AtualizarArvore();
						JOptionPane.showMessageDialog(null,"Removido com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					} catch (CpfNaoEncontrado e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Mensagem", JOptionPane.INFORMATION_MESSAGE);
					} catch (CpfInvalidoException e1){
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Mensagem", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}	 	
		});
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				JOptionPane.showMessageDialog(null,"José Wellington Ideão de Paiva\nKevin Mariz\nJezreel Lau\nHenderson Aryel\n\nVersão 1\nIntegradorCCP5, 2015. Todos os direitos reservados.","Creditos", JOptionPane.INFORMATION_MESSAGE);
											
			}
		});
		mnOpes.add(mntmSobre);
		mnOpes.add(mntmExcluir);
		mnOpes.add(mntmSobre);
		mnOpes.add(mntmSair);
	}
	
}
