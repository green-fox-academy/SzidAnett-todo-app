import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ToDo {
    public static void main(String[]args) {
        File tasks = new File("tasks.txt");

        if (args.length == 0) {
            printUsage();
        } else if (args[0].equals("-l")) {
            if (tasks.length() == 0) {
                System.out.println("No todos for today! :)");
            } else {
                taskList(tasks);
            }
        }
    }

    public static void printUsage() {
        System.out.println("Command Line Todo application");
        System.out.println("=============================");
        System.out.println();
        System.out.println("Command line arguments:");
        System.out.println("  -l   Lists all the tasks");
        System.out.println("  -a   Adds a new task");
        System.out.println("  -r   Removes an task");
        System.out.println("  -c   Completes an task");
    }
    public static void taskList(File file) {
        try {
            Scanner myReader = new Scanner(file);
            int counter = 0;
            while (myReader.hasNextLine()) {
                counter++;
                String data = myReader.nextLine();
                System.out.println(Integer.toString(counter) + " - " + data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("There's no task list");
            System.exit(2);
        }

    }
}

