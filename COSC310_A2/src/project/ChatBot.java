package project;
import java.util.Random;
import java.util.Scanner;

public class ChatBot {
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();
	//for simplicity and size we will work with lowercase words,(transform user input into lower case)
	//Fill up with more valid user inputs
	private static String[] positiveInput = {"yes","yeah","y","yep","affirmative","positive","agreed"};
	private static String[] negativeInput = {"no", "nah", "n", "nop", "negative", "disagree"};
	private static String[] positiveResponse = {"Great! I can recommend you a movie or tv show, would you like it?", 
			"Would you like a movie or tvshow recommendation"};
	private static String[] negativeResponse = {"Im sorry you are upset, but the only thing I can do a recommendation to watch movies or tv shows, would you like one?",
			"I beg your pardon, but the only thing I can do is movies and tvshows recommendation, do you wish one?"};
	private static String[] Greeting= {"Hey would you like to know what I can do?", 
			"Do you wish to explore my functionality?", 
			"Whats up, do want to know something?"};
	private static String initial = "Welcome to iRecommend interactive chat bot";
	private static String[] Goodbye= {"Thank you for using iRecommend, we hope your interaction was succesful"};
	private static String[] closingStatement = {"Would you like to get another recomendation? (type \"q\" to quit"};
	
	
	public static void main(String[] args) {
		String input = "";
		System.out.println(initial);
		
		while(input != "q") {
			System.out.println(Greeting[(rand.nextInt() % 3)]);
			input = scan.nextLine();
			
			if(checkString(input) && checkInput(input)) {
				
			}
			
		}
		
		scan.close();
	}
	
	//This method will convert str into a complete lowercase String to standarize all user input to lowercase and 
	//check if there is any non alphabetical character inside the string in order to ask the user to retype its input
	//Allows user to input a number as long as its 1 digit long(ie > 9 wont be accepted(couldnt finish))
	public static boolean checkString(String str) {		
		char ch[] = str.toCharArray();
		for(int i = 0; i < str.length(); i ++) {
			if((Character.isAlphabetic(ch[i]) == false && str.length() > 1 ) || ch[i] == ' '){
				System.out.println("There is an invalid character in your input " + ch[i] + " please reenter your input with valid form ");
				return false;
			}
		}
		return true;
	}
	
	public static boolean checkInput(String str) {	
		
	}
}