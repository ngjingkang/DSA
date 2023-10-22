package client;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Course;
import entity.Programme;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import java.util.Scanner;
import Utility.TempDatabase;
import utility.Validation;

/**
 *
 * @author Lee Jun Wen
 */
public class CourseSubSystem {

    public static QueueInterface<Course> courseModule(QueueInterface<Course> courseMasterListIn,ListInterface<Programme> progMasterListIn) {
        QueueInterface<Course> courseMasterList = courseMasterListIn;
        ListInterface<Programme> progMasterList = progMasterListIn;
        
        int choice, option;
        do {
            choice = getSubMenuChoice();

            switch (choice) {
                case 1:
                    System.out.println("\n+===========================+");
                    System.out.println("|  TASK: Create New Course  |");
                    System.out.println("+===========================+\n");
                    do {
                        addCourse(courseMasterList);
                        option = getAddMenu();
                    } while (option == 1);
                    break;

                case 2:
                    System.out.println("\n+=========================+");
                    System.out.println("|  TASK: Remove A Course  |");
                    System.out.println("+=========================+\n");
                    do {
                        deleteCourse(courseMasterList, progMasterList);
                        option = getDeleteMenu();
                    } while (option == 1);
                    break;

                case 3:
                    System.out.println("\n+=======================+");
                    System.out.println("|  TASK: Find A Course  |");
                    System.out.println("+=======================+\n");
                    do {
                        searchCourse(courseMasterList, progMasterList);
                        option = getSearchMenu();
                    } while (option == 1);

                    break;

                case 4:
                    System.out.println("\n+================================+");
                    System.out.println("|  TASK: Amend A Course Details  |");
                    System.out.println("+================================+\n");
                    do {
                        modifyCourse(courseMasterList, progMasterList);
                        option = getModifyMenu();
                    } while (option == 1);
                    break;

                case 5:
                    System.out.println("\n+=========================+");
                    System.out.println("|  TASK: List All Course  |");
                    System.out.println("+=========================+");
                    displayCourse(courseMasterList, progMasterList);
                    getDisplayMenu();
                    break;

                case 6:
                    System.out.println("\n+==================================+");
                    System.out.println("|  TASK: Add Programme To A Course  |");
                    System.out.println("+===================================+\n");
                    do {
//                    courseSubSys.displayCourse();
                        addProgrammeToCourse(courseMasterList, progMasterList);
                        option = getAddProgramme();
                    } while (option == 1);
                    break;

                case 7:
                    System.out.println("\n+=======================================+");
                    System.out.println("|  TASK: Delete Programme From A Course  |");
                    System.out.println("+========================================+\n");
                    do {
                        removeProgrammeFromCourse(courseMasterList, progMasterList);
                        option = getDeleteProgramme();
                    } while (option == 1);
                    break;
                case 8:
                    System.out.println("\n+================+");
                    System.out.println("|  TASK: Report  |");
                    System.out.println("+================+\n");
                    courseReport(courseMasterList, progMasterList);
                    getDisplayMenu();
                    break;
            }
        } while (choice != 0);
        return courseMasterList;
    }

    public static void addCourse(QueueInterface<Course> courseMasterList) {
        Scanner scanner = new Scanner(System.in);
        boolean exist;
        do {
            exist = false;
            System.out.print("Course ID: ");
            String courseID = scanner.nextLine();

            Iterator<Course> iterator = courseMasterList.getIterator();

            while (iterator.hasNext()) {
                Course currentCourse = iterator.next();

                if (currentCourse.getCourseID().equals(courseID)) {
                    exist = true;
                    System.out.println(currentCourse.getCourseID() + " already existed ");
                    break;
                }
            }
            if (!exist) {
                System.out.print("Course Name: ");
                String courseName = scanner.nextLine();
                System.out.print("Educational Level: ");
                String eduLevel = scanner.nextLine();
                System.out.print("Credit Hours: ");
                int creditHours = scanner.nextInt();
                scanner.nextLine();

                Course newCourse = new Course(courseID, courseName, eduLevel, creditHours);

                if (courseMasterList != null) {
                    courseMasterList.enqueue(newCourse);

                }
            }
        } while (exist);

    }

    public static void deleteCourse(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Scanner scanner = new Scanner(System.in);
        QueueInterface<Course> tempQueue = new ArrayQueue<Course>();
        displayCourse(courseMasterList, progMasterList);
        int found = 0;

        int i = 0;
        System.out.print("Enter Course Number: ");
        int delete = scanner.nextInt();

        while (!courseMasterList.isEmpty()) {
            i++;
            Course currentCourse = courseMasterList.getFront();

            if (i == delete) {
                found = 1;
                courseMasterList.dequeue();
            } else {
                tempQueue.enqueue(currentCourse);
                courseMasterList.dequeue();
            }

        }

        if (found == 0) {
            System.out.println("\n+---------------------------------------------+");
            System.out.println("|  INFO: Deleted unsuccessfully                |");
            System.out.println("-----------------------------------------------+");

            while (!tempQueue.isEmpty()) {
                courseMasterList.enqueue(tempQueue.getFront());
                tempQueue.dequeue();
            }
        } else {
            while (!tempQueue.isEmpty()) {
                courseMasterList.enqueue(tempQueue.getFront());
                tempQueue.dequeue();
            }
            System.out.println("\n+----------------------------------------------+");
            System.out.println("|  INFO: Deleted have been successfully made.  |");
            System.out.println("-----------------------------------------------+");

        }

    }

    public static void searchCourse(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Course ID to search : ");
        String search = scanner.next();
        System.out.print("\n");

        int found = 0;
        Iterator<Course> iterator = courseMasterList.getIterator();

        while (iterator.hasNext()) {
            Course currentCourse = iterator.next();

            if (currentCourse.getCourseID().equals(search)) {
                found = 1;
                System.out.println("Course ID: " + currentCourse.getCourseID());
                System.out.println("Course Name: " + currentCourse.getCourseName());
                System.out.println("Educational Level: " + currentCourse.getEduLevel());
                System.out.println("Credit Hours: " + currentCourse.getCreditHours() + "");
                if (!currentCourse.getProgList().isEmpty()) {
                    ProgrammeSubSystem.programmeAllDetail(currentCourse.getProgList());
                } else {
                    System.out.println("----------------------------------------------\n");
                    System.out.println("There is no programme in this course");
                    System.out.println("\n----------------------------------------------\n");

                }
            }
        }

        if (found == 0) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|  INFO: No such course existed             |");
            System.out.println("+-------------------------------------------+");
        } else {
            System.out.println("+-------------------------------------------+");
            System.out.println("|  INFO: Search have been succesfully made  |");
            System.out.println("+-------------------------------------------+");

        }

    }

    public static void modifyCourse(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Scanner scanner = new Scanner(System.in);
        displayCourse(courseMasterList, progMasterList);

        System.out.print("Enter Course Number To Amend: ");

        int modify = scanner.nextInt();

        int option = 1;
        int found = 0;
        int i = 0;
        Iterator<Course> iterator = courseMasterList.getIterator();

        while (iterator.hasNext()) {
            i++;
            Course currentCourse = iterator.next();

            if (i == modify) {
                found = 1;

                System.out.println("\n+=====================================+");
                System.out.println("|          AMEND COURSE MENU          |");
                System.out.println("+=====================================+");
                System.out.println("|  1. Amend course ID                 |");
                System.out.println("|  2. Amend course Name               |");
                System.out.println("|  3. Amend course educational level  |");
                System.out.println("|  4. Amend course credit hours       |");
                System.out.println("|  5. Amend all                       |");
                System.out.println("+=====================================+\n");
                System.out.print("Please select an action: ");
                int choice = scanner.nextInt();
                System.out.print("\n");

                switch (choice) {
                    case 1:
                        System.out.print("Course ID: ");
                        String courseID = scanner.nextLine();
                        currentCourse.setCourseID(courseID);

                        System.out.println("\n+-----------------------------------------------+");
                        System.out.println("|  INFO: Modified have been successfully made.  |");
                        System.out.println("+-----------------------------------------------+");
                        break;

                    case 2:
                        System.out.print("Course Name: ");
                        String courseName = scanner.nextLine();

                        currentCourse.setCourseName(courseName);

                        System.out.println("\n+-----------------------------------------------+");
                        System.out.println("|  INFO: Modified have been successfully made.  |");
                        System.out.println("+-----------------------------------------------+");
                        break;

                    case 3:
                        System.out.print("Educational Level: ");
                        String eduLevel = scanner.nextLine();

                        currentCourse.setEduLevel(eduLevel);

                        System.out.println("\n+-----------------------------------------------+");
                        System.out.println("|  INFO: Modified have been successfully made.  |");
                        System.out.println("+-----------------------------------------------+");
                        break;

                    case 4:
                        System.out.print("Credit hours: ");
                        int creditHours = scanner.nextInt();
                        scanner.nextLine();

                        currentCourse.setCreditHours(creditHours);

                        System.out.println("\n+-----------------------------------------------+");
                        System.out.println("|  INFO: Modified have been successfully made.  |");
                        System.out.println("+-----------------------------------------------+");
                        break;

                    case 5:
                        System.out.print("Course ID: ");
                        courseID = scanner.nextLine();
                        System.out.print("Course Name: ");
                        courseName = scanner.nextLine();
                        System.out.print("Educational Level: ");
                        eduLevel = scanner.nextLine();
                        System.out.print("Credit Hours: ");
                        creditHours = scanner.nextInt();
                        scanner.nextLine();

                        currentCourse.setCourseID(courseID);
                        currentCourse.setCourseName(courseName);
                        currentCourse.setEduLevel(eduLevel);
                        currentCourse.setCreditHours(creditHours);

                        System.out.println("\n+-----------------------------------------------+");
                        System.out.println("|  INFO: Modified have been successfully made.  |");
                        System.out.println("+-----------------------------------------------+");
                        break;
                }

            }
        }
        if (found == 0) {
            System.out.println("\n+-----------------------------------------------+");
            System.out.println("|  INFO: No such course existed                 |");
            System.out.println("+-----------------------------------------------+");

        }

    }

    public static void displayCourse(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Iterator<Course> iterator = courseMasterList.getIterator();
        int i = 0;

        while (iterator.hasNext()) {
            Course currentCourse = iterator.next();
            i++;
            System.out.println(i + ") Course ID: " + currentCourse.getCourseID());
            System.out.println("Course Name: " + currentCourse.getCourseName());
            System.out.println("Educational Level: " + currentCourse.getEduLevel());
            System.out.println("Credit Hours: " + currentCourse.getCreditHours() + "");
            if (!currentCourse.getProgList().isEmpty()) {
                ProgrammeSubSystem.programmeAllDetail(currentCourse.getProgList());
            } else {
                System.out.println("----------------------------------------------\n");
                System.out.println("There is no programme in this course");
                System.out.println("\n----------------------------------------------\n");
            }
        }
    }

    public static void addProgrammeToCourse(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Scanner scanner = new Scanner(System.in);
        Iterator<Course> iterator = courseMasterList.getIterator();
        displayCourse(courseMasterList, progMasterList);

        System.out.print("Enter Course Number For Adding Programme : ");

        int search = scanner.nextInt();
        System.out.print("\n");

        int found = 0;
        int i = 0;

        while (iterator.hasNext()) {
            i++;
            Course currentCourse = iterator.next();

            if (i == search) {
                found = 1;
                addProgrammeToCourse(progMasterList, currentCourse);
                System.out.println("\n+--------------------------------------------+");
                System.out.println("|  INFO: Added have been successfully made.  |");
                System.out.println("+--------------------------------------------+");
            }
        }

        if (found == 0) {
            System.out.println("+-------------------------------------------+");
            System.out.println("|  INFO: Not existed                        |");
            System.out.println("+-------------------------------------------+");
        }

    }

    public static void addProgrammeToCourse(ListInterface<Programme> progMasterList, Course course) {
        int entry = ProgrammeSubSystem.programmeList(progMasterList);
        if (entry == -1) {

            System.out.println("Operation Cancelled");
            System.out.println("----------------------------------------------\n");
        } else {
            if (course.getProgList().contains(progMasterList.getEntry(entry))) {
                System.out.println("+-------------------------------------------+");
                System.out.println("|  INFO: Existed                        |");
                System.out.println("+-------------------------------------------+");
            } else {
                course.getProgList().add(progMasterList.getEntry(entry));

            }

        }
    }

    public static void removeProgrammeFromCourse(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Scanner scanner = new Scanner(System.in);
        Iterator<Course> iterator = courseMasterList.getIterator();
        displayCourse(courseMasterList, progMasterList);
        System.out.print("Enter Course Number To Remove Programme : ");
        int search = scanner.nextInt();
        System.out.print("\n");

        int found = 0;
        int i = 0;

        while (iterator.hasNext()) {
            i++;
            Course currentCourse = iterator.next();

            if (i == search) {
                found = 1;
                System.out.println("Course ID: " + currentCourse.getCourseID());
                System.out.println("Course Name: " + currentCourse.getCourseName());
                System.out.println("Educational Level: " + currentCourse.getEduLevel());
                System.out.println("Credit Hours: " + currentCourse.getCreditHours() + "");
                if (!currentCourse.getProgList().isEmpty()) {
                    removeProgrammeFromCourse(currentCourse.getProgList(), currentCourse);
                    System.out.println("\n+----------------------------------------------+");
                    System.out.println("|  INFO: Deleted have been successfully made.  |");
                    System.out.println("-----------------------------------------------+");
                } else {
                                    System.out.println("----------------------------------------------\n");
                System.out.println("There is no programme in this course");
                System.out.println("\n----------------------------------------------\n");
                    System.out.println("\n+---------------------------------------------+");
                    System.out.println("|  INFO: Deleted unsuccessfully                |");
                    System.out.println("-----------------------------------------------+");
                }

            }
        }
        if (found == 0) {
            System.out.println("+----------------------------------------------+");
            System.out.println("|  INFO: Not existed                           |");
            System.out.println("+----------------------------------------------+");
        }
    }

    public static void removeProgrammeFromCourse(ListInterface<Programme> progMasterList, Course course) {

        int entry = ProgrammeSubSystem.programmeList(progMasterList);
        if (entry == -1) {

            System.out.println("Operation Cancelled");
            System.out.println("----------------------------------------------\n");
        } else {
            course.getProgList().remove(entry);

        }
    }

    public static void courseReport(QueueInterface<Course> courseMasterList, ListInterface<Programme> progMasterList) {
        Iterator<Course> iterator = courseMasterList.getIterator();
        int i = 0, count = 0;

        while (iterator.hasNext()) {
            Course currentCourse = iterator.next();
            i++;
            System.out.println(i + ") " + currentCourse.getCourseID() + " , " + currentCourse.getCourseName());
            System.out.println("Number of programme taken : " + currentCourse.getProgList().getNumberOfEntries());

            if (currentCourse.getProgList().getNumberOfEntries() == 0) {
                count++;
            }
        }

        System.out.println("\n+----------------------------------------------------+");
        System.out.println("|  INFO: " + count + " course is not taken by any programme yet  |");
        System.out.println("+----------------------------------------------------+");

    }

    public static void displaySubMenu() {
        System.out.println("+=============================================+");
        System.out.println("|           COURSE MANAGEMENT MENU            |");
        System.out.println("+=============================================+");
        System.out.println("|  1. Create new course                       |");
        System.out.println("|  2. Remove a course                         |");
        System.out.println("|  3. Find a course                           |");
        System.out.println("|  4. Amend a course details                  |");
        System.out.println("|  5. List all courses                        |");
        System.out.println("|  6. Add programme to a course               |");
        System.out.println("|  7. Remove a programme from a course        |");
        System.out.println("|  8. Generate reports                        |");
        System.out.println("|                                             |");
        System.out.println("|  0. Return to main menu                     |");
        System.out.println("+=============================================+");
    }

    public static int getSubMenuChoice() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int choice;

        displaySubMenu();
        System.out.print("\nPlease select an action: ");
        input = scanner.next();
        choice = Validation.validateIntegerInput(input);

        while (choice < 0 || choice > 8) {
            System.out.println("Invalid input. Please re-enter: ");
            input = scanner.next();
            choice = Validation.validateIntegerInput(input);
        }

        return choice;
    }

    public static int getAddMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("\n+--------------------------------------------+");
        System.out.println("|  INFO: Added have been successfully made.  |");
        System.out.println("+--------------------------------------------+");
        System.out.println("|  1. Add another course                     |");
        System.out.println("|                                            |");
        System.out.println("|  0. Return to course management menu       |");
        System.out.println("+--------------------------------------------+");
        System.out.print("\nPlease select an action: ");
        option = scanner.nextInt();
        System.out.print("\n");
        return option;
    }

    public static int getDeleteMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("|  1. Delete another course                    |");
        System.out.println("|                                              |");
        System.out.println("|  0. Return to course management menu         |");
        System.out.println("-----------------------------------------------+");
        System.out.print("\nPlease select an action: ");
        option = scanner.nextInt();
        System.out.print("\n");
        return option;
    }

    public static int getSearchMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("|  1. Search another course                 |");
        System.out.println("|                                           |");
        System.out.println("|  0. Return to course management menu      |");
        System.out.println("+-------------------------------------------+");
        System.out.print("\nPlease select an action: ");
        option = scanner.nextInt();
        System.out.print("\n");
        return option;
    }

    public static int getModifyMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("|  1. Amend another course                      |");
        System.out.println("|                                               |");
        System.out.println("|  0. Return to course management menu          |");
        System.out.println("+-----------------------------------------------+");
        System.out.print("\nPlease select an action: ");
        option = scanner.nextInt();
        System.out.print("\n");
        return option;
    }

    public static void getDisplayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n+--------------------------------------+");
        System.out.println("|  INFO: Press Any Number To Continue  |");
        System.out.println("+--------------------------------------+");
        scanner.nextInt();
    }

    public static int getAddProgramme() {
        Scanner scanner = new Scanner(System.in);
        int option;
        System.out.println("|  1. Add another programme to a course      |");
        System.out.println("|                                            |");
        System.out.println("|  0. Return to course management menu       |");
        System.out.println("+--------------------------------------------+");
        System.out.print("\nPlease select an action: ");
        option = scanner.nextInt();
        System.out.print("\n");
        return option;
    }

    public static int getDeleteProgramme() {
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("|  1. Delete another programme from course     |");
        System.out.println("|                                              |");
        System.out.println("|  0. Return to course management menu         |");
        System.out.println("-----------------------------------------------+");
        System.out.print("\nPlease select an action: ");
        option = scanner.nextInt();
        System.out.print("\n");
        return option;
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
//    public static void updateDatabase(QueueInterface<Course> courseMasterList,ListInterface<Programme> progMasterList){
//        Iterator<Course> iterator = courseMasterList.getIterator();
//     
//        while (iterator.hasNext()) {
//            Course currentCourse = iterator.next();
//        }
//        
//    }
     
        
}
