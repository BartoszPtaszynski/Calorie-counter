import java.time.LocalDate;
import java.util.Scanner;

public class Meal {
    private String name;
    private LocalDate date;

    private int numberOfCalories;

    public Meal(String name, int numberOfCalories) {
        this.name = name;
        this.numberOfCalories = numberOfCalories;
        date=LocalDate.now();
    }

    public static Meal addMeal()
    {
        Meal meal=new Meal("",0);
        Scanner scanner=new Scanner(System.in);
        System.out.print("Provide name of meal: ");meal.name=scanner.nextLine();
        System.out.print("Provide number of calories: ");
        boolean isOk = false;
        do {
            try {
                meal.numberOfCalories = Integer.parseInt(scanner.nextLine());
                isOk = true;
            } catch (NumberFormatException e) {
                System.err.print("Incorrect value, provide once again: ");

            }
        } while (!isOk);
        return meal;

    }

    @Override
    public String toString() {
        return """
               name of meal: %s
               number of calories: "%d"
               date: """.formatted(name,numberOfCalories)+date;
    }
}
