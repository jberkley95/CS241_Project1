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
        String[] commandChoice = null;

        System.out.print("Please enter the initial sequence of values: ");
        String[] inputAsString = input.nextLine().split("\\s+");
        for (String number : inputAsString) {
            binaryTree.insert(Integer.parseInt(number));
        }

        System.out.println("Pre-order: ");
        binaryTree.displayPreOrder();
        System.out.println("In-order: ");
        binaryTree.displayInOrder();
        System.out.println("Post-order: ");
        binaryTree.displayPostOrder();

        while (commandChoice == null || !commandChoice[0].toUpperCase().equals("E")) {
            System.out.println("Command? ");
            commandChoice = input.nextLine().split("\\s+");

            switch (commandChoice[0].toUpperCase()) {
                case "I":
                    if (commandChoice[1].matches("^-?\\d+$")) {
                        binaryTree.insert(Integer.parseInt(commandChoice[1]));
                        break;
                    }
                case "D":
                    if (commandChoice[1].matches("^-?\\d+$")) {
                        if (!binaryTree.delete(Integer.parseInt(commandChoice[1]))) {
                            System.out.println("The Node You're Trying To Delete Doesn't Exist!");
                        }
                        break;
                    }
                case "P":
                    if (commandChoice[1].matches("^-?\\d+$")) {
                        Node temp = binaryTree.getPredecessor(Integer.parseInt(commandChoice[1]));
                        if (temp != null) {
                            System.out.println(temp.data);
                        }
                        break;
                    }
                case "S":
                    if (commandChoice[1].matches("^-?\\d+$")) {
                        Node temp = binaryTree.getSuccessor(Integer.parseInt(commandChoice[1]));
                        if (temp != null) {
                            System.out.println(temp.data);
                        }
                        break;
                    }
                case "E":
                    System.out.println("Thank you for using my program!");
                    break;
                case "H":
                    System.out.println("I  Insert a value");
                    System.out.println("D  Delete a value");
                    System.out.println("P  Find a predecessor");
                    System.out.println("S  Find a successor");
                    System.out.println("E  Exit the program");
                    System.out.println("H  Display this message");
                    break;
                default:
                    System.out.println("Error: Invalid Command, Try Again.");
            }
        }
    }
}
