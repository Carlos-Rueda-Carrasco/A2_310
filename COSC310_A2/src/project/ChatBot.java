package project;
import java.util.Random;
import java.util.Scanner;

public class ChatBot {
	private static Scanner scan = new Scanner(System.in);
	private static Random rand = new Random();
	/** 
	* variables that store some possible inputs by the user, which we use to compare in methods below to see if the responses are
	* positive or negative. Also have greetings and goodbyes (set dialogues for the conversation)
	*/
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
		// counter for the while loop
		int count = 0;
        
        while (count >= 0){
        	
        	if(count == 0) {
	            	// randomly choose greeting message 
			System.out.println(Greeting[(rand.nextInt(3))]);
	        	input = scan.nextLine();
	        	input.toLowerCase();
	        	// check if the user had a possitve response and proceed with the narrative and checks the inputs validity
			if(checkString(input) == true) {
				// check if they give a positive response to the first quesiton and proceed
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
	// this method checks if the user response is positive by iterating through the list of the positive responses declared in the class above 
	public static boolean positiveResponse(String str) {
		for (int i = 0; i < positiveInput.length; i++) {
            if (str.indexOf(positiveInput[i]) != -1)
                return true;
        }
		return false;
	}
	// this method checks if the user repsonse is negative by interating through the list of negative responses declared in the class above
	public static boolean negativeResponse(String str) {
		for (int i = 0; i < negativeInput.length; i++){
            if (str.indexOf(negativeInput[i]) != -1)
                return true;
        }
		return false;
	}
	// This method will convert str into a complete lowercase String to standarize all user input to lowercase and 
	// check if there is any non alphabetical character inside the string in order to ask the user to retype its input
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
