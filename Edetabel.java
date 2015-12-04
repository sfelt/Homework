package Homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Edetabel sisaldab kõikide mängijate tulemusi.
 * Serialiseeritav selleks, et saaks salvestada faili ja taastada.
 * @author Sigrid
 *
 */
public class Edetabel implements java.io.Serializable {
	
	/** Kollektsioon Tulemuste hoimdiseks */
	public ArrayList<Tulemus> tulemused;

	/** Konstruktor on peidetud, kuna kasutatakse failist taastamist */
	private Edetabel() {
		tulemused = new ArrayList<>();
	}

	/** Edetabeli tühjendamine */
	public void kustutaTulemused() {
		tulemused = new ArrayList<>();
	}

	/** 
	 * Edetabeli taastamine failist. Kui failist taastamine ei õnnestu, siis tehakse uus tühi Edetabel.
	 * @return tagastab edetabeli.
	 */
	public static Edetabel taastaEdetabel() {
		Edetabel edetabel = null;

		try {
			FileInputStream loebBaididFailist = new FileInputStream("edetabel.data");// failiga
			ObjectInputStream loebBaididObjektiks = new ObjectInputStream(loebBaididFailist); // ühendatakse
			Object obj = loebBaididObjektiks.readObject();
			if (obj instanceof Edetabel) {
				edetabel = (Edetabel) obj;
			}
			loebBaididObjektiks.close();
		} catch (Exception ex) {
			edetabel = new Edetabel();
		}
		return edetabel;
	}

	/** Serialiseerib ja salvestab edetabel faili */ 
	public void salvestaEdetabel() {
		// Proovib salvestada
		try {
			FileOutputStream kirjutabBaididFaili = new FileOutputStream("edetabel.data");
			ObjectOutputStream kirjutabObjektiBaitideks = new ObjectOutputStream(kirjutabBaididFaili);
			kirjutabObjektiBaitideks.writeObject(this);
			kirjutabObjektiBaitideks.close();
		} catch (Exception ex) {
			ex.printStackTrace(); // trükib veateate, kui ei õnnestu salvestada
		}
	}
}
