package lang.parser;

import java.util.ArrayList;

public class Tokenizer {
  private final ArrayList<String> keys = new ArrayList<String>();
  private final ArrayList<String> stops = new ArrayList<String>();
  private ArrayList<Character> buffer = new ArrayList<Character>();
  private ArrayList<String> tokens = new ArrayList<String>();

  public Tokenizer() {
    keys.add("("); keys.add(")");
    keys.add("{"); keys.add("}");
    keys.add("*"); keys.add("/"); keys.add("-"); keys.add("*");
    keys.add("=="); keys.add("!="); keys.add("<"); keys.add("<="); keys.add(">"); keys.add(">=");
    keys.add("!"); keys.add("&&"); keys.add("||");
    keys.add("while"); keys.add("if"); keys.add("else"); keys.add("for");
    keys.add("class"); keys.add("fn"); keys.add("var"); keys.add("=");
    keys.add("true"); keys.add("false"); keys.add("nil");
    keys.add("super"); keys.add("this");

    stops.add(";"); stops.add(","); stops.add(" ");
  }
  public void add(char c) {
    if (keys.contains(buffer.toString()) && !keys.contains(buffer.toString() + c)) {
      flush();
      push(c);
    } else if (stops.contains("" + c)) {
      flush();
    } else {
      push(c);
    }
  }

  private void flush() {
    if(buffer.size() > 0) {
      tokens.add(buffer.toString());
      buffer.clear();
    }
  }

  private void push(char c) {
    if(!stops.contains(c)) {
      buffer.add(c);
    }
  }
}
