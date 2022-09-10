package com.oop.text;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that find words
 */
public class WordFinder {
  /**
   * Constant word regex
   */
  private static final String WORD_REGEX = "\\b[\\w']*%s[\\w']*\\b";
  /**
   * Constant delimiter for StringJoiner
   */
  private static final String JOINER_SEPARATOR = ", ";

  /**
   * Find all words with provided substring separated by comma
   * @param text to search words from
   * @param substring find words that contains this
   * @return words separated by comma and whitespace (", ")
   */
  public String findWords(final String text, final String substring) {
    StringJoiner joiner = new StringJoiner(JOINER_SEPARATOR);
    String formattedRegex = String.format(WORD_REGEX, Pattern.quote(substring));
    Pattern pattern = Pattern.compile(formattedRegex);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find())
      if (!matcher.group().isEmpty())
        joiner.add(matcher.group());
    return joiner.toString();
  }
}
