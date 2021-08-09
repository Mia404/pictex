package com.daejin.pictex.gui.panel;

import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.daejin.pictex.gui.tessImpl.TextExtractor;

/**
 * 메인프레임 > 이미지 패널 클래스
 * @author 김학성
 */
public class ImgPanel extends JPanel implements DropTargetListener{
	private BufferedImage pic = null;
	
	protected DropTarget dropTarget = null;
	
	public ImgPanel() { 
		dropTarget = new DropTarget(this, this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		try {
			// 기본 이미지 사용
			if(pic == null) {
				pic = ImageIO.read(getClass().getResource("/Images/fileUploadImage.png"));
			}
			g.drawImage(pic, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPic(BufferedImage pic) {
		this.pic = pic;
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) { }
	
	@Override
	public void dragOver(DropTargetDragEvent dtde) { }

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) { }

	@Override
	public void dragExit(DropTargetEvent dte) {	}

	/**
	 * 마우스 버튼을 놓았을 때 
	 */
	@Override
	public void drop(DropTargetDropEvent dtde) {
		// 드래그 액션이 복사일경우
		if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
			try {
				dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE); // 복사 만 받아들임

				List<File> droppedFiles = (List<File>)dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor); // 파일 목록을 리스트로 받아옴
				
				if(droppedFiles.size() <= 1) {
					// 리스트이 각 행마다 처리. 일단은 리스트 형태로 받아오기 때문에 아래와 같이 처리하도록 사용.
					for (File file : droppedFiles) { 
						TextExtractor.getInstance().executeImgText(file.getAbsolutePath());
					}
				}else {
					JOptionPane.showMessageDialog(null, "하나의 파일만 선택해 주세요.", "경고", JOptionPane.WARNING_MESSAGE);	
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}