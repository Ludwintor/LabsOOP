package com.oop.stringanalysers;

public class VowelCounter implements Analyser {
  private static final String VOWELS = "aeiou";

  @Override
  public int analyse(String str) {
    if (str == null)
      return 0;
    Analyser analyser = string -> {
      int count = 0;
      for (int i = 0; i < string.length(); i++) {
        char letter = Character.toLowerCase(string.charAt(i));
        if (VOWELS.indexOf(letter) != -1)
          count++;
      }
      return count;
    };
    return analyser.analyse(str);
  }
}
