import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ToDo {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        Path task = Paths.get("tasks.txt");

        if (args.length == 0) {
            printUsage();

        } else if(!(args[0].equals("-l") || args[0].equals("-a") || args[0].equals("-r") || args[0].equals("-c"))) {
            System.out.println("Unsupported argument");
            printUsage();

        } else if (args[0].equals("-l")) {
            try {
                lists = Files.readAllLines(task);

            }catch (IOException e) {
                System.out.println("No file");
                System.exit(2);
            }
            System.out.println();
            if (lists.size() == 0){
                System.out.println("No todos for today! :)");
            } else {
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println((i+1)+" - "+ lists.get(i));
                }
            }
        } else if(args[0].equals("-a")) {
            if (args.length < 2) {
                System.out.println("Unable to add: no task provided");
                System.exit(2);
            } else {
                try {
                    lists = Files.readAllLines(task);
                    lists.add("[ ] "+args[1]);
                    Files.write(task, lists);
                } catch (IOException e) {
                    System.out.println("Can't add");
                    System.exit(2);
                }
            }
        } else if(args[0].equals("-r")) {
            try {
                lists = Files.readAllLines(task);
                int removeTasks = Integer.parseInt(args[1]) - 1;
                lists.remove(removeTasks);
                Files.write(task, lists);
            } catch (IOException e) {
                System.out.println("Can't remove");
                System.exit(2);
            } catch (IndexOutOfBoundsException e) {
                if (args.length < 2) {
                    System.out.println("Unable to remove: index is not provided");
                    System.exit(2);
                } else {
                    System.out.println("Unable to remove: index is out of bound");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                System.out.println("Unable to remove: index is not a number");
            }
        } else if (args[0].equals("-c")) {
            try {
                lists = Files.readAllLines(task);
                int checkTasks = Integer.parseInt(args[1]) - 1;
                lists.set(checkTasks, "[x"+ lists.get(checkTasks).substring(2));
                Files.write(task, lists);
            } catch (IOException e) {
                System.out.println("Can't write");
                System.exit(2);
            } catch (IndexOutOfBoundsException e) {
                if (args.length < 2) {
                    System.out.println("Unable to check: index is not provided");
                    System.exit(2);
                } else {
                    System.out.println("Unable to check: index is out of bound");
                    System.exit(2);
                }
            } catch (NumberFormatException e) {
                System.out.println("Unable to check: index is not a number");
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
}

