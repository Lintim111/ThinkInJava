package test.hw.q1;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main {

    static class ErrException extends Throwable {

    }

    static class Score {
        private int total = 0;
        private HashMap<Integer, Integer> scores = new HashMap();

        public void setScore(int score) {
            if (scores.containsKey(score)) {
                scores.replace(score, scores.get(score) + 1);
            } else {
                scores.put(score, 1);
            }
            total += score;
        }

        public int getTotal() {
            return total;
        }

        public HashMap<Integer, Integer> getScores() {
            return scores;
        }

        public int compare(Score score){
            if(this.total>score.getTotal()){
                return 1;
            }
            if(this.total<score.getTotal()){
                return -1;
            }
            ArrayList<Integer> orderedScores= new ArrayList<>();
            for(int i=0;i<scores.size();i++){
                orderedScores.add(0);
            }
            for(int s: scores.keySet()){
                for(int i=0;i<scores.size();i++){
                    int pos = scores.size() - 1 - i;
                    if (pos - 1 < 0) {
                        orderedScores.set(pos,s);
                    } else {
                        if (orderedScores.get(pos - 1) < s) {
                            orderedScores.set(pos,orderedScores.get(pos - 1));
                        } else {
                            orderedScores.set(pos,s);
                            break;
                        }
                    }
                }
            }
            for(int i=0;i<scores.size();i++){
                int realScore= orderedScores.get(i);
                if(scores.get(realScore)>score.getScores().get(realScore)){
                    return 1;
                }
                else {
                    if(scores.get(realScore)<score.getScores().get(realScore)){
                        return -1;
                    }
                }
            }
            return 0;
        }
    }

    static class NumScore {
        private int number = 0;
        private Score score = new Score();

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public Score getScore() {
            return score;
        }

        public void setScore(Score score) {
            this.score = score;
        }
    }

    private static ArrayList<NumScore> getScore(ArrayList<NumScore> numberScore, int N, String line) throws ErrException {
        String[] numStrs = line.split(",");
        if (numStrs.length != N)
            throw new ErrException();
        for (int i = 0; i < numStrs.length; i++) {
            Score score = numberScore.get(i).getScore();
            int scoreNum = Integer.parseInt(numStrs[i]);
            if (scoreNum < 0 || scoreNum > 10)
                throw new ErrException();
            score.setScore(scoreNum);
        }
        return numberScore;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int lineCount = 0;
        int M = 0, N = 0;
        String firstLine = in.nextLine();
        String[] firstNumStrs = firstLine.split(",");
        if (firstNumStrs.length != 2) {
            System.out.println(-1);
            return ;
        }
        M = Integer.parseInt(firstNumStrs[0]);
        N = Integer.parseInt(firstNumStrs[1]);
        if (M < 3 || M > 10 || N < 3 || N > 100) {
            System.out.println(-1);
            return;
        }
        ArrayList<NumScore> numScores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            NumScore score = new NumScore();
            score.setNumber(i + 1);
            numScores.add(score);
        }
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            try {
                String line = in.nextLine();
                getScore(numScores, N, line);

            } catch (ErrException e) {
                System.out.println(-1);
                return;
            }
            lineCount++;
            if (lineCount == M)
                break;
        }

        // 数据准备好，排序和比较
        int topN=3;
        ArrayList<NumScore> topTotalScore = new ArrayList<>();
        for(int i=0;i<topN;i++){
            topTotalScore.add(new NumScore());
        }

        for (int i = 0; i < N; i++) {
            for(int j=0;j<topN;j++) {
                int pos = topN - 1 - j;
                if (pos - 1 < 0) {
                    topTotalScore.set(pos,numScores.get(i));
                } else {
                    if (topTotalScore.get(pos - 1).getScore().compare(numScores.get(i).getScore()) < 0) {
                        topTotalScore.set(pos,topTotalScore.get(pos - 1));
                    } else {
                        topTotalScore.set(pos,numScores.get(i));
                        break;
                    }
                }
            }

        }
        // 拼接结果
        String result = "";
        for(int i=0;i<topN;i++){
            result+=topTotalScore.get(i).getNumber()+(i+1<topN?",":"");
        }
        System.out.println(result);

    }
}