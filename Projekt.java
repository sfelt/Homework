package Homework;

import java.util.Random;

import praktikum3.TextIO;

public class Projekt {
	
	public static void main(String[] args) {
		System.out.println("Sisesta oma nimi");
		String kasutajaNimi = TextIO.getlnString();
		kasutajaNimi = kasutajaNimi.substring(0, 1).toUpperCase() + kasutajaNimi.substring(1);
		System.out.format("Tere, %s\n", kasutajaNimi);
		int teheteArv = 0;
		int oigedVastused = 0;
		int minArv = 1;
		int maxArv = 10;
		int maxManguKestvus = 60;
		
		long startTime = System.currentTimeMillis(); 
		while ( (System.currentTimeMillis()-startTime) < (maxManguKestvus * 1000) ) { // lõpetab mängu etteantud ajal//
	
			int tehteMark = juhuslikarv(1, 4);
			int tegelikTulemus = 0;
			String tehe = "+";

			// teha kaks muutujat, mille väärtuseks on juhuslikud arvud
			int juhuslikArvYks = juhuslikarv(minArv, maxArv);
			int juhuslikArvKaks = juhuslikarv(minArv, maxArv);
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
					juhuslikArvYks = juhuslikarv(minArv, maxArv);
					juhuslikArvKaks = juhuslikarv(minArv, maxArv);			
					tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;					
				}
				
				tehe = "/";
				break;
			}

			System.out.format("Kui palju on %d %s %d \n", juhuslikArvYks, tehe, juhuslikArvKaks);
			int kasutajaTulemus = TextIO.getlnInt();
			//kui on aeg otsas väljub programmist
			if (( (System.currentTimeMillis()-startTime) < (maxManguKestvus * 1000) )) {
				break;
			}
			if (tegelikTulemus == kasutajaTulemus) {
				System.out.print("\nTubli, õige vastus! ");
				oigedVastused++;
			} else {
				System.err.print("Oops, vale vastus. ");
			}
			teheteArv++;
			System.out.format("%d/%d. ",oigedVastused,teheteArv);
			System.out.format ("Aega on jäänud %d sekundit \n\n", (startTime - System.currentTimeMillis())/1000 + maxManguKestvus);
			

		} // while tsükli lõpp
	}

	public static int juhuslikarv(int min, int max) {
		int arv = max - min + 1;
		int tulemusYks = (int) (Math.random() * arv + min);
		return tulemusYks;
	}

}



