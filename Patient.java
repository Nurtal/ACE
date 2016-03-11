import java.io.*;
import java.util.*;


public class Patient{


	// ------------------- FIELDS ------------------------
	private String id;
	private String fcsFilename;
	private Graphe graphe;


	// ---------------- CONSTRUCTORS ---------------------
	public Patient(String id_Patient, String fcsFilename_Patient){
		id = id_Patient;
		fcsFilename = fcsFilename_Patient;
	}


	// ------------------ METHODS ------------------------
	
	public String get_id(){
		return id;
	}
	
	public String get_fcsFilename(){
		return fcsFilename;
	}

	public void initialise_graphe(String grapheName_Patient){
		System.out.println("--- Initialise Graphe "+grapheName_Patient+" ---");
		graphe = new Graphe(grapheName_Patient);
		graphe.initialisation(fcsFilename);
		System.out.println("--- => Graphe initialised ");
	}

	public String get_grapheName(){
		return graphe.get_name();
	}

	public Graphe get_graphe(){
		return graphe;
	}

	public void compareTo(Patient patientToCompare){
		/*
		* Compare two patient by comparing graphes
		* 
		*
		* [APPROVED]
		*/

		//Control initialisation of graphe foreach patient
		if(graphe.initialised() && patientToCompare.get_graphe().initialised()){

			System.out.println("--- Proceed to Comparison ---");

			
			/*
			| -> Perform Comparison
			| -> Create bash script to run imagemagick
			| -> Output stream in stderr (not stdout), both are redirected in a log file
			| -> Parse result file, set distortion value for graphe
			*/

			//write bash file
			String[] grapheName_1 = graphe.get_name().split(".png");
			String name_1 = grapheName_1[0];
			String[] grapheName_2 = patientToCompare.get_graphe().get_name().split(".png");
			String name_2 = grapheName_2[0];
			String diff_image = "DATA/GRAPHES/difference_"+name_1+"_"+name_2+".png";
			String log_file = "log.txt";
			try{
				FileWriter fw = new FileWriter("SCRIPTS/comparison.sh");
				fw.write("#/bin/bash\n");
				fw.write("compare -verbose -metric mae DATA/GRAPHES/"+graphe.get_name()+" DATA/GRAPHES/"+patientToCompare.get_graphe().get_name()+" "+diff_image+" > "+log_file+" 2>&1\n");
				fw.close();

			}catch(IOException bashCreation){
				System.out.println("--- [ERROR][Patient Comparison] :"+ bashCreation.getMessage());
			}

			//Change permission on bash file
			try{
				Process chmd = Runtime.getRuntime().exec("chmod +x SCRIPTS/comparison.sh");
			}catch(IOException chmdException){
				System.out.println("[ERROR][Patient Comparison] :" +chmdException.getMessage());
			}

			//Execute bash file
			try{
				Process cmp = Runtime.getRuntime().exec("./SCRIPTS/comparison.sh");
				try{
					cmp.waitFor();
				}catch(InterruptedException cmpException){
					System.out.println("[ERROR][Patient Comparison] :"+ cmpException.getMessage());
				}
				
			}catch(IOException exception){
				System.out.println("[ERROR][Patient Comparison] :"+ exception.getMessage());
			}

			//Parse Result of comparison
			String line = null;
			try{
				FileReader fileReader = new FileReader(log_file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				while((line = bufferedReader.readLine()) != null){
					String[] lineInArray = line.split(" ");
					if(lineInArray.length>2){
						if(lineInArray[4].equals("red:")){
							double distortion = Double.parseDouble(lineInArray[5]);
							graphe.set_redDistortion(distortion);
						}
						if(lineInArray[4].equals("green:")){
							double distortion = Double.parseDouble(lineInArray[5]);
							graphe.set_greenDistortion(distortion);
						}
						if(lineInArray[4].equals("blue:")){
							double distortion = Double.parseDouble(lineInArray[5]);
							graphe.set_blueDistortion(distortion);
						}
						if(lineInArray[4].equals("all:")){
							double distortion = Double.parseDouble(lineInArray[5]);
							graphe.set_allDistortion(distortion);
						}
					}
				}
			}catch(IOException parseException){
				System.out.println("[ERROR][Patient Comparison] :"+ parseException.getMessage());
			}

			System.out.println("--- => Comparison Finished");
		}



	}


}