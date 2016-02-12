/**
 * Created by Jinwoo on 2016-02-11.
 */
package com.example.jinwoo.edulock.patternview;

public class algo {
    public static final int START_POINT = 0;
    public static final int ARR_SIZE = 4;
    public static final int NODE_NUM = ARR_SIZE*ARR_SIZE;
    public static final char[][] array = new char[ARR_SIZE][ARR_SIZE];
    public static final int[][] visit = new int[ARR_SIZE][ARR_SIZE];
    public static final int[][] adjacency_Matrix = new int[NODE_NUM][NODE_NUM];

    static void create_BasePattern(char[][] array, String word) {
        for(int i=0; i<ARR_SIZE; i++) {
            for(int j=0; j<ARR_SIZE; j++) {
                if(i==START_POINT && j==0) {
                    array[i][j] = word.charAt(0);
                    visit[i][j] = 1;
                } else {
                    int randomNum = 0;
                    do {
                        randomNum = randomRange(97, 122);
                    } while(is_DupRandomCoverage(randomNum , word));
                    array[i][j] = (char)randomNum;
                }
            }
        }
    }
    static int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    static boolean is_DupRandomCoverage(int ranNum, String word) {
        for(int i=0; i<word.length(); i++) {
            if((int)word.charAt(i) == ranNum)
                return true;
        }
        return false;
    }

    static void printPattern(char[][] array) {
        for(int i=0; i<ARR_SIZE; i++) {
            for(int j=0; j<ARR_SIZE; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    static int arrX[] = {0, 1, 0, -1};
    static int arrY[] = {1, 0, -1, 0};
    static String word = "transportation";
    static int stringCount = word.length();
    static boolean findCheck = false;

    public static void create_patten(int x, int y, int count) {
        if(count == stringCount || findCheck == true) {
            findCheck = true;
            return;
        }
        visit[x][y] = 1;
        array[x][y] = word.charAt(count);
        printPattern(array);
        System.out.println("=====================================");
        int num = randomRange(0, 3);
        int tempX = 0, tempY = 0;
        int N = 4;
        boolean block = false;

        for(int i = 0; i < 4; i++) {
            tempX = x + arrX[num];
            tempY = y + arrY[num];

            if (tempX < 0 || tempX > 3 || tempY < 0 || tempY > 3) {
                num = ++num % 4;
            } else {
                if (visit[tempX][tempY] != 0)
                    num = ++num % 4;
                else {
                    create_patten(tempX, tempY, count + 1);
                    visit[tempX][tempY] = 0;
                }
            }
        }
    }
    public static void main() {
        for(int i=0; i<ARR_SIZE; i++) {
            for(int j=0; j<ARR_SIZE; j++) {
                visit[i][j] = 0;
            }
        }

        for(int i=0; i<ARR_SIZE; i++) {
            for(int j=0; j<ARR_SIZE; j++) {
                array[i][j] = '-';
            }
        }

        create_patten(randomRange(0, 3),0, 0);
        printPattern(array);

        //create_BasePattern(array, word);
        //create_CompletePattern(array, word);
        //printPattern(array);
    }
}