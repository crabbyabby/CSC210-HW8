import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Store valid words and generate suggestions for misspelled words.
 */
public class WordValidation implements SpellingOperations {
  private Dictionary dictionary;

  /**
   * Create a validator from a dictionary file.
   *
   * @param filename the file containing valid words
   */
  public WordValidation(String filename) {
    // You choose which implementation to use.
    Scanner file = null;
    HashSetDictionary dictionary = new HashSetDictionary();

    try {
      file = new Scanner(new File(filename));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);
    }

    while (file.hasNextLine()) {
      String word = file.nextLine().replaceAll("\\p{P}", "").toLowerCase();
      dictionary.add(word);
    }

    file.close();
  }

  /**
   * Check whether the dictionary contains a word.
   *
   * @param query the word to check
   * @return true if the word is in the dictionary
   */
  public boolean containsWord(String query) {
    return false;
  }

  /**
   * Generate valid near misses for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one edit away
   */
  public Set<String> nearMisses(String query) {
    return new HashSet<>();
  }

  public static void main(String[] args) {
    WordValidation valid = new WordValidation("words.txt");
  }
}
