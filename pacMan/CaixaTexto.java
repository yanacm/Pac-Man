package pacMan;

//caixa final para colocar o nome

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class CaixaTexto extends JFrame{
	
	private JTextField field;
	
	public CaixaTexto() {
		field = new JTextField();
		field.setPreferredSize(new Dimension(100, 100));
		
		this.add(field);
		this.setVisible(true);
	}
	
}
