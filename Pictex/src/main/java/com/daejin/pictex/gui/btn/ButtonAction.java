package com.daejin.pictex.gui.btn;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.daejin.pictex.gui.frame.PictexFrame;

/**
 * 각 버튼에 대한 액션처리를 담아둔 클래스
 * @author 김학성 
 */
public class ButtonAction implements ActionListener{
	// private Logger logger = LoggerFactory.getLogger(ButtonAction.class);

	/**
	 * 버튼 액션 이벤트
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); // 메뉴 아이템의 종류 구분
		
		switch(cmd) {
			case "Text Copy" :
				// Text Copy 버튼이 눌렀을 경우
				
				String text = PictexFrame.getTextPanel().getJta().getText().trim(); // 텍스트 패널 텍스트 가져옴
				
				// 텍스트 없다면 복사 안됨 처리
				if(text.equals("")) break;
				
				// 텍스트 복사 처리
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(text);
				clipboard.setContents(strSel, null);
				JOptionPane.showMessageDialog(null, "텍스트 복사.", "알림", JOptionPane.INFORMATION_MESSAGE);
				break;
		}
	}
}