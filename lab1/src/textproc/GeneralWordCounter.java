package textproc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {

	private Map<String, Integer> m= new HashMap<String, Integer>();
	private Set<String> stopwords = new HashSet<String>(); 				        // en lämplig mängd skapas
																				// orden läses in från Scannern
																				// ’scan’ och lagras i mängden

		
	public GeneralWordCounter(Set<String> word) {
		stopwords=word;
		 
	}
																	
	@Override
	public void process(String w) {
		// TODO Auto-generated method stub
		//om ordet är undantagsord gör inget 
		if (!stopwords.contains(w) ) {				//om w inte är stopword 
			if (!m.containsKey(w)) {				//om w inte finns i map 
				m.put(w, 1);						//lägg ordet i map
			}	
			else {
				int count = m.get(w);					//annars 			 
				count++;
				m.replace(w, count);
				
			}
		}
	}

	@Override
	public void report() {
		// TODO Auto-generated method stub
		
//		for (String key : m.keySet()) {	
//		if (m.get(key)>=200) {
//			System.out.println(key+ ":" + m.get(key));
//		}
//	}
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		
		wordList.sort(new WordCountComparator());
		
		for (int i=0; i<14418; i++) {
		System.out.println(wordList.get(i));
		}
	}
	
	public Set<Map.Entry<String, Integer>> getWords(){		//metod från lab 3
		return m.entrySet();
	}
	
	public Set <String> getKeySet(){
	return m.keySet();					//returnerar ett set med värden från hashmap av typen String
	}
}