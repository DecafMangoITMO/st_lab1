package task_2;

public class Node {
    int key;
    int height;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
