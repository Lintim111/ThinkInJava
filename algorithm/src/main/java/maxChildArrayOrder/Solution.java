package maxChildArrayOrder;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        ArrayList<int[]> testCases = new ArrayList<>();
        testCases.add(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5});
        testCases.add(new int[]{2, 1, 4, 1, 5, 3, 2, 6, 5});
        testCases.add(new int[]{5, 6, 7, 1, 2});
        testCases.add(new int[]{5, 6, 7, 1, 2, 3});
        testCases.add(new int[]{5, 6, 7, 1, 2, 3, 4});
        testCases.add(new int[]{5, 6, 7, 1, 2, 3, 4, 5});
        testCases.add(new int[]{5, 6, 7, 1, 2, 3, 4, 5, 8, 9});
        testCases.add(new int[]{5, 2, 6, 7, 1, 4, 3, 6, 7, 8, 9,});
        testCases.add(new int[]{5, 2, 4, 6, 7, 1, 4, 5, 3, 6, 7, 8, 9,});
        testCases.add(new int[]{5, 2, 4, 6, 7, 1, 5, 4, 5, 3, 6, 7, 8, 9,});
        testCases.add(new int[]{1, 6, 7, 2});
        testCases.add(new int[]{1, 6, 7, 2, 3});
        testCases.add(new int[]{1, 6, 7, 2, 3, 4, 5, 8, 9});
        Solution solution = new Solution();
        for (int i = 0; i < testCases.size(); i++) {
            int[] caseResult = solution.MaxChildArrayOrder(testCases.get(i));
            System.out.print("Case: ");
            for (int j = 0; j < testCases.get(i).length; j++) {
                System.out.print(testCases.get(i)[j] + ", ");
            }

            System.out.print("MaxLength: " + solution.MaxChildArrayOrderCount(testCases.get(i)) + "| ");

            System.out.print("length: " + caseResult.length + ", caseResult: ");
            for (int j = 0; j < caseResult.length; j++) {
                System.out.print(caseResult[j] + ", ");
            }
            System.out.println(" ");
        }

    }

    public int[] MaxChildArrayOrder(int a[]) {
        if (a.length <= 1) {
            return a;
        }
        if (a.length == 2) {
            return new int[]{a[0]};
        }
        ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < tmp.size(); j++) {
                if (a[i] > tmp.get(j).get(tmp.get(j).size() - 1)) {
                    tmp.get(j).add(a[i]);
                }
            }
            ArrayList<Integer> newCase = new ArrayList<>();
            newCase.add(a[i]);
            tmp.add(newCase);
        }
        int maxSub = 0;
        int maxLength = 0;
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).size() > maxLength) {
                maxSub = i;
                maxLength = tmp.get(i).size();
            }
        }

        int[] finalResult = new int[maxLength];
        for (int i = 0; i < tmp.get(maxSub).size(); i++) {
            finalResult[i] = tmp.get(maxSub).get(i).intValue();
        }
        return finalResult;
    }

    public int MaxChildArrayOrderCount(int a[]) {
        int n = a.length;
        int temp[] = new int[n];//temp[i]??????0...i????????????????????????
        for (int i = 0; i < n; i++) {
            temp[i] = 1;//???????????????1
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j] && temp[j] + 1 > temp[i]) {
                    //?????????a[i]????????????????????????????????????temp[i]??????????????????????????????????????????temp+1??????????????????
                    temp[i] = temp[j] + 1;
                }
            }
        }
        int max = temp[0];
        //???temp???????????????????????????
        for (int i = 1; i < n; i++) {
            if (temp[i] > max) {
                max = temp[i];
            }
        }
        return max;
    }
}
