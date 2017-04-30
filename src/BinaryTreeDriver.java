import java.util.Scanner;

/**
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: Apr 23, 2017
 */
public class BinaryTreeDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();

        System.out.println("Please enter the initial sequence of values: ");
        String[] inputAsString = input.nextLine().split("\\s+");
        for (String number : inputAsString) {
            binaryTree.insert(Integer.parseInt(number));
        }

        binaryTree.displayInOrder();
    }
}
