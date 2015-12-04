package Homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Kasutaja andmete ja seadistuste hoidmiseks.
 * Salvestatakse programmist lahkumisel, taastatakse m�ngu alustamuisel.
 * 
 * @author Sigrid
 *
 */
public class Kasutaja implements java.io.Serializable {

	/** Kasutajanime hoimidmiseks */
	public String kasutajaNimi;
	/** Juhuslikuarvu alumine piir */
	public int minArv;
	/** Juhuslikuarvu �lemine piir */
	public int maxArv;
	/** M�ngu kestvus sekundites, vaikimisi 60 sek. */
	public int maxManguKestvus;
	/** Nihutab piire liitmisel */
	public int liitmiseKordaja = 5;
	/** Nihutab piire lahutamisel */
	public int lahutamiseKordaja = 4;
	/** Nihutab piire korrutamisel */
	public int korrutamiseKordaja = 3;
	/** Nihutab piire jagamisel */
	public int jagamiseKordaja = 2;

	/** Konstruktor on peidetud selleks, et kasutaja andmete 
	 * taastamine v�i algv��rtustamine oleks �he meetodiga tehtav.
	 */
	private Kasutaja() {
		minArv = 1;
		maxArv = 20;
		maxManguKestvus = 60;
		liitmiseKordaja = 5;
		lahutamiseKordaja = 4;
		korrutamiseKordaja = 3;
		jagamiseKordaja = 2;
	}

	/**
	 * 
	 * @param kasutajaNimi - kasutaja poolt m�ngu alguses sisestatav nimi
	 * @return tagastab kasutaja objekti
	 */
	public static Kasutaja taastaKasutaja(String kasutajaNimi) {

		Kasutaja kasutaja = null;

		/** Proovime failist kasutaja andmeid sisse lugeda */
		try {
			String failiNimi = kasutajaNimi + ".data"; // kettal oleva faili nimi
			FileInputStream loebBaididFailist = new FileInputStream(failiNimi); // avab faili lugemiseks
			ObjectInputStream loebBaididObjektiks = new ObjectInputStream(loebBaididFailist);

			// loetakse baidid failist ja moodustatakse objekt
			Object obj = loebBaididObjektiks.readObject();

			// kas objekt kuulub Kasutaja klassi?
			if (obj instanceof Kasutaja) {
				kasutaja = (Kasutaja) obj;
			}
			// sulegda voog
			loebBaididObjektiks.close();

		// kui tekib (�ksk�ik mis) viga, siis tehakse uus kasutaja vaikeseadetega			
		} catch (Exception ex) {
			kasutaja = new Kasutaja(); // peidetud konstruktori kasutamine
			kasutaja.kasutajaNimi = kasutajaNimi;
		}

		// tagastab kasutaja, kas taastatud v�i vaikeseadetegea uus 
		return kasutaja;
	}

	/**
	 * Serialiserib ja salvestab kasutaja seaded kasutajanimelisse faili
	 */
	public void salvestaKasutaja() { 

		/* Proovime kasutaja andmeid salvestada faili */		
		try {
			String failiNimi = kasutajaNimi + ".data";

			/* avab �henduse failiga */
			FileOutputStream kirjutabBaididFaili = new FileOutputStream(failiNimi);
			
			/* avab �henduse objektiga ja �hendub faili�hendusega */
			ObjectOutputStream kirjutabObjektiBaitideks = new ObjectOutputStream(kirjutabBaididFaili);
			/* salvestab objekti failiks */
			kirjutabObjektiBaitideks.writeObject(this);
			/* sulgeb �henduse */
			kirjutabObjektiBaitideks.close();
		} catch (Exception ex) {
			ex.printStackTrace(); /* tr�kib v�lja veateate */
		}
	}
}

