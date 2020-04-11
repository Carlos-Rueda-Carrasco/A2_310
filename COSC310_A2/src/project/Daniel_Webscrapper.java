package project;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner; 

/**
*The WebScrapper class will retrieve the information available in the IMDB webpage
*this class will print a list of 10 movies depending of the selection of the user
*the selection can be movies and tv shows, and the order of the list will represent
*the top 10 based on ranking and if wanted for a specific genre
*/

public class WebScrapper {
	
	public static Scanner scan = new Scanner(System.in);
	private static ArrayList <String> genreList = new ArrayList<String>();
	private static ArrayList<String> genre = new ArrayList<String>();
	private static ArrayList<String> list = new ArrayList<String>();
	
	//ADDED CODE
	private static ArrayList<String> directorList = new ArrayList<String>();
	private static ArrayList<String> actorList =new ArrayList<String>();
	
	private static Document document;
	
	
	public WebScrapper(){
		//String storing the URL for movies
		final String URLmovies = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
		//String storing the URL for tvshows
		final String URLtvshows = "https://www.imdb.com/chart/toptv/?ref_=nv_tvv_250";
		
		//This string will store the URL for the chosen genre
		String URLgenre = "";
		
		try{
			//counter for the while loop
			int count2 = 0;
			boolean dicactConvo = false;
			while(count2 >= 0) {
				//if after first iteration of the while loop
				//this if statement will clear the ArrayList and ask the user for next iteration
				if(count2 > 0) {
					genreList.clear();
					genre.clear();
					list.clear();
					
					//ADDED CODE
					directorList.clear();
					actorList.clear();
					
					System.out.println("Would you like another recommendation? Type \"recommendation\" If not, type \"no\" to quit. Or do you want to see a list of top directors or actors?");
					String input = scan.nextLine();
					if(input.equals("no")) {
						break;
					} else if (input.equals("recommendation")){
						dicactConvo = false;
					} else if (input.indexOf("yes") != -1||input.indexOf("yeah") != -1||input.equals("y")||input.indexOf("positive") != -1||input.indexOf("agree") != -1||input.indexOf("sure") != -1||input.indexOf("ok") != -1) {
						directorActorConvo(input);
						dicactConvo = true;
					}
				}
				
				int count = 0;
				String input = "";
				
				//while loop until the user inputs one of the 4 options (movie, movies, tvshows, tv shows)
				while(count >= 0 && !(dicactConvo)) {
					if(count > 0)
					System.out.print(unrelated());
					System.out.println("Do you want movies or tvshows recommendations?");
					// make user input easier to compare with potential responses
					input = scan.nextLine().toLowerCase();
					
					if(input.equals("movies")||input.equals("movie")) {
						document = Jsoup.connect(URLmovies).get();
						break;
					}
					else if(input.indexOf("tvshow")!= -1||input.equals("tv shows")){
						document = Jsoup.connect(URLtvshows).get();
						break;
					}
					count++;
				}
				
				//while loop until the user inputs one of the 4 options (rankings, ranking, genres, genre)
				count = 0;
				while(count >= 0 && !(dicactConvo)) {
					if(count > 0)
						System.out.print(unrelated());
					
					System.out.println("Do you want a recommendation based on genre or just rankings?");
					input = scan.nextLine().toLowerCase();
					if(input.equals("rankings")||input.equals("ranking")) {
						listRanking(document, list);
						printList(list);
						break;
					}
					else if(input.equals("genres")||input.equals("genre")) {
						listGenre(document, genre);
						printList(genre);
						System.out.println("Which genre would you like? (input the number)");
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
	
	//This method will make list the top 10 movie director based on rankings
	//@Document doc parameter, has all the webpage information
	//@ArrayList<String> li parameter, will list the top 10
	public static void listDirectorRanking(Document doc, ArrayList<String> li) {
		int i = 1;
		for(Element row : doc.select(".lister-item-header a")) {
			directorList.add(row.text());
			i++;
			if (i == 11)
				break;
		}
	}
	
	//This method will make list the top 10 actors based on rankings
	//@Document doc parameter, has all the webpage information
	//@ArrayList<String> li parameter, will list the top 10
	public static void listActorRanking(Document doc, ArrayList<String> li) {
		int i = 1;
		for(Element row : doc.select(".lister-item-header a")) {
			actorList.add(row.text());
			i++;
			if (i == 11)
				break;
		}
		
	}
	
	//This method will scrapped the webpage and produce the list of the top 10 based on genre
	//@Document doc parameter, has all the webpage information
	//@ArrayList<String> pararmeter, li is the list of the top 10 movies
	public static void listGenreRanking(Document doc, ArrayList<String> li) {
		int i = 1;
	
		for(Element row : doc.select(".lister-item-header a")) {
			genreList.add(row.text());
			i++;
			if (i == 11)
				break;
		}
	
	}
	
	//This method will set up the URL of the genre
	//@Document doc parameter, has all the webpage information
	//@String input parameter, is the path of the genre
	public static String setUrl(Document doc, String input) {
		int i = Integer.parseInt(input) - 1;
		String url = "";
		Elements elem = doc.select(".quicklinks .subnav_item_main a[href]");
		Element theelem = elem.get(i);
		url = theelem.attr("href");
		url = "https://www.imdb.com" + url;
		
		
		return url;
	}
	
	//This method will make list all the genre available
	//@Document doc parameter, has all the webpage information
	//@ArrayList<String> li parameter, will list all the genre available
	public static void listGenre(Document doc, ArrayList<String> li) {
		for(Element row : doc.select(".quicklinks li")) {
			li.add(row.text());
		}
		
	}
	
	//This method will make list the top 10 movies or tv shows based on rankings
	//@Document doc parameter, has all the webpage information
	//@ArrayList<String> li parameter, will list the top 10
	public static void listRanking(Document doc, ArrayList<String> li) {
		int i = 1;
		for(Element row : doc.select(".lister-list .titleColumn a")) {
			li.add(row.text());
			i++;
			if (i == 11)
				break;
		}
		
	}
	
	//This method will print the Array list
	//@ArrayList<String> li parameter, a list to be printed
	public static void printList(ArrayList<String> al) {
		int i = 1;
		for(String str : al) {
			System.out.println(i + ". " +str);
			i++;
		}
	}
	//ADDED CODE
	public static void directorActorConvo(String input) {
		//ADDED CODE
		//String storing the URL for actors
		final String URLactors = "https://www.imdb.com/list/ls058011111/";
		//String storing the URL for directors
		final String URLdirectors = "https://www.imdb.com/list/ls050328773/";
		System.out.println("Do you want a list of directors or actors?");
		try {
			input = scan.nextLine().toLowerCase();
			if(input.indexOf("directors") != -1||input.indexOf("director") != -1) {
				Document directorsRanked = Jsoup.connect(URLdirectors).get();
				listDirectorRanking(directorsRanked, directorList);
				printList(directorList);
			}
			else if(input.indexOf("actors") != -1||input.indexOf("actor") != -1) {
				Document actorsRanked = Jsoup.connect(URLactors).get();
				listActorRanking(actorsRanked, actorList);
				printList(actorList);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String unrelated(String s) {
		String[] arr = {"Not sure what you meant. ", "I am sorry, ", "I couldn't understand. ", "hmm... Maybe repeat your answer. ", "Could you rephrase that? "};
		return arr[(int)(Math.random() * arr.length)] + s;
	}
	
	public static String unrelated() {
		String[] arr = {"Not sure what you ment. ", "I am sorry, ", "I couldn't understand. ", "hmm... Maybe repeat your answer. ", "Could you rephrase that? "};
		return arr[(int)(Math.random() * arr.length)];
	}
	
}
