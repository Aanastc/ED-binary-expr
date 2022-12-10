import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String infixExpr = sc.nextLine().trim();

        BinaryExprTree binaryExprTree = new BinaryExprTree();

        String postfixExpr = Parser.parse(infixExpr.replaceAll(" ", ""));

        binaryExprTree.build(new LinkedList<String>(Arrays.asList(postfixExpr.split(""))));

        binaryExprTree.print("", binaryExprTree.root, false);

        binaryExprTree.evaluate();

        System.out.println(binaryExprTree.result);

        sc.close();
    }
}
