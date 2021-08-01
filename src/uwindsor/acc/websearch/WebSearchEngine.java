package uwindsor.acc.websearch;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import uwindsor.acc.sorting.Ranking;
import uwindsor.acc.textprocessing.HTMLToText;
import uwindsor.acc.textprocessing.InvertedIndexBuilder;
import uwindsor.acc.textprocessing.SpellChecker;
import uwindsor.acc.textprocessing.Suggestions;

public class WebSearchEngine {
	public static void preProcessor() throws Exception{
		long startTime,endTime;
		System.out.println("Pre-Processing started......................................");
		System.out.println("Converting HTML files to TEXT...............................");
		
		//(1) converting HTML files to TEXT for processing
		File[] files = new File("W3Schools Web Pages").listFiles();
		startTime=System.nanoTime();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile())
			HTMLToText.convert(files[i]);
		}
		endTime = System.nanoTime();
		System.out.println("Time consumed for converting HTML to TEXT: "+(endTime-startTime)+" ns");
		
		//(2) Build record-level-inverted index using hashMap.
		System.out.println("Reading files and building Record Level Inverted Index............................");
		startTime= System.nanoTime();
		File path = new File("W3Schools Web Pages\\Text");
		File[] filesList = path.listFiles();
		for (File file : filesList) {
			InvertedIndexBuilder.invertedIndex(file);
		}
		endTime = System.nanoTime();
		System.out.println("Time consumed for building inverted Index: "+(endTime-startTime)+" ns");
		
		//step 3. ReStructure inverted index to use binary heap.
		System.out.println("Restructuring the inverted index to use Binary Heaps.........................");
		startTime=System.nanoTime();
		Ranking.reStructure();
		endTime = System.nanoTime();
		System.out.println("Time consumed for restructuring Inverted Index: "+(endTime-startTime)+" ns");
		
		//step 4. Construct a TST for suggestions / autocomplete.
		System.out.println("Construsting a TST with all words");
		startTime = System.nanoTime();
		for (File file : filesList) {
			Suggestions.buildTST(file);
		}
		endTime = System.nanoTime();
		System.out.println("Time consumed for building TST: "+(endTime-startTime)+" ns");
		System.out.println("Pre-Processing ended......................................");
	}
	public static void main(String[] args) throws Exception{
		long startTime,endTime;
		startTime = System.nanoTime();
		preProcessor();
		endTime = System.nanoTime();
		System.out.println("Time consumed for all preprocessing activities: "+(endTime-startTime)+" ns");
		int option = 0;
		Scanner in = new Scanner(System.in);
		String keyword;
		while(true) {
			
			switch (option) {
			case 1:
				System.out.println("Enter a keyword to Search :");
				keyword = in.next();
				startTime = System.nanoTime();
				Ranking.search(keyword, 5);
				endTime = System.nanoTime();
				System.out.println("Time consumed for searching a keyword : "+(endTime-startTime)+" ns");
				break;
			case 2:
				System.out.println("Enter a keyword to get suggestions :");
				keyword = in.next();
				startTime = System.nanoTime();
				ArrayList<String> suggestions= Suggestions.getSuggestions(keyword);
				endTime = System.nanoTime();
				System.out.println("Time consumed for getting suggestions: "+(endTime-startTime)+" ns");
				for (String string : suggestions) {
					System.out.println(string);
				}
				break;
			case 3:
				System.out.println("Enter a keyword to check spelling:");
				keyword = in.next();
				startTime = System.nanoTime();
				SpellChecker.spellCheck("keyword");
				endTime = System.nanoTime();
				break;
			case 4:
				System.out.println("Exiting");
				in.close();
				System.exit(0);
				break;
			default:
				break;
			}
			System.out.println("Select any option from below:");
			System.out.println("1. Keyword Search\n2. Get Suggestions\n3. Spell Check\n4. Exit\n");
			option = in.nextInt();
		}
		
	}
	
}
