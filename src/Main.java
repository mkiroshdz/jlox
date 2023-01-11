import lang.parser.Tokenizer;

public class Main {
  public static void main(String[] args) {
    String txt = "val t1 = 123;" +
            "fn func((n1, n2) {" +
            "return n1 + n2" +
            "}";

    Tokenizer t = new Tokenizer();

    for(char c: txt.toCharArray()) {
      t.add(c);
    }

    System.out.println(txt);
  }
}