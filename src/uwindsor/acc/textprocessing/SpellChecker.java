package uwindsor.acc.textprocessing;

import java.io.File;
public class SpellChecker {
	public static void spellCheck(String misSpelled) {
		if(Suggestions.tst.contains(misSpelled)) {
			System.out.println("Spelling found in dictionary");
			return;
		}
		
		Queue<String> strings =  (Queue<String>) Suggestions.tst.prefixMatch(misSpelled.substring(0,1));
		int[] distance = new int[strings.size()];
		int i = 0;
		int flag = 0;
		for (String string : strings) {
			distance[i++] = Sequences.editDistance(misSpelled, string);
			if(distance[i-1] == 1) {
				flag=1;
				System.out.println("Did you mean ?"+ string);
			}
		}
		
		if(flag == 0 )
			System.out.println("No related words found");
	}
}
