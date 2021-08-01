package uwindsor.acc.textprocessing;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InvertedIndexBuilder {
		
		// used to store inverted index in form "Keyword , (FileName, Number of occurrences)
		public static HashMap<String,HashMap<String, Integer>> index = 
				new HashMap<String, HashMap<String,Integer>>();
		public static void invertedIndex(File file) throws Exception {
			
			// read file
			String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
			
					
			//tokenise text to extract only words
			Pattern pat = Pattern.compile("[\\w]+");
			Matcher  m = pat.matcher(str);
			ArrayList<String> words= new ArrayList<String>();
			while(m.find()) {
					words.add(m.group());
				}
			
			
			//build index
			for (String word : words) {
				//if index already contains word.
				if(index.containsKey(word)) {
					
					HashMap<String, Integer> hashMap = index.get(word);
					//number of occurrences
					if(hashMap.containsKey(file.getName())) {
						int n = hashMap.get(file.getName());
						hashMap.put(file.getName(), n+1);
						index.put(word, hashMap);
					}
					else {
						hashMap.put(file.getName(), 1);
						index.put(word, hashMap);
					}
					
				}
				else {
					HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
					hashMap.put(file.getName(), 1);
					index.put(word, hashMap);
				}
			}
		}
}

