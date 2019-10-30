package JavaAdvancedExam_23Oct2019;

import java.util.*;
import java.util.stream.Collectors;

public class MakeASalad02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<String> vegetablesList = Arrays.stream(scanner.nextLine().
                split("\\s+")).
                collect(Collectors.toList());
        List<Integer> caloriesList = Arrays.stream(scanner.nextLine().
                split("\\s+")).
                map(Integer::parseInt).
                collect(Collectors.toList());
        Deque<String> vegetables = new ArrayDeque<>();
        for (String vegetableL : vegetablesList) {
            vegetables.offer(vegetableL);
        }
        Deque<Integer> calories = new ArrayDeque<>();
        for (Integer caloriesL : caloriesList) {
            calories.push(caloriesL);
        }

        Deque<Integer> readySalads = new ArrayDeque<>();

        while (!calories.isEmpty()) {
            if (vegetables.isEmpty()) {
                break;
            }
            int calorieSalad = calories.pop();
            int finishsalad = calorieSalad;
            while (!vegetables.isEmpty()) {
                String oneVegetable = vegetables.poll();
                switch (oneVegetable) {
                    case "tomato":
                        calorieSalad -= 80;
                        break;
                    case "carrot":
                        calorieSalad -= 136;
                        break;
                    case "lettuce":
                        calorieSalad -= 109;
                        break;
                    case "potato":
                        calorieSalad -= 215;
                        break;
                }
                if (calorieSalad <= 0 || vegetables.isEmpty()) {
                    readySalads.add(finishsalad);

                    break;
                }
            }
        }
        print(readySalads);
        if(!vegetables.isEmpty()) {
            printVegetables(vegetables);
        }
        print(calories);


    }

    private static void print(Deque<Integer> a) {
        String b = a.toString();
        b = b.substring(1, b.length() - 1).trim();
        b = b.replaceAll(",", "");
        System.out.println(b);

    }
    private static void printVegetables(Deque<String>a){
        for (String s : a) {
            System.out.print(a.poll()+ " ");
        }
    }
}

/*Make a Salad
Write a program that helps you prepare vegetable salads, which must be with a definite amount of calories.
You will receive two lines. The first one will be the vegetables. The second one, the calorie values of the salads.
Both will be separated by a single space. They will come in the following format:

"{vegetable1} {vegetable2}… {vegetablen}"
"{calories1} {calories2}… {caloriesn}"
Here is a table with the exact names of the vegetables and their calories:

		Vegetables	Calories
         tomato	       80
        carrot	       136
        lettuce	        109
         potato	       215

Start making the salads in the following way: take the last received calories and start adding vegetables from the first received vegetable.
 Each time you take a vegetable, you must reduce the amount of calories for the given salad with its calorie value and remove it from the collection.
  A salad is considered ready, when its calorie value reaches 0. When the salad is ready, remove it from the collection.
  If the calories of the current vegetable exceed the amount of needed calories for the salad, finish the salad and throw what is left of the vegetable. When you run out of either salads to make, or vegetables, print the salads you made (their calorie value) on a single line, separated by space, beginning with the first salad you made in the following format:
"{salad1} {salad2}… {saladn}"
At last, print either the vegetables that are left,
 or the calories of the salads you couldn't prepare,
 depending on the case – if you have vegetables left,
  print them, if you have salads left, print them on a single line, separated by space.
Input
On the first line, you will receive the vegetables – strings separated by a single space.
On the second line, you will receive the salads' calories – integers, separated by a single space.
Input will always be valid.
Output
Print the finished salads' calories from the first made one to the last one in the format described above
Examples

Input
tomato potato carrot lettuce tomato
250 563 478 330 470 112



Output
112 470
330 478 563 250


Comments
We take the first received vegetable - the tomato.
It has 80 calories, so we take the last received salad calories – 112 and subtract 80 from it.
The salad needs 32 more calories. We remove the vegetable and take the next one – potato.
It has 215 calories. The first salad is finished, so we remove the vegetable, also the salad's calories from the collection.
We take the next one, which needs 470 calories, and so on.


Input
carrot tomato potato potato lettuce tomato potato potato
105 130 200 110

Output
110 200 130 105
tomato potato potato
*/