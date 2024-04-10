package experiments;

import java.util.Date;

public class DateGen {

	public static void main(String[] args) {
		
		Date date = new Date();
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));

		
		
		
		System.out.println(date);
		System.out.println(date.toString());
		System.out.println(date.toString().replace(" ", "_").replace(":", "_"));

	}

}
