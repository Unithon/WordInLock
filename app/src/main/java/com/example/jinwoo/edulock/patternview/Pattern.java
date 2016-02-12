package com.example.jinwoo.edulock.patternview;

/**
 * Created by Jinwoo on 2016-02-12.
 */
public class Pattern {
    public static final int ARR_SIZE = 4;
    public static final char[][] array = new char[ARR_SIZE][ARR_SIZE];
    public static final int[][] visit = new int[ARR_SIZE][ARR_SIZE];

    public static int[] arrX = { 0, 1, 0, -1 };
    public static int[] arrY = { 1, 0, -1, 0 };

    public static String word = "apple";

    public static int[] resultX = new int[word.length()];
    public static int[] resultY = new int[word.length()];

    static void create_BasePattern(char[][] array, String word) {
        for (int i = 0; i < ARR_SIZE; i++) {
            for (int j = 0; j < ARR_SIZE; j++) {
                int randomNum = 0;

                do {
                    randomNum = randomRange(97, 122);
                } while (is_DupRandomCoverage(randomNum, word));

                array[i][j] = (char) randomNum;
            }

        }
    }

    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    static boolean is_DupRandomCoverage(int ranNum, String word) {
        for (int i = 0; i < word.length(); i++) {
            if ((int) word.charAt(i) == ranNum)
                return true;
        }

        return false;
    }

    public static void create_patten(int x, int y, int count) {
        int stringCount = word.length();
        boolean findCheck = false;

        int tempX = 0, tempY = 0;
        int num = randomRange(0, 3);

        if (count == stringCount || findCheck == true) {
            findCheck = true;
            return;
        }

        resultX[count] = x;
        resultY[count] = y;

        visit[x][y] = 1;
        array[x][y] = word.charAt(count);

        for (int i = 0; i < 4; i++) {
            tempX = x + arrX[num];
            tempY = y + arrY[num];

            if (tempX < 0 || tempX > 3 || tempY < 0 || tempY > 3) {
                num = ++num % 4;
            } else {
                if (visit[tempX][tempY] != 0)
                    num = ++num % 4;
                else {
                    create_patten(tempX, tempY, count + 1);

                    if (findCheck != true)
                        visit[tempX][tempY] = 0;
                }
            }
        }
    }

    public static void printPattern(char[][] array) {
        for (int i = 0; i < ARR_SIZE; i++) {
            for (int j = 0; j < ARR_SIZE; j++) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println();
        }
    }

    static void printResult(int[] resultX, int[] resultY) {
        System.out.print("resultX[] : ");
        for(int i = 0; i < resultX.length; i++) {
            System.out.print(resultX[i] + " ");
        }

        System.out.println();
        System.out.print("resultY[] : ");
        for(int j=0; j<resultY.length; j++) {
            System.out.print(resultY[j] + " ");
        }
    }


    public static void do_Process() {
        create_BasePattern(array, word);
        create_patten(randomRange(0, 3), 0, 0);
        printPattern(array);
        printResult(resultX, resultY);
    }

    public static int[] get_ResultX() {
        return resultX;
    }

    public static int[] get_ResultY() {
        return resultY;
    }
    /*
    public static void main(String[] args) {
        create_BasePattern(array, word);
        create_patten(randomRange(0, 3), 0, 0);
        printPattern(array);

        System.out.println();
        printResult(resultX, resultY);
    }*/
}