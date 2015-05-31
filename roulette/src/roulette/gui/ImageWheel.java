package roulette.gui;

import javax.swing.*;

//import roulette.server.BufferImage;
//import roulette.server.Graphics2D;
//import roulette.server.ImageIcon;
//import roulette.server.ImageObserver;
//import roulette.server.bufferImage;

import java.awt.*;


public class ImageWheel extends JFrame{
	private ImageIcon image;
	//private Image image;
	private JLabel label;
	ImageWheel(){
		setLayout(new FlowLayout());
		image=new ImageIcon(getClass().getResource("roulette-wheel-low.gif"));
		label=new JLabel(image);
		add(label);
	}
	public void add(JPanel p)
	{
		setSize(375,375);
		p.add(label);
		setVisible(true);
	}
	//public void rollTheWheel(double degree)
	///{
	//	rotateImage(degrees,ImageObserver o);
	//}
	 /*
	 public void rotateImage(double degrees,ImageObserver o)
	 {
		ImageIcon icon=new ImageIcon();
		icon.setImage(image);
		BufferImage blankCanvas=new BufferImage(icon.getIconWidth(),icon.getIconHeight(),BufferImage.TYPE_INT_ARGB);
		Graphics2D g2=(Graphics2D)blankCanvas.getGraphics();
		g2.rotate(Math.toRadians(degrees),icon.getIconWidth()/2,icon.getIconHeight());
		g2.drawImage(image,0,0,o);
		image=blankCanvas;
	}
	*/
}
