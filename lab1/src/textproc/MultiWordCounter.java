package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor{
//varje klass måste implementera alla metoder från interfacet

	private Map<String, Integer> m= new HashMap<String, Integer>();	//map för att vi ska kunna söka värde och hur många gånger det upprepas 
																	//map(lista av par key  är interface och därför kan ej skapa objekt
																	//därför använder HashMap 
	
	public MultiWordCounter(String [] word) {						//konstruktorn
		for(String s: word) {										
			m.put(s, 0);											//loop där word (landsskap) läggs till i listan	map
		}															// =0 för att vi har inte börjat räkna än
	}
	
	public void process(String w) {									// en metod som söker efter key (w) 
		if(m.containsKey(w)) {										
			int count=m.get(w);										// om det finns finns lägg den i count
			count++;												//öka
			m.put(w, count);										//lägg till i map 	
		}
	}
	
	public void report() {
		for (String key : m.keySet()) {								//key läggs till i m (listan) 

			System.out.println(key + ": " + m.get(key));			//skriver ut både key och value
		}
		
		
	}
}