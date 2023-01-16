package pacMan;

import java.awt.Font;
import java.awt.Graphics2D;

public class Texto extends Elemento {

	private Font fonte;

	public Texto() {
		//baixamos uma fonte e colocamos, mas não tinha acentos, então colocamos
		//as palavras "erradas". ex: pontuacao, colocacao
		fonte = new Font("ARCADECLASSIC", Font.PLAIN, 22);
	}

	public Texto(Font fonte) {
		this.fonte = fonte;
	}

	public void desenha(Graphics2D g, String texto) {
		desenha(g, texto, getPx(), getPy());
	}

	public void desenha(Graphics2D g, String texto, int px, int py) {
		if (getCor() != null)
			g.setColor(getCor());
		
		g.setFont(fonte);
		g.drawString(texto, px, py);
	}

	public Font getFonte() {
		return fonte;
	}

	public void setFonte(Font fonte) {
		this.fonte = fonte;
	}

}
