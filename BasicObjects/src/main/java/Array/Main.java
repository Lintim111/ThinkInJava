package Array;

import java.util.*;

public class Main {


    public static void main(String args[]) {
        int size = 1000;
        int a[] = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i;
        }
        Integer integerA[] = new Integer[size * 2 + 1];
        List<Integer> integerArray = new ArrayList<>();
        Vector<Integer> integerVector = new Vector<>();
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        int b[] = Arrays.copyOf(a, size * 2);
        for (int i = 0; i < size * 2; i++) {
            integerA[i] = Integer.valueOf(b[i]);
            integerArray.add(i);
            integerVector.add(i);
            integerLinkedList.add(i);
        }
        Iterator<Integer> iterator = integerLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + " ");
        }

        System.out.println("-------");

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : integerA) {
            stringBuilder.append(integer).append(" ");
        }

        System.out.print(stringBuilder.toString());
    }
}
