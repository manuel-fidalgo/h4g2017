package h4g2017;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame
{
	Cell[][] matrix; 
	int rows,cols;
	ImageManager manager;
	private static int DELAY = 1*1000;
	int edad;
	JMenuBar bar;
	JMenuItem item;
	int segundos;

	private static final boolean DEBUG = false;
	
	public MainWindow()
	{
		
		segundos = 0;
		bar = new JMenuBar();
		bar.setBackground(Color.white);
		item = new JMenuItem("");
		bar.add(item);
		setJMenuBar(bar);

		rows = 14;
		cols = 47;

		matrix = new Cell[rows][cols];
		manager = new ImageManager();

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

		setWindowDetails();

		GridLayout g = new GridLayout(rows, cols);
		g.setVgap(3);
		JPanel p = new JPanel(g);

		
		Cell c;
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){

				c = new Cell(i,j, manager.getRandomImageAndCode());
				matrix[i][j] = c;
				if(j!=0){
					p.add(c);
				}else{
					p.add(new Cell(i,j,manager.getImageWithNumber(i)));
				}
			}
		}

		this.add(p);
		avaliableRow(0);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int j = 0; j < matrix.length; j++) {
					try {
						for (int i = 0; i < 20; i++) {
							Thread.sleep(DELAY);
							segundos++;
							item.setText("Segundos restantes para completar la fila: "+(20-segundos));
						}
						segundos = 0;
					} catch (InterruptedException e) {
						System.err.println("Hilo interrumplido");
					}
					invalidateRow(j);
				}
				//Insertar final test
				calcularResultado();
			}

		});

		t.start();

	}

	private void calcularResultado() {
		
		double omisiones=0;
		double comisiones=0;
		double total_respuestas=0;
		double total_aciertos=0;
		double total_preguntas=0;
		double total_fallos = 0;
		double efectividad = 0;
		double tasa_concentracion = 0;



		Cell c;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				total_preguntas++;
				c = matrix[i][j];
				if(c.choosen){
					total_respuestas++;
					if(!c.debeEstarMarcado)
						comisiones++;
					else
						total_aciertos++;
				}else{
					if(c.debeEstarMarcado)
						omisiones++;
				}
			}
		}

		total_fallos = comisiones + omisiones;
		efectividad = total_respuestas -(omisiones+comisiones);
		tasa_concentracion = total_aciertos - comisiones;

		boolean indicio = false;
		switch (edad) {
		case 6:
			if(tasa_concentracion<66.6) indicio=true;
			break;
		case 7:
			if(tasa_concentracion<84.8) indicio=true;
			break;
		case 8:
			if(tasa_concentracion<98.56) indicio=true;
			break;
		case 9:
			if(tasa_concentracion<109.05) indicio=true;
			break;
		case 10:
			if(tasa_concentracion<123.29) indicio=true;
			break;
		case 11:
			if(tasa_concentracion<126.16) indicio=true;
			break;
		default:
			break;
		}
		System.out.println("");
		String pre = "Tasa de concentracion = "+tasa_concentracion;
		if (indicio){
			pre = pre + "\nHay indicios de TDAH";
		}else{
			pre = pre + "\nNo existe indicio de TDAH";
		}
		
		//todo-> add the icon
		if(DEBUG)System.out.printf("omisiones=%f;\n"
				+ "comisiones=%f \n"
				+ "total_respuestas=%f \n "
				+ "total_aciertos=%f \n "
				+ "total_preguntas=%f\n"
				+ "total_fallos = %f\n"
				+ "efectividad = %f\n"
				+ "tasa_concentracion = %f\n",omisiones,comisiones,total_respuestas,total_aciertos,
				total_preguntas,total_fallos,efectividad,tasa_concentracion);
		
		ImageIcon icon = new ImageIcon("Source/icon.png");
		
		JOptionPane.showMessageDialog(null, pre, "Resultados", 1, icon);
		dispose();
	}

	private void invalidateRow(int i) {

		for (int j = 0; j < cols; j++) {
			matrix[i][j].customHide();
			try{ 
				avaliableRow(i+1);
			}catch(Exception e){

			}
		}
	}

	private void avaliableRow(int i){

		for (int j = 0; j < cols; j++) {
			matrix[i][j].makeAvaliable();;
		}
	}


	private void setWindowDetails() {

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

	}
}