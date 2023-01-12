package lang.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Keys {
  private final ArrayList<String> list = new ArrayList<String>();
  
  public Keys() {
    list.add(";"); list.add(","); // Delimiters
    list.add("("); list.add(")"); // Grouping
    list.add("{"); list.add("}"); // Blocks
    list.add("*"); list.add("/"); list.add("-"); list.add("*"); // Binary Operations
    list.add("=="); list.add("!="); list.add("<"); list.add("<="); list.add(">"); list.add(">="); // Comparison
    list.add("!"); list.add("&&"); list.add("||"); // Logic
    list.add("while"); list.add("if"); list.add("else"); list.add("for"); // Flow
    list.add("class"); list.add("fn"); list.add("var"); list.add("="); // Definitions
    list.add("true"); list.add("false"); list.add("nil"); // Values
    list.add("super"); list.add("this");
  }

  public boolean matches(String txt) {
    for(String k: list) {
      if(txt.matches("^" + txt + "*")) { return true; }
    }
    return false;
  }
}
