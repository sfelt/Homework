package Homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Sisaldab ühte tulemust.
 * kasutatakse Edetabeli koostamnisel.
 * @author Sigrid
 *
 */
public class Tulemus implements java.io.Serializable {

	/** Tulemuse tekkimise aeg */
	public LocalDateTime aeg;
	/** Mängu punktismma */
	public int punktiSumma;
	/** kes mängis */
	public String kasutajaNimi;

	/** getter tehtud selleks, et Collections.sort nõuab meetodit */ 
	public int getPunktiSumma() {
		return punktiSumma;
	}
	
	/**
	 * Kirjutab üle toString meetod selleks, et edetabeli kirjet kuvada.
	 * @return Väljastamiseks sobiv edetabeli rida 
	 */
	@Override
	public String toString() {
		/* Uus aja vormindaja selleks, et aeg oleks Eesti korrektne */
		DateTimeFormatter ajaVormindaja = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		/* Vormindatud aeg */ 
		String vormindatudAeg = aeg.format(ajaVormindaja); 
		
		return String.format("%d %s %s", punktiSumma, kasutajaNimi, vormindatudAeg);
	}

	/**
	 * Konstruktor, mis teeb uue tulemuse ja väärtustab kasutajanime
	 * ning aja.
	 * @param kasutajaNimi
	 */
	public Tulemus(String kasutajaNimi){
		this.kasutajaNimi = kasutajaNimi;
		this.aeg = LocalDateTime.now();
	}
}
