package pacMan;

//telas de digitar o nome e do ranking
//criarFile (184) mostra o txt com º_ e vazio qnd n tem nada na posição ainda

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import pacMan.CenarioPadrao;
import pacMan.Util;

public class FimCenario extends CenarioPadrao{
	
	private Path fileRanking;
	private Ranking ranking;
	private Jogador jogador;
	
	public FimCenario(int largura, int altura) {
		super(largura, altura);
	}

	@Override
	public void carregar() {
		
		this.ranking = new Ranking();
		
		if(this.jogador != null) {

			atualizar();
		}
		
	}

	@Override
	public void descarregar() {
		this.jogador = null;
		this.ranking = null;
		
	}

	@Override
	public void atualizar() {
		fileRanking = Path.of("C:\\leviYana\\RankingPacman.txt");
		
		if(Files.notExists(fileRanking)) {
			
			try {
				
				Files.createFile(fileRanking);
				criarFile();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			atualizarRankingAtual();
		}
		
	}

	@Override
	public void desenhar(Graphics2D g) {
		
		if (this.jogador == null) {
			FontMetrics metrics = g.getFontMetrics(g.getFont());

			String textoFimDeJogo = "FIM DE JOGO!";
			String textoNome = "Digite seu nome";
			
			g.setColor(Color.WHITE);
			g.drawString(textoNome, this.largura / 2 - metrics.stringWidth(textoNome) / 2, this.altura / 3);
			
			g.setColor(Color.WHITE);
			g.drawString(textoFimDeJogo, this.largura / 2 - metrics.stringWidth(textoFimDeJogo) / 2, this.altura / 4);
			
		} else {
			desenhaRanking(g);
		}
		
	}
	
	public void desenhaRanking(Graphics2D g) {
		
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		
		int rectX = 25;
		int lagX = largura - 350;
		
		String texto = "RANKING";
		
		g.setColor(Color.WHITE);
		g.drawString(texto, this.largura / 2 - metrics.stringWidth(texto) / 2, this.altura / 7);
		
		texto = "Levi e Yana  Pac Man";
		
		g.setColor(Color.YELLOW);
		g.drawString(texto, this.largura / 2 - metrics.stringWidth(texto) / 2, this.altura / 15);
		
		texto = "COLOCACAO";
		
		g.setColor(Color.WHITE);
		g.drawString(texto, this.largura / 6 - metrics.stringWidth(texto) / 2, this.altura / 4);
		
		texto = "JOGADOR";
		
		g.setColor(Color.WHITE);
		g.drawString(texto, this.largura / 2 - metrics.stringWidth(texto) / 2, this.altura / 4);
		
		texto = "PONTOS";
		
		g.setColor(Color.WHITE);
		g.drawString(texto, this.largura / 2 + metrics.stringWidth(texto) + 20, this.altura / 4);
		
		desenhaRank(g, rectX, lagX, metrics);
		desenhaNome(g, rectX + 120, lagX + 50, metrics);
		desenhaPontos(g, rectX + 285, lagX, metrics);
	}
	
	public void desenhaRank(Graphics2D g, int rectX, int lagX, FontMetrics metrics) {
		
		int esp = 30;
		
		for(int i = 0; i < ranking.getListaRanking().size(); i++) {
						
			String pos = Integer.toString(i + 1);
			
			int x = rectX + (lagX - metrics.stringWidth(pos)) / 2;
			
			g.setColor(Color.YELLOW);
			
			g.drawString(pos, x, this.altura / 4 + esp);
			
			esp += 30;
		}
		
	}
	
	public void desenhaNome(Graphics2D g, int rectX, int lagX, FontMetrics metrics) {
		int esp = 30;
		
		for(int i = 0; i < ranking.getListaRanking().size(); i++) {
			
			String nome = ranking.getListaRanking().get(i).getNome();
			
			int x = rectX + (lagX - metrics.stringWidth(nome)) / 2;
			
			g.setColor(Color.YELLOW);
			
			g.drawString(nome.toUpperCase(), x, this.altura / 4 + esp);
			
			esp += 30;
		}
	}
	
	public void desenhaPontos(Graphics2D g, int rectX, int lagX, FontMetrics metrics) {
		
		int esp = 30;
		
		for(int i = 0; i < ranking.getListaRanking().size(); i++) {
			
			String pts = Integer.toString(ranking.getListaRanking().get(i).getPontos());
			
			int x = rectX + (lagX - metrics.stringWidth(pts)) / 2;
			
			g.setColor(Color.YELLOW);
			
			g.drawString(pts, x, this.altura / 4 + esp);
			
			esp += 30;
		}
	}
	
	public void criarFile() {
		List<String> listaDados = new ArrayList<>();
		
		for(int i = 0; i < ranking.getNumDeColocacoes(); i++) {
			
			
			String jPos = Integer.toString(i + 1);
			String jPontos = "0000";
			String jNome = "vazio";
			
			String jListaDados = jPos + "º_" + jNome + "_" + jPontos + "_";
			
			listaDados.add(jListaDados);
			
		}

		try {
			Files.write(Path.of("C:\\leviYana\\RankingPacman.txt"), listaDados , Charset.defaultCharset());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		atualizarRankingAtual();
	}
	
	public void atualizarFile() {
		List<String> listaDados = new ArrayList<>();
		
		for(int i = 0; i < ranking.getListaRanking().size(); i++) {
			
			Jogador jRanking = ranking.getListaRanking().get(i);
			String jPos = Integer.toString(jRanking.getPosicaoRanking());
			String jPontos = Integer.toString(jRanking.getPontos());
			String jNome = jRanking.getNome();
			
			String jListaDados = jPos + "º_" + jNome + "_" + jPontos + "_";
			
			listaDados.add(jListaDados);
			
		}

		try {
			Files.write(Path.of("C:\\leviYana\\RankingPacman.txt"), listaDados , Charset.defaultCharset());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarRankingAtual() {
		List<String> texto;
		
		try {
			texto = Files.readAllLines(fileRanking, StandardCharsets.ISO_8859_1);
			
			
			for(int i = 0; i < texto.size();i++) {
				
				String[] linha = texto.get(i).split("_");
				int colocacao = Integer.parseInt(linha[0].replace("º",""));
				String nome = linha[1];
				int pontos = Integer.parseInt(linha[2]);
				
				Jogador j = new Jogador(nome, pontos);
				j.setPosicaoRanking(colocacao);
				ranking.carregarJogador(j);
			}
			ranking.carregarJogador(this.jogador);
			ranking.ordenarRanking();
			atualizarFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

}
