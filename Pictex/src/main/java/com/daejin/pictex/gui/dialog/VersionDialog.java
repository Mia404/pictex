package com.daejin.pictex.gui.dialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 버전 다이얼로그
 * @author 김학성
 */ 
public class VersionDialog extends JDialog{
	private static VersionDialog versionDialog = null;
	
	private JLabel lb1 = new JLabel("Pictex 1.0 ver");
	private JLabel lb2 = new JLabel("대진대학교 컴퓨터공학전공 김학성");
	
	private JLabel imgLb  = new JLabel();
	private Font font     = new Font("굴림", Font.BOLD, 20);
	private ImageIcon iconIc = new ImageIcon(getClass().getResource("/Images/icon.png"));
	
	/**
	 * 화면 구성 처리
	 */
	private VersionDialog() {
		setDialogLocation();
		
		this.setTitle("Version");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		
		lb1.setBounds(120,55,500,20);
		lb1.setFont(font);
		lb2.setBounds(120,80,500,20);
		
		// 이미지 스케일 조정
		Image xImg = iconIc.getImage();
		Image yImg = xImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon xyImg = new ImageIcon(yImg);
		
		imgLb.setBounds(35, 50, 50, 50); // 가로위치, 세로위치, 가로넓이, 세로넓이
		imgLb.setIcon(xyImg);
		// imgLb.setBorder(new TitledBorder(new LineBorder(Color.red,5))); 보더 라인으로 이미지 라벨 위치 확인
		
		this.add(lb1);
		this.add(lb2);
		this.add(imgLb);
		this.repaint();
        
		this.setSize(370,200);
		this.setResizable(false);
		this.setVisible(true);
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				// ESC 키 눌렸을 시 visible
				if (e.getKeyCode() == 27){
					VersionDialog.getInstance().setVisible(false);
				}
			}
		});
	}
	
	/**
	 * 싱글톤 패턴 사용
	 * @return
	 */
	public static VersionDialog getInstance() {
		if(versionDialog == null) versionDialog = new VersionDialog();
		return versionDialog;
	}
	
	/**
	 *  중앙에서 켜지도록 변경
	 */
	public void setDialogLocation(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
        if(gd.length > 0) {
            int width  = gd[0].getDefaultConfiguration().getBounds().width;
            int height = gd[0].getDefaultConfiguration().getBounds().height;
            
            this.setSize(width / 2, height / 2);
            
            this.setLocation(((width  / 2) - (this.getSize().width  / 2)) + gd[0].getDefaultConfiguration().getBounds().x, ((height / 2) - (this.getSize().height / 2)) + gd[0].getDefaultConfiguration().getBounds().y);
        }
	}	
}