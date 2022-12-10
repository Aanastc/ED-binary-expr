import java.util.Stack;

public class Parser {

  public static String parse(String args) {
    if (lexer(args)) {
      throw new IllegalArgumentException();
    }

    String posfixa = "";

    Stack<Character> pilha = new Stack<>();
    int cont = 0;
    char c;

    while (cont < args.length()) {
      c = args.charAt(cont);
      switch (c) {
        case '*':
        case '/':
        case '+':
        case '-':
        case '^':
          while ((!pilha.isEmpty()) && prioridade(c) <= prioridade(pilha.peek()))
            posfixa += pilha.pop();

          pilha.push(c);
          break;

        case '(':
          pilha.push(c);
          break;

        case ')':
          while (pilha.peek() != '(')
            posfixa += pilha.pop();

          if (pilha.peek() == '(')
            pilha.pop();
          break;

        default:
          posfixa += args.charAt(cont);
          break;
      }
      cont++;
    }

    while (pilha.size() > 0) {
      if (pilha.peek() != '(')
        posfixa += pilha.pop();
      else {
        pilha.pop();
      }
    }
    return posfixa;

  }

  public static int prioridade(char elemento) {

    int prioridade;

    switch (elemento) {
      case '+':
      case '-':
        prioridade = 1;
        break;
      case '*':
      case '/':
        prioridade = 2;
        break;
      case '^':
        prioridade = 3;
        break;
      default:
        prioridade = 0;
        break;
    }
    return prioridade;
  }

  public static boolean lexer(String expressao) {
    Stack<Character> pilha = new Stack<>();

    int abertos = 0;
    boolean errado = false;
    for (int i = expressao.length() - 1; i >= 0; i--) {
      char caracter = expressao.charAt(i);
      pilha.push(caracter);
    }

    while (!pilha.isEmpty()) {
      char caracter = pilha.pop();

      if (caracter == ')') {
        if (abertos == 0) {
          errado = true;
          break;
        }
        abertos--;
      } else if (caracter == '(') {
        abertos++;
      }
    }

    return errado || abertos > 0;
  }
}
