package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        /* __Task Manager  Console Program__

Task
(id(serial),title,content(text),status(varchar),created_date(timestamp),finished_date(timestamp))

status - TaskStatus - Enum
ACTIVE,DONE

***** Menu *****
1-Create
    Enter title:
    Enter content:
2-Active Task List
3-Finished Task List
4-Update (by id)
    Enter Task Id:
    Enter Title:
    Enter Content:
5-Delete by id:
    Enter Delete Task Id:
6-Mark as Done:
    Enter Task Id To Mark it as Done:
*/

        ScannerUtil scannerUtil = new ScannerUtil();
        ConnectionToDatabase connection = new ConnectionToDatabase();



        do {
            System.out.println("""
                    1-Create Task
                    2-Active Task List
                    3-Finished Task List
                    4-Update (by id)
                    5-Delete (by id)
                    6-Mark as Done
                    0.Exit
                    """);
            String option = scannerUtil.nextLine(":?");
            if (option.equals("0")) {
                break;
            }
            switch (option){
                case "1"->{
                    String title = scannerUtil.nextLine("enter title:");
                    String content = scannerUtil.nextLine("enter  content:");

                    boolean result=connection.createTask(title,content);
                    if (result) {
                        System.out.println("Task created👌👌👌");
                    }else {
                        System.out.println("There was an error adding!!!");
                    }
                }
                case "2"->{
                    List<Task> allActivTaskList=connection.getAllActivTask(TasKStatus.ACTIVE.toString());
                    if (allActivTaskList.isEmpty()) {
                        System.out.println("Task not found!!!");
                    }
                    allActivTaskList.forEach(System.out::println);
                }
                case "3"->{
                    List<Task> allActivTaskList=connection.getAllActivTask(TasKStatus.DONE.toString());
                    if (allActivTaskList.isEmpty()) {
                        System.out.println("Task not found!!!");
                    }
                    allActivTaskList.forEach(System.out::println);
                }
                case "4"->{
                    int id = scannerUtil.nextInt("enter id:");
                    String updateTitle = scannerUtil.nextLine("Enter update title:");
                    String updateContent = scannerUtil.nextLine("Enter update content:");

                    boolean rerult=connection.updateTask(id,updateTitle,updateContent);
                    if (rerult) {
                        System.out.println("Uptade succesfull👌👌👌");
                    }else {
                        System.out.println("Error!!!");
                    }

                }
                case "5"->{
                    int id = scannerUtil.nextInt("Enter id:");
                   boolean result= connection.delete(id);
                    if (result) {
                        System.out.println("Delete succesfull👌👌👌");
                    }else {
                        System.out.println("Error!!!");
                    }
                }
                case "6"->{
                    int id = scannerUtil.nextInt("Enter id:");
                    boolean result=connection.markAsDone(id);

                    if (result) {
                        System.out.println("Operation succesfull👌👌👌");
                    }else {
                        System.out.println("Error!!!");
                    }
                }

            }


        }while (true);

    }


}