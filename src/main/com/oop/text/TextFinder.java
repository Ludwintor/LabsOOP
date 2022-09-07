package com.oop.text;

public class TextFinder {
  private static final char[] DELIMITERS = " .,!?\n:;()".toCharArray();

  /**
   * Find all words with provided substring separated by comma
   * @param text to search words from
   * @param substring find words that contains this
   * @return words separated by comma and whitespace (", ")
   */
  public String findWords(final String text, final String substring) {
    StringBuilder result = new StringBuilder();
    int start = appendNextWord(result, text, substring, 0);
    while (start != -1) {
      result.append(", ");
      start = appendNextWord(result, text, substring, start);
    }
    return result.toString();
  }

  /**
   * Append word in current position to StringBuilder and
   * shift start position to next word (if found)
   * @param builder StringBuilder to append words
   * @param text to search words from
   * @param substring find words that contains this
   * @param start current word position
   * @return shifted start position for next word
   */
  private int appendNextWord(final StringBuilder builder, final String text,
                             final String substring, final int start) {
    String word = getWord(text, start);
    builder.append(word);
    return text.indexOf(substring, start + word.length());
  }

  /**
   * Get word in provided start position
   * @param text to search words from
   * @param start current word position
   * @return found word
   */
  private String getWord(final String text, int start) {
    if (start != -1) {
      int end = findWordEnd(text, start);
      return text.substring(start, end);
    }
    return "";
  }

  /**
   * Find end of word index
   * @param text to search words from
   * @param start current word position
   * @return where current word ends
   */
  private int findWordEnd(final String text, int start) {
    int index = -1;
    for (char delimiter : DELIMITERS) {
      int currentIndex = text.indexOf(delimiter, start);
      index = currentIndex == -1 ?
              index : index != -1 ?
              Math.min(currentIndex, index) : currentIndex;
    }
    return index == -1 ? text.length() : index;
  }
}
