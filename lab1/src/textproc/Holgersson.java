package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		TextProcessor multi = new MultiWordCounter(REGIONS);
		
		List<TextProcessor> list = new ArrayList<>();
		list.add(new SingleWordCounter("nils"));
		list.add(new SingleWordCounter("norge"));
		
		Set<String> stopwords = new HashSet<String>(); 	
		Scanner s = new Scanner(new File("nilsholg.txt"));
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		
																			// en lämplig mängd skapas
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
		    stopwords.add(word);												// orden läses in från Scannern
		}																// ’scan’ och lagras i mängden
		scan.close();

		TextProcessor r = new GeneralWordCounter(stopwords);
		
	
		
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for(TextProcessor p:list){		//for each loop
				p.process(word);
			}
			multi.process(word);	//små bokstäver ingen ny for loop krävs för vi har redan en 
			r.process(word);
		}

		s.close();

		for(TextProcessor p:list) {	//den räknar hur många ordet kommer upp 
		p.report();
		
	}
		r.report();
	multi.report();
	
	long t1 = System.nanoTime();
	System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
}
	
}