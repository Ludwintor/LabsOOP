import com.oop.stringanalysers.ConsonantCounter;
import com.oop.stringanalysers.VowelCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnalysersTest {
  @Test
  public void TestVowelCounter() {
    String text = "Hello, world!";
    int expected = 3;
    VowelCounter vowelCounter = new VowelCounter();
    int result = vowelCounter.analyse(text);
    Assertions.assertEquals(expected, result);
  }

  @Test
  public void TestConsonantCounter() {
    String text = "Hello, world!";
    int expected = 7;
    ConsonantCounter consonantCounter = new ConsonantCounter();
    int result = consonantCounter.analyse(text);
    Assertions.assertEquals(expected, result);
  }
}
