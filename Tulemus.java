package Homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author Sigrid
 *
 */
public class Tulemus implements java.io.Serializable {
	
	public LocalDateTime aeg;
	public int punktiSumma;
	public String kasutajaNimi;
	
	public int getPunktiSumma() {
		return punktiSumma;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		
		DateTimeFormatter ajaVormindaja = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		String vormindatudAeg = aeg.format(ajaVormindaja); 
		
		return String.format("%d %s %s", punktiSumma, kasutajaNimi, vormindatudAeg);
	}
	
	public Tulemus(String kasutajaNimi){
		this.kasutajaNimi = kasutajaNimi;
		this.aeg = LocalDateTime.now();
	}
}
