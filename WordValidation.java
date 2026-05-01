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
    this.dictionary = dictionary;

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
    query = query.replaceAll("\\p{P}", "").toLowerCase(); 
    HashSet<String> nearMisses = new HashSet<String>();

    nearMisses.addAll(deletions(query));
    nearMisses.addAll(insertions(query));
    nearMisses.addAll(substitutions(query));
    nearMisses.addAll(transpose(query));
    nearMisses.addAll(split(query));
    return nearMisses;
  }

  /**
   * Generate valid deletions for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one deletion away
   */
  public Set<String> deletions(String query) {
    HashSet<String> deletions = new HashSet<String>();
    
    for (int i = 0; i < query.length(); i++) {
      StringBuilder word = new StringBuilder(query);
      word.deleteCharAt(i);

      if (this.dictionary.contains(word.toString())) {
        deletions.add(word.toString());
      }
    }

    return deletions;
  }

  /**
   * Generate valid insertions for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one insertion away
   */
  public Set<String> insertions(String query) {
    HashSet<String> insertions = new HashSet<String>();
    for (int i = 0; i < query.length(); i++) {

      for (char c = 'a'; c < 'z'; c++) {
        StringBuilder word = new StringBuilder(query);
        word.insert(i, c);

        if (this.dictionary.contains(word.toString())) {
          insertions.add(word.toString());
        }
      }
    }
    return insertions;
  }


  /**
   * Generate valid substitutions for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one substitution away
   */
  public Set<String> substitutions(String query) {
    HashSet<String> substitutes = new HashSet<String>();

    for (int i = 0; i < query.length(); i++) {
      StringBuilder word = new StringBuilder(query);

      for (char c = 'a'; c < 'z'; c++) {
        word.setCharAt(i, c);

         if (this.dictionary.contains(word.toString())) {
          substitutes.add(word.toString());
        }
      }
    }

    return substitutes;
  }

  /**
   * Generate valid transposes for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one transpose away
   */
  public Set<String> transpose(String query) {
    HashSet<String> transpose = new HashSet<String>();

    for (int i = 0; i < query.length() - 1; i++) {
      StringBuilder word = new StringBuilder(query);

      char temp = word.charAt(i);
      word.setCharAt(i, word.charAt(i+1));
      word.setCharAt(i+1, temp);
      if (this.dictionary.contains(word.toString())) {
        transpose.add(word.toString());
      }
    }

    return transpose;
  }

  /**
   * Generate valid splits for a query word.
   *
   * @param query the word to check
   * @return a set of valid suggestions that are one edit away
   */
  public Set<String> split(String query) {
    HashSet<String> split = new HashSet<String>();

    for (int i = 1; i < query.length()-1; i++) {
      StringBuilder word = new StringBuilder(query);

      word.insert(i, ' ');
      String left = query.substring(0, i);
      String right = query.substring(i);

      if (this.dictionary.contains(left) && this.dictionary.contains(right)) {
        split.add(word.toString());
      }
    }
    return split;
  }

  public static void main(String[] args) {
    WordValidation valid = new WordValidation("words.txt");
  }
}
