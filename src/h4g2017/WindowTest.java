package h4g2017;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import jdk.internal.org.objectweb.asm.Label;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


@SuppressWarnings("serial")
public class WindowTest extends JFrame{

	private String secuencia;
	private boolean fallo1 = false;
	private JTextField textField;
	private JButton btnNewButton;
	TestWisc test;
	AudioInputStream[] sonidos;
	int edad, aciertos, fallos;


	public WindowTest() {
		test = new TestWisc();
		sonidos = new AudioInputStream[14];
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	int index;
	private void initialize() {
		
		boolean correcta = false;
		do{
			String s = JOptionPane.showInputDialog("Inserta edad (de 6 a 11 años):");
			try{
				edad = Integer.parseInt(s);
				if(edad>=6 || edad<=11){
					correcta = true;
				}
			}catch (Exception e) {}

		}while(!correcta);
		
		
		loadSonidos();

		index = 0;

		setBounds(100, 100, 450, 250);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBackground(Color.white);
		getContentPane().setBackground(Color.white);
		
		
		textField = new JTextField();
		textField.setBounds(142, 101, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		setResizable(false);

		btnNewButton = new JButton(new ImageIcon("Source/play.png"));
		btnNewButton.setBackground(Color.white);
		btnNewButton.setBounds(new Rectangle(278, 80, 64, 64));
		btnNewButton.setBorder(null);
	
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(index!=0){
					String s = textField.getText();
					if(s.equalsIgnoreCase(test.getNext())){
						aciertos++;
					}else{
						fallos++;
					}
				}
				textField.setEnabled(false);
				textField.setText("");
				playSonidoNo(index);
				index++;
				try{Thread.sleep(2000);}catch(Exception e){System.err.println("Interrupted");};
				textField.setEnabled(true);

				if(index == sonidos.length){
					darResultados();
				}

			}

		});
		getContentPane().add(btnNewButton);
		
		ImageIcon icon = new ImageIcon("Source/icon.png");
		JLabel l = new JLabel(icon);
		l.setBounds(0,101,icon.getIconWidth(),icon.getIconHeight());
		getContentPane().add(l);
		


	}

	private void darResultados() {
		String pre = "Aciertos: "+aciertos+"\nFallos: "+fallos;
		if(fallos>=2){
			pre = pre + "\nTest no superado";
		}else{
			pre = pre + "\nTest superado";
		}
		
		JOptionPane.showMessageDialog(null, pre, "Resultados", 1);
		dispose();
	}


	private void playSonidoNo(int i){
		AudioInputStream audioIn = sonidos[i];
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (LineUnavailableException|IOException e) { e.printStackTrace();}

	}

	private void loadSonidos() {
		try{
			sonidos[0] = AudioSystem.getAudioInputStream(new File("Source/sonidos/3-8-6.wav"));
			sonidos[1] = AudioSystem.getAudioInputStream(new File("Source/sonidos/6-1-2.wav"));
			sonidos[2] = AudioSystem.getAudioInputStream(new File("Source/sonidos/3-4-1-7.wav"));
			sonidos[3] = AudioSystem.getAudioInputStream(new File("Source/sonidos/6-1-5-8.wav"));
			sonidos[4] = AudioSystem.getAudioInputStream(new File("Source/sonidos/8-4-2-3-9.wav"));
			sonidos[5] = AudioSystem.getAudioInputStream(new File("Source/sonidos/5-2-1-8-6.wav"));
			sonidos[6] = AudioSystem.getAudioInputStream(new File("Source/sonidos/3-8-9-1-7-4.wav"));
			sonidos[7] = AudioSystem.getAudioInputStream(new File("Source/sonidos/7-9-6-4-8-3.wav"));
			sonidos[8] = AudioSystem.getAudioInputStream(new File("Source/sonidos/5-1-7-4-2-3-8.wav"));
			sonidos[9] = AudioSystem.getAudioInputStream(new File("Source/sonidos/9-8-5-2-1-6-3.wav"));
			sonidos[10] = AudioSystem.getAudioInputStream(new File("Source/sonidos/1-6-4-5-9-7-6-3.wav"));
			sonidos[11] = AudioSystem.getAudioInputStream(new File("Source/sonidos/2-9-7-6-3-1-5-4.wav"));
			sonidos[12] = AudioSystem.getAudioInputStream(new File("Source/sonidos/5-3-8-7-1-2-4-6-9.wav"));
			sonidos[13] = AudioSystem.getAudioInputStream(new File("Source/sonidos/4-2-6-9-1-7-8-3-5.wav"));
		}catch(Exception e ){
			System.err.println("No found audio");
			e.printStackTrace();
		}

	}

}

