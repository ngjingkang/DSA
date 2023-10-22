/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.ArrayList;
import adt.HashInterface;
import adt.HashMap;
import entity.Semester;
import entity.Tutor;
import static java.lang.System.exit;
import java.util.Iterator;
import java.util.Scanner;
import adt.ListInterface;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

/**
 *
 * @author Tan Su Jing
 */
public class TutorManagement {

    HashInterface<String, Tutor> tutorTable = new HashMap<>();
    ListInterface<Tutor> tutorList1 = new ArrayList();
    ListInterface<Tutor> tempTableAdd = new ArrayList();
    ListInterface<Tutor> tempTableDel = new ArrayList();

    //Semester's Tutor Set
    HashInterface<String, Tutor> tutorSet1 = new HashMap<>(10);
    HashInterface<String, Tutor> tutorSet2 = new HashMap<>(10);
    HashInterface<String, Tutor> tutorSet3 = new HashMap<>(10);

    //Initialize Semester
    private Semester s1 = new Semester(23, 1);
    private Semester s2 = new Semester(23, 2);
    private Semester s3 = new Semester(23, 3);

    private int option;
    private int option1;
    private int num = 0;
    private int countAdd = 0;
    private int coundDel = 0;
    private int tutorCounter = 9; // Initialize the tutor counter to 9

    public TutorManagement() throws AWTException {
        //initialize data
        tutorTable.add("T001", new Tutor("T001", "Ben Tennyson", "ben@tarc.edu.my", "0123433223", "Professor"));
        tutorTable.add("T002", new Tutor("T002", "Tan Su Jing", "sj@tarc.edu.my", "0187776666", "Senior Lecturer"));
        tutorTable.add("T003", new Tutor("T003", "Sean Tan", "sean@tarc.edu.my", "0123455555", "Lecturer"));
        tutorTable.add("T004", new Tutor("T004", "Sophia Lee", "soph@tarc.edu.my", "0198887776", "Senior Lecturer"));
        tutorTable.add("T005", new Tutor("T005", "James Lee", "p4303@gmail.com", "0125527728", "Lecturer"));
        tutorTable.add("T006", new Tutor("T006", "Chai Yong Qi", "yq@tarc.edu.my", "0109998877", "Associate Professor"));
        tutorTable.add("T007", new Tutor("T007", "Han Yi Ting", "yt@tarc.edu.my", "0987772266", "Professor"));
        tutorTable.add("T008", new Tutor("T008", "Yap Rou Xin", "rx1@tarc.edu.my", "0123452267", "Lecturer"));

        tutorSet1.add("T001", new Tutor("T001", "Ben Tennyson", "ben@tarc.edu.my", "0123433223", "Professor"));
        tutorSet1.add("T002", new Tutor("T002", "Tan Su Jing", "sj@tarc.edu.my", "0187776666", "Senior Lecturer"));
        tutorSet1.add("T003", new Tutor("T003", "Sean Tan", "sean@tarc.edu.my", "0123455555", "Lecturer"));

        tutorSet2.add("T004", new Tutor("T004", "SophiaLee", "soph@tarc.edu.my", "0198887776", "Senior Lecturer"));
        tutorSet2.add("T005", new Tutor("T005", "James Lee", "p4303@gmail.com", "0125527728", "Lecturer"));

        tutorSet3.add("T006", new Tutor("T006", "Chai Yong Qi", "yq@tarc.edu.my", "0109998877", "Associate Professor"));
        tutorSet3.add("T007", new Tutor("T007", "Han Yi Ting", "yt@tarc.edu.my", "0987772266", "Professor"));
        tutorSet3.add("T008", new Tutor("T008", "Yap Rou Xin", "rx1@tarc.edu.my", "0123452267", "Lecturer"));

        s1.setTutorSet(tutorSet1);
        s2.setTutorSet(tutorSet2);
        s3.setTutorSet(tutorSet3);
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

    public void addTutor() throws AWTException {      //add tutor
        char opt;
        do {
            clearScreen();
            System.out.println("=================================");
            System.out.println("\t  ADD NEW TUTOR");
            System.out.println("=================================");
            String tId = generateTutorId(); // Generate the next tutor ID
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter new tutor's Name :");
            String tName = scanner.nextLine();

            System.out.println("Enter new tutor's Email :");
            String tEmail = scanner.nextLine();

            System.out.println("Enter new tutor's PhoneNumber :");
            String tPhno = scanner.nextLine();

            System.out.println("Enter new tutor's Position :");
            String tPosition = scanner.nextLine();

            Tutor newTutor = new Tutor(tId, tName, tEmail, tPhno, tPosition);

            tutorTable.add(tId, newTutor);
            tempTableAdd.add(newTutor); //add tutor to temp list
            System.out.println("\nTutor added successfully.");
            countAdd++;

            System.out.print("Continue adding items ? (y/n): ");
            opt = scanner.next().charAt(0);
            if (opt == 'n' || opt == 'N') {
                tutorMenu();
            }
        } while (opt == 'y' || opt == 'Y');
    }

    public void removeTutor() throws AWTException {    //remove tutor
        char opt;
        Scanner scanner = new Scanner(System.in);
        do {
            clearScreen();
            System.out.println("=================================================================================================");
            System.out.println("\t\t\t\t\tREMOVE TUTOR");
            System.out.println("=================================================================================================");
            //display Tutor List
            System.out.println("Tutor List: ");
            subDisplayTutors();
            System.out.println("\nEnter tutor id to remove: ");
            String rmtutor = scanner.nextLine();

            if (tutorTable.contains(rmtutor)) {
                Tutor removed = tutorTable.get(rmtutor);
                tutorTable.remove(rmtutor); // Remove the tutor associated with the given ID
                tempTableDel.add(removed);
                System.out.println("\nTutor with ID " + rmtutor + " has been removed.");
            } else {
                System.err.println("\nTutor with ID " + rmtutor + " not found.");
            }

            System.out.print("Continue to delete tutors ? (y/n): ");
            opt = scanner.nextLine().charAt(0);
            if (opt == 'n' || opt == 'N') {
                tutorMenu();
            }
        } while (opt == 'y' || opt == 'Y');
    }

    public void updateTutor() throws AWTException {      //update tutor details
        char opt;
        Scanner scanner = new Scanner(System.in);
        do {
            clearScreen();
            tutorList1 = tutorTable.getVal();
            System.out.println("=================================================================================================");
            System.out.println("\t\t\t\t\tUPDATE TUTOR");
            System.out.println("=================================================================================================");
            //display list
            System.out.println("Tutor List:");
            subDisplayTutors();
            System.out.println("\nEnter tutor id to update: ");
            String updatetutor = scanner.nextLine();

            tutorTable.computeIfPresent(updatetutor, (key, value) -> {
                System.out.println("\nTutor Details: ");
                System.out.println("-----------------------------------");
                System.out.println("Name         : " + value.getName());
                System.out.println("Email        : " + value.getEmail());
                System.out.println("Phone Number : " + value.getPhno());
                System.out.println("Position     : " + value.getPosition());

                System.out.println("\n+=================================+");
                System.out.println("|     Select Option to Update     |");
                System.out.println("+=================================+");
                System.out.println("| 1. Update Tutor Name            |");
                System.out.println("| 2. Update Tutor Email           |");
                System.out.println("| 3. Update Tutor Phone Number    |");
                System.out.println("| 4. Update Tutor Position        |");
                System.out.println("+=================================+");
                System.out.print("\nEnter Option: ");
                String input = scanner.nextLine();
                option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        System.out.println("\nEnter New Tutor Name: ");
                        String updatetName = scanner.nextLine();
                        value.setName(updatetName);
                        break;
                    case 2:
                        System.out.println("\nEnter New Tutor Email: ");
                        String updatetEmail = scanner.nextLine();
                        value.setEmail(updatetEmail);
                        break;
                    case 3:
                        System.out.println("\nEnter New Tutor Phone Number: ");
                        String updatetPhno = scanner.nextLine();
                        value.setPhno(updatetPhno);
                        break;
                    case 4:
                        System.out.println("\nEnter New Tutor Position: ");
                        String updatetPosition = scanner.nextLine();
                        value.setPosition(updatetPosition);
                        break;
                    default:
                        break;
                }
                return value; // Return the value (tutor) as it is
            });

            System.out.println("\nUpdate Successful.");
            System.out.println("Continue to update tutors ? (y/n): ");
            opt = scanner.nextLine().charAt(0);
            if (opt == 'n' || opt == 'N') {
                tutorMenu();
            }
        } while (opt == 'y' || opt == 'Y');
    }

    public void displayTutor() throws AWTException {      //display all tutor
        char opt;
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("=================================================================================================");
        System.out.println("                                        DISPLAY ALL TUTOR                                     ");
        System.out.println("=================================================================================================");
        subDisplayTutors();

        System.out.print("\nReturn to Previous Page? (y/n): ");
        opt = scanner.next().charAt(0);
        if (opt == 'y' || opt == 'Y') {
            tutorMenu();
        }
    }

    public void subDisplayTutors() {
        tutorList1 = tutorTable.getVal();
        //sort values by id
        int n = tutorList1.getNumberOfEntries();
        for (int i = 1; i < n ; i++) {
            for (int j = 1; j < n - i ; j++) {
                String tutorId1 = tutorList1.getEntry(j).getId();
                String tutorId2 = tutorList1.getEntry(j ).getId();

                // Compare tutorIds and swap if necessary
                if (tutorId1.compareTo(tutorId2) > 0) {
                    // Swap tutorList[j] and tutorList[j+1]
                    Tutor temp = tutorList1.getEntry(j);
                    tutorList1.replace(j, tutorList1.getEntry(j ));
                    tutorList1.replace(j , temp);
                }
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("\tTutor ID    Name                Email               Phone Number     Position          ");
        System.out.println("-------------------------------------------------------------------------------------------------");
        for (int i = 1; i < tutorList1.getNumberOfEntries()+1; i++) {
            String tutorId = tutorList1.getEntry(i).getId();
            String name = tutorList1.getEntry(i).getName();
            String email = tutorList1.getEntry(i).getEmail();
            String phoneNumber = tutorList1.getEntry(i).getPhno();
            String position = tutorList1.getEntry(i).getPosition();
            System.out.printf((i ) + "\t%-12s%-20s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
        }
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    public void searchTutor() throws AWTException {
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("+==============================+");
        System.out.println("|         Search Tutor         |");
        System.out.println("+==============================+");
        System.out.println("| 1. By ID                     |");
        System.out.println("| 2. By First Character of Name|");
        System.out.println("| 3. By Position               |");
        System.out.println("| 4. Back                      |");
        System.out.println("| 5. Exit                      |");
        System.out.println("+==============================+");
        System.out.print("\nEnter Option: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                searchTutorID();
                break;
            case 2:
                searchTutorName();
                break;
            case 3:
                searchTutorPosition();
                break;
            case 4:
                tutorMenu();
                break;
            case 5:
                exit(0);
                break;
            default:
                break;
        }
    }

    public void searchTutorID() throws AWTException { //using lambda expression to iterate over hashMap 
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter Tutor ID: ");
            String tutid = scanner.nextLine();

            tutorTable.computeIfPresent(tutid, (key, value) -> {
                System.out.println("\nSearch Result: ");
                System.out.println("-----------------------------------");
                System.out.println("Name         : " + value.getName());
                System.out.println("Email        : " + value.getEmail());
                System.out.println("Phone Number : " + value.getPhno());
                System.out.println("Position     : " + value.getPosition());
                return value; // Return the value (tutor) as it is
            });

            if (!tutorTable.contains(tutid)) {
                System.err.println("No Tutor Found.");
            }

            System.out.print("\nContinue to Search by ID? (y/n): ");
            option = scanner.nextLine().charAt(0);
            if (option == 'n' || option == 'N') {
                searchTutor();
            }
        } while (option == 'y' || option == 'Y');
    }

    public void searchTutorName() throws AWTException { //using ArrayList Iterator to iterate over ArrayList
        Scanner scanner = new Scanner(System.in);
        int valid = 0;
        do {
            System.out.println("Enter First Character of Tutor's Name: ");
            char searchChar = scanner.nextLine().charAt(0);

            tutorList1 = tutorTable.getVal();
            Iterator<Tutor> iterator = tutorList1.iterator();

            System.out.println("\nSearch Result: ");
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
            System.out.println("--------------------------------------------------------------------------------------------");
            num = 0;
            while (iterator.hasNext()) {
                Tutor tutor = iterator.next();
                if (Character.toUpperCase(tutor.getName().charAt(0)) == Character.toUpperCase(searchChar)) {
                    num++;
                    String tutorId = tutor.getId();
                    String name = tutor.getName();
                    String email = tutor.getEmail();
                    String phoneNumber = tutor.getPhno();
                    String position = tutor.getPosition();
                    System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
                    valid++;
                }
            }
            System.out.println("--------------------------------------------------------------------------------------------");
            if (valid == 0) {
                System.err.println("No Tutor Found.");
            }

            System.out.print("\nContinue to Search by Name? (y/n): ");
            option = scanner.nextLine().charAt(0);
            if (option == 'n' || option == 'N') {
                searchTutor();
            }
        } while (option == 'y' || option == 'Y');
    }

    //no validation
    public void searchTutorPosition() throws AWTException {  //using lambda expression to iterate over hashMap 
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter Tutor Position: ");
            String tutPosition = scanner.nextLine();

            System.out.println("\nSearch Result: ");
            System.out.println("======================================================================================");
            System.out.println("Position: " + tutPosition.toUpperCase());
            System.out.println("======================================================================================");
            System.out.println("\tTutor ID    Name           Email             Phone Number       ");
            System.out.println("--------------------------------------------------------------------------------------");
            num = 0;
            tutorTable.forEach((id, tutor) -> {
                if (tutor.getPosition().equalsIgnoreCase(tutPosition)) {
                    num++;
                    String tutorId = id;
                    String name = tutor.getName();
                    String email = tutor.getEmail();
                    String phoneNumber = tutor.getPhno();
                    System.out.printf(num + "\t%-12s%-15s%-20s%-17s\n", tutorId, name, email, phoneNumber);
                }
            });
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.print("\nContinue to Search by Position? (y/n): ");
            option = scanner.nextLine().charAt(0);
            if (option == 'n' || option == 'N') {
                searchTutor();
            }
        } while (option == 'y' || option == 'Y');
    }

    public void filterTutorMenu() throws AWTException {     //Filter tutors based on criteria
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("+==============================+");
        System.out.println("|         Filter Tutor         |");
        System.out.println("+==============================+");
        System.out.println("| 1. By Position               |");
        System.out.println("| 2. By Employment Type        |");
        System.out.println("| 3. Back                      |");
        System.out.println("| 4. Exit                      |");
        System.out.println("+==============================+");
        System.out.print("\nEnter Choice: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                filterTutorPosition();
                break;
            case 2:
                filterTutorEmploymentType();
                break;
            case 3:
                tutorMenu();
                break;
            case 4:
                exit(0);
                break;
            default:
                break;
        }
    }

    public void filterTutorPosition() throws AWTException { //using lambda expression to iterate over hashMap 
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println("+==============================+");
        System.out.println("|  Filter Tutor by Position    |");
        System.out.println("+==============================+");
        System.out.println("| 1. Professor                 |");
        System.out.println("| 2. Associate Professor       |");
        System.out.println("| 3. Senior Lecturer           |");
        System.out.println("| 4. Lecturer                  |");
        System.out.println("| 5. Back                      |");
        System.out.println("| 6. Exit                      |");
        System.out.println("+==============================+");
        System.out.println("\nEnter option: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                clearScreen();
                System.out.println("======================================================================================");
                System.out.println("\t\t\t\tFILTER RESULT - PROFESSOR");
                System.out.println("======================================================================================");
                displayProfessor();
                System.out.println("--------------------------------------------------------------------------------------\n");
                back0();
                break;
            case 2:
                clearScreen();
                System.out.println("======================================================================================");
                System.out.println("\t\t\t\tFILTER RESULT - ASSOCIATE PROFESSOR");
                System.out.println("======================================================================================");
                displayAssociateProfessor();
                System.out.println("--------------------------------------------------------------------------------------\n");
                back0();
                break;
            case 3:
                clearScreen();
                System.out.println("======================================================================================");
                System.out.println("\t\t\t\tFILTER RESULT - SENIOR LECTURER");
                System.out.println("======================================================================================");
                displaySeniorLecturer();
                System.out.println("--------------------------------------------------------------------------------------\n");
                back0();
                break;
            case 4:
                clearScreen();
                System.out.println("======================================================================================");
                System.out.println("\t\t\t\tFILTER RESULT - LECTURER");
                System.out.println("======================================================================================");
                displayLecturer();
                System.out.println("--------------------------------------------------------------------------------------\n");
                back0();
                break;
            case 5:
                filterTutorMenu();
                break;
            case 6:
                exit(0);
                break;
            default:
                break;
        }
    }

    public void filterTutorEmploymentType() throws AWTException { //using lambda expression to iterate over hashMap 
        Scanner scanner = new Scanner(System.in);
        clearScreen();

        System.out.println("+=================================+");
        System.out.println("| Filter Tutor by Employment Type |");
        System.out.println("+=================================+");
        System.out.println("| 1. Full Time                    |");
        System.out.println("| 2. Part Time                    |");
        System.out.println("| 3. Back                         |");
        System.out.println("| 4. Exit                         |");
        System.out.println("+=================================+");
        System.out.println("\nEnter option: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                clearScreen();
                num = 0;
                System.out.println("============================================================================================");
                System.out.println("\t\t\t\tFiltered Result - Full Time");
                System.out.println("============================================================================================");
                System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
                System.out.println("--------------------------------------------------------------------------------------------");
                tutorTable.forEach((id, tutor) -> {
                    String email = tutor.getEmail();
                    if (!email.matches("^p\\d{4}.*")) { // Use a regular expression to check the email pattern
                        num++;
                        String tutorId = id;
                        String name = tutor.getName();
                        String phoneNumber = tutor.getPhno();
                        String position = tutor.getPosition();
                        System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
                    }
                });
                System.out.println("--------------------------------------------------------------------------------------------");
                if (num == 0) {
                    System.err.println("No Result Found");
                }
                back1();
                break;
            case 2:
                clearScreen();
                num = 0;
                System.out.println("============================================================================================");
                System.out.println("\t\t\t\tFilter Result - Part Time");
                System.out.println("============================================================================================");
                System.out.println("\tTutor ID    Name           Email             Phone Number       ");
                System.out.println("--------------------------------------------------------------------------------------------");
                tutorTable.forEach((id, tutor) -> {
                    String email = tutor.getEmail();
                    if (email.matches("^p\\d{4}.*")) {  // Use a regular expression to check the email pattern
                        num++;
                        String tutorId = id;
                        String name = tutor.getName();
                        String phoneNumber = tutor.getPhno();
                        System.out.printf(num + "\t%-12s%-15s%-20s%-17s\n", tutorId, name, email, phoneNumber);
                    }
                });
                System.out.println("--------------------------------------------------------------------------------------------");
                if (num == 0) {
                    System.err.println("No Result Found");
                }
                back1();
                break;
            case 4:
                exit(0);
                break;
            case 3:
                filterTutorMenu();
                break;
            default:
                break;
        }
    }

    //report
    public void reportMenu() throws AWTException {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("+====================================+");
        System.out.println("|                Report              |");
        System.out.println("+====================================+");
        System.out.println("| 1. Total Added and Deleted Tutors  |");
        System.out.println("| 2. Total Tutors for each Position  |");
        System.out.println("| 3. Back                            |");
        System.out.println("| 4. Exit                            |");
        System.out.println("+====================================+");
        System.out.println("\nEnter option: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                report1();
                break;
            case 2:
                report2();
                break;
            case 3:
                tutorMenu();
                break;
            case 4:
                exit(0);
                break;
            default:
        }
    }

    public void report1() throws AWTException {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();

        System.out.println("============================================================================================");
        System.out.println("\t\t   REPORT OF TOTAL ADDED AND DELETED TUTORS - " + today);
        System.out.println("============================================================================================");
        System.out.println("TOTAL TUTORS ADDED");
        System.out.println("============================================================================================");
        System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
        System.out.println("--------------------------------------------------------------------------------------------");

        num = 0;
//        countAdd = 0;
        Iterator<Tutor> iterator = tempTableAdd.iterator();
        while (iterator.hasNext()) {
            num++;
//            countAdd++;
            Tutor tutor = iterator.next();
            String tutorId = tutor.getId();
            String name = tutor.getName();
            String email = tutor.getEmail();
            String phoneNumber = tutor.getPhno();
            String position = tutor.getPosition();
            System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
        }
        System.out.println("\nTotal number of tutors added : " + tempTableAdd.getNumberOfEntries());
        System.out.println("============================================================================================");
        System.out.println("TOTAL TUTORS DELETED");
        System.out.println("============================================================================================");
        System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
        System.out.println("--------------------------------------------------------------------------------------------");

        num = 0;
        iterator = tempTableDel.iterator();
        while (iterator.hasNext()) {
            num++;
//            coundDel++;
            Tutor tutor = iterator.next();
            String tutorId = tutor.getId();
            String name = tutor.getName();
            String email = tutor.getEmail();
            String phoneNumber = tutor.getPhno();
            String position = tutor.getPosition();
            System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
        }
        System.out.println("\nTotal number of tutors deleted : " + tempTableDel.getNumberOfEntries());
        System.out.println("============================================================================================");
        System.out.println("\nPress Enter to Return....");
        String enter = scanner.nextLine();
        reportMenu();
    }

    public void report2() throws AWTException {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        LocalDate today = LocalDate.now();
        int temp = 0;

        System.out.println("======================================================================================");
        System.out.println("\t\t   REPORT OF TOTAL TUTORS FOR EACH POSITION - " + today);
        System.out.println("======================================================================================");
        System.out.println("POSITION: PROFESSOR");
        System.out.println("======================================================================================");
        displayProfessor();
        System.out.println("\nTotal Number of Professors: " + num);
        temp += num;
        System.out.println("======================================================================================");
        System.out.println("POSITION: ASSOCIATE PROFESSOR");
        System.out.println("======================================================================================");
        displayAssociateProfessor();
        System.out.println("\nTotal Number of Associate Professors: " + num);
        temp += num;
        System.out.println("======================================================================================");
        System.out.println("POSITION: SENIOR LECTURER");
        System.out.println("======================================================================================");
        displaySeniorLecturer();
        System.out.println("\nTotal Number of Senior Lecturers: " + num);
        temp += num;
        System.out.println("======================================================================================");
        System.out.println("POSITION: LECTURER");
        System.out.println("======================================================================================");
        displayLecturer();
        System.out.println("\nTotal Number of Lecturers: " + num);
        temp += num;
        System.out.println("======================================================================================");
        System.out.println("\nTOTAL NUMBER OF TUTORS: " + temp);

        System.out.println("Press Enter to Return....");
        String enter = scanner.nextLine();
        reportMenu();
    }

    public void setTutSem() throws AWTException {     //set tutor semester object
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("+====================================+");
        System.out.println("|         ACTION FOR SEMESTER        |");
        System.out.println("+====================================+");
        System.out.println("| 1. Add Tutor to Semester           |");
        System.out.println("| 2. Remove Tutor from Semester      |");
        System.out.println("| 3. View Semester Tutor List        |");
        System.out.println("| 4. Back                            |");
        System.out.println("| 5. Exit                            |");
        System.out.println("+====================================+");
        System.out.println("\nEnter Option: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                assignTutor();
                break;
            case 2:
                removeAssignedTutor();
                break;
            case 3:
                displayAssignedTutor();
                break;
            case 4:
                tutorMenu();
                break;
            case 5:
                exit(0);
                break;
            default:
                break;
        }
    }

    public void assignTutor() throws AWTException {    //assign tutor to semester (no validation)
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        System.out.println("=================================================================================================");
        System.out.println("\t\t\t\tASSIGN TUTOR TO SEMESTER");
        System.out.println("=================================================================================================");

        do {
            //display list
            System.out.println("Tutor List: ");
            subDisplayTutors();

            System.out.println("\nEnter tutor id to be assigned: ");
            String tutid = scanner.nextLine();

            if (!tutorTable.contains(tutid)) {
                System.out.println("Tutor not found.");
            }

            System.out.println("\nSemester List: ");
            System.out.println("1. Year 20" + s1.getYear() + " Semester " + s1.getSemNo());
            System.out.println("2. Year 20" + s2.getYear() + " Semester " + s2.getSemNo());
            System.out.println("3. Year 20" + s3.getYear() + " Semester " + s3.getSemNo());
            System.out.println("\nEnter Semester: ");
            String input = scanner.nextLine();
            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    Tutor assignedTutor1 = tutorTable.get(tutid);
                    assignedTutor1.setSem(s1);
                    tutorSet1.add(tutid, assignedTutor1);
                    System.out.println("\nTutor " + assignedTutor1.getName() + " has been assigned to Semester 1.");
                    break;
                case 2:
                    Tutor assignedTutor2 = tutorTable.get(tutid);
                    assignedTutor2.setSem(s2);
                    tutorSet2.add(tutid, assignedTutor2);
                    System.out.println("\nTutor " + assignedTutor2.getName() + " has been assigned to Semester 2.");
                    break;
                case 3:
                    Tutor assignedTutor3 = tutorTable.get(tutid);
                    assignedTutor3.setSem(s3);
                    tutorSet3.add(tutid, assignedTutor3);
                    System.out.println("\nTutor " + assignedTutor3.getName() + " has been assigned to Semester 3.");
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    break;
            }

            System.out.print("Continue to assign tutors ? (y/n): ");
            option = scanner.nextLine().charAt(0);
            if (option == 'n' || option == 'N') {
                setTutSem();
            }
        } while (option == 'y' || option == 'Y');
    }

    public void removeAssignedTutor() throws AWTException {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================================================================");
        System.out.println("\t\t\t\tREMOVE TUTOR FROM SEMESTER");
        System.out.println("============================================================================================");
        do {
            System.out.println("Semester List: ");
            System.out.println("1. Year 20" + s1.getYear() + " Semester " + s1.getSemNo());
            System.out.println("2. Year 20" + s2.getYear() + " Semester " + s2.getSemNo());
            System.out.println("3. Year 20" + s3.getYear() + " Semester " + s3.getSemNo());
            System.out.println("\nEnter Semester to View: ");
            String input = scanner.nextLine();
            option = Integer.parseInt(input);

            switch (option) {
                case 1:
                    System.out.println("Tutor List: ");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    displayTutorSem1();
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Enter Tutor ID to remove from Semester: ");
                    String rmAssignedTutor1 = scanner.nextLine();
                    if (tutorSet1.contains(rmAssignedTutor1)) {
                        tutorSet1.remove(rmAssignedTutor1);
                        System.out.println("\nTutor with ID " + rmAssignedTutor1 + " has been removed from Year 2023 Semester 1.");
                    } else {
                        System.err.println("\nTutor not found.");
                    }
                    break;
                case 2:
                    System.out.println("Tutor List: ");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    displayTutorSem2();
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Enter Tutor ID to remove from Semester: ");
                    String rmAssignedTutor2 = scanner.nextLine();
                    if (tutorSet2.contains(rmAssignedTutor2)) {
                        tutorSet2.remove(rmAssignedTutor2);
                        System.out.println("\nTutor with ID " + rmAssignedTutor2 + " has been removed from Year 2023 Semester 2.");
                    } else {
                        System.err.println("\nTutor not found.");
                    }
                    break;
                case 3:
                    System.out.println("Tutor List: ");
                    System.out.println("--------------------------------------------------------------------------------------------");
                    displayTutorSem3();
                    System.out.println("--------------------------------------------------------------------------------------------");
                    System.out.println("Enter Tutor ID to remove from Semester: ");
                    String rmAssignedTutor3 = scanner.nextLine();
                    if (tutorSet3.contains(rmAssignedTutor3)) {
                        tutorSet3.remove(rmAssignedTutor3);
                        System.out.println("\nTutor with ID " + rmAssignedTutor3 + " has been removed from Year 2023 Semester 3.");
                    } else {
                        System.err.println("\nTutor not found.");
                    }
                    break;
                case 4:
                    exit(0);
                    break;
                default:
                    break;
            }

            System.out.print("Continue to delete ? (y/n): ");
            option1 = scanner.nextLine().charAt(0);
            if (option1 == 'n' || option1 == 'N') {
                setTutSem();
            }
        } while (option1 == 'y' || option1 == 'Y');
    }

    public void displayAssignedTutor() throws AWTException {
        clearScreen();
        int count1 = s1.getTutorSet().getVal().getNumberOfEntries();
        int count2 = s2.getTutorSet().getVal().getNumberOfEntries();
        int count3 = s3.getTutorSet().getVal().getNumberOfEntries();

        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("\tDISPLAY TUTOR IN SEMESTER");
        System.out.println("=========================================");
        System.out.println("Semester List: ");
        System.out.println("1. Year 20" + s1.getYear() + " Semester " + s1.getSemNo());
        System.out.println("2. Year 20" + s2.getYear() + " Semester " + s2.getSemNo());
        System.out.println("3. Year 20" + s3.getYear() + " Semester " + s3.getSemNo());
        System.out.println("\nEnter Semester to View: ");
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                clearScreen();
                num = 0;
                System.out.println("Tutors Assigned to Year 2023 Semester 1: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                displayTutorSem1();
                System.out.println("\nTotal tutors assigned to Year 2023 Semester 1: " + count1);
                System.out.println("--------------------------------------------------------------------------------------------");
                break;

            case 2:
                clearScreen();
                num = 0;
                System.out.println("Tutors Assigned to Year 2023 Semester 2: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                displayTutorSem2();
                System.out.println("\nTotal tutors assigned to Year 2023 Semester 1 = " + count2);
                System.out.println("--------------------------------------------------------------------------------------------");
                break;
            case 3:
                clearScreen();
                num = 0;
                System.out.println("Tutors Assigned to Year 2023 Semester 3: ");
                System.out.println("--------------------------------------------------------------------------------------------");
                displayTutorSem3();
                System.out.println("\nTotal tutors assigned to Year 2023 Semester 1 = " + count3);
                System.out.println("--------------------------------------------------------------------------------------------");
                break;
            case 4:
                exit(0);
                break;
            default:
                break;
        }
        System.out.print(
                "\nReturn to Previous Page? (y/n): ");
        option = scanner.nextLine().charAt(0);
        if (option == 'y' || option == 'Y') {
            setTutSem();
        }
    }

    private String generateTutorId() {
        String prefix = "T";
        String numericPart = String.format("%03d", tutorCounter); // Format the counter as a 3-digit number
        String id = prefix + numericPart;

        // Increment the tutor counter for the next ID
        tutorCounter++;
        return id;
    }

    public void tutorMenu() throws AWTException {
        clearScreen();
        System.out.println("+====================================+");
        System.out.println("|        TUTOR MANAGEMENT MENU       |");
        System.out.println("+====================================+");
        System.out.println("| 1. Add New Tutor                   |");
        System.out.println("| 2. Display All Tutor               |");
        System.out.println("| 3. Remove Tutor                    |");
        System.out.println("| 4. Update Tutor Details            |");
        System.out.println("| 5. Search Tutor                    |");
        System.out.println("| 6. Filter Tutor by Category        |");
        System.out.println("| 7. Generate Report                 |");
        System.out.println("| 8. Semester Actions                |");
        System.out.println("| 9. Exit                            |");
        System.out.println("+====================================+");
        System.out.print("\nEnter Option: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        option = Integer.parseInt(input);

        switch (option) {
            case 1:
                addTutor();
                break;
            case 2:
                displayTutor();
                break;
            case 3:
                removeTutor();
                break;
            case 4:
                updateTutor();
                break;
            case 5:
                searchTutor();
                break;
            case 6:
                filterTutorMenu();
                break;
            case 7:
                reportMenu();
                break;
            case 8:
                setTutSem();
                break;
            case 9:
                System.out.println("Exited");
                break;
            default:
                break;
        }
    }

    public void displayProfessor() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       ");
        System.out.println("--------------------------------------------------------------------------------------");
        tutorTable.forEach((id, tutor) -> {
            if (tutor.getPosition().equals("Professor")) {
                num++;
                String tutorId = id;
                String name = tutor.getName();
                String email = tutor.getEmail();
                String phoneNumber = tutor.getPhno();
                System.out.printf(num + "\t%-12s%-15s%-20s%-17s\n", tutorId, name, email, phoneNumber);
            }
        });
        if (num == 0) {
            System.err.println("No Result Found");
        }
    }

    public void displayAssociateProfessor() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       ");
        System.out.println("--------------------------------------------------------------------------------------");
        tutorTable.forEach((id, tutor) -> {
            if (tutor.getPosition().equals("Associate Professor")) {
                num++;
                String tutorId = id;
                String name = tutor.getName();
                String email = tutor.getEmail();
                String phoneNumber = tutor.getPhno();
                System.out.printf(num + "\t%-12s%-15s%-20s%-17s\n", tutorId, name, email, phoneNumber);
            }
        });
        if (num == 0) {
            System.err.println("No Result Found");
        }
    }

    public void displaySeniorLecturer() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       ");
        System.out.println("--------------------------------------------------------------------------------------");
        tutorTable.forEach((id, tutor) -> {
            if (tutor.getPosition().equals("Senior Lecturer")) {
                num++;
                String tutorId = id;
                String name = tutor.getName();
                String email = tutor.getEmail();
                String phoneNumber = tutor.getPhno();
                System.out.printf(num + "\t%-12s%-15s%-20s%-17s\n", tutorId, name, email, phoneNumber);
            }
        });
        if (num == 0) {
            System.err.println("No Result Found");
        }
    }

    public void displayLecturer() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       ");
        System.out.println("--------------------------------------------------------------------------------------");
        tutorTable.forEach((id, tutor) -> {
            if (tutor.getPosition().equals("Lecturer")) {
                num++;
                String tutorId = id;
                String name = tutor.getName();
                String email = tutor.getEmail();
                String phoneNumber = tutor.getPhno();
                System.out.printf(num + "\t%-12s%-15s%-20s%-17s\n", tutorId, name, email, phoneNumber);
            }
        });
        if (num == 0) {
            System.err.println("No Result Found");
        }
    }

    public void displayTutorSem1() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
        System.out.println("--------------------------------------------------------------------------------------------");
        tutorSet1.forEach((id, tutor) -> {
            if (tutorSet1.isEmpty()) {
                System.err.println("No Tutor Found\n");
            }
            num++;
            String tutorId = id;
            String name = tutor.getName();
            String email = tutor.getEmail();
            String phoneNumber = tutor.getPhno();
            String position = tutor.getPosition();
            System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
        }
        );
    }

    public void displayTutorSem2() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
        System.out.println("--------------------------------------------------------------------------------------------");
        tutorSet2.forEach((id, tutor) -> {
            if (tutorSet2.isEmpty()) {
                System.err.println("No Tutor Found\n");
            }
            num++;
            String tutorId = id;
            String name = tutor.getName();
            String email = tutor.getEmail();
            String phoneNumber = tutor.getPhno();
            String position = tutor.getPosition();
            System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
        }
        );
    }

    public void displayTutorSem3() {
        num = 0;
        System.out.println("\tTutor ID    Name           Email             Phone Number       Position");
        System.out.println("--------------------------------------------------------------------------------------------");
        tutorSet3.forEach((id, tutor) -> {
            if (tutorSet3.isEmpty()) {
                System.err.println("No Tutor Found\n");
            }
            num++;
            String tutorId = id;
            String name = tutor.getName();
            String email = tutor.getEmail();
            String phoneNumber = tutor.getPhno();
            String position = tutor.getPosition();
            System.out.printf(num + "\t%-12s%-15s%-20s%-17s%-10s%n", tutorId, name, email, phoneNumber, position);
        }
        );
    }

    public void back0() throws AWTException {
        System.out.println("Press Enter to Return....");
        Scanner scanner = new Scanner(System.in);
        String enter = scanner.nextLine();
        filterTutorPosition();
    }

    public void back1() throws AWTException {
        System.out.println("Press Enter to Return....");
        Scanner scanner = new Scanner(System.in);
        String enter = scanner.nextLine();
        filterTutorMenu();
    }

    public static void tutorManagement() throws AWTException {
        TutorManagement tutorManagement = new TutorManagement();
        tutorManagement.tutorMenu();
    }
}
