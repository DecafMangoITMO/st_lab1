package task_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    private AVLTree avlTree;

    @BeforeEach
    public void setup() {
        avlTree = new AVLTree();
    }

    /*
    1)  1

    2)  1
         \
          2

    3)  1               2
         \             / \
          2    -->    1   3
           \
            3
     */
    @Test
    public void checkStandardUsageWithLeftRotate() {
        int[] inputs = {1, 2, 3};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        assertEquals(2, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertEquals(3, avlTree.root.right.key);
    }

    /*
    1)  3

    2)      3
           /
          2

    3)      3               2
           /               / \
          2               1   3
         /
        1
     */
    @Test
    public void checkStandardUsageWithRightRotate() {
        int[] inputs = {3, 2, 1};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        assertEquals(2, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertEquals(3, avlTree.root.right.key);
    }

    @Test
    public void checkDuplicates() {
        int[] inputs = {1, 2, 3, 1, 2, 3};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        assertEquals(2, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertEquals(3, avlTree.root.right.key);
    }

    @Test
    public void checkSearchPresent() {
        int[] inputs = {1, 2, 3};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        assertNotNull(avlTree.search(1));
        assertNotNull(avlTree.search( 2));
        assertNotNull(avlTree.search(3));
    }

    @Test
    public void checkSearchNotPresent() {
        int[] inputs = {1, 2, 3};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        assertNull(avlTree.search(-1));
    }

    @Test
    public void checkDeleteNotPresent() {
        int[] inputs = {1, 2, 3};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        avlTree.root = avlTree.delete(avlTree.root, -1);

        assertEquals(2, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertEquals(3, avlTree.root.right.key);
    }

    /*
              2             2
             / \    -->    /
            1   3         1
     */
    @Test
    public void checkDeletePresent() {
        int[] inputs = {1, 2, 3};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        avlTree.root = avlTree.delete(avlTree.root, 3);

        assertEquals(2, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertNull(avlTree.root.right);
    }

    /*
              2             2
             / \    -->    /
            1   3         1
     */
    @Test
    public void checkDeletePresentOnlyRoot() {
        avlTree.root = avlTree.insert(avlTree.root, 1);

        avlTree.root = avlTree.delete(avlTree.root, 1);

        assertNull(avlTree.root);
    }

    /*
               5(4)
              / \
             /   \
            3     7
           / \   / \
          2  4  6   8
         /           \
        1             9
     */
    @Test
    public void checkHeight() {
        int[] inputs = {5, 3, 7, 2, 4, 6, 8, 1, 9};

        for (int input : inputs)
            avlTree.root = avlTree.insert(avlTree.root, input);

        // root
        assertEquals(4, avlTree.height(avlTree.root));

        // first layer
        assertEquals(3, avlTree.height(avlTree.root.left));
        assertEquals(3, avlTree.height(avlTree.root.right));

        // second layer
        assertEquals(2, avlTree.height(avlTree.root.left.left));
        assertEquals(1, avlTree.height(avlTree.root.left.right));
        assertEquals(1, avlTree.height(avlTree.root.right.left));
        assertEquals(2, avlTree.height(avlTree.root.right.right));

        // third layer
        assertEquals(1, avlTree.height(avlTree.root.left.left.left));
        assertEquals(1, avlTree.height(avlTree.root.right.right.right));
    }

    /*
            1               3
             \             /
              3    -->    1
             /             \
            2               2
     */
    @Test
    public void testLeftRotate() {
        Node x = new Node(1);
        Node y = new Node(3);
        Node z = new Node(2);

        x.right = y;
        y.left = z;
        avlTree.root = x;

        avlTree.root = avlTree.rotateLeft(avlTree.root);

        assertEquals(3, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertEquals(2, avlTree.root.left.right.key);
    }

    /*
            3               1
           /                 \
          1         -->       3
           \                 /
            2               2
     */
    @Test
    public void testRightRotate() {
        Node x = new Node(3);
        Node y = new Node(1);
        Node z = new Node(2);

        x.left = y;
        y.right = z;
        avlTree.root = x;

        avlTree.root = avlTree.rotateRight(avlTree.root);

        assertEquals(1, avlTree.root.key);
        assertEquals(3, avlTree.root.right.key);
        assertEquals(2, avlTree.root.right.left.key);
    }


    /*
                3                 2
               /                 / \
              1        -->      1   3
               \
                2
    */
    @Test
    public void testRebalance() {
        Node x = new Node(3);
        Node y = new Node(1);
        Node z = new Node(2);

        x.left = y;
        y.right = z;
        avlTree.root = x;
        x.height = 3;
        y.height = 2;
        z.height = 1;

        avlTree.root = avlTree.rebalance(avlTree.root);

        assertEquals(2, avlTree.root.key);
        assertEquals(1, avlTree.root.left.key);
        assertEquals(3, avlTree.root.right.key);
    }

}
