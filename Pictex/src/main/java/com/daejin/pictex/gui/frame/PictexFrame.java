package com.daejin.pictex.gui.frame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

import com.daejin.pictex.gui.menu.PictexMenu;
import com.daejin.pictex.gui.panel.ImgPanel;
import com.daejin.pictex.gui.panel.TextPanel;

/**
 * GUI 메인 프레임 클래스
 * 
 * @author 김학성 
 */
public class PictexFrame{
	// 메뉴바
	private PictexMenu menu  = new PictexMenu();
	
	// 메인 프레임
	private static JFrame mainFrame = null;
	
	// 메인 프레임 > 이미지 패널
	private static ImgPanel imgPanel = null;
	
	// 메인 프레임 > 텍스트 패널
	private static TextPanel textPanel = null;
	
	public PictexFrame() {
		// 메인 프레임 설정
		mainFrame = new JFrame(); 
		mainFrame.setTitle("Pictex!");
		mainFrame.setBounds(0, 0, 750, 850);   // 가로위치, 세로위치, 가로길이, 세로길이
		mainFrame.setLocationRelativeTo(null); // 중앙에 오도록
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 메인프레임 아이콘 이미지 삽입 
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/icon.png")));
		
		// 메인프레임 > 이미지 패널 설정
		imgPanel = new ImgPanel();
		
		// 메인프레임 > 텍스트 패널 설정
		textPanel = new TextPanel();
		
		// 컴포넌트 추가 
		mainFrame.add(imgPanel, BorderLayout.CENTER); // 이미지 패널
		mainFrame.add(textPanel, BorderLayout.SOUTH); // 텍스트 패널
		 
		mainFrame.setJMenuBar(menu);   // 메인 프레임에 메뉴바 설정
		mainFrame.setResizable(false); // 사이즈 조절 불가능
		mainFrame.setVisible(true);    // 메인 프레임 visible
	}

	/**
	 * 메인 프레임 가져오기
	 * @return JFrame
	 */
	public static JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * 메인프레임 > 이미지 패널 가져오기
	 * @return ImgPanel
	 */
	public static ImgPanel getImgPanel() {
		return imgPanel;
	}
	
	/**
	 * 메인프레임 > 텍스트 패널 가져오기
	 * @return TextPanel
	 */
	public static TextPanel getTextPanel() {
		return textPanel;
	}
}