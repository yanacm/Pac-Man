package pacMan;

public class Jogador {
	
	private String nome;
	private int pontos;
	private int posicaoRanking;
	
	public Jogador() {
		this.posicaoRanking = 1;
	}
	
	public Jogador(String nome, int pontos) {
		this.nome = nome;
		this.pontos = pontos;
		this.posicaoRanking = 5; 
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getPosicaoRanking() {
		return posicaoRanking;
	}

	public void setPosicaoRanking(int posicaoRanking) {
		this.posicaoRanking = posicaoRanking;
	}
}
