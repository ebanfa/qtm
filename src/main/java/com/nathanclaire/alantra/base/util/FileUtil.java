/**
 * 
 */
package com.nathanclaire.alantra.base.util;

import java.io.File;

/**
 * @author Edward Banfa 
 *
 */
public class FileUtil {
	
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

	public static void deleteFile(String msgAttachmentFile) 
	{
		try{
    		File file = new File(msgAttachmentFile);
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!>>>>>>>>>>>>>>>>>>>>>>.");
    		}else{
    			System.out.println("Delete operation is failed.>>>>>>>>>>>>>>>>>>>>>>>>>");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
