//223 e 224 pega os pontos e o nome do jogador            212    211

package pacMan;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import pacMan.JogoCenario.Estado;
import pacMan.CenarioPadrao;

public class Jogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int FPS = 1000 / 20;
	private static final int JANELA_ALTURA = 550;
	private static final int JANELA_LARGURA = 448;
	private JPanel tela;
	private Graphics2D g2d;
	private BufferedImage buffer;
	private CenarioPadrao cenario;
	private JogoCenario jogoCenario;
	private Jogador jogador;
	private FimCenario fimCenario;
	private JTextField field = new JTextField();
	private Container contentPane;

	public enum Tecla {
		CIMA, BAIXO, ESQUERDA, DIREITA, BA, BB, BC;
	}

	public static boolean[] controleTecla = new boolean[Tecla.values().length];

	public static void liberaTeclas() {
		for (int i = 0; i < controleTecla.length; i++) {
			controleTecla[i] = false;
		}
	}

	private void setaTecla(int tecla, boolean pressionada) {
		switch (tecla) {
		case KeyEvent.VK_UP:
			controleTecla[Tecla.CIMA.ordinal()] = pressionada;
			break;
		case KeyEvent.VK_DOWN:
			controleTecla[Tecla.BAIXO.ordinal()] = pressionada;
			break;
		case KeyEvent.VK_LEFT:
			controleTecla[Tecla.ESQUERDA.ordinal()] = pressionada;
			break;
		case KeyEvent.VK_RIGHT:
			controleTecla[Tecla.DIREITA.ordinal()] = pressionada;
			break;

		case KeyEvent.VK_ESCAPE:
			controleTecla[Tecla.BB.ordinal()] = pressionada;
			break;

		case KeyEvent.VK_SPACE:
			controleTecla[Tecla.BC.ordinal()] = pressionada;
			break;

		case KeyEvent.VK_ENTER:
			controleTecla[Tecla.BA.ordinal()] = pressionada;
		}
	}
	
	public static int nivel;
	public static boolean pausado;

	public Jogo() {
		
		this.setFocusable(true);
		this.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setaTecla(e.getKeyCode(), false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				setaTecla(e.getKeyCode(), true);
			}
	    });
		field.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setaTecla(e.getKeyCode(), false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				setaTecla(e.getKeyCode(), true);
			}
	    });

		buffer = new BufferedImage(JANELA_LARGURA, JANELA_ALTURA, BufferedImage.TYPE_INT_RGB);
		
		g2d = buffer.createGraphics();

		tela = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(buffer, 0, 0, null);
			}

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(JANELA_LARGURA, JANELA_ALTURA);
			}

			@Override
			public Dimension getMinimumSize() {
				return getPreferredSize();
			}
		};
		
		contentPane = getContentPane();
		contentPane.add(tela);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setFocusable(true);
		requestFocus();
		
		pack();

		setVisible(true);
		tela.repaint();
		
		tela.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				setaTecla(e.getKeyCode(), false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				setaTecla(e.getKeyCode(), true);
			}
			
		});

	}

	private void carregarJogo() {
		cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
		cenario.carregar();
	}

	public void iniciarJogo() {
		long prxAtualizacao = 0;

		while (true) {
			if (System.currentTimeMillis() >= prxAtualizacao) {
				

				g2d.setColor(Color.BLACK);
				g2d.fillRect(0, 0, JANELA_LARGURA, JANELA_ALTURA);

				if (controleTecla[Tecla.BA.ordinal()]) {
					// Pressionou espaço ou enter
					if (cenario instanceof InicioCenario) {
						cenario.descarregar();
						cenario = null;
						cenario = new JogoCenario(tela.getWidth(), tela.getHeight());
						
						jogoCenario = (JogoCenario) cenario;

						g2d.setColor(Color.WHITE);
						g2d.drawString("CARREGANDO. . .", 20, 20);
						tela.repaint();

						cenario.carregar();


					} else {
						if (cenario instanceof JogoCenario) {
							Jogo.pausado = !Jogo.pausado;
							
						} else {
							
							fimCenario = (FimCenario) cenario;
							
							if(fimCenario.getJogador() == null) { 
								
								jogador = new Jogador();
								
								jogador.setPontos(jogoCenario.getPontos());
								jogador.setNome(field.getText());
								
								fimCenario.setJogador(jogador);
								tela.remove(field);
								tela.repaint();
								
								cenario.atualizar();
							}
						}
						
					}

					liberaTeclas();

				} else if (controleTecla[Tecla.BB.ordinal()]) { 
					// Pressionou ESQ
					if (!(cenario instanceof InicioCenario)) {
						
						cenario.descarregar();

						cenario = null;
						cenario = new InicioCenario(tela.getWidth(), tela.getHeight());
						cenario.carregar();

					}

					liberaTeclas();

				}

				if (cenario == null) {
					g2d.setColor(Color.WHITE);
					g2d.drawString("CARREGANDO...", 20, 20);

				} else {
					if (!Jogo.pausado && !(cenario instanceof FimCenario))
						cenario.atualizar();

					cenario.desenhar(g2d);

					if (Jogo.pausado) {
						g2d.setColor(Color.YELLOW);
						g2d.drawString("PAUSADO! PRESSIONE ENTER PARA VOLTAR", tela.getWidth() / 2 - 200, tela.getHeight() / 4);
					}
				}
				
				if (cenario instanceof JogoCenario) {
					
					if(jogoCenario.getEstado() != Estado.JOGANDO) {
						
						cenario.descarregar();

						cenario = null;
						cenario = new FimCenario(tela.getWidth(), tela.getHeight());
						
						g2d.setColor(Color.BLACK);
						g2d.fillRect(0, 0, JANELA_LARGURA, JANELA_ALTURA);
						
						cenario.carregar();
						
						addTextField();
					}
				}

				tela.repaint();
				prxAtualizacao = System.currentTimeMillis() + FPS;
			}
		}
	}
	
	public void addTextField() {
		 
		//campo para pessoa colocar o nome
		
		tela.add(field);
		
		field.setText("");
		field.getParent().setLayout(new GridBagLayout());
		field.setPreferredSize(new Dimension(300, 20)); //largura e altura da text box
		field.setLocation(JANELA_LARGURA, JANELA_ALTURA);
		
		field.setFont(new Font("ARCADECLASSIC", Font.PLAIN, 22));
		field.setBackground(Color.YELLOW); //cor de fundo da text box
		field.setCaretColor(Color.BLACK); 
		field.setForeground(Color.BLACK); //cor da fonte
		field.setHorizontalAlignment(javax.swing.JTextField.CENTER);
		field.setBorder(new LineBorder(Color.YELLOW));
		
		field.repaint();
		setVisible(true);
	}
	
	
	public JogoCenario getJogoCenario() {
		return jogoCenario;
	}

	public void setJogoCenario(JogoCenario jogoCenario) {
		this.jogoCenario = jogoCenario;
	}
	
	public FimCenario getFimCenario() {
		return fimCenario;
	}

	public void setFimCenario(FimCenario fimCenario) {
		this.fimCenario = fimCenario;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public static void main(String[] args) {
		Jogo jogo = new Jogo();

		jogo.carregarJogo();
		jogo.iniciarJogo();
	}

}
