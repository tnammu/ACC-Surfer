package uwindsor.acc.crawler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {

	public static Set<String> visited = new HashSet<String>();
	public static Set<String> unvisited = new HashSet<String>();
	public static String baseUrl = "https://www.w3schools.com";
	
	public static void visit(String url) throws IOException {
		try {
			visited.add(url);
			Document document = Jsoup.connect(url).get();
			String path = "W3Schools Web Pages";
			new File(path).mkdirs();
			PrintWriter out = new PrintWriter(path + "\\" + document.title() + ".html");
			out.println(document.html());
			out.close();
			
			Elements links = document.select("a[href]");
			
			for (Element link : links) {
				unvisited.add(link.absUrl("href"));
			}
		} catch (Exception e) {
			System.out.println(e);
			
		}
		finally {
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		visit(baseUrl);
		while(visited.size()<=1000) {
			String[] s = new String[unvisited.size()];
			int i = 0;
			for (String string : unvisited) {
				s[i++] = string;
			}
			for (int j = 0; j < s.length; j++) {
				if(visited.size()>=1000)break;
				if(!visited.contains(s[j]))
				visit(s[j]);
			}
			
		}
	}
	
	
}
