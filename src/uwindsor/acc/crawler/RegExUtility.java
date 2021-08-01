package uwindsor.acc.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtility {
	
	/**
	 * @param file
	 * @return list of Urls in file
	 * @throws IOException
	 */
	public static ArrayList<String> findURLs(File file) throws IOException{
		ArrayList<String> urls = new ArrayList<String>();
		Pattern pattern = Pattern.compile("href=\"/[\\w]+(/[.\\w]+)*");
		String text = new String(Files.readAllBytes(Paths.get(file.getPath())));
		Matcher  m = pattern.matcher(text);
		while(m.find()) {
			urls.add(m.group());
		}
		File url = new File("url.txt");
		url.createNewFile();
		Files.write(Paths.get(url.getName()),urls,StandardOpenOption.APPEND);
		return urls;
	}
	
	
	/**
	 * @param file
	 * @return list of phone numbers 
	 * @throws IOException
	 */
	public static ArrayList<String> findPhoneNumbers(File file) throws IOException{
		ArrayList<String> phoneNumbers = new ArrayList<String>();
		String ccode = "(\\+[\\d]{1,2}-)?";
		String p1 = "(\\(?[\\d]{3}\\)?)";
		String c1 = "([- ])+";
		String p2 = "([\\d]{3})";
		String c2 = "([- ])+";
		String p3 = "([\\d]{4})";
		String p = ccode.concat(p1).concat(c1).concat(p2).concat(c2).concat(p3);
		Pattern pattern = Pattern.compile(p);
		String text = new String(Files.readAllBytes(Paths.get(file.getPath())));
		Matcher  m = pattern.matcher(text);
		while(m.find()) {
			phoneNumbers.add(m.group());
		}
		File phone = new File("phone.txt");
		phone.createNewFile();
		Files.write(Paths.get(phone.getName()),phoneNumbers,StandardOpenOption.APPEND);
		return phoneNumbers;
	}
	
	
	/**
	 * @param file
	 * @return list of email addresses
	 * @throws IOException
	 */
	public static ArrayList<String> findEmailIds(File file) throws IOException{
		ArrayList<String> emailIds = new ArrayList<String>();
		Pattern pattern = Pattern.compile("[-\\w.]+@([\\w]+\\.[-\\w]+)+");
		String text = new String(Files.readAllBytes(Paths.get(file.getPath())));
		Matcher  m = pattern.matcher(text);
		while(m.find()) {
			
			emailIds.add(m.group());
		}
		File email = new File("email.txt");
		email.createNewFile();
		Files.write(Paths.get(email.getName()),emailIds,StandardOpenOption.APPEND);
		return emailIds;
	}
	
	public static void findPhoneNumbers() throws IOException {
		File phone = new File("phone.txt");
		phone.delete();
		File[] files = new File("W3C Web Pages\\Text1").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				if(findPhoneNumbers((files[i])).size()!=0) {
					System.out.println(files[i].getName()+" "+findPhoneNumbers(files[i]));
				}
			}
		}
	}
	
	public static void findEmailIds() throws IOException {
		File email = new File("email.txt");
		email.delete();
		File[] files = new File("W3C Web Pages\\Text1").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				if(findEmailIds((files[i])).size()!=0) {
					System.out.println(files[i].getName()+" "+findEmailIds(files[i]));
				}
				
			}
			
		}
	}
	
	public static void findURLs() throws IOException {
		File url = new File("url.txt");
		url.delete();
		File[] files = new File("W3C Web Pages").listFiles();
		for (int i = 0; i < files.length; i++) {
			if(files[i].isFile()) {
				if(findURLs((files[i])).size()!=0) {
					System.out.println(files[i].getName()+" "+findURLs(files[i]));
				}
			}
		}
	}
	
}
