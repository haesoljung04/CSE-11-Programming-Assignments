import java.util.Scanner;

public class GradeCalculator {
    public static void main (String[] args) {
        // Create a scanner object
        Scanner input = new Scanner(System.in);
        // Create 
        int n = input.nextInt();
        if (n <= 0) {
            System.out.print("invalid input");
            return;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int paScore = input.nextInt();
            if (paScore >= 0 && paScore <= 100) {
                sum += paScore;
            }
            else {
                System.out.print("invalid input");
                return;
            }
        }
        double decSum = sum;
        double AveragePaScore = (decSum / n);
        int MidtermScore = input.nextInt();
        if (MidtermScore < 0 || MidtermScore > 100) {
            System.out.println("invalid input");
            return;
        }

        int FinalScore = input.nextInt();
        if (FinalScore < 0 || FinalScore > 100) {
            System.out.println("invalid input");
            return;
        }
        double OverallScore = (AveragePaScore * 0.5) + (MidtermScore * 0.125) + (FinalScore * 0.375);
        
        if (OverallScore >= 90 && OverallScore <= 100) {
            System.out.println(OverallScore);
            System.out.println("A");
        }
        else if (OverallScore >= 80 && OverallScore < 90) {
            System.out.println(OverallScore);
            System.out.println("B");
        }
        else if (OverallScore >= 70 && OverallScore < 80) {
            System.out.println(OverallScore);
            System.out.println("C");
        }
        else if (OverallScore >= 60 && OverallScore <=70) {
            System.out.println(OverallScore);
            System.out.println("D");
        }
        else {
            System.out.println(OverallScore);
            System.out.println("F");
        }

        }


    }

    

