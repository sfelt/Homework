package Homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Seaded {
	public int minArv;
	public int maxArv;
	public int maxManguKestvus;
	public int maxKorrutis;

	public void salvestaSeaded() { // tuleb kindlasti m‰ngu lıpus v‰lja kutsuda
		// objekti serialiseerimine ja salvestamine
		try {
			FileOutputStream f_out = new FileOutputStream("kasutajaandmed.data");
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(this);
			obj_out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	// konstruktoris algv‰‰rtustatakse seaded vaikev‰‰rtustega, ei ole avalikuks kasutamiseks
	private Seaded() {
		minArv = 1;
		maxArv = 20;
		maxKorrutis = 100;
		maxManguKestvus = 60;
		}

	public static Seaded taastaSeaded() { // kasutatakse seadete objekti loomiseks
		
		Seaded taastatudSeaded = new Seaded(); // vaikeseadetega algv‰‰rtustamine

		try {
			FileInputStream f_in = new FileInputStream("seaded.ser");

			ObjectInputStream obj_in = new ObjectInputStream(f_in);

			Object obj = obj_in.readObject();
			if (obj instanceof Seaded) {
				taastatudSeaded = (Seaded) obj;
			}

		} catch (Exception ex) {
			// ex.printStackTrace(); // tuleks ‰ra peita, sest igal juhul algv‰‰rtustatakse
		}
			
		return taastatudSeaded;
	}
}
