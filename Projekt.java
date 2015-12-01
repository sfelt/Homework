package Homework;

import java.util.Random;

import praktikum3.TextIO;

public class Projekt {

	public static Seaded seaded;// seaded objekt on n‰htav terves selles klassis

	public static void main(String[] args) {

		System.out.println("Sisesta oma nimi");
		String kasutajaNimi = TextIO.getlnString();
		kasutajaNimi = kasutajaNimi.substring(0, 1).toUpperCase() + kasutajaNimi.substring(1);
		System.out.format("Tere, %s\n", kasutajaNimi);
		seaded = Seaded.taastaSeaded();
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
			case 4:
				seadistamine();
				break;
			}
		}

		// TODO: salvestada seaded

		// TODO: kuvada statistika

	}

	public static void seadistamine() {

		while (true) {
			System.out.println(
					"Tee valik, millist seadet soovid muuta. 1. Saad muuta minimaalse arvu v‰‰rtust; 2. Saad muuta maksimaalse arvu "
							+ "v‰‰rtust; 3. Saad muuta m‰ngu kestvust; 4. L‰heb seadetest v‰lja");
			int kasutajaSisestus = TextIO.getInt();
			if (kasutajaSisestus == 4) {
				break;
			}
			switch (kasutajaSisestus) {
			case 1:
				System.out.format("Muuda minimaalse arvu v‰‰rtust, praegune v‰‰rtus on %i\n", seaded.minArv);
				seaded.minArv = TextIO.getInt();
				break;
			case 2:
				System.out.format("Muuda maksimaalse arvu v‰‰rtust, praegune v‰‰rtus on %i\n", seaded.maxArv);
				seaded.maxArv = TextIO.getInt();
				break;
			case 3:
				System.out.format("Muuda m‰ngukestvust, praegune m‰ngukestvus on %i sekundit\n",
						seaded.maxManguKestvus);
				seaded.maxManguKestvus = TextIO.getInt();
				break;
			}
		}
	}

	public static void edetabeliKuvamine() {

		// TODO: edetabeli kuvamine
	}

	public static void arvutamine() {
		int teheteArv = 0;
		int oigedVastused = 0;
		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < (seaded.maxManguKestvus * 1000)) { // lıpetab
																								// m‰ngu
																								// etteantud
																								// ajal//

			int tehteMark = juhuslikarv(1, 4);
			int tegelikTulemus = 0;
			String tehe = "+";

			// teha kaks muutujat, mille v‰‰rtuseks on juhuslikud arvud
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

				while (juhuslikArvYks % juhuslikArvKaks != 0) { // kontrollib
																// kas j‰‰k on 0
					juhuslikArvYks = juhuslikarv(seaded.minArv, seaded.maxArv);
					juhuslikArvKaks = juhuslikarv(seaded.minArv, seaded.maxArv);
					tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;
				}

				tehe = "/";
				break;
			}

			System.out.format("Kui palju on %d %s %d \n", juhuslikArvYks, tehe, juhuslikArvKaks);
			int kasutajaTulemus = TextIO.getlnInt();
			// kui on aeg otsas v‰ljub programmist
			if (((System.currentTimeMillis() - startTime) < (seaded.maxManguKestvus * 1000))) {
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
					(startTime - System.currentTimeMillis()) / 1000 + seaded.maxManguKestvus);

		} // while ts¸kli lıpp

	}

	public static int juhuslikarv(int min, int max) {
		int arv = max - min + 1;
		int tulemusYks = (int) (Math.random() * arv + min);
		return tulemusYks;
	}

}
