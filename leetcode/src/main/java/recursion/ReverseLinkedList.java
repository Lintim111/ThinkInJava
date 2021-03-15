package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReverseLinkedList implements ITestRunner {
    class ListNode {
        private int value;
        private ListNode next;

        public ListNode(int v) {
            value = v;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            String s = "" + this.value;
            if (this.next != null) {
                s += "->" + this.next.toString();
            }
            return s;
        }
    }

    List<ListNode> testCases;
    Random random = new Random(47);

    @Override
    public void initTestCases() {
        testCases = new ArrayList<>();
        testCases.add(null);
        testCases.add(new ListNode(1));
        for (int count = 0; count < random.nextInt(10); count++) {
            ListNode head = new ListNode(random.nextInt(10));
            ListNode parent = head;
            for (int i = 0; i < random.nextInt(100); i++) {
                ListNode node = new ListNode(random.nextInt(20));
                parent.setNext(node);
                parent = node;
            }
            parent.setNext(null);
            testCases.add(head);
        }
    }

    @Override
    public void run() {
        initTestCases();

        for (ListNode head : testCases) {
            System.out.println(head + ": ");
            head = swapPairs(head);
            System.out.println(head);
        }

    }

    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return null;
        ListNode current = head.next;
        if (current == null) {
            return head;
        }
        ListNode tmp_next = current.next;
        head.next = swapPairs(tmp_next);
        current.next = head;
        return current;
    }
}