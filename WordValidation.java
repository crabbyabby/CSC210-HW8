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
    query = query.replaceAll("\\p{P}", "").toLowerCase(); 

    if (dictionary.contains(query)) {
      return true;
    }
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

  public Set<String> deletions(String query) {
    HashSet<String> deletions = new HashSet<String>();
    
    for (int i = 0; i < query.length(); i++) {
      StringBuilder word = new StringBuilder(query);
      word.deleteCharAt(i);

      if (dictionary.contains(word.toString())) {
        deletions.add(word.toString());
      }
    }

    return deletions;
  }

  public Set<String> insertions(String query) {
    HashSet<String> insertions = new HashSet<String>();

    char[] alphabet = {'a'};
    return insertions;
  }

  public Set<String> substitutions(String query) {
    HashSet<String> substitutes = new HashSet<String>();

    return substitutes;
  }

  public Set<String> transpose(String query) {
    HashSet<String> transpose = new HashSet<String>();

    return transpose;
  }

  public Set<String> split(String query) {
    HashSet<String> split = new HashSet<String>();

    return split;
  }

  public static void main(String[] args) {
    WordValidation valid = new WordValidation("words.txt");
  }
}
