import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.image.ImageView;


public class traducirImagen extends ImageView {

	
	private static final long serialVersionUID = -4780788625883802149L;
	
	
	private QuadTree quadTree;

	
	private int level;
	
	public traducirImagen(String fileName, String filePath, 
			QuadTree quadTree) {
		
		
	
		this.quadTree = quadTree;
		
		
		initComponents();
			
	}
	
	private void initComponents() {
		
		setLayout(new BorderLayout(0, 0));
		
		int height = quadTree.height();
		
		level = height - 1;
		

		lblImage.setVerticalAlignment(SwingConstants.TOP);
		lblImage.setHorizontalAlignment(SwingConstants.LEFT);
		
		basePanel.setViewportView(lblImage);		
		
		createImage();
	}
	
	private void createImage() {
		
		int w = quadTree.getWidth();
		int h = quadTree.getHeight();
		
		lblImage.setPreferredSize(new Dimension(w, h));
		lblImage.setSize(new Dimension(w, h));
		
		BufferedImage buffer = new BufferedImage(w, h, 
				BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = buffer.createGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		paintImage(g2d, quadTree.getRoot(), level);
		
		ImageIcon icon = new ImageIcon(buffer);
		
		lblImage.setIcon(icon);
		
	}
	
	private void paintImage(Graphics2D g2d, Node node, int level) {
		
		//ou estÃ¡ no nivel de detalhamento desejado (level = 0) ou o nÃ³ Ã© uma 
		//folha e nÃ£o existe nÃ­vel inferior
		if(level == 0 || node.isLeaf()) {
			
			Color c = node.getColor();
			
			int x = node.getX();
			int y = node.getY();
			
			int w = node.getWidth();
			int h = node.getHeight();
			
			g2d.setColor(c);
			
			g2d.fillRect(x, y, w, h);
			
			return;
											
		}		 
		
		for(int i = 0; i < node.getQ().length; ++i) {
			
			if(node.getQ()[i] != null) {
				paintImage(g2d, node.getQ()[i], level - 1);	
			}			
			
		}
				
	}
	public QuadTree getQuadTree() {
		return quadTree;
	}
}
