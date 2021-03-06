package Homework;

import static java.util.Comparator.comparing;
import java.util.Collections;

/**
 * Peastarvutamise treenimise m�ng.
 * Tulemused hoitakse edetabelis.
 * @author Sigrid Felt IA 18
 */
public class Projekt {	

	/** Hoiab kasutaja seadeid */
	public static Kasutaja kasutaja;
	/** Edetabel, sisaldab tulemusi */ 
	public static Edetabel edetabel;

	/** main meetod, k�ivitab programmi */
	public static void main(String[] args) {
		
		System.out.println("Peastarvutamise programm. Autor: Sigrid Felt, IA18");
		System.out.println("Sisesta oma nimi");
		String kasutajaNimi = TextIO.getlnString();
		kasutajaNimi = kasutajaNimi.substring(0, 1).toUpperCase() + kasutajaNimi.substring(1);
		System.out.format("Tere, %s\n", kasutajaNimi);
		kasutaja = Kasutaja.taastaKasutaja(kasutajaNimi);
		edetabel = Edetabel.taastaEdetabel();

		while (true) {
			System.out.println("Tee valik:  1. L�hme m�ngima; 2. Kuva edetabel 3. V�lju");
			int sisestus = TextIO.getInt();

			// tegevus vastavalt men�� valikule
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
				seadistamine(); // salajane men��valik
				break;
			}
		}

		kasutaja.salvestaKasutaja();
		edetabel.salvestaEdetabel();
	}
	
	/** Kasutaja saab muuta erinevaid seadeid. */
	public static void seadistamine() {

		// l�putu ts�kkel
		while (true) {
			System.out.println(
					"Tee valik, millist seadet soovid muuta: \n1. Saad muuta minimaalse arvu v��rtust; \n2. Saad muuta maksimaalse arvu "
							+ "v��rtust; \n3. Saad muuta m�ngu kestvust; \n4. Saad kustutada tulemused edetabelist; \n5. L�heb seadetest v�lja \n");
			int kasutajaSisestus = TextIO.getInt();
			if (kasutajaSisestus == 5) {
				break;
			}
			// tegevus vastavalt men�� valikule
			switch (kasutajaSisestus) {
			case 1:
				System.out.format("Muuda minimaalse arvu v��rtust, praegune v��rtus on %d\n", kasutaja.minArv);
				kasutaja.minArv = TextIO.getInt(); 
				break;
			case 2:
				System.out.format("Muuda maksimaalse arvu v��rtust, praegune v��rtus on %d\n", kasutaja.maxArv);
				kasutaja.maxArv = TextIO.getInt();
				break;
			case 3:
				System.out.format("Muuda m�ngukestvust, praegune m�ngukestvus on %d sekundit\n",
						kasutaja.maxManguKestvus);
				kasutaja.maxManguKestvus = TextIO.getInt();
				break;
			case 4: 
				edetabel.kustutaTulemused(); // k�ikide tulemuste eemaldamine edetabelist
				break;
			}
		}
	}
	
	/**
	 * Kuvab edetabelis olevad tulemused punktisumma kahanevas j�rjekorras.
	 */
	public static void edetabeliKuvamine() {
		if (!edetabel.tulemused.isEmpty()) {

			// Sorteerime tulemuste j�rgi kasvavas j�rjekorras
			Collections.sort(edetabel.tulemused, comparing(Tulemus::getPunktiSumma));
			// Keerame kollektsiooni tagurpidi
			Collections.reverse(edetabel.tulemused);
			
			// foreach ts�kkel 
			int reaArv = 1; // tulemuse koht tabelis
			for (Tulemus tulemus : edetabel.tulemused) {
				System.out.println(reaArv + ". " + tulemus.toString());
				reaArv++;
			}
		}
	}
	
	/**
	 * Genereerib juhuslikke arve, juhuslikud arvud korrutab vastava kordajaga l�bi.Valib juhuslikult tehetem�rgi.
	 * Kontrollib kas kasutaja sisestatud tulemus on v�rdne s�steemi poolt arvutatud tulemusega.
	 */
	public static void arvutamine() {
		int teheteArv = 0;
		int oigedVastused = 0;
		long startTime = System.currentTimeMillis();
		
		Tulemus tulemus = new Tulemus(kasutaja.kasutajaNimi) ;

		while ((System.currentTimeMillis() - startTime) < (kasutaja.maxManguKestvus * 1000)) { // l�petab m�ngu etteantud ajal 

			// genereeritakse juhuslik arv, mida kasutatakse tehtem�rgina 
			int tehteMark = juhuslikarv(1, 4);
			// tegeliku tulemuse hoidmiseks
			int tegelikTulemus = 0;
			String tehe = "+";

			// teha kaks muutujat, mis hakkavad sisaldama juhuslikke arve
			int juhuslikArvYks = 0; 
			int juhuslikArvKaks = 0; 

			// muutujatele v��rtused, tehtem�rk ja tegeliku tulemuse arvutamine
			switch (tehteMark) {
			case 1: // liitmine, ��ri nihutatakse liitmiskordaja abil
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

				// kontrollib kas j��k on 0, kui ei ole, siis arvutab uued arvud
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
			// kui on aeg otsas v�ljub programmist
			if (((System.currentTimeMillis() - startTime) < (kasutaja.maxManguKestvus * 1000))) {
				// break;
			}
			if (tegelikTulemus == kasutajaTulemus) {
				System.out.print("\nTubli, �ige vastus! ");
				oigedVastused++;
			} else {
				System.err.print("Oops, vale vastus. ");
			}
			teheteArv++;
			System.out.format("%d/%d. ", oigedVastused, teheteArv);
			System.out.format("Aega on j��nud %d sekundit \n\n",
					(startTime - System.currentTimeMillis()) / 1000 + kasutaja.maxManguKestvus);

		} // while ts�kli l�pp
		tulemus.punktiSumma = (oigedVastused * 1000) - ((teheteArv - oigedVastused) * 100);
		edetabel.tulemused.add(tulemus);
		edetabeliKuvamine();	
	}
	
	/** Juhusliku arvu genereerimine
	 *
	 * @param min - Juhusliku arvu alampiir
	 * @param max - Juhusliku arvu �lempiir
	 * @return genereeritud juhuslik arv
	 */
	public static int juhuslikarv(int min, int max) {
		int arv = max - min + 1;
		int tulemusYks = (int) (Math.random() * arv + min);
		return tulemusYks;
	}
}
