package model.validate;

import java.util.Arrays;

public class ValidateCPF {

    private int digitChecker1 = 0;
    private int digitChecker2 = 0;
    private int first = 10;
    private int second = 11;

    private boolean check = Boolean.FALSE;

   
    public boolean validate(String userCPF) {

        char[] arrayChar = userCPF.toCharArray();
        int[] arrayInt = new int[arrayChar.length];
        int[] arrayCPF = new int[11];

        for (int i = 0; i < 11; i++) {
            arrayInt[i] = Integer.parseInt(String.valueOf(arrayChar[i]));
        }

        if (userCPF.equals("00000000000")
                || userCPF.equals("11111111111")
                || userCPF.equals("22222222222")
                || userCPF.equals("33333333333")
                || userCPF.equals("44444444444")
                || userCPF.equals("55555555555")
                || userCPF.equals("66666666666")
                || userCPF.equals("77777777777")
                || userCPF.equals("88888888888")
                || userCPF.equals("99999999999")
                || (userCPF.length() != 11)) {

                return check;
        } else {

            // Calculating the First Digit Checker
            for (int i = 0; i < 9; i++) {
                digitChecker1 += arrayInt[i] * first;
                arrayCPF[i] = arrayInt[i];
                first--;
            }
            digitChecker1 = digitChecker1 % 11;
            digitChecker1 = (11 - digitChecker1);

            if (digitChecker1 > 9) {
                digitChecker1 = 0;
                arrayCPF[9] = 0;
            } else {
                arrayCPF[9] = digitChecker1;
            }

            // Calculating the second Digit Checker
            for (int i = 0; i < 10; i++) {
                digitChecker2 += arrayCPF[i] * second;
                second--;
            }
            digitChecker2 = digitChecker2 % 11;
            digitChecker2 = (11 - digitChecker2);

            if (digitChecker2 > 9) {
                digitChecker2 = 0;
                arrayCPF[10] = 0;
            } else {
                arrayCPF[10] = digitChecker2;
            }

            check = arraysEquals(arrayCPF, arrayInt);
        }
        return check;
    }

    public boolean arraysEquals(int[] arrayCPF, int[] arrayInt) {

        boolean checkEquals = Boolean.FALSE;

        if (Arrays.equals(arrayCPF, arrayInt)) {
            checkEquals = Boolean.TRUE;
        }

        return checkEquals;
    }
}
