import java.util.ArrayList;
import java.util.List;

class Node {
    Node left;
    Node right;
    int value;
}

public class InOrderTree {
    void inOrder2(Node root) {
        if (root == null)
            return;
        List<Node> path = new ArrayList<>();
        path.add(root);
        while (path.size() > 0) {
            Node last = path.get(path.size() - 1);
            Node temp_left = last.left;
            if (temp_left != null) {
                path.add(temp_left);
                continue;
            }
            System.out.println(last);
            path.remove(path.size() - 1);
            if (last.right != null) {
                path.add(last.right);
                continue;
            }
            Node parent = path.get(path.size() - 1);
            System.out.println(parent);
            path.remove(path.size() - 1);
            if (parent.right != null) {
                path.add(parent.right);
            }

        }
    }
}
