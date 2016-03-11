import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.util.*;


public class DataManager{


	public void readFile(String binaryFile){

		byte[] data = new byte[1024];

		int readBytes;
		try {
  			FileInputStream in = new FileInputStream(new File(binaryFile));
  			while ((readBytes = in.read(data)) != -1) {
    			System.out.println("read " + readBytes + " bytes, and placed them into temp array named data");
    			//System.out.println(in.read(data));

    			try{
					String doc2 = new String(data, "UTF-8");
					System.out.println(doc2);
				}catch(Exception e2){
					e2.printStackTrace();
				}

  			}
  			in.close();
		} catch (Exception e) {
  			e.printStackTrace();
		}

		
		



		/*
		Path p = FileSystems.getDefault().getPath("", "DATA/FCS/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs");
        try{
        	byte [] fileData = Files.readAllBytes(p);
        	System.out.println(fileData);
        }catch(IOException exception){
        	System.out.println("Error : "+ exception.getMessage());
        }
        */
        
	}


}