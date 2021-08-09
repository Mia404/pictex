package com.daejin.pictex.gui.tessImpl;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import com.daejin.pictex.gui.frame.PictexFrame;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class TextExtractor{
	private ITesseract instance = new Tesseract(); // Tesseract 사용 OCR 객체
	
	private static TextExtractor textExtractor = null;
	
	private TextExtractor() {}
	
	/** 
	 * 테서렉트 사용 객체 : 싱글톤 패턴 사용
	 * @return
	 */ 
	public static TextExtractor getInstance() {
		if(textExtractor == null) textExtractor = new TextExtractor();
		return textExtractor;
	}
	
	/**
	 * 이미지에서 텍스트 추출 후
	 * 각 컴포넌트에 이미지 및 글자 세팅
	 * 
	 * 메인프레임 > 이미지패널
	 * 메인프레임 > 텍스트에이리어
	 * 
	 * @param String filePath
	 */
	public void executeImgText(String filePath){
		String[] arrExt = { "bmp", "jpeg", "gif", "png", "tiff", "jpg", "tif"};
		String fileExt = FilenameUtils.getExtension(filePath).toLowerCase(); // 파일 확장자

		// 이미지 파일 확장자 체크
		int cnt = 0;
		for(String chkExt : arrExt) {
			if(chkExt.equals(fileExt)) {
				cnt++;
				break;
			}
		}
		if(cnt == 0) {
			JOptionPane.showMessageDialog(null, "이미지 파일이 아닙니다.\n bmp, jpg, gif, png, tiff", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			// 메인프레임, 메인프레임 > 이미지 : 다시 그리기
			PictexFrame.getMainFrame().repaint(); // 다시 그리기 (메인 프레임 영역 내에서 이미지 변경 잔상 제거) 
			PictexFrame.getImgPanel().setPic(ImageIO.read(new File(filePath)));
			PictexFrame.getImgPanel().repaint();  // 다시 그리기 (패널 내에서 이미지 변경 잔상 제거)
			
			// 테서렉트 리소스 설정
			File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven 빌드 영어 데이터 번들
	        instance.setDatapath(tessDataFolder.getPath());
	        instance.setLanguage("eng+kor");                     // 추출할 언어 지정
	        instance.setTessVariable("user_defined_dpi", "300"); // 이미지 해상도 설정 (추후 이미지 사이즈에 따른 해상도 조절 기능 필요)
	        
	        // 이미지에서 텍스트 추출 : 추출된 문자 양쪽 공백 및 개행 제거
			String text = instance.doOCR(new File(filePath)).trim();
			
			// 메인프레임 > 텍스트에어리어 : 텍스트 설정 및 커서 위치 조절
			PictexFrame.getTextPanel().getJta().setText(text);
			PictexFrame.getTextPanel().getJta().setCaretPosition(0);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (TesseractException te) {
			te.printStackTrace();
		}
	}
}