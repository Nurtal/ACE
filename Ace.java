//import magick.ImageInfo;
//import magick.MagickImage;
//import magick.*;
import java.io.*;
import java.util.*;

public class Ace{




    
	//------------------ MAIN ---------------------------
    public static void main(String[] args){



    	//----------- PROCEDURES ------------------------------------------------

        System.out.println("--- # => Running Ace <= # ---\n");        




        // --- Test Patient Manipulation
        Patient murlock = new Patient("m765", "/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs");
        Patient panda = new Patient("Robert", "/home/foulquier/Bureau/SpellCraft/flowBeads-master/nathan/QC8PeaksBeads_32140219_SAS_ARIA_19MAR2015_19MAR2015.fcs");
        murlock.initialise_graphe("machin.png");
        panda.initialise_graphe("bidule.png");
        
        murlock.compareTo(panda);

        //System.out.println(murlock.get_grapheName());














        // --- Test Access to FCS file
       	/*
        DataManager bidule = new DataManager();
       	bidule.readFile("DATA/FCS/QC8PeaksBeads_32140203_SAS_ARIA_16MAR2015_16MAR2015.fcs");
        */

        /*
        try{
        	ImageInfo truc = new ImageInfo("DATA/rose.jpg");
        	MagickImage machin = new MagickImage(truc);

        }catch(Exception e){

        	System.out.println("ERROR");
        }
        */

        /*
        // Create a compare object specifying the 2 images for comparison.
		ImageCompare ic = new ImageCompare("imagecompare/test1.jpg", "imagecompare/test2.jpg");
		// Set the comparison parameters.
		// (num vertical regions, num horizontal regions, sensitivity, stabilizer)
		ic.setParameters(8, 6, 5, 10);
		// Display some indication of the differences in the image.
		ic.setDebugMode(2);
		// Compare.
		ic.compare();
		// Display if these images are considered a match according to our parameters.
		System.out.println("Match: " + ic.match());
		
		// If its not a match then write a file to show changed regions.
		//if (!ic.match()) saveJPG(ic.getChangeIndicator(), "c:\\changes.jpg");
		*/



    }

    

}