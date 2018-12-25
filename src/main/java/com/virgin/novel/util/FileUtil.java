package com.virgin.novel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class FileUtil {
	public static void main(String[] args) {
		//findImage("c:/");
		//Calendar calendar = Calendar.getInstance();
		//System.out.println(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
		 String[] teamdates = "".split(",");
		 System.out.println(teamdates.length);
		 System.out.println(teamdates[0].equals(""));
         System.out.println(teamdates[teamdates.length-1].equals(""));
	}
	public static void findImage(String dirPath){
		File dir = new File(dirPath);
		File[] fileList = dir.listFiles();
		if(fileList!=null && fileList.length>0){
			for(File file : fileList){
				System.out.println(file.getPath());
				if(file.isFile() && file.getName().endsWith(".jpg")){
					copyFile(file,"D:\\image");
				}else if(!"D:/image".equals(file.getPath())){
					findImage(file.getPath());
				}
			}
		}
	}
	
	public static void copyFile(File sourceFile, String destDir){
		try {
			FileInputStream fis = new FileInputStream(sourceFile);
			String fileName = sourceFile.getName();
			FileOutputStream fos = new FileOutputStream(destDir+"/"+fileName);
			byte[] b = new byte[1024];
			int len;
			while((len=fis.read(b))!=-1){
				fos.write(b, 0, len);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
