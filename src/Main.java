import lang.parser.Tokenizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    BufferedReader reader;
    Tokenizer tokenizer = new Tokenizer();

    try {
      reader = new BufferedReader(new FileReader("/Users/monica/Documents/script.lox"));
      int lineNumber = 0;
      String line = reader.readLine();
      while (line != null) {
        process(tokenizer, line, lineNumber);
        line = reader.readLine();
        lineNumber++;
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(tokenizer.getTokens());
  }

  private static void process(Tokenizer tokenizer, String line, int lineNumber) {
    int idx = 0;
    char[] chars  = line.toCharArray();
    while(idx < chars.length) {
      tokenizer.ingest(chars[idx], lineNumber);
      idx++;
    }
  }
}