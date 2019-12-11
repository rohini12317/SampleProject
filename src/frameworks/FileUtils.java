package frameworks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class FileUtils {
	
	public static void readalllinesfromFile(String[] args){
		/*if file extension type is properties we have an object called "properties"*/
		
		
		
		String path="E:\\file\\credentials.txt";
		File  fr=new File(path);
		List<String> alldata=new ArrayList<>();
		//HashMap<String, String> alldata=new HashMap<String, String>();
		Scanner in;
		//we are using scanner object to read entire data line by line bcoz file and buffer
		//reads data as a single character//
		
		
		
		try {
			in=new Scanner(fr);
			while(in.hasNext()) {
				/*String line=in.next();
				String[] propertydata=line.split("=");
				alldata.put(propertydata[0], propertydata[1]);*/
				 
				alldata.add(in.next());
		}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(alldata);
	}
	
	public static void readFromProperties(String filepath) throws IOException {
		
		
		
		
		try {
			
			FileInputStream fis=new FileInputStream(filepath);
			
			//for properties object we should use fileinput stream first//
			Properties props=new Properties();
			props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static boolean checkkforfile(String filepath) {
		File f=new File(filepath);
		
		if(f.exists()) {
			return true;
		}else {
		return false;
	}
	}
	
	public static String getfileExtension(String filepath) {
		String extension="";
		if(checkkforfile(filepath)) {
			File f=new File(filepath);
			if(f.isFile()) {
				String filename=f.getName();
				String[] names=filename.split("\\.");
				extension=names[names.length-1];
			}else {
				System.out.println("given path is not valid");
			}
		}else {
			System.out.println("no file exist in given path");
		}
		return extension;
	
		
	}
	
	}
	

	


