package fireDrillTwo;

import java.util.Scanner;

public class TaskTwo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int sum = 0;

        for (int count = 0; count < 10; count++) {
            System.out.println("Enter score:");
            int score = input.nextInt();

            sum += score;
        }

        System.out.printf("Average of scores ====> %.2f", sum / 10.0);
    }
}
