package qrcode;

// Google zxing
// Japan qrcode

public class Test {
	public static void main(String[] args) throws Exception{
//		String imgPath = "D:\\二维码.png";
//		String content = "ホワイトアルバムなんて知らない。 \r\n" + 
//				"だって、もう何も歌えない。 \r\n" + 
//				"届かない恋なんてしない。 \r\n" + 
//				"だって、もう人を愛せない。";
		
		String imgPath = "D:\\CSDN.png";
		String content = "https://me.csdn.net/museyouxia";
		
		QRCodeUtil qrCodeUtil = new QRCodeUtil();
		qrCodeUtil.encoderQRCode(content, imgPath, "png", 20);
	}
}
