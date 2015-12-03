package Homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Edetabel {
	
	
	public static Edetabel taastatudEdetabel () {
		Edetabel edetabel = null;
	try {
		FileInputStream loebBaididFailist = new FileInputStream("edetabel.data");//failiga ühendatakse

		ObjectInputStream loebBaididObjektiks = new ObjectInputStream(loebBaididFailist); //ühendatakse failiga

		Object obj = loebBaididObjektiks.readObject();
		if (obj instanceof Edetabel) {
			edetabel = (Edetabel) obj;
		}

	} catch (Exception ex) {
		edetabel = new Edetabel();
		
	}

	return edetabel;
}

// tuleb kindlasti mängu lõpus välja kutsuda objekti serialiseerimine ja salvestamine
public void salvestaEdetabel() { 
	try {
		
		FileOutputStream kirjutabBaididFaili = new FileOutputStream("edetabel.data");
		ObjectOutputStream kirjutabObjektiBaitideks = new ObjectOutputStream(kirjutabBaididFaili);
		kirjutabObjektiBaitideks.writeObject(this);
		kirjutabObjektiBaitideks.close();
	} catch (Exception ex) {
		ex.printStackTrace();
	}
}

}


