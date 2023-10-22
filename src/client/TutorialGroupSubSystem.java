package client;

import adt.LinkedQueue;
import adt.QueueInterface;
import java.util.*;
import entity.TutorialGroup;
import entity.Student;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ng Jing Kang
 */
public class TutorialGroupSubSystem {

    // TODO code application logic here
    public static QueueInterface<TutorialGroup> TutorialGroupModule(QueueInterface<TutorialGroup> tutGrpListGroupIn, Student studListStudentIn) throws AWTException {
        String KeepGoing = "y";
        QueueInterface<TutorialGroup> tutGrpListGroup = tutGrpListGroupIn;
        Student studListStudent = studListStudentIn;
        Scanner scan = new Scanner(System.in);
        Iterator iterator = tutGrpListGroup.getIterator();
        Iterator<TutorialGroup> iterator2 = tutGrpListGroup.getIterator();
//        TutorialGroup currentGroup = new TutorialGroup();
        do {
            clearScreen();
            System.out.println("+================================+");
            System.out.println("| Tutorial Group Management Menu |");
            System.out.println("+================================+");
            System.out.println("| 1.Add Group                    |");
            System.out.println("| 2.List All Groups              |");
            System.out.println("| 3.Remove Group                 |");
            System.out.println("| 4.Count Total Groups           |");
            System.out.println("| 5.FILTER Groups                |");
            System.out.println("| 6.ADD Students To A Group      |");
            System.out.println("| 7.REMOVE Student From A Group  |");
            System.out.println("| 8.SEARCH Student In A Group    |");
            System.out.println("| 9.LIST Students In A Group     |");
            System.out.println("| 10.Show Total Active Students  |");
            System.out.println("| 11.CHANGE Group For A Student  |");
            System.out.println("| 12.SWAP Students               |");
            System.out.println("| 13.REPORTS                     |");
            System.out.println("| 14.Exit                        |");
            System.out.println("+================================+");
            System.out.println("Choice:");
            int choice = Integer.parseInt(scan.next());

            switch (choice) {

                case 1:
                    //add a group
                    do {
                        AddGroup(tutGrpListGroup);
                        System.out.println("\nAdd another group? (Y/N):");
                        KeepGoing = scan.next();
                    } while ((KeepGoing.equals("Y")) || (KeepGoing.equals("y")));
                    break;

                case 2:
                    //List all Groups
                    ListAllGroup(tutGrpListGroup, studListStudent);
                    System.out.println("\n Press B to Main Menu ...");
                    KeepGoing = scan.next();
                    break;
                case 3:
                    //Remove a group
                    tutGrpListGroup = RemoveGroup(tutGrpListGroup, studListStudent);
                    System.out.println("\nPress B to Main Menu ...");
                    KeepGoing = scan.next();
                    break;

                case 4:
                    //Get Total Groups
                    CountGroups(tutGrpListGroup, studListStudent);

                    System.out.println("\nPress B to Main Menu ...");
                    KeepGoing = scan.next();
                    break;

                case 5:
                    //Filter Groups
                    clearScreen();
                    System.out.println("+================================+");
                    System.out.println("|         FILTER GROUP           |");
                    System.out.println("+================================+");
                    System.out.println("| 1.Available Groups             |");
                    System.out.println("| 2.Full Groups                  |");
                    System.out.println("| 3.Back                         |");
                    System.out.println("+================================+");
                    System.out.println("Enter choice:");
                    int choice3 = Integer.parseInt(scan.next());

                    switch (choice3) {
                        case 1:
                            //list all available groups
                            KeepGoing = ListAvailableGroups(tutGrpListGroup, studListStudent);
                            break;
                        case 2:
                            //list all full groups
                            KeepGoing = ListFullGroups(tutGrpListGroup, studListStudent);
                            break;
                        case 3:

                            break;
                        default:
                            System.out.println("\u001B[31mWrong input! Please enter again...\u001B[0m");
                    }
                    break;
//
                case 6:
                    //Add student into group
                    KeepGoing = AddStudToGroup(tutGrpListGroup, studListStudent);

                    break;
                case 7:
                    //Remove a student from a group
                    KeepGoing = RemoveStudent(tutGrpListGroup, studListStudent);

                    break;
                case 8:
                    //Search a student
                    KeepGoing = SearchStudent(tutGrpListGroup, studListStudent);

                    break;
                case 9:
                    //List students in a group
                    clearScreen();
                    KeepGoing = ListStudentsInGroup(tutGrpListGroup, studListStudent);

                    break;
//
                case 10:
                    //Show Total Active Students In Groups
                    clearScreen();

                    KeepGoing = ListAllStudents(tutGrpListGroup, studListStudent);
                    break;
                case 11:
                    //Change group for a student
                    KeepGoing = ChangeStudentGroup(tutGrpListGroup, studListStudent);

                    break;
                case 12:
                    //Swap students
                    KeepGoing = SwapStudents(tutGrpListGroup, studListStudent);

                    break;
                case 13:
                    //Reports
                    clearScreen(); 
                    System.out.println("+================================+");
                    System.out.println("|            REPORTS             |");
                    System.out.println("+================================+");
                    System.out.println("| 1.Group Report                 |");
                    System.out.println("| 2.Students Report              |");
                    System.out.println("| 3.Back                         |");
                    System.out.println("+================================+");
                    System.out.println("Enter choice:");
                   
                    int choice4 = Integer.parseInt(scan.next());

                    switch (choice4) {
                        case 1:
                            //group report
                            KeepGoing = GenerateGroupReport(tutGrpListGroup, studListStudent);
                            break;
                        case 2:
                            //student report
                            KeepGoing = GenerateStudentReport(tutGrpListGroup, studListStudent);
                            break;
                        case 3:

                            break;
                        default:
                            System.out.println("\u001B[31mWrong input! Please enter again...\u001B[0m");
                    }

                    break;
                case 14:
                    KeepGoing = "Y";
                    break;
                default:
                    System.err.println("Wrong input! Please enter again...");

            }
        } while ((KeepGoing.equals("N")) || (KeepGoing.equals("n")) || (KeepGoing.equals("b")) || (KeepGoing.equals("B")));
        return tutGrpListGroup;
    }

    public static void clearScreen() throws AWTException {
        Robot rob = new Robot();
        try {
            rob.keyPress(KeyEvent.VK_CONTROL); // press "CTRL"
            rob.keyPress(KeyEvent.VK_L); // press "L"
            rob.keyRelease(KeyEvent.VK_L); // unpress "L"
            rob.keyRelease(KeyEvent.VK_CONTROL); // unpress "CTRL"
            Thread.sleep(100); // add delay in milisecond, if not there will automatically stop after clear
        } catch (InterruptedException e) {
        }
    }

    public static void AddGroup(QueueInterface<TutorialGroup> tutGrpListGroup) throws AWTException {

        Scanner scan = new Scanner(System.in);

//        TutorialGroup newGroup = new TutorialGroup();
        clearScreen();
        System.out.println("=============================");
        System.out.println("|        ADDING GROUP       |");
        System.out.println("=============================");
        System.out.println("Enter Group ID:");
        String ID = scan.next();
//        newGroup.setGrpID(ID);
        System.out.println("=============================");
        System.out.println("Enter Capacity:");
        int Capacity = Integer.parseInt(scan.next());
//        newGroup.setCapacity(Capacity);
        System.out.println("=============================");
        TutorialGroup newGroup = new TutorialGroup(ID, Capacity);
        tutGrpListGroup.enqueue(newGroup);

        System.out.println("\u001B[32mGroup " + ID + " with " + Capacity + " max capacity has been ADDED!\u001B[0m");

    }

    public static String SearchGroup(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup;
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";
        if (tutGrpListGroup.isEmpty()) {

            System.out.println("No Group Found!");
        } else {
            clearScreen();
            System.out.println("\nSEARCHING");
            System.out.println("=============================");
            System.out.println("Group_ID" + "\t" + "Capacity");

            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                System.out.println(currentGroup.getGrpID() + "\t\t" + count + "/" + currentGroup.getCapacity());

            }
            System.out.println("=============================");

            System.out.println("Enter Group_ID to search:");
            String GrpToSearch = scan.next();

            Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();
            while (GroupIterator2.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator2.next();
                if (currentGroup.getGrpID().equals(GrpToSearch)) {
                    Iterator iterator3 = studListStudent.getIterator();
                    clearScreen();
                    System.out.println("\n" + currentGroup.getGrpID());
                    System.out.println("=============================");

                    while (iterator3.hasNext()) {
                        Student currentstudent = new Student();
                        currentstudent = (Student) iterator3.next();
                        if (currentstudent.getGrpID().equals(GrpToSearch)) {
                            System.out.println(currentstudent.getID() + "\t" + currentstudent.getName());
                            count++;
                        }
                    }
                    System.out.println("=============================");

                    System.out.println(count + " - \u001B[32mActive Member(s)\u001B[0m");

                    System.out.println("\nPress B to Main Menu ...");
                    KeepGoing = scan.next();
                }

            }
        }
        return KeepGoing;
    }

    public static void ListAllGroup(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
//            TutorialGroup currentGroup = new TutorialGroup();

            clearScreen();
            System.out.println(" =============================");
            System.out.println(" |      GROUPS LISTING       |");
            System.out.println(" =============================");
            System.out.println(" |    Groups      Capacity   |");
            System.out.println(" |---------------------------|");
            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                TutorialGroup currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                System.out.println(" |    " + currentGroup.getGrpID() + "         " + count + "/" + currentGroup.getCapacity() + "     |");

            }
            System.out.println(" =============================");

        }

    }

    public static void ListAllTutorialGroupOnly(QueueInterface<TutorialGroup> tutGrpListGroup) {
        int count = 0;

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            System.out.println("Group_ID\n");

            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {

                TutorialGroup currentGroup = GroupIterator.next();
                count++;

                System.out.println(count + ")" + currentGroup.getGrpID());

            }

        }

    }

    public static QueueInterface<TutorialGroup> RemoveGroup(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup;

        if (tutGrpListGroup.isEmpty()) {

            System.out.println("No Group Found!");
        } else {
            clearScreen();
            System.out.println(" =============================");
            System.out.println(" |       DELETING GROUP      |");
            System.out.println(" =============================");
            System.out.println(" |    Groups      Capacity   |");
            System.out.println(" |---------------------------|");
            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                System.out.println(" |    " + currentGroup.getGrpID() + "         " + count + "/" + currentGroup.getCapacity() + "     |");

            }
            System.out.println(" =============================");
        }

        QueueInterface<TutorialGroup> tempGroup_remove = new LinkedQueue<>();
        System.out.println(" Enter Group_ID to be DELETE:");
        Scanner scanDelete = new Scanner(System.in);
        String IDtoDelete = scanDelete.next();
        QueueInterface<TutorialGroup> WantedGroup = new LinkedQueue<>();
        String RemovedID = "";
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        while (GroupIterator2.hasNext()) {

            currentGroup = GroupIterator2.next();
            if (!currentGroup.getGrpID().equals(IDtoDelete)) {
                WantedGroup.enqueue(currentGroup);
            } else {
                tempGroup_remove.enqueue(currentGroup);
                RemovedID = currentGroup.getGrpID();
            }
        }
        if (tempGroup_remove.isEmpty()) {
            tutGrpListGroup = WantedGroup;
            clearScreen();
            System.out.println("\u001B[31mID entered is NOT exist!\u001B[0m");

        } else {
            tutGrpListGroup = WantedGroup;
            clearScreen();
            System.out.println("\u001B[31m" + RemovedID + " is deleted!\u001B[0m");

        }
        return tutGrpListGroup;
    }

    public static void CountGroups(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        clearScreen();
        System.out.println("\n         TOTAL GROUP");
        System.out.println("==============================");
        System.out.println("| \u001B[31m " + tutGrpListGroup.CountEntries() + "\u001B[0m Tutorial Groups in List |");
        System.out.println("==============================");
    }

    public static String AddStudToGroup(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";
        clearScreen();

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {

            System.out.println(" =============================");
            System.out.println(" |  ADDING STUDENTS TO GROUP |");
            System.out.println(" =============================");

            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                System.out.println(" |    " + currentGroup.getGrpID() + "         " + count + "/" + currentGroup.getCapacity() + "     |");

            }

            System.out.println("=============================");
            int GrpCheck = 0;
            String GrpToAsign;
            do {

                System.out.println(" Enter Group_ID to ADD:");
                System.out.print(" ");

                GrpToAsign = scan.next();
                Iterator<TutorialGroup> GroupIterator3 = tutGrpListGroup.getIterator();

                while (GroupIterator3.hasNext()) {

                    currentGroup = GroupIterator3.next();
                    if (currentGroup.getGrpID().toLowerCase().equals(GrpToAsign.toLowerCase())) {
                        GrpCheck++;
                    }

                }
                System.out.println(" =============================");

                if (GrpCheck == 0) {
                    System.out.println(" \u001B[31mWrong input! Please enter again...\u001B[0m");

                }
            } while (GrpCheck == 0);

            System.out.println("\n Enter Student_ID to ADD:");
            System.out.print(" ");
            String StudToAsign = scan.next();

            Iterator iterator3 = studListStudent.getIterator();
            int exist = 0;

            while (iterator3.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) iterator3.next();
                if (currentstudent.getID().equals(StudToAsign)) {
                    if (currentstudent.getGrpID().equals("")) {
                        currentstudent.setGrpID(GrpToAsign);
                        System.out.println("\n\u001B[32mStudent(" + StudToAsign + ") has been assigned to Group(" + GrpToAsign + "\u001B[0m)");
                        System.out.println("\nPress B to Main Menu ...");
                        KeepGoing = scan.next();

                        exist++;
                    } else {
                        System.out.println("\n \u001B[31mStudent(" + StudToAsign + ") already in another Group!!!\u001B[0m");
                        System.out.println("\nPress B to Main Menu ...");
                        KeepGoing = scan.next();

                        exist++;
                    }
                    exist++;
                }
            }
            if (exist == 0) {
                System.err.println(" Student(" + StudToAsign + ") does not exist!!");
                System.out.println("\nPress B to Main Menu ...");
                KeepGoing = scan.next();
            }

        }
        return KeepGoing;
    }

    public static String RemoveStudent(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";
        clearScreen();

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            System.out.println(" ========================================");
            System.out.println(" |           GROUPS LISTING            |");
            System.out.println(" =======================================");

            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();
                System.out.println(" --------------------------------------------------");

                System.out.println(" | " + currentGroup.getGrpID()+ "                                          |");
                System.out.println(" --------------------------------------------------");
                System.out.println(" | Student_ID:      Name:                         |");
                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        System.out.printf(" | %s          %-30s|\n",currentstudent.getID(),currentstudent.getName());
                        count++;
                    }
                }
                System.out.println(" --------------------------------------------------");
                System.out.println(" " + count + " - \u001B[32mActive Member(s)\u001B[0m\n");

            }
                System.out.println(" ==================================================");
            int exist = 0;
            do {

                System.out.println("\n Enter Student_ID to be REMOVE:");
                System.out.print(" ");
                String StudToRemove = scan.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    String currentGrpID = currentstudent.getGrpID();
                    if (currentstudent.getID().equals(StudToRemove)) {
                        currentstudent.setGrpID("");
                        System.out.println(" \u001B[32mStudent - " + currentstudent.getName() + "(" + StudToRemove + ") has been removed from " + currentGrpID+"\u001B[0m");
                        System.out.println("\nPress B to Main Menu ...");
                        KeepGoing = scan.next();
                        exist++;
                    }
                }
                if (exist == 0) {

                    System.out.println("\n\u001B[31mStudent(" + StudToRemove + ") does not exist!!\u001B[0m");
                    System.out.println("\u001B[31mPlease Enter again...\u001B[0m");

                }
            } while (exist == 0);
        }
        return KeepGoing;
    }

    public static String SearchStudent(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup;
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        clearScreen();

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
           System.out.println(" =============================");
            System.out.println(" |      GROUPS LISTING       |");
            System.out.println(" =============================");
            System.out.println(" |    Groups      Capacity   |");
            System.out.println(" |---------------------------|");
            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                 currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                System.out.println(" |    " + currentGroup.getGrpID() + "         " + count + "/" + currentGroup.getCapacity() + "     |");

            }
            System.out.println(" =============================");

            int GrpCheck = 0;
            String GrpToSearch;
            do {
                System.out.println("\nEnter Group_ID to search:");
                GrpToSearch = scan.next();
                Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

                GroupIterator2 = tutGrpListGroup.getIterator();
                while (GroupIterator2.hasNext()) {
                    int count = 0;
                    currentGroup = GroupIterator2.next();
                    if (currentGroup.getGrpID().equals(GrpToSearch)) {
                        Iterator iterator3 = studListStudent.getIterator();
                        clearScreen();
                        System.out.println("\nGROUP: " + currentGroup.getGrpID());
                        System.out.println("===================================");
                        System.out.println("Student_ID \tName");

                        GrpCheck++;
                        while (iterator3.hasNext()) {
                            Student currentstudent = new Student();
                            currentstudent = (Student) iterator3.next();
                            if (currentstudent.getGrpID().equals(GrpToSearch)) {
                                System.out.println(currentstudent.getID() + "\t\t" + currentstudent.getName());
                                count++;
                            }
                        }
                        System.out.println("===================================");

                        System.out.println(count + " - \u001B[32mActive Member(s)\u001B[0m");

                    }

                }
                if (GrpCheck == 0) {
                    System.out.println("\n\u001B[31mGroup(" + GrpToSearch + ") does not exist!!\u001B[0m");
                    System.out.println("\u001B[31mPlease Enter again...\u001B[0m");
                }
            } while (GrpCheck == 0);

            System.out.println("\nEnter student name to Search:");
            scan.nextLine();
            String StudToSearch = scan.nextLine();

            int exist = 0;
            Iterator iterator4 = studListStudent.getIterator();
            while (iterator4.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) iterator4.next();
                if (currentstudent.getName().toLowerCase().equals(StudToSearch.toLowerCase())) {
                    clearScreen();
                    System.out.println("\nStudent Details");
                    System.out.println("=======================================");
                    System.out.println("Name            : " + currentstudent.getName());
                    System.out.println("ID              : " + currentstudent.getID());
                    System.out.println("Email           : " + currentstudent.getEmail());
                    System.out.println("Phone No        : " + currentstudent.getPhNo());
                    System.out.println("CGPA            : " + currentstudent.getCGPA());
                    System.out.println("Current Group   : " + currentstudent.getGrpID());
                    System.out.println("=======================================");
                    System.out.println("\nPress B to Main Menu ...");
                    KeepGoing = scan.next();
                    exist++;
                }
            }
            if (exist == 0) {
                System.out.println("\n\u001B[31mStudent(" + StudToSearch + ") are not in Group(" + GrpToSearch + ")\u001B[0m");
                System.out.println("\nPress B to Main Menu ...");
                KeepGoing = scan.next();
            }
        }
        return KeepGoing;
    }

    public static String ListStudentsInGroup(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            System.out.println(" =============================");
            System.out.println(" |      GROUPS LISTING       |");
            System.out.println(" =============================");
            System.out.println(" |    Groups      Capacity   |");
            System.out.println(" |---------------------------|");
            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                System.out.println(" |    " + currentGroup.getGrpID() + "         " + count + "/" + currentGroup.getCapacity() + "     |");

            }
            System.out.println(" =============================");


            int GrpCheck = 0;
            String GrpToSearch;
            do {
                System.out.println("\nEnter Group_ID to search:");
                GrpToSearch = scan.next();
                Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

                GroupIterator2 = tutGrpListGroup.getIterator();
                while (GroupIterator2.hasNext()) {
                    int count = 0;
                    currentGroup = GroupIterator2.next();
                    if (currentGroup.getGrpID().equals(GrpToSearch)) {
                        Iterator iterator3 = studListStudent.getIterator();
                        clearScreen();
                        System.out.println("\nGROUP " + currentGroup.getGrpID());
                        System.out.println("===================================");
                        System.out.println("Student_ID \tName");

                        GrpCheck++;
                        while (iterator3.hasNext()) {
                            Student currentstudent = new Student();
                            currentstudent = (Student) iterator3.next();
                            if (currentstudent.getGrpID().equals(GrpToSearch)) {
                                System.out.println(currentstudent.getID() + "\t\t" + currentstudent.getName());
                                count++;
                            }
                        }
                        System.out.println("===================================");

                        System.out.println(count + " - \u001B[32mActive Member(s)\u001B[0m");

                    }

                }
                if (GrpCheck == 0) {
                    System.out.println("\n\u001B[31mGroup(" + GrpToSearch + ") does not exist!!\u001B[0m");
                    System.out.println("\u001B[31mPlease Enter again...\u001B[0m");
                }
            } while (GrpCheck == 0);

            System.out.println("\nPress B to Main Menu ...");
            KeepGoing = scan.next();
        }
        return KeepGoing;
    }
//

    public static String ListAllStudents(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
        while (GroupIterator.hasNext()) {
            int count = 0;
            currentGroup = GroupIterator.next();

            Iterator iterator3 = studListStudent.getIterator();
            System.out.println("\nGROUP: " + currentGroup.getGrpID());
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("Student_ID  Group       Email                           Phone_No        CGPA    Name");
            while (iterator3.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) iterator3.next();
                if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                    System.out.println(currentstudent);
                    count++;
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------");

            System.out.println(count + " - \u001B[32mActive Member(s)\u001B[0m");

        }

        System.out.println("\nPress B to Main Menu ...");
        KeepGoing = scan.next();
        return KeepGoing;
    }

    public static String ChangeStudentGroup(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        clearScreen();

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            System.out.println("GROUPS");
            System.out.println("===================================");
            while (GroupIterator2.hasNext()) {
               int count = 0;
                currentGroup = GroupIterator2.next();

                Iterator iterator3 = studListStudent.getIterator();
                System.out.println(" --------------------------------------------------");

                System.out.println(" | " + currentGroup.getGrpID()+ "                                          |");
                System.out.println(" --------------------------------------------------");
                System.out.println(" | Student_ID:      Name:                         |");
                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        System.out.printf(" | %s          %-30s|\n",currentstudent.getID(),currentstudent.getName());
                        count++;
                    }
                }
                System.out.println(" --------------------------------------------------");
                System.out.println(" " + count + " - \u001B[32mActive Member(s)\u001B[0m\n");

            }
            System.out.println("\nEnter student name to Change:");
            String StudToChange = scan.nextLine();
            Iterator StudIterator = studListStudent.getIterator();
            int exist = 0;

            Iterator StudentIterator = studListStudent.getIterator();
            while (StudentIterator.hasNext()) {
                Student currentstudent2 = new Student();
                currentstudent2 = (Student) StudIterator.next();
                if (currentstudent2.getName().toLowerCase().equals(StudToChange.toLowerCase())) {
                    clearScreen();
                    System.out.println("\nStudent Details");
                    System.out.println("=======================================");
                    System.out.println("Name            : " + currentstudent2.getName());
                    System.out.println("ID              : " + currentstudent2.getID());
                    System.out.println("Current Group   : " + currentstudent2.getGrpID());
                    System.out.println("=======================================");

                    System.out.println("\n\nAVAILABLE GROUP");
                    System.out.println("=======================================");
                    System.out.println("Group_ID" + "\t" + "Capacity");

                    Iterator<TutorialGroup> GroupIterator3 = tutGrpListGroup.getIterator();
                    while (GroupIterator3.hasNext()) {
                        int count = 0;
                        currentGroup = GroupIterator3.next();

                        Iterator iterator3 = studListStudent.getIterator();

                        while (iterator3.hasNext()) {
                            Student currentstudent = new Student();
                            currentstudent = (Student) iterator3.next();
                            if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                                count++;
                            }
                        }
                        System.out.println(currentGroup.getGrpID() + "\t\t" + count + "/" + currentGroup.getCapacity());

                    }
                    System.out.println("=======================================");

                    System.out.println("\nEnter Group_ID to Change:");
                    String GroupToChange = scan.nextLine();

                    currentstudent2.setGrpID(GroupToChange);
                    clearScreen();
                    System.out.println("\n\u001B[32mSuccessfully Changed\u001B[0m");
                    System.out.println("Student Details");
                    System.out.println("=======================================");
                    System.out.println("Name            : " + currentstudent2.getName());
                    System.out.println("ID              : " + currentstudent2.getID());
                    System.out.println("Current Group   : " + currentstudent2.getGrpID());
                    System.out.println("=======================================");

                    System.out.println("\n\nUPDATED GROUP");
                    System.out.println("=======================================");
                    System.out.println("Group_ID" + "\t" + "Capacity");
                    Iterator<TutorialGroup> GroupIterator4 = tutGrpListGroup.getIterator();
                    while (GroupIterator4.hasNext()) {
                        int count = 0;
                        currentGroup = GroupIterator4.next();

                        Iterator iterator3 = studListStudent.getIterator();

                        while (iterator3.hasNext()) {
                            Student currentstudent = new Student();
                            currentstudent = (Student) iterator3.next();
                            if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                                count++;
                            }
                        }
                        System.out.println(currentGroup.getGrpID() + "\t\t" + count + "/" + currentGroup.getCapacity());

                    }
                    System.out.println("=======================================");
                    System.out.println("\nPress B to Main Menu ...");
                    KeepGoing = scan.next();
                    break;
                }

            }

        }
        return KeepGoing;
    }

    public static String SwapStudents(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";
        clearScreen();

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            Iterator<TutorialGroup> GroupIterator3 = tutGrpListGroup.getIterator();
            System.out.println("GROUPS");
            System.out.println("===================================");
            while (GroupIterator3.hasNext()) {
                 int count = 0;
                currentGroup = GroupIterator3.next();

                Iterator iterator3 = studListStudent.getIterator();
                System.out.println(" --------------------------------------------------");

                System.out.println(" | " + currentGroup.getGrpID()+ "                                          |");
                System.out.println(" --------------------------------------------------");
                System.out.println(" | Student_ID:      Name:                         |");
                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        System.out.printf(" | %s          %-30s|\n",currentstudent.getID(),currentstudent.getName());
                        count++;
                    }
                }
                System.out.println(" --------------------------------------------------");
                System.out.println(" " + count + " - \u001B[32mActive Member(s)\u001B[0m\n");

            }
            System.out.println("\nEnter First Student Name to Change:");
            String StudToSwap1 = scan.nextLine();

            System.out.println("\nEnter Second Student Name to Change:");
            String StudToSwap2 = scan.nextLine();
            int exist = 0;

            Iterator StudentIterator2 = studListStudent.getIterator();
            int i = 1;
            int j = 1;
            String GrpToSwap1 = "";
            String GrpToSwap2 = "";
            clearScreen();
            System.out.println("\n           Students Details          ");
            System.out.println("=======================================");
            while (StudentIterator2.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) StudentIterator2.next();

                if (currentstudent.getName().toLowerCase().equals(StudToSwap1.toLowerCase())) {
                    GrpToSwap1 = currentGroup.getGrpID();

                } else if (currentstudent.getName().toLowerCase().equals(StudToSwap2.toLowerCase())) {
                    GrpToSwap2 = currentstudent.getGrpID();
                }
            }

            Iterator StudentIterator3 = studListStudent.getIterator();
            while (StudentIterator3.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) StudentIterator3.next();
                if (currentstudent.getName().toLowerCase().equals(StudToSwap1.toLowerCase())) {

                    System.out.println("Students " + i);
                    System.out.println("Name            : " + currentstudent.getName());
                    System.out.println("ID              : " + currentstudent.getID());
                    System.out.println("Current Group   : " + currentstudent.getGrpID());
                    i++;
                    GrpToSwap1 = currentGroup.getGrpID();
                    currentstudent.setGrpID(GrpToSwap2);

                } else if (currentstudent.getName().toLowerCase().equals(StudToSwap2.toLowerCase())) {
                    System.out.println("\nStudents " + i);
                    System.out.println("Name            : " + currentstudent.getName());
                    System.out.println("ID              : " + currentstudent.getID());
                    System.out.println("Current Group   : " + currentstudent.getGrpID());
                    i++;
                    GrpToSwap2 = currentstudent.getGrpID();
                    currentstudent.setGrpID(GrpToSwap1);

                }

            }
            System.out.println("=======================================");

            Iterator StudentIterator4 = studListStudent.getIterator();
            System.out.println("\n           \u001B[32mSuccessfully Swapped\u001B[0m");
            System.out.println("=======================================");
            while (StudentIterator4.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) StudentIterator4.next();

                if (currentstudent.getName().toLowerCase().equals(StudToSwap1.toLowerCase())) {

                    System.out.println("Students " + j);
                    System.out.println("Name            : " + currentstudent.getName());
                    System.out.println("ID              : " + currentstudent.getID());
                    System.out.println("Current Group   : " + currentstudent.getGrpID());
                    j++;

                } else if (currentstudent.getName().toLowerCase().equals(StudToSwap2.toLowerCase())) {
                    System.out.println("\nStudents " + j);
                    System.out.println("Name            : " + currentstudent.getName());
                    System.out.println("ID              : " + currentstudent.getID());
                    System.out.println("Current Group   : " + currentstudent.getGrpID());
                    j++;

                }

            }
            System.out.println("=======================================");
            System.out.println("\nPress B to Main Menu ...");
            KeepGoing = scan.next();
        }
        return KeepGoing;
    }

    public static String GenerateGroupReport(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";
        clearScreen();

        int totalG,
                AvaG = 0,
                FullG = 0,
                totalStud,
                AsnStud = 0,
                OHStud = 0;
        totalG = tutGrpListGroup.CountEntries();
        Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();

        GroupIterator = tutGrpListGroup.getIterator();
        while (GroupIterator.hasNext()) {
            int count = 0;
            currentGroup = GroupIterator.next();

            Iterator iterator3 = studListStudent.getIterator();

            while (iterator3.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) iterator3.next();
                if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                    count++;
                }
            }
            if (count < currentGroup.getCapacity()) {
                AvaG++;
            }
            if (count >= currentGroup.getCapacity()) {
                FullG++;
            }
        }
        totalStud = studListStudent.CountEntries();

        GroupIterator = tutGrpListGroup.getIterator();

        Iterator StudIterator = studListStudent.getIterator();
        while (StudIterator.hasNext()) {
            Student currentStud = new Student();
            currentStud = (Student) StudIterator.next();

            if (!currentStud.getGrpID().equals("")) {
                AsnStud++;
            } else if (currentStud.getGrpID().equals("")) {
                OHStud++;
            }

        }
        System.out.println("                           GROUP REPORT                             ");
        System.out.println("+==================================================================+");
        System.out.println("| Total Groups    : " + totalG + "                                              |");
        System.out.println("|==================================================================|");
        System.out.println("| Available Group : " + AvaG + "                                              |");
        ListAvailableGroups_Void(tutGrpListGroup, studListStudent);
        System.out.println("| Full Group      : " + FullG + "                                              |");
        ListFullGroups_Void(tutGrpListGroup, studListStudent);
        System.out.println("+==================================================================+");

        System.out.println("\nPress B to Main Menu ...");
        KeepGoing = scan.next();
        return KeepGoing;
    }

    public static String GenerateStudentReport(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";
        clearScreen();

        int totalG,
                AvaG = 0,
                FullG = 0,
                totalStud,
                AsnStud = 0,
                OHStud = 0;
        totalG = tutGrpListGroup.CountEntries();
        Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();

        GroupIterator = tutGrpListGroup.getIterator();
        while (GroupIterator.hasNext()) {
            int count = 0;
            currentGroup = GroupIterator.next();

            Iterator iterator3 = studListStudent.getIterator();

            while (iterator3.hasNext()) {
                Student currentstudent = new Student();
                currentstudent = (Student) iterator3.next();
                if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                    count++;
                }
            }
            if (count < currentGroup.getCapacity()) {
                AvaG++;
            }
            if (count >= currentGroup.getCapacity()) {
                FullG++;
            }
        }
        totalStud = studListStudent.CountEntries();

        GroupIterator = tutGrpListGroup.getIterator();

        Iterator StudIterator = studListStudent.getIterator();
        while (StudIterator.hasNext()) {
            Student currentStud = new Student();
            currentStud = (Student) StudIterator.next();

            if (!currentStud.getGrpID().equals("")) {
                AsnStud++;
            } else if (currentStud.getGrpID().equals("")) {
                OHStud++;
            }

        }
        System.out.println("                                           STUDENT REPORT                                                ");
        System.out.println("+===========================================================================================================+");
        System.out.println("| Total Student     : " + totalStud + "                                                                                    |");
        System.out.println("|===========================================================================================================|");
        System.out.println("| Assigned Students : " + AsnStud + "                                                                                    |");
        ListAssignedStudentDetails(tutGrpListGroup, studListStudent);
        System.out.println("+===========================================================================================================+");

        System.out.println("| On-hold Students  : " + OHStud + "                                                                                     |");
        ListOnHoldtudentDetails(tutGrpListGroup, studListStudent);
        System.out.println("+===========================================================================================================+");

        System.out.println("\nPress B to Main Menu ...");
        KeepGoing = scan.next();
        return KeepGoing;
    }

    public static String ListAvailableGroups(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            clearScreen();
            System.out.println(" =============================");
            System.out.println(" |     AVAILABLE GROUPS      |");
            System.out.println(" =============================");
            System.out.println(" |    Groups      Capacity   |");
            System.out.println(" |---------------------------|");

            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                if (count < currentGroup.getCapacity()) {
                    System.out.println(" |    " + currentGroup.getGrpID() + "         " + count + "/" + currentGroup.getCapacity() + "     |");

                }

            }
            System.out.println(" =============================");

            System.out.println("\nPress B to Main Menu ...");
            KeepGoing = scan.next();
        }
        return KeepGoing;
    }

    public static void ListAvailableGroups_Void(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|          Group_ID            |            Capacity               |");
            System.out.println("|------------------------------------------------------------------|");
            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                if (count < currentGroup.getCapacity()) {
                    System.out.println("|           " + currentGroup.getGrpID() + "              |              " + count + "/" + currentGroup.getCapacity() + "                 |");

                }

            }
            System.out.println("+==================================================================+");

        }
    }

    public static String ListFullGroups(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            clearScreen();
            int full = 0;
            System.out.println(" =============================");
            System.out.println(" |        FULL GROUPS        |");
            System.out.println(" =============================");
            System.out.println(" |    Groups      Capacity   |");
            System.out.println(" |---------------------------|");

            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                if (count >= currentGroup.getCapacity()) {
                    System.out.println(" |    " + currentGroup.getGrpID() + "           " + count + "/" + currentGroup.getCapacity() + "       |");
                    full = 1;
                }

            }

            System.out.println(" =============================");
            if (full == 0) {
                System.out.println(" \u001B[31mThere are no FULL GROUP!\u001B[0m");
            }
            System.out.println("\n Press B to Main Menu ...");
            KeepGoing = scan.next();
        }
        return KeepGoing;
    }

    public static void ListFullGroups_Void(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator<TutorialGroup> GroupIterator2 = tutGrpListGroup.getIterator();

        GroupIterator2 = tutGrpListGroup.getIterator();
        TutorialGroup currentGroup = new TutorialGroup();
        Scanner scan = new Scanner(System.in);
        String KeepGoing = "";

        if (tutGrpListGroup.isEmpty()) {
            System.out.println("No Group Found!");
        } else {
            int full = 0;
            System.out.println("|------------------------------------------------------------------|");
            System.out.println("|          Group_ID            |            Capacity               |");
            System.out.println("|------------------------------------------------------------------|");

            Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
            while (GroupIterator.hasNext()) {
                int count = 0;
                currentGroup = GroupIterator.next();

                Iterator iterator3 = studListStudent.getIterator();

                while (iterator3.hasNext()) {
                    Student currentstudent = new Student();
                    currentstudent = (Student) iterator3.next();
                    if (currentstudent.getGrpID().equals(currentGroup.getGrpID())) {
                        count++;
                    }
                }
                if (count >= currentGroup.getCapacity()) {
                    System.out.println(currentGroup.getGrpID() + "\t\t" + count + "/" + currentGroup.getCapacity() + "\t\u001B[31m" + "MAX" + "\u001B[0m");
                    full = 1;
                }

            }

            if (full == 0) {
                System.out.println("|                   \u001B[31mThere are no FULL GROUP\u001B[0m                        |");
            }

        }
    }

    public static void ListAssignedStudentDetails(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator StudIterator = studListStudent.getIterator();
        System.out.println("|-----------------------------------------------------------------------------------------------------------|");

        System.out.println("| Student ID | Group |              Name              |              Email             |   Phone No  | CGPA |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------|");

        while (StudIterator.hasNext()) {
            Student currentStud = new Student();
            currentStud = (Student) StudIterator.next();

            if (!currentStud.getGrpID().equals("")) {

                System.out.printf("|  %s   | \u001B[32m%s\u001B[0m | %-30s | %-30s | %s | %.2f |\n", currentStud.getID(), currentStud.getGrpID(), currentStud.getName(), currentStud.getEmail(), currentStud.getPhNo(), currentStud.getCGPA());

            }

        }

    }

    public static void ListOnHoldtudentDetails(QueueInterface<TutorialGroup> tutGrpListGroup, Student studListStudent) throws AWTException {
        Iterator StudIterator = studListStudent.getIterator();
        System.out.println("|-----------------------------------------------------------------------------------------------------------|");

        System.out.println("| Student ID | Group |              Name              |              Email             |   Phone No  | CGPA |");
        System.out.println("|-----------------------------------------------------------------------------------------------------------|");

        while (StudIterator.hasNext()) {
            Student currentStud = new Student();
            currentStud = (Student) StudIterator.next();

            if (currentStud.getGrpID().equals("")) {
                System.out.printf("|  %s   |   -   | %-30s | %-30s | %s | %.2f |\n", currentStud.getID(), currentStud.getName(), currentStud.getEmail(), currentStud.getPhNo(), currentStud.getCGPA());
            }

        }

    }
}
