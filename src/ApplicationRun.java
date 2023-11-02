import java.util.InputMismatchException;
import java.util.Scanner;
public class ApplicationRun {
    private Person person;

    public ApplicationRun() {
        person= Person.getArchivePerson();
    }

    public void start()
    {

        Scanner scanner=new Scanner(System.in);
        int decision=0;
        loop:
        while (true)
        {
            if(person==null)
            {

                System.out.print("No person in your application. Add a new Person (enter 1) ! or quit application (enter 0) :");
                try{
                    decision=scanner.nextInt();
                }
                catch (InputMismatchException e)
                {
                    System.err.println("Bad value");
                    continue loop;
                }
                switch (decision)
                {
                    case 1-> {
                        person=new Person();
                    }
                    case 0->{ break loop;}
                    default -> {
                        System.err.println("Bad value");
                        continue loop;
                    }
                }


            }
            else
            {
                person.getInfo();
                System.out.print("""
                        1. provide today's weight.
                        2. add meal.
                        3. print history of your weight.
                        4. print history of your calories eaten.
                        5. print your eaten Meals from the last week
                        6. EXIT
                        """);
                try{
                    decision=scanner.nextInt();
                }
                catch (InputMismatchException e)
                {
                    System.err.println("Bad value");
                    continue;
                }

                switch (decision)
                {
                    case 1->{
                        try{
                            System.out.print("Provide today's weight: ");
                            scanner.nextLine();
                            person.setWeight(Double.parseDouble(scanner.nextLine()));
                        }
                        catch (NumberFormatException e)
                        {
                            System.err.println("invalid data");

                        }
                    }
                    case 2->person.addMeal();
                    case 3->person.printHistoryOfWeight();
                    case 4->person.printHistoryOfCalories();
                    case 5->person.printListOfMeal();
                    case 6->System.exit(0);
                }
            }
        }

    }




}
