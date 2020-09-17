package com.junyi.BackTracking;

/**
 * User: JY
 * Date: 2019/3/8 0008
 * Description: 求解八皇后问题
 */
public class EightQueen {
    private int[] result = new int[8];
    private int num = 0;

    public void cal8Queen(int row) {
        if (row == 8) {
            num++;
            printResult();
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOK(row, column)) {
                result[row] = column;
                cal8Queen(row+1);
            }
        }
    }

    private boolean isOK(int row, int column) {
        int leftup = column-1, rightup = column+1;
        for (int i = row-1; i >= 0; i--) {
            if (result[i] == column) return false;
            if (leftup >= 0) {
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) {
                if (result[i] == rightup) return false;
            }
            leftup--;
            rightup++;
        }
        return true;
    }

    private void printResult() {
        System.out.println("Type : " + num);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (result[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        EightQueen eq = new EightQueen();
        eq.cal8Queen(0);
    }
}
