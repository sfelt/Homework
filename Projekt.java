package Homework;

import static java.util.Comparator.comparing;
import java.util.Collections;

/**
 * Peastarvutamise treenimise mäng.
 * Tulemused hoitakse edetabelis.
 * @author Sigrid Felt IA 18
 */
public class Projekt {	

	/** Hoiab kasutaja seadeid */
	public static Kasutaja kasutaja;
	/** Edetabel, sisaldab tulemusi */ 
	public static Edetabel edetabel;

	/** main meetod, käivitab programmi */
	public static void main(String[] args) {
		
		System.out.println("Peastarvutamise programm. Autor: Sigrid Felt, IA18");
		System.out.println("Sisesta oma nimi");
		String kasutajaNimi = TextIO.getlnString();
		kasutajaNimi = kasutajaNimi.substring(0, 1).toUpperCase() + kasutajaNimi.substring(1);
		System.out.format("Tere, %s\n", kasutajaNimi);
		kasutaja = Kasutaja.taastaKasutaja(kasutajaNimi);
		edetabel = Edetabel.taastaEdetabel();

		while (true) {
			System.out.println("Tee valik:  1. Lähme mängima; 2. Kuva edetabel 3. Välju");
			int sisestus = TextIO.getInt();

			// tegevus vastavalt menüü valikule
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
				seadistamine(); // salajane menüüvalik
				break;
			}
		}

		kasutaja.salvestaKasutaja();
		edetabel.salvestaEdetabel();
	}
	
	/** Kasutaja saab muuta erinevaid seadeid. */
	public static void seadistamine() {

		// lõputu tsükkel
		while (true) {
			System.out.println(
					"Tee valik, millist seadet soovid muuta: \n1. Saad muuta minimaalse arvu väärtust; \n2. Saad muuta maksimaalse arvu "
							+ "väärtust; \n3. Saad muuta mängu kestvust; \n4. Saad kustutada tulemused edetabelist; \n5. Läheb seadetest välja \n");
			int kasutajaSisestus = TextIO.getInt();
			if (kasutajaSisestus == 5) {
				break;
			}
			// tegevus vastavalt menüü valikule
			switch (kasutajaSisestus) {
			case 1:
				System.out.format("Muuda minimaalse arvu väärtust, praegune väärtus on %d\n", kasutaja.minArv);
				kasutaja.minArv = TextIO.getInt(); 
				break;
			case 2:
				System.out.format("Muuda maksimaalse arvu väärtust, praegune väärtus on %d\n", kasutaja.maxArv);
				kasutaja.maxArv = TextIO.getInt();
				break;
			case 3:
				System.out.format("Muuda mängukestvust, praegune mängukestvus on %d sekundit\n",
						kasutaja.maxManguKestvus);
				kasutaja.maxManguKestvus = TextIO.getInt();
				break;
			case 4: 
				edetabel.kustutaTulemused(); // kõikide tulemuste eemaldamine edetabelist
				break;
			}
		}
	}
	
	/**
	 * Kuvab edetabelis olevad tulemused punktisumma kahanevas järjekorras.
	 */
	public static void edetabeliKuvamine() {
		if (!edetabel.tulemused.isEmpty()) {

			// Sorteerime tulemuste järgi kasvavas järjekorras
			Collections.sort(edetabel.tulemused, comparing(Tulemus::getPunktiSumma));
			// Keerame kollektsiooni tagurpidi
			Collections.reverse(edetabel.tulemused);
			
			// foreach tsükkel 
			int reaArv = 1; // tulemuse koht tabelis
			for (Tulemus tulemus : edetabel.tulemused) {
				System.out.println(reaArv + ". " + tulemus.toString());
				reaArv++;
			}
		}
	}
	
	/**
	 * Genereerib juhuslikke arve, juhuslikud arvud korrutab vastava kordajaga läbi.Valib juhuslikult tehetemärgi.
	 * Kontrollib kas kasutaja sisestatud tulemus on võrdne süsteemi poolt arvutatud tulemusega.
	 */
	public static void arvutamine() {
		int teheteArv = 0;
		int oigedVastused = 0;
		long startTime = System.currentTimeMillis();
		
		Tulemus tulemus = new Tulemus(kasutaja.kasutajaNimi) ;

		while ((System.currentTimeMillis() - startTime) < (kasutaja.maxManguKestvus * 1000)) { // lõpetab mängu etteantud ajal 

			// genereeritakse juhuslik arv, mida kasutatakse tehtemärgina 
			int tehteMark = juhuslikarv(1, 4);
			// tegeliku tulemuse hoidmiseks
			int tegelikTulemus = 0;
			String tehe = "+";

			// teha kaks muutujat, mis hakkavad sisaldama juhuslikke arve
			int juhuslikArvYks = 0; 
			int juhuslikArvKaks = 0; 

			// muutujatele väärtused, tehtemärk ja tegeliku tulemuse arvutamine
			switch (tehteMark) {
			case 1: // liitmine, ääri nihutatakse liitmiskordaja abil
				juhuslikArvYks = juhuslikarv(kasutaja.minArv * kasutaja.liitmiseKordaja, kasutaja.maxArv * kasutaja.liitmiseKordaja);
				juhuslikArvKaks = juhuslikarv(kasutaja.minArv * kasutaja.liitmiseKordaja, kasutaja.maxArv * kasutaja.liitmiseKordaja);
				tegelikTulemus = juhuslikArvYks + juhuslikArvKaks;
				tehe = "+";
				break;
			case 2: // lahutamine
				juhuslikArvYks =  juhuslikarv(kasutaja.minArv * kasutaja.lahutamiseKordaja, kasutaja.maxArv * kasutaja.lahutamiseKordaja);
				juhuslikArvKaks = juhuslikarv(kasutaja.minArv * kasutaja.lahutamiseKordaja, kasutaja.maxArv * kasutaja.lahutamiseKordaja);
				tegelikTulemus = juhuslikArvYks - juhuslikArvKaks;
				tehe = "-";
				break;
			case 3: // korrutamine
				juhuslikArvYks = juhuslikarv(kasutaja.minArv * kasutaja.korrutamiseKordaja, kasutaja.maxArv * kasutaja.korrutamiseKordaja);
				juhuslikArvKaks = juhuslikarv(kasutaja.minArv * kasutaja.korrutamiseKordaja, kasutaja.maxArv * kasutaja.korrutamiseKordaja);
				tegelikTulemus = juhuslikArvYks * juhuslikArvKaks;
				tehe = "*";
				break;
			case 4: // jagamine
				juhuslikArvYks = juhuslikarv(kasutaja.minArv * kasutaja.jagamiseKordaja, kasutaja.maxArv * kasutaja.jagamiseKordaja);
				juhuslikArvKaks = juhuslikarv(kasutaja.minArv * kasutaja.jagamiseKordaja, kasutaja.maxArv * kasutaja.jagamiseKordaja);
				
				tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;

				// kontrollib kas jääk on 0, kui ei ole, siis arvutab uued arvud
				while (juhuslikArvYks % juhuslikArvKaks != 0) {
					juhuslikArvYks = juhuslikarv(kasutaja.minArv * kasutaja.jagamiseKordaja, kasutaja.maxArv * kasutaja.jagamiseKordaja);
					juhuslikArvKaks = juhuslikarv(kasutaja.minArv * kasutaja.jagamiseKordaja, kasutaja.maxArv * kasutaja.jagamiseKordaja);
					tegelikTulemus = juhuslikArvYks / juhuslikArvKaks;
				}

				tehe = "/";
				break;
			}

			System.out.format("Kui palju on %d %s %d \n", juhuslikArvYks, tehe, juhuslikArvKaks);
			int kasutajaTulemus = TextIO.getlnInt();
			// kui on aeg otsas väljub programmist
			if (((System.currentTimeMillis() - startTime) < (kasutaja.maxManguKestvus * 1000))) {
				// break;
			}
			if (tegelikTulemus == kasutajaTulemus) {
				System.out.print("\nTubli, õige vastus! ");
				oigedVastused++;
			} else {
				System.err.print("Oops, vale vastus. ");
			}
			teheteArv++;
			System.out.format("%d/%d. ", oigedVastused, teheteArv);
			System.out.format("Aega on jäänud %d sekundit \n\n",
					(startTime - System.currentTimeMillis()) / 1000 + kasutaja.maxManguKestvus);

		} // while tsükli lõpp
		tulemus.punktiSumma = (oigedVastused * 1000) - ((teheteArv - oigedVastused) * 100);
		edetabel.tulemused.add(tulemus);
		edetabeliKuvamine();	
	}
	
	/** Juhusliku arvu genereerimine
	 *
	 * @param min - Juhusliku arvu alampiir
	 * @param max - Juhusliku arvu ülempiir
	 * @return genereeritud juhuslik arv
	 */
	public static int juhuslikarv(int min, int max) {
		int arv = max - min + 1;
		int tulemusYks = (int) (Math.random() * arv + min);
		return tulemusYks;
	}
}
