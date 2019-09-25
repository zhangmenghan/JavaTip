package qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class QRCodeUtil {
	public void encoderQRCode(String content, String imgPath, String imgType, int size) throws Exception {
		File file = new File(imgPath);
		BufferedImage bufImg = qRcodeCommon(content, imgType, size);
		ImageIO.write(bufImg, imgType, file);
	}

	public BufferedImage qRcodeCommon(String content, String imgType, int size) throws Exception {
		BufferedImage bufImg = null;

		Qrcode qrCodeHandler = new Qrcode();
		// 设置二维码的拍错率： L M Q H
		// 排错率越高,可存储信息越少,但是清晰度越高
		qrCodeHandler.setQrcodeErrorCorrect('H');
		// 可存放的信息类型 N 数字 A 数字+A-Z B 所有
		qrCodeHandler.setQrcodeEncodeMode('B');
		// 尺寸 取值范围1-40
		qrCodeHandler.setQrcodeVersion(size);

		byte[] contentBytes = content.getBytes("UTF-8");
		boolean[][] codeOut = qrCodeHandler.calQrcode(contentBytes);

		int imdSize = 67 + 12 * (size - 1);

		bufImg = new BufferedImage(imdSize, imdSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D gs = bufImg.createGraphics();
		gs.setBackground(Color.WHITE);
		gs.clearRect(0, 0, imdSize, imdSize);
		gs.setColor(new Color(99,207,255));
		int pixoff = 2;
		
		for (int i = 0; i < codeOut.length; i++) {
			for (int j = 0; j < codeOut.length; j++) {
				if(codeOut[i][j]) {
					gs.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
				}
			}
		}
		
		//增加logo
		Image logo = ImageIO.read(new File("D:\\log.png"));
		int maxHeight = bufImg.getHeight();
		int maxWdith = bufImg.getWidth();
		
		gs.drawImage(logo,imdSize/3,imdSize/3,maxWdith/3,maxHeight/3,null);
		
		gs.dispose();
		bufImg.flush();
		
		return bufImg;
	}
}
