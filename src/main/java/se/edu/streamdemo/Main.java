package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

//        System.out.println("Printing all data ...");
//        printAllData(tasksData);
//        printDataWithStreams(tasksData);


        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStreams(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));

//        System.out.println("Printing deadlines ...");
//        printDeadlines(tasksData);
//        printDeadlinesUsingStreams(tasksData);
        System.out.println("number of deadlines using iteration: " + countDeadlines(tasksData));
        System.out.println("number of deadlines using streams: " + countDeadlinesUsingStreams(tasksData));

        ArrayList<Task> filteredList = filterTasksByString(tasksData, "11");
        System.out.println(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("print with iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasksData) {
        System.out.println("print with streams");
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("print deadlines with iterations");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }


    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("print deadlines using streams sorted: ");
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasksData, String filterString) {
        return (ArrayList<Task>) tasksData.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(toList());
    }

    public static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        return (int) tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();
    }

}
