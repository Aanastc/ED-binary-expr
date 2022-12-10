public class Node {
  public NodeType type;
  public String value;
  public Node left;
  public Node right;

  public Node(NodeType type, String value) {
    this.type = type;
    this.value = value;
    this.right = null;
    this.left = null;
  }
}
