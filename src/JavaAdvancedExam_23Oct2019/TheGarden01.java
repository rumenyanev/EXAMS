package JavaAdvancedExam_23Oct2019;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TheGarden01 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countCarrots = 0;
        int countPotatoes = 0;
        int countLettuce = 0;

        int countHarmed = 0;

        int n = Integer.parseInt(scanner.nextLine());
        List<List<String>> matrix = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            matrix.add(i, new ArrayList<>());
            matrix.get(i).addAll(Arrays
                    .stream(scanner.nextLine().split("\\s+"))
                    .collect(Collectors.toList()));
        }

        String input = scanner.nextLine();
        while (!input.equals("End of Harvest")) {
            String[] arr = input.split("\\s+");

            String command = arr[0];
            int row = Integer.parseInt(arr[1]);
            int col = Integer.parseInt(arr[2]);

            switch (command) {
                case "Harvest":
                    if (row >= 0 && row < matrix.size()) {
                        if (col >= 0 && col < matrix.get(row).size()) {
                            String symbol = matrix.get(row).get(col);
                            switch (symbol) {
                                case "C":
                                    countCarrots++;
                                    break;
                                case "L":
                                    countLettuce++;
                                    break;
                                case "P":
                                    countPotatoes++;
                                    break;
                            }
                            matrix.get(row).set(col, " ");
                        }
                    }
                    break;
                case "Mole":
                    String direction = arr[3];
                    direction = direction.substring(0, 1).toUpperCase() + direction.substring(1);
                    if (row >= 0 && row < matrix.size()) {
                        if (col >= 0 && col < matrix.get(row).size()) {
                            switch (direction) {
                                case "Up":
                                    for (int i = row; i >= 0; i -= 2) {
                                        if (!matrix.get(i).get(col).equals(" ")) {
                                            matrix.get(i).set(col, " ");
                                            countHarmed++;
                                        }
                                    }
                                    break;
                                case "Down":
                                    for (int i = row; i < matrix.size(); i += 2) {
                                        if (!matrix.get(i).get(col).equals(" ")) {
                                            matrix.get(i).set(col, " ");
                                            countHarmed++;
                                        }
                                    }

                                    break;
                                case "Left":
                                    for (int i = col; i >= 0; i -= 2) {
                                        if (!matrix.get(row).get(i).equals(" ")) {
                                            matrix.get(row).set(i, " ");
                                            countHarmed++;
                                        }
                                    }

                                    break;
                                case "Right":
                                    int aaa = 111;
                                    for (int i = col; i < matrix.get(row).size(); i += 2) {
                                        if (!matrix.get(row).get(i).equals(" ")) {
                                            matrix.get(row).set(i, " ");
                                            countHarmed++;
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                    break;
            }
            input = scanner.nextLine();
        }
        printMatrix(matrix);
        System.out.println("Carrots: " + countCarrots);
        System.out.println("Potatoes: " + countPotatoes);
        System.out.println("Lettuce: " + countLettuce);
        System.out.println("Harmed vegetables: " + countHarmed);
    }

    private static void printMatrix(List<List<String>> matrix) {
        StringBuilder sb = new StringBuilder();
        for (List<String> matrix1 : matrix) {
            for (int j = 0; j < matrix1.size(); j++) {
                sb.append(matrix1.get(j)).append(" ");
            }
            String line = sb.toString();
            line = line.substring(0, line.length() - 1);
            System.out.print(line);
            sb.delete(0, sb.length());
            System.out.println();
        }
    }
}


/*   The Garden
Create a program that helps you harvest vegetables. There are three kinds of vegetables in your garden:
⦁	Lettuce  – 'L', Potatoes – 'P', Carrots  – 'C'
First, you will receive the rows of the garden. Then for each row, you will receive the vegetables, separated by space in the following format:
"{vegetable1} {vegetable2} {vegetable3}… {vegetablen}"
Then you will start receiving commands. Here are the possible ones you can receive:

⦁	"Harvest {row} {col}" – you must go to the given place in the garden and harvest the vegetable, if it exists.
When you harvest a vegetable, you leave an empty space in the cell – ' '.
Keep in mind, that you can't harvest a vegetable, which was already harvested or harmed.

⦁	"Mole {row} {col} {direction}" – there is a mole in that cell and it goes in that direction, which means the mole,
goes from this cell until the last cell in the given direction. It harms the given cell, skips the next, and harms the next one, an so on until the last cell.
 Mark the harmed cells with a space - ' '. Keep in mind, that you can't harm a vegetable, that was already harmed or harvested.

  There are four possible directions:
⦁	"Up", "Down", "Left", "Right"
⦁	"End of Harvest" – ends the input.
Here is an example of the mole's harm radius:

In the end, print the resulting garden. The cells must be separated by a space. Then print the vegetbles you have succesfully harvested and the count of harmed vegetables you have found in the following format:
"Carrots: {countOfCarrots}
Potatos: {countOfPotatos}
Lettuce: {countOfCucmbers}
Harmed vegetables: {count}"
Input / Constraints
⦁	On the first line, you will receive the count of rows.
⦁	On the next lines, for each row, you will receive the vegetables in the described format.
⦁	Next, until you receive "End of Harvest", you will be receiving commands in the described format.
⦁	The input will always be valid and you don't need to check it explicitly.
Output
⦁	Print the resulting garden – each cell separated by a single space.
⦁	Print the harvested and harmed vegatables in the format described above.
Examples
Input

4
L P C L L
L L C P P P
C C C C
P C L P C L P C L
Harvest 0 2
Harvest 3 0
Harvest 4 2
Mole 2 2 up
Mole 1 1 right
End of Harvest


Output
L P   L L
L   C   P
C C   C
  C L P C L P C L
Carrots: 1
Potatoes: 1
Lettuce: 0
Harmed vegetables: 4



When we receive the "Harvest" command, we go to the given coordinates and harvest the 'C' and leave an empty space ' '. After that, we go to the 'P' on 3 0 and we take it. After that we receive invalid coordinates, so we don't do anything. Upon receiving the mole command, we harm the vegetable in its cell and every vegetable in the described way – harm the current cell, skip the next and this repeats until the end of the row/coll. We leave empty spaces in the cells. In the end, we have 4 harmed vegetables, one harvested carrot and one harvested potato.

Input
3
P L C
C C C C C C
L L P P P L L L
Harvest 0 0
Harvest 1 3
Mole 2 0 up
Harvest 2 5
Harvest 1 1
Harvest 0 2
Harvest 1 4
End of Harvest


Output
L
C   C     C
  L P P P   L L
Carrots: 4
Potatoes: 1
Lettuce: 1
Harmed vegetables: 1
*/
