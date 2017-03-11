package h4g2017;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BackgorundPanel extends JPanel{
	
	public BackgorundPanel(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		try {
			g.drawImage(ImageIO.read(new File("Source/back.png")), 0, 0, getWidth(), getHeight(), null);
		} catch (IOException e) {
			System.err.println("Back not found");
		}

	}
}
@SuppressWarnings("serial")
class WebPanel extends JScrollPane {
	
	public JEditorPane pane;
	
	public WebPanel(String url){
		
		pane = new JEditorPane();
		pane.setEditable(false);
		try {
			  pane.setPage(url);
			}catch (IOException e) {
			  pane.setContentType("text/html");
			  pane.setText("<html>Could not load</html>");
			}
		add(pane);
	}
}
