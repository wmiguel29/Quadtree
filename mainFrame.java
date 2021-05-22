import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.Canvas;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textNombreArchivo;
	private JTextField textRutaArchivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(142, 253, 529, 292);
		contentPane.add(canvas);
		
		JButton btnSelectArchivo = new JButton("Cargar Archivo");
		btnSelectArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser filechoose=new JFileChooser();
				
				int opcion= filechoose.showOpenDialog(btnSelectArchivo);//puyede haber error aca
				
				if(opcion==JFileChooser.APPROVE_OPTION) {
					
					//cojo el nombre del archivo selecciionado
					String rutaArchivo= filechoose.getSelectedFile().getPath();
					String nombreArchivo= filechoose.getSelectedFile().getName();
					
					textRutaArchivo.setText(rutaArchivo);
					textNombreArchivo.setText(nombreArchivo);
					
				}
				
				
				
			}
		});
		btnSelectArchivo.setBounds(73, 21, 154, 23);
		contentPane.add(btnSelectArchivo);
		
		JLabel lblNombreArchivo = new JLabel("Nombre archivo");
		lblNombreArchivo.setBounds(73, 55, 144, 14);
		contentPane.add(lblNombreArchivo);
		
		textNombreArchivo = new JTextField();
		textNombreArchivo.setBounds(76, 72, 566, 30);
		contentPane.add(textNombreArchivo);
		textNombreArchivo.setColumns(10);
		
		JLabel lblRutaArchivo = new JLabel("Ruta Archivo");
		lblRutaArchivo.setBounds(77, 124, 79, 14);
		contentPane.add(lblRutaArchivo);
		
		textRutaArchivo = new JTextField();
		textRutaArchivo.setBounds(76, 138, 566, 30);
		contentPane.add(textRutaArchivo);
		textRutaArchivo.setColumns(10);
		
		JButton btnNewButton = new JButton("Ejecutar");
		btnNewButton.setBounds(335, 192, 90, 30);
		contentPane.add(btnNewButton);
	}
}
