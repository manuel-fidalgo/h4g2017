package h4g2017;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		setWindowDetails();
		BorderLayout l = new BorderLayout();
		frame.getContentPane().setLayout(l);
		frame.add(new BackgorundPanel(),BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Tests");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Test D2");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow m = new MainWindow();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Test WISC-R");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowTest t = new WindowTest();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);

		JMenu mnEjercicios = new JMenu("Juegos");
		menuBar.add(mnEjercicios);

		JMenuItem juego_1 = new JMenuItem("Juego 1");
		juego_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Arduino\\arduino arduino/juegoImpulsividad/juegoImpulsividad.ino");
					} catch  ( IOException er){
						System.out.println("Error uploadling the program to the arduino");
					}
			}
		});
		
		JMenuItem juego_2 = new JMenuItem("Juego 2");
		juego_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Arduino\\arduino arduino/juegoRepetirleds/juegoRepetirleds.ino");
					} catch  ( IOException er){
						System.out.println("Error uploadling the program to the arduino");
					}
			}
		});
		
		mnEjercicios.add(juego_1);
		mnEjercicios.add(juego_2);


		JMenu mnInformacin = new JMenu("Información");
		menuBar.add(mnInformacin);
		
		
		JMenu mnInformacinParaPadres = new JMenu("Información para padres");
		mnInformacin.add(mnInformacinParaPadres);

		JMenuItem mntmInformacinParaPadres = new JMenuItem("Información especifica para padres");
		mntmInformacinParaPadres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe http://www.alenhi.org/padres-y-profesores/padres/");
				} catch (IOException f) {
					f.printStackTrace();
				}
			}
		});
		mnInformacinParaPadres.add(mntmInformacinParaPadres);

		JMenuItem mntmTratamiento = new JMenuItem("Tratamiento");
		mntmTratamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe http://www.alenhi.org/el-tdah/tratamiento/");
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}

			}
		});
		mnInformacinParaPadres.add(mntmTratamiento);

		JMenuItem mntmTdahEnEl = new JMenuItem("TDAH en el aula");
		mntmTdahEnEl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe http://www.fundacioncadah.org/web/categoria/tdah-en-el-aula-estrategias-en-aula.html");
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}


			}
		});
		mnInformacinParaPadres.add(mntmTdahEnEl);

		JMenuItem mntmManualParaPadres = new JMenuItem("Manual para padres");
		mntmManualParaPadres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe http://www.tdahytu.es/tdah-en-la-pareja/");
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		mnInformacinParaPadres.add(mntmManualParaPadres);

		JMenu mnInformacinParaAdultos = new JMenu("Información para adultos");
		mnInformacin.add(mnInformacinParaAdultos);

		JMenuItem mntmTdahEnAdultos = new JMenuItem("TDAH en adultos");
		mntmTdahEnAdultos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnInformacinParaAdultos.add(mntmTdahEnAdultos);

		JMenuItem mntmTdahEnLa = new JMenuItem("TDAH en la pareja");
		mntmTdahEnLa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe http://www.tdahytu.es/tdah-en-la-pareja/");
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
		});
		mnInformacinParaAdultos.add(mntmTdahEnLa);

		JMenuItem mntmConsejos = new JMenuItem("Consejos");
		mntmConsejos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe http://www.tdahytu.es/consejos-para-adultos-con-tdah/");
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}

			}
		});
		mnInformacinParaAdultos.add(mntmConsejos);
	}
	private void setWindowDetails() {

		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

	}
	
	
}