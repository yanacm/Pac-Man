package pacMan;

//primeira tela do jogo

import java.awt.Color;
import java.awt.Graphics2D;

public class Menu extends Texto {

	private short idx;
	private String rotulo;
	//private String rotulo2;
	private String[] opcoes;
	private boolean selecionado;

	public Menu(String rotulo) {
		super();

		this.rotulo = rotulo;
		setLargura(100);
		setAltura(30);
		setCor(Color.WHITE);  //cor do nome "fome: sem, muita e pouca
		
		/*this.rotulo2 = rotulo2;
		setLargura(100);
		setAltura(10);
		setCor(Color.WHITE);*/
	}

	public void addOpcoes(String... opcao) {
		opcoes = opcao;
	}

	@Override
	public void desenha(Graphics2D g) {
		if (opcoes == null)
			return;

		g.setColor(getCor());
		super.desenha(g, String.format("%s: <%s>", getRotulo(), opcoes[idx]), getPx(), getPy() + getAltura());

		if (selecionado)
			g.drawLine(getPx(), getPy() + getAltura() + 5, getPx() + getLargura(), getPy() + getAltura() + 5);

	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
	/*public String getRotulo2() {
		return rotulo2;
	}

	public void setRotulo2(String rotulo2) {
		this.rotulo2 = rotulo2;
	}*/

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;

	}

	public int getOpcaoId() {
		return idx;
	}

	public String getOpcaoTexto() {
		return opcoes[idx];
	}

	public void setTrocaOpcao(boolean esquerda) {
		if (!isSelecionado() || !isAtivo())
			return;

		idx += esquerda ? -1 : 1;

		if (idx < 0)
			idx = (short) (opcoes.length - 1);
		else if (idx == opcoes.length)
			idx = 0;

	}

}
