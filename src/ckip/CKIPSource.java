package ckip;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import tw.cheyingwu.ckip.CKIP;

public class CKIPSource {
	private CKIP ckip;
	private Properties props;
	private String ip;
	private int port;
	private String username;
	private String password;
	
	public CKIPSource() throws IOException, ClassNotFoundException {
		this("ckip.properties");			// 讀取 ckip 連接設定檔
	}
	
	public CKIPSource(String configFile) throws IOException {
		props = new Properties();
		props.load(new FileInputStream("properties/" + configFile));
		
		ip = props.getProperty("ckipConnection.ip");
		port = Integer.parseInt(props.getProperty("ckipConnection.port"));
		username = props.getProperty("ckipConnection.username");
		password = props.getProperty("ckipConnection.password");
	}
	
	public CKIP getConnection() {
		ckip = new CKIP(ip, port, username, password);
		System.out.println("CKIP Connection");
		return ckip;
	}
	
	public CKIP getCKIP() {
		return ckip;
	}
}