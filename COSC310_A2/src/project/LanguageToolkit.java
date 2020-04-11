package project;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.coref.data.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
//OpenNlP API...
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;
/*
 * LanguageToolkit version 1.1
 * contains constructors and methods for implementing evaluation of user inputs
 */

public class LanguageToolkit{
	String model;
	//Set the file path of the model for the tool you want to use
	public LanguageToolkit(String model_filePath) {
		this.model=model_filePath;
		
	}
	//Used to derive adjectives from POS()
	public class KeyValuePair {
	    String key;
	    String value;
	    public KeyValuePair(String key, String value) {
	        this.key = key;
	        this.value = value;
	    }
	}
	//Returns int from 0-4 on scale of positivity
	public int getSentiment(String s) {
	    Sentiment.init();
	    return Sentiment.findSentiment(s);
	}
	//Returns a List<String> with the adjectives found in a <token, tag> KeyValuePair list
	public List<String> FindAdjectives(List<KeyValuePair> tokenTagList) {//will find all adjectives in an array list of tag count strings;
		List<String> adjectives = new ArrayList<String>();
		for (int i = 0; i < tokenTagList.size(); i++) {
			if(tokenTagList.get(i).value.contains("JJ"))//adjective codes contain at least "JJ" 
				adjectives.add(tokenTagList.get(i).key);
		}
		return adjectives;
	}
	//Establishes a KeyValuePair list for the different POS found in userInput
	public List<KeyValuePair> POS(String userInput) throws IOException { 
		   //Loading sentence detector model 
	      InputStream inputStream = new FileInputStream(model); 
	      POSModel model = new POSModel(inputStream);
	      //Instantiating POSTaggerME class 
	      POSTaggerME tagger = new POSTaggerME(model);
	      //Tokenizing the sentence using WhitespaceTokenizer class  
	      WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE; 
	      String[] tokens = whitespaceTokenizer.tokenize(userInput); 
	      //Generating tags 
	      String[] tags = tagger.tag(tokens);
	      //Instantiating the POSSample class 
	      POSSample sample = new POSSample(tokens, tags);
	      List<KeyValuePair> tokenTagList = new ArrayList<>();
	      for (int j = 0; j < tokens.length; j++) {
	    	  tokenTagList.add(new KeyValuePair(tokens[j], tags[j]));
		}
	     
	      return tokenTagList;
	}
	//Used to find occurrences of identifiable person names
	public String FindNamedEnt(String str) throws IOException {
		//Loading the tokenizer model 
      InputStream inputStreamTokenizer = new  FileInputStream("C:/Users/thewi/Downloads/en-token.bin");
      TokenizerModel tokenModel = new TokenizerModel(inputStreamTokenizer); 
      //Instantiating the TokenizerME class 
      TokenizerME tokenizer = new TokenizerME(tokenModel); 
      //Tokenizing the sentence in to a string array 
      String sentence = str; 
      String tokens[] = tokenizer.tokenize(sentence);      
      //Loading the NER-person model 
      InputStream inputStreamNameFinder = new 
         FileInputStream(model);       
      TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);   
      //Instantiating the NameFinderME class 
      NameFinderME nameFinder = new NameFinderME(model);//need to change class for different entity types            
      //Finding the names in the sentence 
      Span nameSpans[] = nameFinder.find(tokens);   
      String result = "";
      int count = 0;
      //Printing the names and their spans in a sentence 
      for(Span s: nameSpans)        //use for loop to find each individual name and its count
    	  result = s.toString()+"  "+tokens[s.getStart()]+count++;
      return result;//returns postion[x..y) person personName
		
	}
	//Used to determine the mentions in a coreference chain
	public String FindCoreferences (String str) {
		Annotation document = new Annotation(str);
	    Properties props = new Properties();
	    ArrayList<CorefChain> corefChains = new ArrayList<CorefChain>();
	    List<Mention> mentions = new ArrayList<Mention>();
	    //setting property and NLP pipeline
	    props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,coref");
	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
	    pipeline.annotate(document);
	    
	    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
	      corefChains.add(cc);
	    }
	    for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
	      for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
	        mentions.add(m);//add each each individual coreference mention to mentions
	       }
	      
	    }
	    return mentions.toString();
	    

	}
	
	}
