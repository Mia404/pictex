package com.daejin.pictex.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FilenameUtils;

import com.daejin.pictex.gui.dialog.HelpDialog;
import com.daejin.pictex.gui.dialog.VersionDialog;
import com.daejin.pictex.gui.frame.PictexFrame;
import com.daejin.pictex.gui.tessImpl.TextExtractor;

/**
 * 메뉴 아이템 클릭 이벤트 처리 
 * @author 김학성 
 */
public class MenuItemAction implements ActionListener {
	
	/**
	 * 메뉴 아이템 액션 이벤트
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand(); // 메뉴 아이템의 종류 구분
		
		switch(cmd) { 
			case "Open File" :
				// File > Open File 클릭
				openFile();
				break;
			case "Save":
				// File > Save 클릭
				save();
				break;
			case "Exit" :
				// File > Exit 클릭
				System.exit(0); 
				break;
			case "Option":
				// Tools > Option 클릭
				break;
			case "Help Contents":
				// Help > Help Contents 클릭
				HelpDialog.getInstance().setVisible(true);
				break;
			case "Version":
				// Help > Version 클릭
				VersionDialog.getInstance().setVisible(true);
				break;
		}
	}
	
	/**
	 * 파일열기 : 이미지 파일 열기
	 */
	private void openFile() {
		// File > Open File 클릭 이벤트 발생 시
		JFileChooser chooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("bmp, jpg, gif, png, tiff Images..", "bmp", "jpg", "gif", "png", "tiff"); // 파일 다이얼로그 제목, 파일필터 확장자
		chooser.setFileFilter(filter); // 파일 다이얼로그 필터 설정
		
		// 파일 다이얼로그 출력
		int ret = chooser.showOpenDialog(null);
		
		// 창을 강제로 닫았거나 취소버튼을 눌렀을 경우 종료
		if(ret != JFileChooser.APPROVE_OPTION) { 
			JOptionPane.showMessageDialog(null, "파일을 선택해 주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
			
		}else {
			// 사용자가 파일을 선택하고 "열기"버튼을 누른 경우
			String filePath = chooser.getSelectedFile().getPath(); // 파일 경로명

			// 텍스트 추출
			TextExtractor.getInstance().executeImgText(filePath);
		}
	}
	
	/**
	 * 저장 : 텍스트 내용을 저장
	 */
	private void save() {
		JFileChooser chooser = new JFileChooser();
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("텍스트 문서(*.txt)", "txt"); // 파일 다이얼로그 제목, 파일필터 확장자
		chooser.setFileFilter(filter); // 파일 다이얼로그 필터 설정
		// 필터링될 확장자 추가 
		chooser.addChoosableFileFilter(filter);
		
		if(chooser.showSaveDialog(PictexFrame.getMainFrame()) == chooser.APPROVE_OPTION) {
			try {
				String str = PictexFrame.getTextPanel().getJta().getText().trim();
				
				// 글자가 없을 때 저장 X 
//				if(str.length() < 1)
//					return;
				
				// 파일 확장자 얻어오기 소문자로 변경
				String extension = FilenameUtils.getExtension(chooser.getSelectedFile().toString()).toLowerCase();
				
				// 파일 확장자가 존재하지 않거나 같지 않을 경우, 저장 시 .txt 확장자 붙도록 처리
				if(!extension.equals("TXT")) {
					extension = ".txt";
				}
				
				// 파일 저장 처리
				String newFileName = chooser.getSelectedFile().toString() + extension;
				File f = new File(newFileName);
				FileWriter fw = new FileWriter(f);
				fw.write(str);
				fw.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}