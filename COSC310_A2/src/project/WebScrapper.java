package project;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner; 

public class WebScrapper {
	
	public static Scanner scan = new Scanner(System.in);
	
	private static ArrayList <String> genreList = new ArrayList<String>();
	private static ArrayList<String> genre = new ArrayList<String>();
	private static ArrayList<String> list = new ArrayList<String>();
	
	private static Document document;
	
	
	public WebScrapper(){
		
		final String URLmovies = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
		
		final String URLtvshows = "https://www.imdb.com/chart/toptv/?ref_=nv_tvv_250";
		
		String URLgenre = "";
		
		try{
			int count2 = 0;
			while(count2 >= 0) {
				if(count2 > 0) {
					genreList.clear();
					genre.clear();
					list.clear();
					System.out.println("Would you like another recomedation? (Type no to quit)");
					if(scan.nextLine().equals("no"))
						break;
				}
				int count = 0;
				String input = "";
				
				while(count >= 0) {
					if(count > 0)
						System.out.print("I am sorry, ");
					System.out.println("Do you want movies or tvshows recommendations?");
					// make user input easier to compare with potential responses
					input = scan.nextLine().toLowerCase();
					
					if(input.equals("movies")) {
						document = Jsoup.connect(URLmovies).get();
						break;
					}
					else if(input.equals("tvshows")){
						document = Jsoup.connect(URLtvshows).get();
						break;
					}
					count++;
				}
				
				count = 0;
				while(count >= 0) {
					if(count > 0)
						System.out.print("I am sorry, ");
					
					System.out.println("Do you want a recomendation based on genre or just rankings?");
					input = scan.nextLine().toLowerCase();
					if(input.equals("rankings")) {
						listRanking(document, list);
						printList(list);
						break;
					}
					else if(input.equals("genre")) {
						listGenre(document, genre);
						printList(genre);
						System.out.println("Which genre would you like? (Input the number)");
						input = scan.nextLine();
						URLgenre = setUrl(document, input);
						
						Document genreRanked = Jsoup.connect(URLgenre).get();
						
						listGenreRanking(genreRanked, genreList);
					
						
						printList(genreList);
						
						break;
					}
					count++;
				}
				count2++;
			}
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listGenreRanking(Document doc, ArrayList<String> li) {
		int i = 1;
	
		for(Element row : doc.select(".lister-item-header a")) {
			genreList.add(row.text());
			i++;
			if (i == 11)
				break;
		}
	
	}
	
	public static String setUrl(Document doc, String input) {
		int i = Integer.parseInt(input) - 1;
		String url = "";
		Elements elem = doc.select(".quicklinks .subnav_item_main a[href]");
		Element theelem = elem.get(i);
		url = theelem.attr("href");
		url = "https://www.imdb.com" + url;
		
		
		return url;
	}
	
	
	public static void listGenre(Document doc, ArrayList<String> li) {
		for(Element row : doc.select(".quicklinks li")) {
			li.add(row.text());
		}
		
	}
	
	public static void listRanking(Document doc, ArrayList<String> li) {
		int i = 1;
		for(Element row : doc.select(".lister-list .titleColumn a")) {
			li.add(row.text());
			i++;
			if (i == 11)
				break;
		}
		
	}
	
	public static void printList(ArrayList<String> al) {
		int i = 1;
		for(String str : al) {
			System.out.println(i + ". " +str);
			i++;
		}
	}
	
}