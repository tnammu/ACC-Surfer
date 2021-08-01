package uwindsor.acc.textprocessing;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Suggestions {
	
	public static TST<Integer> tst = new TST<Integer>();
	public static void buildTST(File file) throws Exception{
		String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
		//tokenize text to extract only words
		Pattern pat = Pattern.compile("[\\w]+");
		Matcher  m = pat.matcher(str);
		ArrayList<String> words= new ArrayList<String>();
		while(m.find()) {
				words.add(m.group());
			}
		for (int i = 0; i < words.size(); i++) {
			if(tst.contains(words.get(i))) {
				tst.put(words.get(i), tst.get(words.get(i))+1);
			}
			else
			tst.put(words.get(i),1);
		}	
	}
	
	public static ArrayList<String> getSuggestions(String keyword){
		Queue<String> suggestions = (Queue<String>) tst.prefixMatch(keyword);
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i < suggestions.size(); i++) {
			strings.add(suggestions.dequeue());
		}
		return strings;
	}
	
	public static void main(String[] args) throws Exception{
		File path = new File("W3C Web Pages\\Text");
		File[] listOfFiles = path.listFiles();
		for (File file : listOfFiles) {
			buildTST(file);
		}
		System.out.println(getSuggestions("We"));
	}
}
