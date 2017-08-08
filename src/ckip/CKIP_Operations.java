package ckip;

import java.io.IOException;

import tw.cheyingwu.ckip.CKIP;

public class CKIP_Operations {
	private CKIP ckip;
	private CKIPSource ckipSource;
	
	public CKIP_Operations() {
		try {
			ckipSource = new CKIPSource();
			ckip = ckipSource.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CKIP CKIP_Send(String content) {
		ckip.setRawText(content);
		ckip.send();
		return ckip;
	}
	
	public CKIP getCKIP() {
		return ckip;
	}
}
