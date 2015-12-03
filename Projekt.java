package Homework;

import static java.util.Comparator.comparing;
import java.util.Collections;

public class Projekt {

	public static Kasutaja kasutaja;// seaded objekt on n‰htav terves selles klassis
	public static Edetabel edetabel;

	public static void main(String[] args) {

		System.out.println("Sisesta oma nimi");
		String kasutajaNimi = TextIO.getlnString();
		kasutajaNimi = kasutajaNimi.substring(0, 1).toUpperCase() + kasutajaNimi.substring(1);
		System.out.format("Tere, %s\n", kasutajaNimi);
		kasutaja = Kasutaja.taastaKasutaja(kasutajaNimi);
		edetabel = Edetabel.taastaEdetabel();
		while (true) {
			System.out.println("Tee valik:  1. L‰hme m‰ngima; 2. Kuva edetabel 3. V‰lju");
			int sisestus = TextIO.getInt();
			if (sisestus == 3) {
				break;
			}
			switch (sisestus) {
			case 1:
				arvutamine();
				break;
			case 2:
				edetabeliKuvamine();
				break;
			case 99:
				seadistamine();
				break;
			}
		}

		kasutaja.salvestaKasutaja();
		edetabel.salvestaEdetabel();
	}

	public static void seadistamine() {

		while (true) {
			System.out.println(
					"Tee valik, millist seadet soovid muuta: \n1. Saad muuta minimaalse arvu v‰‰rtust; \n2. Saad muuta maksimaalse arvu "
							+ "v‰‰rtust; \n3. Saad muuta m‰ngu kestvust; \n4. Saad kustutada tulemused edetabelist; \n5. L‰heb seadetest v‰lja \n");
			int kasutajaSisestus = TextIO.getInt();
			if (kasutajaSisestus == 5) {
				break;
			}
			switch (kasutajaSisestus) {
			case 1:
				System.out.format("Muuda minimaalse arvu v‰‰rtust, praegune v‰‰rtus on %d\n", kasutaja.minArv);
				kasutaja.minArv = TextIO.getInt();
				break;
			case 2:
				System.out.format("Muuda maksimaalse arvu v‰‰rtust, praegune v‰‰rtus on %d\n", kasutaja.maxArv);
				kasutaja.maxArv = TextIO.getInt();
				break;
			case 3:
				System.out.format("Muuda m‰ngukestvust, praegune m‰ngukestvus on %d sekundit\n",
						kasutaja.maxManguKestvus);
				kasutaja.maxManguKestvus = TextIO.getInt();
				break;
			case 4: 
			edetabel.kustutaTulemused();
			break;
			}
		}
	}

	public static void edetabeliKuvamine() {
		if (!edetabel.tulemused.isEmpty()) {

			// Sorteerime tulemuste j‰rgi kasvavas j‰rjekorras
			Collections.sort(edetabel.tulemused, comparing(Tulemus::getPunktiSumma));
			Collections.reverse(edetabel.tulemused);
			
			// foreach ts¸kkel 
			int reaArv = 1;
			for (Tulemus tulemus : edetabel.tulemused) {
				System.out.println(reaArv + ". " + tulemus.toString());
				reaArv++;
			}

		}
	}

	public static void arvutamine() {
		int teheteArv = 0;
		int oigedVastused = 0;
		long startTime = System.currentTimeMillis();
		
		Tulemus tulemus = new Tulemus(kasutaja.kasutajaNimi) ;

		while ((System.currentTimeMillis() - startTime) < (kasutaja.maxManguKestvus * 1000)) { // lıpetab m‰ngu etteantud ajal 

			int tehteMark = juhuslikarv(1, 4);
			int tegelikTulemus = 0;
			String tehe = "+";

			// teha kaks muutujat, mille v‰‰rtuseks on juhuslikud arvud
			int juhuslikArvYks = juhuslikarv(kasutaja.minArv, kasutaja.maxArv);
			int juhuslikArvKaks = juhuslikarv(kasutaja.minArv, kasutaja.maxArv);

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

				while (juhuslikArvYks % juhuslikArvKaks != 0) { // kontrollib
																// kas j‰‰k on 0
					juhuslikArvYks = juhuslikarv(kasutaja.minArv, kasutaja.maxArv);
					juhuslikArvKaks = juhuslikarv(kasutaja.minArv, kasutaja.maxArv);
					tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;
				}

				tehe = "/";
				break;
			}

			System.out.format("Kui palju on %d %s %d \n", juhuslikArvYks, tehe, juhuslikArvKaks);
			int kasutajaTulemus = TextIO.getlnInt();
			// kui on aeg otsas v‰ljub programmist
			if (((System.currentTimeMillis() - startTime) < (kasutaja.maxManguKestvus * 1000))) {
				// break;
			}
			if (tegelikTulemus == kasutajaTulemus) {
				System.out.print("\nTubli, ıige vastus! ");
				oigedVastused++;
			} else {
				System.err.print("Oops, vale vastus. ");
			}
			teheteArv++;
			System.out.format("%d/%d. ", oigedVastused, teheteArv);
			System.out.format("Aega on j‰‰nud %d sekundit \n\n",
					(startTime - System.currentTimeMillis()) / 1000 + kasutaja.maxManguKestvus);

		} // while ts¸kli lıpp
		tulemus.punktiSumma = (oigedVastused * 1000) - ((teheteArv - oigedVastused) * 100);
		edetabel.tulemused.add(tulemus);

		edetabeliKuvamine();	
	}

	public static int juhuslikarv(int min, int max) {
		int arv = max - min + 1;
		int tulemusYks = (int) (Math.random() * arv + min);
		return tulemusYks;
	}
}
