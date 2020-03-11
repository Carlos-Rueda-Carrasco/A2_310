package A2;
import java.util.Scanner;

public class ar {
	//for simplicity and size we will work with lowercase words,(transform user input into lower case)
	//Fill up with more valid user inputs
	private String[] PositiveResponses = {"yes","yeah","y","yep","affirmative","positive","agreed"};
	private String[] NegativeResponses = {};
	private String[] Greetings= {"Welcome to iRecommend interactive chat bot"};
	private String[] Goodbye= {"Thank you for using iRecommend, we hope your interaction was succesful"};
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter string ");
		String stringy = scan.nextLine();
		System.out.println(Check(stringy));
		scan.close();
		}
	//This method will convert str into a complete lowercase String to standarize all user input to lowercase and 
	//check if there is any non alphabetical character inside the string in order to ask the user to retype its input
	//Allows user to input a number as long as its 1 digit long(ie > 9 wont be accepted(couldnt finish))
	public static String Check(String str) {
		String lowercase = str.toLowerCase();
		
		char ch[] = str.toCharArray();
		for(int i = 0; i < str.length(); i ++) {
			if(Character.isAlphabetic(ch[i]) == false && str.length() > 1 ) {
				
				System.out.println("CharacterInputed " + ch[i]);
				String error = ("There is an invalid character in your input " + ch[i] + " please reenter your input with valid form ");
				return error;
				
			}
			

		}
		return lowercase;
		
		
		
		
		
		
	}
	
	
	
	
	}
	