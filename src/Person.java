import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    private  String name;
    private  double currentWeight;
    private double targetWeight;
    private double startedWeight;

    private ArrayList<Meal> listOfMeals;

    public Person(String name, double weight, double targetWeight) {
        this.name = name;
        this.currentWeight = weight;
        this.targetWeight = targetWeight;
        this.startedWeight=weight;
        listOfMeals=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return currentWeight;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setWeight(double weight) {
        this.currentWeight = weight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public static   Person addPerson()
    {
        Person person=new Person("",0,0);
        Scanner scanner=new Scanner(System.in);

        System.out.print("Provide your name: "); person.name=scanner.nextLine();

        System.out.print("Provide your current weight: ");

        boolean isOk=false;
        do {
            try {
                double x=Double.parseDouble(scanner.nextLine());
                person.currentWeight = x;
                person.startedWeight=x;

                isOk = true;
            } catch (NumberFormatException e) {

                System.err.print("Incorrect value, provide once again: ");
                // Odrzucenie nieprawidłowej wartości
            }
        } while (!isOk);

        System.out.print("Provide your target weight: ");
        isOk = false;
        do {
            try {
                person.targetWeight = Double.parseDouble(scanner.nextLine());
                isOk = true;
            } catch (NumberFormatException e) {
                System.err.print("Incorrect value, provide once again: ");
                // Odrzucenie nieprawidłowej wartości
            }
        } while (!isOk);
        return person;
    }

    public void getInfo() {
        System.out.printf("""
                          NAME: %s
                CURRENT WEIGHT: %.2f kg
                 TARGET WEIGHT: %.2f kg 
                YOUR WEIGHT HAS BEEN CHANGED SINCE BEGINING ABOUT: %.2f
                TODAY CALORIES: %d
                %n""", name,currentWeight,targetWeight,currentWeight-startedWeight,getTodayCalories());
    }
    public int getTodayCalories()
    {
        int calories=0;
        for(Meal meal: listOfMeals)
        {
            if(meal.getDate().equals( LocalDate.now()))
            {
                calories+=meal.getNumberOfCalories();
            }
        }


        return calories;
    }
    public void addMeal()
    {
        listOfMeals.add(Meal.addMeal());
    }

    public void printListOfMeal()
    {
        for(Meal meal:listOfMeals)
        {
            System.out.println(meal);
        }
    }
}
