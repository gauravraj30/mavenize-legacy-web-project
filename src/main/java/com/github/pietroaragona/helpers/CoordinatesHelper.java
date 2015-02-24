package com.github.pietroaragona.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.maven.model.Model;

public class CoordinatesHelper {

	public static void run(Model model) {
		Scanner sc = new Scanner(System.in);
		model.setModelVersion("4.0.0");
		System.out.println("GroupId: ");
		model.setGroupId(sc.next());
		System.out.println("ArtifactId: ");
		model.setArtifactId(sc.next());
		String defaultVersion = "0.0.1-SNAPSHOT";
		System.out.println("Version: (" + defaultVersion + ")");
//		 String version2 = sc.nextLine();
//		 System.out.println("letto ["+version2+"]");

//		String s = null;
//		try{
//		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
//		    s = bufferRead.readLine();
//	 
//		    System.out.println(s);
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//		}
//		String version = s.trim().equals("") ? defaultVersion : s;
		String version = sc.nextLine();
//		String version = sc.hasNext() ? sc.nextLine() : defaultVersion;
		
		model.setVersion(version.trim().equals("") ? defaultVersion : version);
		model.setPackaging("war");
		sc.close();

	}

}
