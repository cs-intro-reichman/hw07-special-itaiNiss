
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		if (str == null || str.isEmpty()) 
		{
			return str;
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// Your code goes here
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.isEmpty()) 
		{
			return word2.length();
		}
		if (word2.isEmpty()) 
		{
			return word1.length();
		}
		int dist;
		if (word1.charAt(0) == word2.charAt(0)) 
		{
    		dist = 0;
		} 
		else 
		{
    		dist = 1;
		}
		int deleteDist = levenshtein(tail(word1), word2) + 1;
		int insertDist = levenshtein(word1, tail(word2)) + 1;
		int substituteDist = levenshtein(tail(word1), tail(word2)) + dist;
		return Math.min(deleteDist, Math.min(insertDist, substituteDist));

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i = 0; i < dictionary.length; i++) 
		{
            if (in.hasNextLine()) 
			{
                dictionary[i] = in.readLine();
            } 
			else 
			{
                break; 
            }
        }

        return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		word = word.toLowerCase();
		String closestWord = word;
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < dictionary.length; i++) 
		{
			String dictWord = dictionary[i];
			int distance = levenshtein(word, dictWord);
			if (distance < minDistance) 
			{
				closestWord = dictWord;
				minDistance = distance;
			}
		}
		if (minDistance <= threshold) 
		{
			return closestWord;
		} 
		else 
		{
			return word;
		}
	}
}

