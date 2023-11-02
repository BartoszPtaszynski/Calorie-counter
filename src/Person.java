import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Person {
    private  String name;
    private double targetWeight;
    private ArrayList<Weight> weights;


    private ArrayList<Meal> listOfMeals;

    public Person(String name, ArrayList<Weight> weights, double targetWeight) {
        this.name = name;

        this.targetWeight = targetWeight;
        this.weights=weights;
        listOfMeals=Meal.getArchiveMeals();

    }
    public   Person ()
    {

        weights=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);

        System.out.print("Provide your name: "); this.name=scanner.nextLine();

        System.out.print("Provide your current weight: ");

        boolean isOk=false;
        do {
            try {
                double x=Double.parseDouble(scanner.nextLine());
                weights.add(new Weight(x,LocalDate.now()));

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
                this.targetWeight = Double.parseDouble(scanner.nextLine());
                isOk = true;
            } catch (NumberFormatException e) {
                System.err.print("Incorrect value, provide once again: ");
            }
        } while (!isOk);
        String data="NAME:"+this.name+"\nTARGETWEIGHT:"+this.targetWeight+"\n"+this.weights.get(0);
        try{
            FileWriter fileWriter=new FileWriter("resources/personData");
            fileWriter.write(data);
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("ERROR WITH FIND FILE");
        }
        listOfMeals=new ArrayList<>();

    }
    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weights.add(new Weight(weight,LocalDate.now()));
        refreshPersonFile();
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;

    }

    public static Person getArchivePerson()
    {
        try{
            File personFile=new File("resources/personData");
            Scanner fileReader=new Scanner(personFile);
            String name;
            double targetWeight;
            ArrayList<Weight> weights=new ArrayList<>();
            if(fileReader.hasNextLine())
            {
                name=fileReader.nextLine().substring(5);
                targetWeight=Double.parseDouble(fileReader.nextLine().substring(13));
                String data;
                double weight;
                int year,month,day;
                while (fileReader.hasNextLine())
                {
                    data=fileReader.nextLine();
                    weight=Double.parseDouble(data.substring(0,data.indexOf(" ")));
                    year=Integer.parseInt(data.substring(data.indexOf(" ")+1,data.indexOf(" ")+5));
                    month=Integer.parseInt(data.substring(data.indexOf(" ")+6,data.indexOf(" ")+8));
                    day=Integer.parseInt(data.substring(data.indexOf(" ")+9,data.indexOf(" ")+11));
                    weights.add(new Weight(weight,LocalDate.of(year,month,day)));
                }

                return new Person(name,weights,targetWeight);
            }
            fileReader.close();
        }catch (FileNotFoundException e)
        {
            System.out.println("FILE ERROR");
        }
        return null;
    }

    public void getInfo() {
        System.out.printf("""
                          
                          NAME: %s
                CURRENT WEIGHT: %.2f kg
                 TARGET WEIGHT: %.2f kg 
                YOUR WEIGHT HAS BEEN CHANGED SINCE BEGINING ABOUT: %.2f
                TODAY CALORIES: %d
                %n""", name,weights.get(weights.size()-1).getWeight(),
                targetWeight,weights.get(weights.size()-1).getWeight()-weights.get(0).getWeight(),
                getEatenCalories(LocalDate.now()));
    }
    public int getEatenCalories(LocalDate date)
    {
        int calories=0;
        for(Meal meal: listOfMeals)
        {
            if(meal.getDate().equals( date))
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
        LocalDate today=LocalDate.now();
        LocalDate currentDate=today.minusDays(7);
        LocalDate date=today.minusDays(7);

        int number=0;
        for(Meal meal:listOfMeals)
        {
           if(meal.getDate().isAfter(date))
           {
               if(!currentDate.equals(meal.getDate())||currentDate.equals(listOfMeals.get(listOfMeals.size()-1)))
               {
                   System.out.println("-".repeat(40));
                   System.out.println("EATEN CALORIES "+meal.getDate()+": "+getEatenCalories(meal.getDate()));
               }
               System.out.println(meal+"\n");
               number++;


               currentDate=meal.getDate();
           }
        }
        if(number==0)
        {
            System.out.println("NO MEALS IN THE HISTORY!");
        }
    }
    private void refreshPersonFile()
    {
        String data="NAME:"+name+"\nTARGETWEIGHT:"+targetWeight;
        for(Weight w:weights)
        {
            data=data+"\n"+w;
        }
        try{
            FileWriter fileWriter=new FileWriter("resources/personData");
            fileWriter.write(data);
            fileWriter.close();

        }
        catch (IOException e)
        {
            System.out.println("ERROR WITH FIND FILE");
        }

    }

    public void printHistoryOfWeight() {
        System.out.println("YOUR CHANGE OF THE WEIGHT:");
        System.out.println("WEIGHT:\tDATE:");
        for(Weight w:weights)
        {
            System.out.println(w.getWeight()+"KG\t"+w.getDate());
        }

    }

    public void printHistoryOfCalories() {
        if(listOfMeals.size()==0){ System.out.println("NO ADDED MEALS YET"); return;}
        System.out.println("YOUR DAILY CALORIES:");
        LocalDate date=listOfMeals.get(0).getDate();
        for(Meal m:listOfMeals) {
            if (!m.getDate().equals(date)||m==listOfMeals.get(listOfMeals.size()-1))
            {
                System.out.println("KCAL: "+getEatenCalories(date)+"\tDATE:"+date);
                date=m.getDate();
            }
        }

    }
}
