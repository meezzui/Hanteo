package org.example;

import java.util.Scanner;

public class Currency {
    public int change(int amount, int[] coins) {
        int[] makeArray = new int[amount + 1];
        makeArray[0] = 1; // 합계가 0일 때는 하나의 방법(아무 동전도 사용하지 않는 방법)만 있다.

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                makeArray[i] += makeArray[i - coin];
            }
        }

        return makeArray[amount];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Currency currencyChange = new Currency();

        System.out.print("서로 다른 종류의 통화를 입력하세요. 콤마와 숫자 사이에 공백을 넣어주세요. (예: {1, 2, 3}): ");
        String currenciesInput = scanner.nextLine().trim();

        // 입력 값이 유효한지 확인
        if (!currenciesInput.matches("\\{\\s*\\d+(\\s*,\\s*\\d+)*\\s*\\}")) {
            System.out.println("유효하지 않은 입력값 입니다.");
            return;
        }

        // 중괄호와 쉼표를 제거하고 숫자들을 구분하여 배열로 변환
        String[] currencyArray = currenciesInput.substring(1, currenciesInput.length() - 1).split(", ");
        int[] currencies = new int[currencyArray.length];
        for (int i = 0; i < currencyArray.length; i++) {
            try {
                currencies[i] = Integer.parseInt(currencyArray[i]);
            } catch (NumberFormatException e) {
                System.out.println("유효하지 않은 숫자가 포함되어 있습니다.");
                return;
            }
        }

        System.out.print("합계를 입력하세요: ");
        int amount = scanner.nextInt();

        int ways = currencyChange.change(amount, currencies);
        System.out.println("합계를 만드는 방법의 수: " + ways);
    }
}
