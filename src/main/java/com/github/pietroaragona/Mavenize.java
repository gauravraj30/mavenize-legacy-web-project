package com.github.pietroaragona;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;

import com.github.pietroaragona.helpers.CoordinatesHelper;
import com.github.pietroaragona.helpers.DependenciesHelper;
import com.github.pietroaragona.helpers.PluginHelper;
import com.github.pietroaragona.helpers.PropertyHelper;


/**
*
* @author Pietro Aragona
* @since 07-Oct-2014
*/
public class Mavenize {
	public static Scanner sc;
	
	static Model model = new Model();
	
    static String projectName;
    static String projectBaseDir;
    static String webContentDir;
    static String libDir;
    static String libDirRelativePath;

	private static String finalName;
	
    static{
    	sc = new Scanner(System.in);
    }
    
	public static void main(String[] args) {
		//TODO aggiungere gestione  context path /archibus
		readLegacyProjectInfo();
		printLegacyProjectInfo();
		
		CoordinatesHelper.run(model);
		PropertyHelper.run(model, webContentDir, projectName, finalName);
		
	       Build build = new Build();
	        build.setFinalName("${final.name}");
	        build.setSourceDirectory("${src.dir}");
	        
	        PluginHelper.run(model, build);
	        DependenciesHelper.run(model, build, libDirRelativePath, libDir);
	        
		sc.close();
		String outputPomFile = projectBaseDir+"/"+"pom.xml";
		 FileWriter w = null;
		try {
			w = new FileWriter(new File(outputPomFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        try {
				new MavenXpp3Writer().write(w, model);
				System.out.println("Pom file created: " + outputPomFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private static void printLegacyProjectInfo() {
		System.out.println("Project Name: " + projectName);
		System.out.println("Path on File system: " + projectBaseDir);
		System.out.println("WebContent dir: " + webContentDir);
		System.out.println("Final name: " + finalName);
		System.out.println();
	}

	private static void readLegacyProjectInfo() {
		
		System.out.print("Project Name: ");
		projectName = sc.nextLine();
		System.out.print("Path on File system: ");
		projectBaseDir = sc.next();
		System.out.print("WebContent dir: ");
		webContentDir = sc.next();
		libDir = projectBaseDir + "/" + webContentDir + "/" + "WEB-INF" + "/" + "lib";
		if(!new File(libDir).exists()){
			System.out.println("Path [" + libDir+"] not exists!\nExit...");
			System.exit(0);
		}
		libDirRelativePath = "${project.basedir}" + "/" + webContentDir + "/" + "WEB-INF" + "/" + "lib";
		
		System.out.println("Final name: ");
		finalName = sc.next();
	}

}
