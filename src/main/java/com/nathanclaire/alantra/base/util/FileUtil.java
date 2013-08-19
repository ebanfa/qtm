/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;

import org.joda.time.DateTime;

/**
 * @author Edward Banfa 
 *
 */
public class FileUtil {

	public static final String DEFAULT_ATTACHMENTS_FOLDER = "/home/administrator/Projects/alantra/data/attachments/";
	
	/**
	 * @param directoryName
	 */
	public static File createDirectoryIfNeeded(String directoryName)
	{
	  File theDir = new File(directoryName);
	  // if the directory does not exist, create it
	  if (!theDir.exists())
	  {
	    try {
			//System.out.println("creating directory: " + directoryName);
			theDir.mkdirs();
			if (!theDir.mkdir()) System.out.println("Failed to create directory:" + directoryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  else
	  {
		  //System.out.println(String.format("The directory %s already exits", directoryName));
	  }
	  return theDir;
	}
	
	public static File[] getFilesWithExtension(String directory, final String extension){
		File dir = new File(directory);
		File[] matches = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(extension);
			}
		});
		return matches;
	}

	public static void deleteFile(String msgAttachmentFile) 
	{
		try{
    		File file = new File(msgAttachmentFile);
    		if(file.delete())
    		{
    			System.out.println(file.getName() + " is deleted!>>>>>>>>>>>>>>>>>>>>>>.");
    		}else
    		{
    			System.out.println("Delete operation is failed.>>>>>>>>>>>>>>>>>>>>>>>>>");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	/**
	 * @param messageLite
	 * @param filename
	 * @param input
	 * @throws IOException
	 */
	public static String saveFile(String filename, InputStream input) throws ApplicationException  
	{ 
		File file = initializeFile(filename);
		writeToFile(input, file);
		return file.getAbsolutePath();
	}

	/**
	 * @param input
	 * @param file
	 * @throws ApplicationException
	 */
	public static void writeToFile(InputStream input, File file) throws ApplicationException {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			BufferedInputStream bis = new BufferedInputStream(input);
			int aByte;
			while ((aByte = bis.read()) != -1) {
				bos.write(aByte);
			}
			bos.flush();
			bos.close();
			bis.close();
		} catch (FileNotFoundException e) {
			throw new ApplicationException("");
		} catch (IOException e) {
			throw new ApplicationException("");
		}
	}

	/**
	 * @param filename
	 * @return
	 */
	public static File initializeFile(String filename) {
		FileUtil.createDirectoryIfNeeded(DEFAULT_ATTACHMENTS_FOLDER);
		filename = DEFAULT_ATTACHMENTS_FOLDER.concat(filename);
		// Do no overwrite existing file
		File file = new File(filename);
		DateTime dt = new DateTime();
		for (int i = 0; file.exists(); i++) {
			filename = filename + dt.toString() ;
			file = new File(filename);
		}
		return file;
	}
}
