package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        List<Integer> randomNumList = makeRandomNum();
        List<Integer> inputNumList = makeInputNumList();
    }
    public static List<Integer> makeRandomNum() {
        List<Integer> randomNumList = new ArrayList<>();
        while(randomNumList.size() < 3) {
            int newRandomNum = Randoms.pickNumberInRange(1, 9);
            if (!randomNumList.contains(newRandomNum)) {
                randomNumList.add(newRandomNum);
            }
        }
        return randomNumList;
    }
    public static List<Integer> makeInputNumList() {
        System.out.print("숫자를 입력해주세요 : ");

        String inputString = Console.readLine();

        List<Integer> inputNumList = new ArrayList<>();
        for (int inputStringIdx = 0; inputStringIdx < inputString.length(); inputStringIdx++) {
            int inputNum = (int) inputString.charAt(inputStringIdx) - 48;
            inputNumList.add(inputNum);
        }
        if (isValidList(inputNumList)) {
            return inputNumList;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public static boolean isValidList(List<Integer> numList) {
        if (numList.size() != 3) {
            return false;
        }
        Set<Integer> numSet= new HashSet<>(numList);
        if (numList.size() != numSet.size()) {
            return false;
        }
        for (int num : numList) {
            if (num < 1 || num > 9) {
                return false;
            }
        }
        return true;
    }
    public static Integer cntContain(List<Integer> inputNumInfo, List<Integer> answerNumInfo) {
        int contain = 0;
        int inputNum = inputNumInfo.get(1);
        int anwerNum = answerNumInfo.get(1);

        if (inputNum == anwerNum) {
            contain ++;
        }
        return contain;
    }
    public static Integer cntStrike(List<Integer> inputNumInfo, List<Integer> answerNumInfo) {
        int strike = 0;
        int inputidx = inputNumInfo.get(0);
        int inputNum = inputNumInfo.get(1);
        int answeridx = answerNumInfo.get(0);
        int anwerNum = answerNumInfo.get(1);

        if (inputNum == anwerNum) {
            if (inputidx == answeridx) {
                strike ++;
            }
        }
        return  strike;
    }
    public static List<Integer> makeResultList (List<Integer> inputNumList, List<Integer> answerList) {
        int strike = 0, contain = 0;
        for (int inputIdx = 0; inputIdx < 3; inputIdx++) {
            for (int answerIdx = 0; answerIdx < 3; answerIdx++) {
                int inputNum = inputNumList.get(inputIdx);
                int answerNum = answerList.get(answerIdx);
                List<Integer> inputNumInfo = List.of(inputIdx, inputNum);
                List<Integer> answerNumInfo = List.of(answerIdx, answerNum);
                contain = contain + cntContain(inputNumInfo, answerNumInfo);
                strike = strike + cntStrike(inputNumInfo, answerNumInfo);
            }
        }
        int ball = contain - strike;
        return List.of(ball, strike);
    }
}
