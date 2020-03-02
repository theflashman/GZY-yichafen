package com.iwork.G629.util;

import org.junit.Test;

import com.iwork.G629.util.QRcode.QRCodeUtil;

public class QRCodeUtilTest {
	@Test
	public void testQRCode() throws Exception {
		/**
		 * QRCodeUtil.encode(text, imgPath, destPath, true);
		 * 
		 * text：编码到二维码中的内容，这里是“我是小铭”
		 * 
		 * imgPath：要嵌入二维码的图片路径，如果不写或者为null则生成一个没有嵌入图片的纯净的二维码
		 * 
		 * destPath：生成的二维码的存放路径
		 * 
		 * true：表示将嵌入二维码的图片进行压缩，如果为“false”则表示不压缩。
		 * 
		 * 解析二维码方法：
		 * 
		 * QRCodeUtil.decode(destPath);
		 * 
		 * destPath：将要解析的二维码的存放路径
		 */
		// 存放在二维码中的内容
		String text = "我是小铭";
		// 嵌入二维码的图片路径,null時生成純净的二維碼
		@SuppressWarnings("unused")
		String imgPath = "G:/qrCode/dog.jpg";
		// 生成的二维码的路径及名称
		String destPath = "C:\\Users\\曾越爸爸\\Desktop\\test.jpg";
		// 生成二维码
		QRCodeUtil.encode(text, null, destPath, true);
		// 解析二维码
		String str = QRCodeUtil.decode("C:\\Users\\曾越爸爸\\Desktop\\showqrcode");
		// 打印出解析出的内容
		System.out.println(str);

	}

}
