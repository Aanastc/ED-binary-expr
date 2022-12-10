import java.util.LinkedList;
import java.util.Stack;

public class BinaryExprTree {
  public Node root;
  public int result;

  public BinaryExprTree() {
    this.root = null;
  }

  public void build(LinkedList<String> input) {
    Stack<Node> stack = new Stack<>();

    for (String token : input) {
      if (token.matches("\\d+")) {
        stack.push(new Node(NodeType.OPERAND, token));
      }

      if (token.matches("[+-/*]")) {
        Node node = new Node(NodeType.OPERATOR, token);

        node.right = stack.pop();
        node.left = stack.pop();

        stack.push(node);
      }
    }

    root = stack.pop();
  }

  public void evaluate() {
    result = eval(root);
  }

  public int eval(Node n) {
    if (n == null) {
      return 0;
    }

    if (n.type == NodeType.OPERAND) {
      return Integer.parseInt(n.value);
    }

    int l = eval(n.left);
    int r = eval(n.right);

    switch (n.value) {
      case "+":
        return l + r;

      case "-":
        return l - r;

      case "*":
        return l * r;

      case "/":
        return l / r;

      default:
        return 0;
    }
  }

  public void print(String prefix, Node n, boolean isLeft) {
    if (n != null) {
      print(prefix + "     ", n.right, false);
      System.out.println(prefix + ("|-- ") + n.value);
      print(prefix + "     ", n.left, true);
    }
  }
}
