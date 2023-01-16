package lang.parser;

import java.util.HashMap;
import java.util.Map;

public class Token {
  private final static String[] single = {
          ".*\\\"", // Text literals
          ".*\\t", ".*\\s", // Spaces & tabs
          ".*;", ".*,",
          ".*\\(", ".*\\)", ".*\\{", ".*\\}",
          "[a-z]*\\.", ".*\\+", ".*\\-", ".*=", ".*\\*", "/", ".*\\>", ".*\\<", ".*!",
  };
  private final static String[] ahead = {
          ".*=[= ! \\< \\>]", //
          ".*\\*[\\*]", // Multiplication
          ".*/\\*",
          "[0-9]+\\."
  };

  private static final Map<String, TokenType> lexemeToType = new HashMap<>();
  private static final Map<String, TokenType> keywords = new HashMap<>();

  static {
    lexemeToType.put(";", TokenType.SEMICOLON);
    lexemeToType.put(",", TokenType.COMMA);
    lexemeToType.put("(", TokenType.LEFT_PAREN);
    lexemeToType.put(")", TokenType.RIGHT_PAREN);
    lexemeToType.put("{", TokenType.LEFT_BRACE);
    lexemeToType.put("}", TokenType.RIGHT_BRACE);
    lexemeToType.put(".", TokenType.DOT);
    lexemeToType.put("+", TokenType.PLUS);
    lexemeToType.put("-", TokenType.MINUS);
    lexemeToType.put("=", TokenType.EQUAL);
    lexemeToType.put("*", TokenType.STAR);
    lexemeToType.put("/", TokenType.SLASH);
    lexemeToType.put(">", TokenType.GREATER);
    lexemeToType.put(">=", TokenType.GREATER_EQUAL);
    lexemeToType.put("<", TokenType.LESS);
    lexemeToType.put("<=", TokenType.LESS_EQUAL);
    lexemeToType.put("!", TokenType.BANG);
    lexemeToType.put("!=", TokenType.BANG_EQUAL);
    lexemeToType.put("==", TokenType.EQUAL_EQUAL);

    keywords.put("and",    TokenType.AND);
    keywords.put("or",     TokenType.OR);

    keywords.put("class",  TokenType.CLASS);
    keywords.put("if",     TokenType.IF);
    keywords.put("else",   TokenType.ELSE);
    keywords.put("for",    TokenType.FOR);
    keywords.put("while",  TokenType.WHILE);

    keywords.put("fun",    TokenType.FUN);
    keywords.put("return", TokenType.RETURN);
    keywords.put("super",  TokenType.SUPER);
    keywords.put("this",   TokenType.THIS);
    keywords.put("var",    TokenType.VAR);

    keywords.put("nil",    TokenType.NIL);
    keywords.put("false",  TokenType.FALSE);
    keywords.put("true",   TokenType.TRUE);
  }

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

  public static Token create(String lexeme, int line, int column) {
    TokenType type = lexemeToType.get(lexeme);

    if(type == null) {
      if(lexeme.matches("^\\\".*\\\"$")) {
        type = TokenType.STRING;
      } else if (lexeme.matches("^/\\*.*$")) {
        type = TokenType.COMMENT;
      } else if (lexeme.matches("^([0-9]\\.)?[0-9]*$")) {
        type = TokenType.NUMBER;
      } else if(keywords.containsKey(lexeme)) {
        type = keywords.get(lexeme);
      } else {
        type = TokenType.IDENTIFIER;
      }
    }

    return new Token(type, lexeme, new Object(), line, column);
  };

  final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;

  final int column;

  public Token(TokenType type, String lexeme, Object literal, int line, int column) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
    this.column = column;
  }

  public String toString() {
    return "Type:" + type + " Lexeme: " + lexeme;
  }
}
