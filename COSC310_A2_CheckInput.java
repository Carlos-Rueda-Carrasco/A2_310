	/*
    * Check if user input is a positive or negative response
    * param: user input as str
    * return: char 'p' if str is positive, 'n' if str is negative, or 'x' if it is neither positive or negative
    */
	public static char checkInput(String str) {	
		//for every word in positiveInput check if it is part of  if so, reutrn 'p' for positive
        for (int i = 0; i < positiveInput.length; i++) {
            if (str.indexOf(positiveInput[i]) != -1)
                return 'p';
        }
        //for every word in negativeInput check if it is past of str if so, return 'n' for negative
        for (int i = 0; i < negativeInput.length; i++){
            if (str.indexOf(negativeInput[i] != -1))
                return 'n';
        }
        //else return 'x' if str could not be identified as a postive or negative sentence. 
        return 'x';
	}
}