package project;
import java.util.Random;
import java.util.Scanner;

public class ChatBot {
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();
	//for simplicity and size we will work with lowercase words,(transform user input into lower case)
	//Fill up with more valid user inputs
	private static String[] positiveInput = {"yes","yeah","y","yep","affirmative","positive","agreed","sure","ok"};
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
	public static boolean flag1 = true;
	public static boolean flag2 = true;
	
	
	public static void main(String[] args) {
		String input = "";
		System.out.println(initial);
		
		int count = 0;
        
        while (count >= 0){
        	
        	if(count == 0) {
	            System.out.println(Greeting[(rand.nextInt(3))]);
	        	input = scan.nextLine();
	        	input.toLowerCase();
	        	if(checkString(input) == true) {
	        		if(checkResponse(input, count) == true) {
	        			count++;
	        		}
	        		
	        	}
	        	
        	}
        	else if(count > 0) {
        		if(flag1 == true) {
        			System.out.println(positiveResponse[(rand.nextInt(2))]);
        		}
        		else {
        			System.out.println(negativeResponse[(rand.nextInt(2))]);
        		}
        		
    	        input = scan.nextLine();
    	        if(checkString(input) == true) {
	        		if(checkResponse(input, count) == true) {
	        			if(flag2 == false) {
	        				System.out.println(Goodbye[0]);
	        				break;
	        			}
	        			WebScrapper w = new WebScrapper();
	        			System.out.println(Goodbye[0]);
	        			break;
	        		}
    	        }       		
        	}
        }
		scan.close();
	}
	
	public static boolean checkResponse(String str, int count) {
		if(positiveResponse(str) == true) {
			if(count == 0) {
				flag1 = true;
			}
			else {
				flag2 = true;
			}
			return true;
		}
		else if(negativeResponse(str) == true) {
			if(count == 0) {
				flag1 = false;
			}
			else {
				flag2 = false;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean positiveResponse(String str) {
		for (int i = 0; i < positiveInput.length; i++) {
            if (str.indexOf(positiveInput[i]) != -1)
                return true;
        }
		return false;
	}
	
	public static boolean negativeResponse(String str) {
		for (int i = 0; i < negativeInput.length; i++){
            if (str.indexOf(negativeInput[i]) != -1)
                return true;
        }
		return false;
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
	
}
