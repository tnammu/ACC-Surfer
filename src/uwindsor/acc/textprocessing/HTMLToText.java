package uwindsor.acc.textprocessing;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;

public class HTMLToText {
	
	/**
	 * @param file HTML file that is to be converted to plain text
	 * @return Plain text of given HTML file
	 * Also Writes the plain text to a file in $currentdirectory/Text1 folder
	 * @throws IOException
	 */
	public static File convert(File file) throws IOException {
		
		org.jsoup.nodes.Document doc =Jsoup.parse(file, "UTF-8");
		String path = file.getPath();
		path = path.substring(0, path.indexOf("\\")+1);
		new File(path+"\\"+"Text").mkdirs();
		path = path.concat("Text\\"+file.getName().substring(0,file.getName().length()-4)+"txt");
		
		PrintWriter out = new PrintWriter(path);
		out.println(doc.wholeText());
		out.close();
		return new File(path);
	}
	public static void main(String[] args) throws IOException {
		File[] files = new File("W3Schools Web Pages").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile())
			convert(files[i]);
		}
		
	}
}
