package project;

import java.io.IOException;
import java.util.*;
import project.LanguageToolkit.KeyValuePair;

/*
 * This test class illustrates the functionality of the Language Toolkit for implementation with the ChatBot
 * what it will do is take a string of text, use FindCoreferences() to find a coref chain (can be adapted for multiple chains)
 * then it uses POS() to tag the different POS within the text input
 * from this it uses tool.FindAdjectives() to separate the adjectives into a list of key value pairs <token,tag>
 * the token of each adjective is then evaluated for sentiment with tool.getSentiment()
 * this value may be used to determine positivity or negativity from the input.
 */
public class test_langtoolkit {
	public static String text = "I am so angry. What the fuck is going on? Bad things are difficult to discuss. That moview was terrible, I don't like it.";

	public static void main(String[] args) throws IOException {
		// Establish file path to model file location
		String posModel_filePath = "C:/Users/thewi/Downloads/en-pos-maxent.bin";
		try {
			// Create language toolkit object with desired model
			LanguageToolkit tool = new LanguageToolkit(posModel_filePath);
			// Determine coref and
			String mentions = tool.FindCoreferences(text);
			//KeyValuePair is used to 
			List<KeyValuePair> tagged = tool.POS(mentions);
			List<String> adjectives = tool.FindAdjectives(tagged);
			// Obtain sentiments for each adjective
			for (int i = 0; i < adjectives.size(); i++) {
				//int value between 0-4 this determines the positivity of the adjective
				System.out.println(tool.getSentiment(adjectives.get(i)));
			}
			System.out.println("Here are the adjectives found in the given String: "+adjectives.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
