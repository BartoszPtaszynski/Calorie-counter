import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public Meal(String name, int numberOfCalories,LocalDate date) {
        this.name = name;
        this.numberOfCalories = numberOfCalories;
        this.date=date;
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
        String line="NAME:"+meal.name+"CALORIES:"+meal.numberOfCalories+"DATE:"+meal.date+"\n";

        try{
            BufferedWriter  fileWriter=new BufferedWriter (new FileWriter("src/listOfMeals",true));
            fileWriter.write(line);
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR DURING WRITING TO THE FILE");
        }
        return meal;

    }

    @Override
    public String toString() {
        return """
               name of meal: %s
               number of calories: "%d"
               date: """.formatted(name,numberOfCalories)+date;
    }

    public int getNumberOfCalories() {
        return numberOfCalories;
    }

    public LocalDate getDate() {
        return date;
    }
    public static ArrayList<Meal> getArchiveMeals()
    {
        ArrayList<Meal> meals=new ArrayList<>();
        try{
            File mealsFile=new File("src/listOfMeals");
            Scanner fileReader=new Scanner(mealsFile);
            String name;
            LocalDate date;
            int calories;
            String data;
            int year,month,day;
            while(fileReader.hasNextLine())
            {
                data=fileReader.nextLine();
                name=data.substring(5,data.indexOf("CALORIES:"));
                calories=Integer.parseInt(data.substring(data.indexOf("CALORIES:")+9,data.indexOf("DATE:")));
                year=Integer.parseInt(data.substring(data.indexOf("DATE:")+5,data.indexOf("DATE:")+9));
                month=Integer.parseInt(data.substring(data.indexOf("DATE:")+10,data.indexOf("DATE:")+12));
                day=Integer.parseInt(data.substring(data.indexOf("DATE:")+13,data.indexOf("DATE:")+15));
                date=LocalDate.of(year,month,day);
                meals.add(new Meal(name,calories,date));

            }

        }catch (FileNotFoundException e)
        {
            System.out.println("FILE ERROR");
        }
        return meals;
    }

}
