package itsudparis.application;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import itsudparis.tools.FileTool;
import itsudparis.tools.JenaEngine;

public class StreamInputDataset {
	
    public static final String ns = "http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#";
    public static final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
    public static final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	
    public void run(Model model,int choice) {

	        // dataset path
	        String fileName = "";
	        switch (choice) {
            	case 1 : fileName = "data/Datasets/2010-11-04.txt"; break;
            	case 2 : fileName = "data/Datasets/2010-11-12.txt"; break;
            	case 3 : fileName = "data/Datasets/2010-12-25.txt"; break;
            	case 4 : fileName = "data/Datasets/2010-12-30.txt"; break;
            	case 5 : fileName = "data/Datasets/2011-02-01.txt"; break;
               
	        }

	        //read file into stream, try-with-resources
	        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
	        	
	            stream.forEach(line -> {
	                try{

	                	String[] split = line.split("\\s+");
	                    String typeCapteur="";
	                    String nomCapteur="";
						String time="";
	                    String room="" ;
	                    String valeurCapteur="";

	                    switch (split[2].substring(0,1)) {
	                        case "D":
	                            typeCapteur = "Door";
	                            time =split[1];
	                            switch (split[2].substring(0,4)) {
                                case "D001":
                                	room ="Living";
                                	nomCapteur="D001";
                                	break;
                                case "D002":
                                	room ="Kitchen";
                                	nomCapteur="D002";
                                	break;
                                case "D003":
                                    room= "Bathroom_big";
                                	nomCapteur="D003";
                                    break;
                                case "D004":
                                    room= "Office";
                                	nomCapteur="D004";
                                    break;

	                            }

	                            break;
	                        case "T":
	                            typeCapteur = "Temperature";
	                            time =split[1];
	                            switch (split[2].substring(0,4)) {
	                                case "T001":
	                                	room ="Bedroom";
	                                	nomCapteur="T001";
	                                	break;
	                                case "T002":
	                                	room ="Living";
	                                	nomCapteur="T002";
	                                    break;
	                                case "T003":
	                                	room ="Kitchen";
	                                	nomCapteur="T003";
	                                	break;
	                                case "T004":
	                                	room ="Bathroom_big";
	                                	nomCapteur="T004";
	                                    break;
	                                case "T005":
	                                	room ="Office";
	                                	nomCapteur="T005";
	                                	break;
	                              
	                            }
	                          	    	                 
	    	                    

	                            break;
	                        case "M":
	                        	typeCapteur = "Mouvement";
	                            time =split[1];
	                            switch (split[2].substring(0,4)) {
                                case "M001":
                                	room ="Bedroom";
                                	nomCapteur="M001";
                                	break;
                                case "M002":
                                	nomCapteur="M002";
                                	room ="Bedroom";
                                	break;
                                case "M003":
                                    room= "Bedroom";
                                	nomCapteur="M003";
                                    break;
                                case "M004":
                                	room ="Bedroom";
                                	nomCapteur="M004";
                                	break;
                                case "M005":
                                	nomCapteur="M005";
                                	room ="Bedroom";
                                	break;
                                case "M006":
                                    room= "Bedroom";
                                	nomCapteur="M006";
                                    break;
                                case "M007":
                                	room ="Bedroom";
                                	nomCapteur="M007";
                                	break;
                                case "M008":
                                	nomCapteur="M008";
                                	room ="Living";
                                	break;
                                case "M009":
                                    room= "Living";
                                	nomCapteur="M009";
                                    break;
                                case "M010":
                                	room ="Living";
                                	nomCapteur="M010";
                                	break;
                                case "M011":
                                	nomCapteur="M011";
                                	room ="Living";
                                	break;
                                case "M012":
                                    room= "Living";
                                	nomCapteur="M012";
                                    break;
                                case "M013":
                                	room ="Bedroom";
                                	nomCapteur="M013";
                                	break;
                                case "M014":
                                	nomCapteur="M014";
                                	room ="Dining";
                                	break;
                                case "M015":
                                    room= "Kitchen";
                                	nomCapteur="M015";
                                    break;
                                case "M016":
                                	room ="Kitchen";
                                	nomCapteur="M016";
                                	break;
                                case "M017":
                                	nomCapteur="M017";
                                	room ="Kitchen";
                                	break;
                                case "M018":
                                    room= "Kitchen";
                                	nomCapteur="M018";
                                    break;
                                case "M019":
                                	room ="Kitchen";
                                	nomCapteur="M019";
                                	break;
                                case "M020":
                                	nomCapteur="M020";
                                	room ="Living";
                                	break;
                                case "M021":
                                    room= "Dining";
                                	nomCapteur="M021";
                                    break;
                                case "M022":
                                	room ="Bedroom";
                                	nomCapteur="M022";
                                	break;
                                case "M023":
                                	nomCapteur="M023";
                                	room ="Bedroom";
                                	break;
                                case "M024":
                                    room= "Bedroom";
                                	nomCapteur="M024";
                                    break;
                                case "M025":
                                	room ="Office";
                                	nomCapteur="M025";
                                	break;
                                case "M026":
                                	nomCapteur="M026";
                                	room ="Office";
                                	break;
                                case "M027":
                                    room= "Office";
                                	nomCapteur="M027";
                                    break;
                                case "M028":
                                    room= "Office";
                                	nomCapteur="M028";
                                    break;
                                case "M029":
                                	room ="Bathroom_big";
                                	nomCapteur="M029";
                                	break;
                                case "M030":
                                	nomCapteur="M030";
                                	room ="Bathroom_big";
                                	break;
                                case "M031":
                                    room= "Bathroom_big";
                                	nomCapteur="M031";
                                    break;
	                            }

	                            break;
	                            
	                    }

	                    valeurCapteur =split[3];
	                    if (model.contains(model.getResource(ns+nomCapteur),null, (RDFNode) null)) {
	                    	if (typeCapteur.equalsIgnoreCase("Mouvement") || typeCapteur.equalsIgnoreCase("Door") ) {
	                    		System.out.println("Update=========================>"+nomCapteur);
	    	                    JenaEngine.updateValueOfDataTypeProperty(model, ns,nomCapteur,"etat",valeurCapteur);
	    	                    JenaEngine.updateValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
	    	                    JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"date",time);
	    	             
	                    	}else {
	                    		System.out.println("Update=========================>"+nomCapteur);
	                    		JenaEngine.updateValueOfDataTypeProperty(model, ns,nomCapteur,"degre",
	    	                    		Double.parseDouble(valeurCapteur));
	    	                    JenaEngine.updateValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
	    	                    JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"date",time);
	                    	}
	                    	
	                    }else {
	                    if (typeCapteur.equalsIgnoreCase("Mouvement") || typeCapteur.equalsIgnoreCase("Door") ) {
                    		System.out.println("Create------------------------->"+nomCapteur);
	                    JenaEngine.createInstanceOfClass(model, ns,typeCapteur,nomCapteur);
	                    JenaEngine.addValueOfDataTypeProperty(model, ns,nomCapteur,"etat",valeurCapteur);
	                    JenaEngine.addValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
	                    JenaEngine.addValueOfDataTypeProperty(model,ns,nomCapteur,"date",time);
	                    }
	                    else { 
                    		System.out.println("Create------------------------->"+nomCapteur);
	                    	JenaEngine.createInstanceOfClass(model, ns,typeCapteur,nomCapteur);
	                    JenaEngine.addValueOfDataTypeProperty(model, ns,nomCapteur,"degre",
	                    		Double.parseDouble(valeurCapteur));
	                    JenaEngine.addValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
	                    JenaEngine.addValueOfDataTypeProperty(model,ns,nomCapteur,"date",time);
	                    	
	                    }
	                    }      
	                    detectActivity(model.listStatements(),model,time);
	                    
	                } catch(Exception e){
                    e.printStackTrace();
                }
	            });
                
	        } catch(Exception e){
	        		e.printStackTrace(); 
	        		
            }
	        
	  }
	  public static void detectActivity(StmtIterator geriter, Model model,String time) throws InterruptedException {

		  while(geriter.hasNext()) {
			  Statement stmt = geriter.nextStatement();
			  Resource subject = stmt.getSubject();
			  Property predicate =stmt.getPredicate();
			  RDFNode object = stmt.getObject();
			  if(!object.toString().startsWith("http")) {
				 
				  //M31 capteur de mouvement dans BATHROOM
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M031")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
		                	System.out.println("\n-=-=-=> Quelqu'un dans la douche à "+" "+time);
		                   
						}
				  }
				  
				  
				   //M0014 SALLE
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M014")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {

							System.out.println("\n -=-=-=> Quelqu'un mange dans la salle à "+" "+time);
						}
				  }
				   
				  //M19 KITCHEN
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M019")) {
					if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						
						System.out.println("\n =====> Quelqu'un dans la cuisine, la lampe est allumé à "+" "+time);
						
					}
					
				  }
				  
				  //M0015 KITCHEN
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M015")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
								System.out.println("\n =====> Quelqu'un prepare à manger à "+" "+time);
							
						}
				  }
				  
				  //M009 SALON
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M009")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						
							System.out.println("\n =====> Quelqu'un se détend dans le salon à "+" "+time);
							
						}
				  }
				  
				  //M0010 SALON
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M010")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Quelqu'un se détend dans le salon à "+" "+time);
							
						}
				  }
				  
				  
				  //M0020 SALON
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M020")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Mouvement dans le salon, lampe allumé à "+" "+time);
							
						}
				  }
				
				//M007 BEDROOM
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M007")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Mouvement dans la grande chambre, Lampe allumé à "+" "+time);
							
						}
				  }
				  
				  
				//M024 Bedroom
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M024")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Mouvement dans la petite chambre, Lampe allumé à "+" "+time);
							
						}
				  }
				//M027 OFFICE
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M027")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Mouvement dans le bureau, Lampe allumé à "+" "+time);
							
						}
				  }
				  
				  
				//M026 OFFICE
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M026")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Quelqu'un travail sur le bureau à "+" "+time);
							
						}
				  }
				  
				  
				//M003 BEDROOM
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M003")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Quelqu'un est allongé sur le lit à "+" "+time);
							
						}
				  }
				  
				  
				//M004 BATHROOM
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#M004")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							
							System.out.println("\n =====> Entrain de marcher de la chambre à la douche à "+" "+time);
							
						}
				  }
				  						  				  
				
		 }
	  }
    }
}


	                    
	                    
	                    
	                    
	                    
	                    

	                    

	           
