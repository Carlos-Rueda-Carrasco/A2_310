package webscrape;


import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner; 

public class WebScrape {
	
	public static Scanner scan = new Scanner(System.in);
	
	private static ArrayList<String> genre = new ArrayList<String>();
	private static ArrayList<String> list = new ArrayList<String>();
	
	private static Document document;
	
	
	public static void main(String[] args){
		
		final String URLmovies = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
		
		final String URLtvshows = "https://www.imdb.com/chart/toptv/?ref_=nv_tvv_250";
		
		String URLgenre = "";

		try{
			int count2 = 0;
			while(count2 >= 0) {
				if(count2 > 0) {
					System.out.println("Would you like another recommedation? (Type no to quit)");
					if(scan.nextLine().equals("no"))
						break;
				}
				int count = 0;
				String input = "";
				
				while(count >= 0) {
					if(count > 0)
						System.out.print("I am sorry, ");
					System.out.println("Do you want a recommendation for a movie or a tv show?");
					// ignore upper case inputs to facilitate string comparison
					input = scan.nextLine().toLowerCase();
					
					if(input.equals("movie")) {
						document = Jsoup.connect(URLmovies).get();
						break;
					}
					else if(input.equals("tv show")){
						document = Jsoup.connect(URLtvshows).get();
						break;
					}
					count++;
				}
				
				count = 0;
				while(count >= 0) {
					if(count > 0)
						System.out.print("I am sorry, ");
					
					System.out.println("Do you want a recomendation based on genre or just their rankings?");
					input = scan.nextLine();
					if(input.equals("rankings")) {
						listRanking(document, list);
						printList(list);
						break;
					}
					else if(input.equals("genre")) {
						listGenre(document, genre);
						printList(genre);
						System.out.println("Which genre would you like?");
						input = scan.nextLine();
						setUrl(document, URLgenre, input);
						
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
	
	public static void setUrl(Document doc, String url, String input) {
		for(Element row : doc.select(".quicklinks li")) {
			li.add(row.text());
		}
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

