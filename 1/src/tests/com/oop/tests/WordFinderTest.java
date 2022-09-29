package com.oop.tests;

import static org.junit.jupiter.api.Assertions.*;

import com.oop.text.WordFinder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class WordFinderTest {
  private static WordFinder wordFinder;

  private static Stream<Arguments> dataset() {
    return Stream.of(Arguments.of("", "hoh", ""),
                     Arguments.of("Hey. It's me!", "", "Hey, It's, me"),
                     Arguments.of("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "ip", "ipsum, adipiscing"),
                     Arguments.of("!!!!DES.....E  SD,, LES", "ES", "DES, LES"),
                     Arguments.of("", "", ""),
                     Arguments.of("Today's weather is bad. But we still go", "gone", ""),
                     Arguments.of("      ()", "what", ""));
  }

  @BeforeAll
  public static void setup() {
    wordFinder = new WordFinder();
  }

  @ParameterizedTest(name = "TextFinder with substring \"{1}\" must find \"{2}\"")
  @MethodSource(value = "dataset")
  public void test(String text, String substring, String expected) {
    String result = wordFinder.findWords(text, substring);
    assertEquals(result, expected);
  }
}
