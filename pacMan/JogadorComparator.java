package pacMan;

import java.util.Comparator;

public class JogadorComparator implements Comparator<Jogador>{

	@Override
	public int compare(Jogador j1, Jogador j2) {
		int resultado = 0;
		
		resultado = j2.getPontos() - j1.getPontos();
		
		if(j2.getPontos() == j1.getPontos()) {
			
			if(j1.getNome().equals("------")) {
				
				resultado = 1;
			}
			else if(j2.getNome().equals("------")) {
				
				resultado = -1;
			}
			else {
				
				resultado = j1.getNome().compareTo(j2.getNome());
			}
			
		}
		
		return resultado;
	}

}
