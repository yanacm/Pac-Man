package pacMan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ranking {
	private Jogador jogador;
	private List<Jogador> listaRanking = new ArrayList<>();
	private int numDeColocacoes = 10;
	
	public Ranking() {
		
	}
	
	public void carregarJogador(Jogador jogador) {
		this.jogador = jogador;
		
		this.listaRanking.add(getJogador());
		
	}
	
	public void ordenarRanking() {
		
		Collections.sort(listaRanking, new JogadorComparator()); //collection tem o método sort
		//sort para ordenação do ranking
	
		for(int i = 1; i <= listaRanking.size(); i++) {
			
			if(i > numDeColocacoes) {
				listaRanking.remove(i-1);
			}
			else {
				listaRanking.get(i-1).setPosicaoRanking(i);
			}
			
		}
		
	}
	

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public List<Jogador> getListaRanking() {
		return listaRanking;
	}

	public void setListaRanking(List<Jogador> listaRanking) {
		this.listaRanking = listaRanking;
	}

	public int getNumDeColocacoes() {
		return numDeColocacoes;
	}

	public void setNumDeColocacoes(int numDeColocacoes) {
		this.numDeColocacoes = numDeColocacoes;
	}
}
