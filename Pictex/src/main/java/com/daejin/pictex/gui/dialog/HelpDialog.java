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
 * 도움말 다이얼로그
 * @author 김학성
 */ 
public class HelpDialog extends JDialog{
	private static HelpDialog helpDialog = null;
	
	private JLabel textLb = new JLabel("이미지 업로드 / 텍스트 추출 !");
	private JLabel imgLb  = new JLabel();
	private Font font     = new Font("굴림", Font.BOLD, 20);
	private ImageIcon helpIc = new ImageIcon(getClass().getResource("/Images/help.png"));

	/**
	 * 화면 구성 처리
	 */
	private HelpDialog() { 
		setDialogLocation(); 
		
		this.setTitle("Help Contents");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.white);
		
		textLb.setBounds(10, 10, 300, 20);
		textLb.setFont(font);
		
		// 이미지 스케일 조정
		Image xImg = helpIc.getImage();
		Image yImg = xImg.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
		ImageIcon xyImg = new ImageIcon(yImg);
		
		imgLb.setBounds(35, 50, 600, 400); // 가로위치, 세로위치, 가로넓이, 세로넓이
		imgLb.setIcon(xyImg);
		// imgLb.setBorder(new TitledBorder(new LineBorder(Color.red,5))); 보더 라인으로 이미지 라벨 위치 확인
		
		this.add(textLb);
		this.add(imgLb);
		this.repaint();
        
		this.setSize(650, 500);
		this.setResizable(false);
		this.setVisible(true);
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) {
				// ESC 키 눌렸을 시 visible
				if (e.getKeyCode() == 27){
					HelpDialog.getInstance().setVisible(false);
				}
			}
		});
	}
	
	/**
	 * 싱글톤 패턴 사용
	 * @return
	 */
	public static HelpDialog getInstance() {
		if(helpDialog == null) helpDialog = new HelpDialog();
		return helpDialog;
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