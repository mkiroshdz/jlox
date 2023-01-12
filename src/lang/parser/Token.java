package lang.parser;

public class Token {
  private final static String[] single = {
          ".*\\\"", // Text literals
          ".*\\t", ".*\\s", // Spaces & tabs
          ".*;", ".*,",
          ".*\\(", ".*\\)", ".*\\{", ".*\\}", ".*\\[", ".*\\]",
          ".*\\.", ".*\\+", ".*\\-", ".*=", ".*\\*", "/", ".*\\>", ".*\\<", ".*!",
  };
  private final static String[] ahead = {
          ".*=[= ! \\< \\>]", //
          ".*\\*[\\*]", // Multiplication
          ".*/\\*"
  };

  // TODO: handle == != <= => && || while if else for class fn var true false nil super this return


  private static  boolean checkAhead(String txt) {
    for(String k: ahead) {
      if(txt.matches("^" + k + "$")) { return true; }
    }
    return false;
  }

  private static  boolean checkSingle(String txt) {
    for(String k: single) {
      if(txt.matches("^" + k + "$")) { return true; }
    }
    return false;
  }

  public static boolean detectBreak(String prefix, String c) {
    boolean isString = prefix.matches(".*\\\".*");
    boolean isComment = prefix.matches(".*\\/\\*.*");

    if(isString) {
      return prefix.matches(".*\\\".*\\\".*");
    } else if(isComment) {
      boolean close = prefix.matches(".*\\*/.*");
      return prefix.matches(".*\\*/.*");
    } else {
      return !checkAhead(prefix + c) && (checkSingle(prefix) || checkSingle(c));
    }
  }

  final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;

  final int column;

  private Token(TokenType type, String lexeme, Object literal, int line, int column) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
    this.column = column;
  }
}
