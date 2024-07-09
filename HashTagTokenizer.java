import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0].toLowerCase();
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		// Your code here

		for (int i = 0; i < dictionary.length; i++) 
		{
            if (word.equals(dictionary[i])) 
			{
                return true;
            }
        }
        return false;
    }

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) 
		{
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) 
		{
            String prefix = hashtag.substring(0, i);

            if (existInDictionary(prefix, dictionary)) 
			{
                System.out.println(prefix);
                breakHashTag(hashtag.substring(i), dictionary);
                	break;
            }
        }
    }
}

