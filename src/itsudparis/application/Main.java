package itsudparis.application;

import java.util.Scanner;

import com.hp.hpl.jena.rdf.model.Model;

import itsudparis.tools.FileTool;
import itsudparis.tools.JenaEngine;

public class Main {

	public static void main(String[] args) {
		
	    String inputDataOntology = "data/Ontology/SmartHomeAruba.owl";
	    final String ns = "http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#";
	    final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
	    final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	    String inputRule = "data/Rules/rules.txt";

	    Model model = JenaEngine.readModel(inputDataOntology);
	    Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, inputRule);

	    /****CREATION D'OBJETS****/
	    /****************DINING****************/
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "table_dining");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "table_dining", "estDans", "Dining");

        
	    /****************LIVING****************/
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_living");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_living", "estDans", "Living");

        JenaEngine.createInstanceOfClass(inferedModel, ns,"Lampe", "lampe_living");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "lampe_living", "estDans", "Living");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"TV", "tv_living");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "tv_living", "estDans", "Living");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Climatiseur", "clim");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "clim", "estDans", "Living");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Lit", "canape");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "canape", "estDans", "Living");
        
        
	    /****************KITCHEN****************/  
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_kitchen");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_kitchen", "estDans", "Kitchen");
       
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "refrigerateur");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "refrigerateur", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Gaziniere", "Gaziniere");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Gaziniere", "estDans", "Kitchen");
        JenaEngine.addValueOfDataTypeProperty(inferedModel, ns,"Gaziniere","etat","OFF");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Micro-ondes");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Micro-ondes", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Table à manger");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Table à manger", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Chaise");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Chaise", "estDans", "Kitchen");
        
        
        /****************OFFICE****************/          
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Chauffage", "chauffage_office");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "chauffage_office", "estDans", "Office");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, ns, "chauffage_office", "etat", "OFF");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_office");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_office", "estDans", "Office");
         
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Lampe", "lampe_office");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "lampe_office", "estDans", "Office");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Bureau");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Bureau", "estDans", "Office");
        
        
        /****************BEDROOM****************/
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Lit", "bed");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "bed", "estDans", "Bedroom");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_bed");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_bed", "estDans", "Bedroom");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Chauffage_bed");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Chauffage_bed", "estDans", "Bedroom");
        
        
        
        /******************CHOIX DE LA DATE QU'ON VEUT LIRE ********************/
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
        	System.out.println("Veuillez choisir une date à évaluer de 1 à 5:");
	        	System.out.println("1: pour la date 2010-11-04");
	        	System.out.println("2: pour la date 2010-11-12");
	        	System.out.println("3: pour la date 2010-12-25");
	        	System.out.println("4: pour la date 2010-12-30");
	        	System.out.println("5: pour la date 2011-02-01");

        	choice = scanner.nextInt();
        	
        }while(choice<0 && choice>6);
        
        /******************LECTURE DE FICHIER CSV with Infered Model********************/
        
		StreamInputDataset stream = new StreamInputDataset();
		stream.run(inferedModel,choice);
		
	
		
		/**********************Querries*************************/
		
		System.out.println("Les objets de Kitchen:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?obj WHERE { ?obj"
				+ " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <"+ns+"Objet> }"));
		
		
		System.out.println("Les résultats de requetes:");
		
		System.out.println("Le dernier état du volet kitchen ");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date ?degre WHERE { "
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_kitchen>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_kitchen>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#date> ?date ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#T003>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#degre> ?degre  }"));
		
		
		System.out.println("Le dernier état du volet bed ");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date ?degre WHERE { "
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_bed>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_bed>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#date> ?date ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#T001>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#degre> ?degre  }"));
		
		System.out.println("Le dernier état du volet office ");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date ?degre WHERE { "
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_office>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_office>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#date> ?date ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#T005>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#degre> ?degre  }"));
		
		
		 
		
		System.out.println("Le dernier état du volet Living ");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date ?degre WHERE { "
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_living>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#volet_living>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#date> ?date ."
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#T002>"
				+ "<http://www.semanticweb.org/win10/ontologies/2022/0/SmartHomeAruba#degre> ?degre  }"));
		
		
		
		
		
		/*********************SAUVEGARDE DE L'ONTOLOGIE**********************/
		FileTool filetool = new FileTool();
		System.out.println("Sauvegarde de l'ontologie aruba sur la journée numéro "+choice);
        filetool.saveOWL(model, "data/Output/Home_Aruba_Test_Day"+choice);
		
	}

}
