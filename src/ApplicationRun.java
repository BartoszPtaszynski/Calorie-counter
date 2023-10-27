import java.util.InputMismatchException;
import java.util.Scanner;

public class ApplicationRun {
    private Person person;


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
                    case 1-> person=Person.addPerson();
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
                System.out.println("""
                        1. provide today's weight.
                        2. add meal.
                        3. print history of eaten calories and weight.
                        """);
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
                    case 1->{}
                    case 2->person.addMeal();
                    case 3->person.printListOfMeal();

                }
            }
        }
    }


}
