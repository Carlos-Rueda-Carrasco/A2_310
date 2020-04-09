import java.io.FileInputStream;

import net.didion.jwnl.JWNL;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

public class BoilerPlateJWNL {

	public static void main(String[] args) {
		try {
			JWNL.initialize(new FileInputStream("C:\\Users\\thewi\\OneDrive\\Eclipse Workspaces\\COSC310\\310_project\\dict\\properties.xml"));
			final Dictionary dictionary = Dictionary.getInstance();
			IndexWord idxWord = dictionary.getIndexWord(POS.NOUN,"blue");
			Synset[] senses = idxWord.getSenses();
			
			for (Synset set : senses) {
				System.out.println(idxWord+": "+set.getGloss());
			}
			
		} catch (Exception e) {
			System.out.println("exception "+ e);
		}

	}

}
