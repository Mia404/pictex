package com.daejin.pictex.gui.menu;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * GUI 메뉴 바 클래스
 * @author 김학성
 */ 
public class PictexMenu extends JMenuBar {
	private MenuItemAction listener = new MenuItemAction(); 
	 
	/**
	 * 메뉴바 생성자
	 */
	public PictexMenu() {
		// 파일메뉴 및 아이템 정의
		JMenu fileMenu = new JMenu("File");
		JMenuItem fmiOpenFile  = new JMenuItem("Open File");
		JMenuItem fmiSave = new JMenuItem("Save");
		JMenuItem fmiExit = new JMenuItem("Exit");

		// 파일 메뉴 아이템 세팅 및 메뉴 액션 처리
		fileMenu.add(fmiOpenFile).addActionListener(listener);
		fileMenu.add(fmiSave).addActionListener(listener);
		fileMenu.addSeparator();
		fileMenu.add(fmiExit).addActionListener(listener);
		
		// 파일 메뉴 아이템 단축키 설정
		fmiOpenFile.setAccelerator(KeyStroke.getKeyStroke('O',InputEvent.CTRL_MASK)); // 열기 Ctrl + O 
		fmiSave.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));    // 저장 Ctrl + S
		fmiExit.setAccelerator(KeyStroke.getKeyStroke('W', InputEvent.CTRL_MASK));    // 닫기  Ctrl + W

		// 파일 저장 아이콘 설정
		fmiSave.setIcon(new ImageIcon(getClass().getResource("/Images/save.png")));

		
		// 도구메뉴 및 아이템 정의, 메뉴 액션 처리 추후 구현
//		JMenu toolsMenu = new JMenu("Tools");
//		JMenuItem toolsOption = new JMenuItem("Option");
//		toolsMenu.add(toolsOption).addActionListener(listener);
		
		
		// 도움말 및 아이템 정의, 메뉴 액션 처리
		JMenu helpMenu = new JMenu("Help");
		JMenuItem helpContent = new JMenuItem("Help Contents");
		JMenuItem helpVersion = new JMenuItem("Version");
		
		// 헬프 메뉴 아이템 단축키 설정
		helpContent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));    // 도움말 F1
		
		helpMenu.add(helpContent).addActionListener(listener);
		helpMenu.addSeparator();
		helpMenu.add(helpVersion).addActionListener(listener);

		
		// 메뉴바에 각 메뉴 추가
		add(fileMenu);
//		add(toolsMenu);
		add(helpMenu);
		setVisible(true); // 메뉴 바 보이도록 설정 
	}
}