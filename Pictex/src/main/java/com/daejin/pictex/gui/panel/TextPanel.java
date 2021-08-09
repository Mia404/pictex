package com.daejin.pictex.gui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.daejin.pictex.gui.btn.ButtonAction;

/**
 * 메인프레임 > 텍스트 패널 클래스
 * @author 김학성
 */
public class TextPanel extends JPanel { 
	private ButtonAction btnAction = new ButtonAction(); 
	
	private GridBagLayout grdb = new GridBagLayout();
	private GridBagConstraints gbc = new GridBagConstraints();
	
	private JTextArea jta   = new JTextArea();          // 텍스트 에어리어
	private JScrollPane jsc = new JScrollPane();        // 스크롤
	private JButton jb      = new JButton("Text Copy"); // 복사 버튼
	
	public TextPanel() {
		this.setLayout(grdb);
		
		gbc.fill = GridBagConstraints.BOTH; 
		
		// 위치 설정 텍스트 에어리어
		gbc.weightx = 0.2; // 0.2 비율
		gbc.gridx = 0; // x
        gbc.gridy = 0; // y
        grdb.setConstraints(jsc, gbc);
        
        // 위치 설정 카피 버튼
        gbc.weightx = 0.1; // 0.1 비율
        gbc.gridx = 1; // x
        gbc.gridy = 0; // y
        grdb.setConstraints(jb, gbc);
        
        jb.addActionListener(btnAction);
		
		// 텍스트 에어리어, 버튼 설정
		jta = new JTextArea(10, 50);
		jsc = new JScrollPane(jta);
		this.add(jsc);
		this.add(jb);
		
		this.setVisible(true);
	}

	public JTextArea getJta() {
		return jta;
	}

	public JButton getJb() {
		return jb;
	}
}