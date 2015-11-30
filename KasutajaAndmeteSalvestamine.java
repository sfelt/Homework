package Homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import praktikum3.TextIO;

public class KasutajaAndmeteSalvestamine {

	public static void main(String[] args) {
		KasutajaAndmed kasutaja = new KasutajaAndmed();
		System.out.println("Sisesta oma nimi");
		kasutaja.kasutajaNimi = TextIO.getln();
		
		// objekti serialiseerimine ja salvestamine
		try {
			FileOutputStream f_out = new FileOutputStream("kasutajaandmed.data");
			ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(kasutaja);
			obj_out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//objekti taastamine ja deserialiseerimine
		// Read from disk using FileInputStream
		try {
		FileInputStream f_in = new 
			FileInputStream("kasutajaandmed.data");

		// 
		ObjectInputStream obj_in = 
			new ObjectInputStream (f_in);

		 
		Object obj = obj_in.readObject();
		if (obj instanceof KasutajaAndmed)
		{
			// 
			KasutajaAndmed taastatudKasutaja = (KasutajaAndmed) obj;
			System.out.println(taastatudKasutaja.kasutajaNimi);			
			
		}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		


	}
}

//kopeeritud kood
