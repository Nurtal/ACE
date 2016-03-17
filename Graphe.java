import java.io.*;
import java.util.*;


public class Graphe{


	// ------------------- FIELDS ------------------------
	private String grapheName;
	private boolean isInitialise;
	
	private double red_distortion;
	private double green_distortion;
	private double blue_distortion;
	private double all_distortion;

	// ---------------- CONSTRUCTORS ---------------------
	public Graphe(String name){
		if(!(name.equals("fcs.png"))){
			grapheName = name;
			isInitialise = false;
		}else{
			System.out.println("[WARNING][Graphe Construction] : grapheName can't be equal to \"fcs.png\", setting graphe name to \"murlock.png\"\n");
			grapheName = "murlock.png";
			isInitialise = false;
		}
	}

	// ------------------ METHODS ------------------------

	public void set_redDistortion(double r_distortion){
		red_distortion = r_distortion;
	}
	public void set_greenDistortion(double g_distortion){
		green_distortion = g_distortion;
	}
	public void set_blueDistortion(double b_distortion){
		blue_distortion = b_distortion;
	}
	public void set_allDistortion(double a_distortion){
		all_distortion = a_distortion;
	}

	public double get_redDistortion(){
		return red_distortion;
	}
	public double get_greenDistortion(){
		return green_distortion;
	}
	public double get_blueDistortion(){
		return blue_distortion;
	}
	public double get_allDistortion(){
		return all_distortion;
	}



	public String get_name(){
		return grapheName;
	}

	public boolean initialised(){
		return isInitialise;
	}

	public void initialisation(String fcsFileName){
		/*
		* -> Create a R script generating an image from fcsFileName
		* -> Execute R script
		* -> Set isInitialise to True
		*
		* TODO: Adjust Output directories
		*
		* [APPROVED]
		*/

		// Create  the R Script
		String[] tmpScript_nameInArray = grapheName.split(".png");
		String tmpScript_name = tmpScript_nameInArray[0]+".R";
		try{
			FileWriter fw = new FileWriter("SCRIPTS/TMP/"+tmpScript_name);
			fw.write("require(flowCore)\n");
			fw.write("require(flowClust)\n");
			fw.write("library(flowViz)\n");
			fw.write("source(\"SCRIPTS/fcs.R\")\n");
			fw.write("x <- flowCore::read.FCS(file.path(\""+fcsFileName+"\"))\n");
			fw.write("summary(x)\n");
			fw.write("png(filename=\"DATA/GRAPHES/"+grapheName+"\")\n");
			fw.write("plot(x)\n");
			fw.write("dev.off()\n");
			fw.close();
		}catch(IOException exception){
			System.out.println("[ERROR][Graphe Initialisation] "+ exception.getMessage());
		}

		// Execute R Script
		try{
			Process init = Runtime.getRuntime().exec("Rscript SCRIPTS/TMP/"+tmpScript_name);
			try{
					init.waitFor();
				}catch(InterruptedException initException){
					System.out.println("[ERROR][Graphe Initialisation] :"+ initException.getMessage());
				}
		}catch(IOException exception){
			System.out.println("[ERROR][Graphe Initialisation] :"+ exception.getMessage());
		}
		 
		// Set isInitialise to True
		isInitialise = true;
	}


	public void matrixModification(String fcsFileName){
		/*
		*
		* => Create a new graphe with random changes in
		* the compensation matrix
		* ( for now work only on random changes )
		* ( The precedent graphe is replace ) 
		*
		* -> Create R Script
		* -> Execute R Script (generate png)
		*
		* TODO: -> check matrix diagonalisation
		*		-> may be not the best place for this method,
		*		   relocation on patient class ?
		*
		* [APPROVED]
		*/


		// Create the R Script
		String[] tmpScript_nameInArray = grapheName.split(".png");
		String tmpScript_name = tmpScript_nameInArray[0]+"_matrixModification.R";
		try{
			FileWriter fw = new FileWriter("SCRIPTS/TMP/"+tmpScript_name);
			fw.write("require(flowCore)\n");
			fw.write("require(flowClust)\n");
			fw.write("library(flowViz)\n");
			fw.write("source(\"SCRIPTS/fcs.R\")\n");
			fw.write("source(\"SCRIPTS/library.R\")\n");
			fw.write("x <- flowCore::read.FCS(file.path(\""+fcsFileName+"\"))\n");
			fw.write("y <- retrieveCompensationMatrix(file.path(\""+fcsFileName+"\"))\n");
			
			for(int x = 1; x < 9; x++){
				for(int y = 1; y < 9; y++){
					if(y != x){
						double r = Math.random()*1.0;
						fw.write("y["+x+","+y+"] <- "+r+"\n");
					}
				}
			}

			fw.write("png(filename=\"DATA/GRAPHES/"+grapheName+"\")\n");
			fw.write("plot(x)\n");
			fw.write("dev.off()\n");
			fw.close();
		}catch(IOException exception){
			System.out.println("[ERROR][Graphe Initialisation] "+ exception.getMessage());
		}


		// Execute R Script
		try{
			Process init = Runtime.getRuntime().exec("Rscript SCRIPTS/TMP/"+tmpScript_name);
			try{
					init.waitFor();
				}catch(InterruptedException initException){
					System.out.println("[ERROR][Graphe Modification] :"+ initException.getMessage());
				}
		}catch(IOException exception){
			System.out.println("[ERROR][Graphe Modification] :"+ exception.getMessage());
		}

	}





}