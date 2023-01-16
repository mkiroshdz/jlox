package lang.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
  private ArrayList<String> tokens = new ArrayList<String>();
  private ArrayList<Character> buffer = new ArrayList<Character>();

  public void ingest(char c, int lineNumber) {
    String prefix = listAsStr(buffer);
    String current = "" + c;
    boolean brk = Token.detectBreak(prefix, current);
    if(brk && !prefix.matches("^\\s*\\t*$")) {
      Token token = Token.create(prefix, lineNumber, 0);
      System.out.println(token);
      tokens.add(prefix);
      buffer.clear();
    }
    buffer.add(c);
  }

  public ArrayList<String> getTokens() {
    return tokens;
  }

  private String listAsStr(List<Character> list) {
    String txt = "";
    for(char c:list) { txt = txt + c; }
    return txt;
  }
}
