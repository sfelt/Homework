package Homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Kasutaja implements java.io.Serializable {

	public String kasutajaNimi;
	public int minArv;
	public int maxArv;
	public int maxManguKestvus;

	private Kasutaja() {
		minArv = 1;
		maxArv = 20;
		maxManguKestvus = 60;
	}

	// kasutatakse seadete objekti loomiseks
	public static Kasutaja taastaKasutaja(String kasutajaNimi) {

		Kasutaja kasutaja = null;

		try {
			String failiNimi = kasutajaNimi + ".data";
			FileInputStream f_in = new FileInputStream(failiNimi);

			ObjectInputStream obj_in = new ObjectInputStream(f_in);

			Object obj = obj_in.readObject();
			if (obj instanceof Kasutaja) {
				kasutaja = (Kasutaja) obj;
			}

		} catch (Exception ex) {
			kasutaja = new Kasutaja();
			kasutaja.kasutajaNimi = kasutajaNimi;
		}

		return kasutaja;
	}

	// tuleb kindlasti mängu lõpus välja kutsuda objekti serialiseerimine ja salvestamine
	public void salvestaKasutaja() { 
		try {
			String failiNimi = kasutajaNimi + ".data";
			FileOutputStream f_out = new FileOutputStream(failiNimi);
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(this);
			obj_out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
