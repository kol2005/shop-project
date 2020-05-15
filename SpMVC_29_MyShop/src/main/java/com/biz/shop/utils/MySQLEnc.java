package com.biz.shop.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MySQLEnc {

	
	public static void main(String[] args) {
		
		StandardPBEStringEncryptor pbEnc = new StandardPBEStringEncryptor();
		
		Map<String,String>envList = System.getenv();
		System.out.println(envList.get("BIZ.COM"));
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("MySQL UserName >>");
		String userName = scanner.nextLine();
		
		System.out.print("MySQL Password >>");
		String password = scanner.nextLine();
		
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(envList.get("BIZ.COM"));
		
		String encUserName = pbEnc.encrypt(userName);
		String encPassword = pbEnc.encrypt(password);
		
		System.out.printf("userName : %s \n", encUserName);
		System.out.printf("password : %s \n", encPassword);
		
//		String saveFile = "./src/main/webapp/WEB-INF/spring/db.connection.properties";
		
		ResourceLoader resLoader = new DefaultResourceLoader();
		Resource res = resLoader.getResource("file:src/main/resources/db.connection2.properties");
		
		String saveUserName = String.format("mysql.username=%s", encUserName);
		String savePassword = String.format("mysql.password=%s", encPassword);
		

			PrintWriter out;
			try {
				out = new PrintWriter(res.getFile());
				out.println(saveUserName);
				out.println(savePassword);
				out.flush();
				out.close();
				
				System.out.println(res.getFile().toString() + " 저장 완료!!");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		scanner.close();
		
		System.out.println("db.connection.properties 저장 완료!");
		
	}
	
}










