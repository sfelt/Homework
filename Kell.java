package Homework;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Kell {
	public static void main(String[] args) {
		String kuupaevKellaAeg = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		System.out.println(kuupaevKellaAeg);

	}
}
