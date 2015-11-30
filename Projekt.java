package Homework;

import java.util.Random;

import praktikum3.TextIO;

public class Projekt {
	
	public static void main(String[] args) {

		System.out.println("Sisesta oma nimi");
		String kasutajaNimi = TextIO.getlnString();
		kasutajaNimi = kasutajaNimi.substring(0, 1).toUpperCase() + kasutajaNimi.substring(1);
		System.out.format("Tere, %s\n", kasutajaNimi);
		Seaded seaded = Seaded.taastaSeaded();
				
		// TODO: teha valik, kas seadete muutmine või siis mängimine 

		int teheteArv = 0;
		int oigedVastused = 0;
		long startTime = System.currentTimeMillis(); 
		
		while ( (System.currentTimeMillis()-startTime) < (seaded.maxManguKestvus * 1000) ) { // lõpetab mängu etteantud ajal//
	
			int tehteMark = juhuslikarv(1, 4);
			int tegelikTulemus = 0;
			String tehe = "+";

			// teha kaks muutujat, mille väärtuseks on juhuslikud arvud
			int juhuslikArvYks = juhuslikarv(seaded.minArv, seaded.maxArv);
			int juhuslikArvKaks = juhuslikarv(seaded.minArv, seaded.maxArv);
			
			// teha nende muutujatega tehteid
			switch (tehteMark) {
			case 1: // liitmine
				tegelikTulemus = juhuslikArvYks + juhuslikArvKaks;
				break;
			case 2: // lahutamine
				tegelikTulemus = juhuslikArvYks - juhuslikArvKaks;
				tehe = "-";
				break;
			case 3: // korrutamine
				tegelikTulemus = juhuslikArvYks * juhuslikArvKaks;
				tehe = "*";
				break;
			case 4: // jagamine

				tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;
				
				while (juhuslikArvYks % juhuslikArvKaks != 0) { // kontrollib kas jääk on 0
					juhuslikArvYks = juhuslikarv(seaded.minArv, seaded.maxArv);
					juhuslikArvKaks = juhuslikarv(seaded.minArv, seaded.maxArv);			
					tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;					
				}
				
				tehe = "/";
				break;
			}

			System.out.format("Kui palju on %d %s %d \n", juhuslikArvYks, tehe, juhuslikArvKaks);
			int kasutajaTulemus = TextIO.getlnInt();
			//kui on aeg otsas väljub programmist
			if (( (System.currentTimeMillis()-startTime) < (seaded.maxManguKestvus * 1000) )) {
			//	break;
			}
			if (tegelikTulemus == kasutajaTulemus) {
				System.out.print("\nTubli, õige vastus! ");
				oigedVastused++;
			} else {
				System.err.print("Oops, vale vastus. ");
			}
			teheteArv++;
			System.out.format("%d/%d. ",oigedVastused,teheteArv);
			System.out.format ("Aega on jäänud %d sekundit \n\n", (startTime - System.currentTimeMillis())/1000 + seaded.maxManguKestvus);		

			// TODO: muuta seadetes olevaid maxArv ja minArv 
			
		} // while tsükli lõpp
		
		
		// TODO: salvestada seaded
		
		// TODO: kuvada statistika
		
		
	}

	public static int juhuslikarv(int min, int max) {
		int arv = max - min + 1;
		int tulemusYks = (int) (Math.random() * arv + min);
		return tulemusYks;
	}

}



