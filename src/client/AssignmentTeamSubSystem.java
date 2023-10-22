package client;

import adt.ArraySetList;
import adt.ListInterface;
import adt.SetListInterface;
import entity.Assignment;
import entity.AssignmentTeam;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.awt.AWTException;
import java.util.Iterator;
import java.util.Scanner;
import boundary.AssTeamSubSysUI;
import adt.ArrayQueue;
import adt.LinkedQueue;
import adt.QueueInterface;
import utility.Validation;

/**
 *
 * @author Yap Ying Sin
 */
public class AssignmentTeamSubSystem {
    
    /* NEED TO CONVERT ALL COLLECTIONS TO SET-LIST TYPE */
    private SetListInterface<Course> courseMasterList = new ArraySetList<>();
    private SetListInterface<Programme> progMasterList = new ArraySetList<>();
    private SetListInterface<TutorialGroup> tutGrpMasterList = new ArraySetList<>();
    private SetListInterface<Student> studMasterList = new ArraySetList<>();
    private SetListInterface<AssignmentTeam> assTeamMasterList = new ArraySetList<>();
    private SetListInterface<Assignment> assMasterList = new ArraySetList<>();
    
    private QueueInterface<Course> tempCourseList = new ArrayQueue<>();
    private QueueInterface<TutorialGroup> tempTutGrpList = new LinkedQueue<>();
    
    static Scanner scan = new Scanner(System.in);
    
    public AssignmentTeamSubSystem(QueueInterface<Course> courseMasterList,
                                   ListInterface<Programme> progMasterList,
                                   QueueInterface<TutorialGroup> tutGrpMasterList,
                                   ListInterface<Student> studMasterList,
                                   ListInterface<Assignment> assMasterList) {
        
        tempCourseList = courseMasterList;
        Iterator courseItr = tempCourseList.getIterator();
        while (courseItr.hasNext()) {
            this.courseMasterList.add(tempCourseList.dequeue());
        }
        for (int i = 1; i <= progMasterList.getNumberOfEntries(); i++) {
            this.progMasterList.add(progMasterList.getEntry(i));
        }
        tempTutGrpList = tutGrpMasterList;
        Iterator tutGrpItr = tempTutGrpList.getIterator();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            this.tutGrpMasterList.add(tutGrpEntry);
        }
        
        for (int i = 1; i <= studMasterList.getNumberOfEntries(); i++) {
            this.studMasterList.add(studMasterList.getEntry(i));
        }
        
        tutGrpItr = tutGrpMasterList.getIterator();
        assTeamMasterList.clear();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            Iterator assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
                
                assTeamMasterList.add(assTeamEntry);
            }
        }
        
        for (int i = 1; i <= assMasterList.getNumberOfEntries(); i++) {
            this.assMasterList.add(assMasterList.getEntry(i));
        }
        
        // Initialize courses to each assignment
        assMasterList.getEntry(1).setCourse(this.courseMasterList.getEntry(1));
        assMasterList.getEntry(2).setCourse(this.courseMasterList.getEntry(1));
        assMasterList.getEntry(3).setCourse(this.courseMasterList.getEntry(1));
        
        assMasterList.getEntry(4).setCourse(this.courseMasterList.getEntry(2));
        assMasterList.getEntry(5).setCourse(this.courseMasterList.getEntry(2));
        
        // Initialize assignments to each assignment team
        assTeamMasterList.getEntry(1).getAssList().add(assMasterList.getEntry(1));
        assTeamMasterList.getEntry(1).getAssList().add(assMasterList.getEntry(2));
        assTeamMasterList.getEntry(1).getAssList().add(assMasterList.getEntry(3));
        
        assTeamMasterList.getEntry(2).getAssList().add(assMasterList.getEntry(4));
        assTeamMasterList.getEntry(2).getAssList().add(assMasterList.getEntry(5));
    }
    
    public static void assignmentTeamModule(QueueInterface<Course> courseMasterList,
                                            ListInterface<Programme> progMasterList,
                                            QueueInterface<TutorialGroup> tutGrpMasterList,
                                            ListInterface<Student> studMasterList,
                                            ListInterface<AssignmentTeam> assTeamMasterList,
                                            ListInterface<Assignment> assMasterList) throws AWTException {
        
        AssignmentTeamSubSystem assTeamSubSys = new AssignmentTeamSubSystem(courseMasterList, progMasterList, tutGrpMasterList,
                                                                            studMasterList, assMasterList);
        
        int menuChoice, contYesNo;
        
        do {
            menuChoice = assTeamSubSys.getSubMenuChoice();
            AssTeamSubSysUI.clearScreen();
            
            switch(menuChoice) {
                case 1:
                    do {
                        contYesNo = assTeamSubSys.createTeam();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 2:
                    do {
                        contYesNo = assTeamSubSys.removeTeam();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 3:
                    do {
                        contYesNo = assTeamSubSys.amendTeamDetails();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 4:
                    do {
                        contYesNo = assTeamSubSys.addStudToTeam();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 5:
                    do {
                        contYesNo = assTeamSubSys.removeStudFromTeam();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 6:
                    do {
                        contYesNo = assTeamSubSys.filterTeam();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 7:
                    assTeamSubSys.listAllTeams();
                    AssTeamSubSysUI.pressEnterToCont();
                    AssTeamSubSysUI.clearScreen();
                    
                    break;
                    
                case 8:
                    do {
                        contYesNo = assTeamSubSys.listAllStudUnderTeam();
                    } while (contYesNo != 0);
                    
                    break;
                    
                case 9:
                    do {
                        contYesNo = assTeamSubSys.generateReport();
                    } while (contYesNo != 0);
                    
                    break;
                    
                default:
                    break;
            }
            
        } while (menuChoice != 0);
        
        System.out.println("+---------------------------------------------------+");
        System.out.println("|  Exiting Assignment Team Management Subsystem...  |");
        System.out.println("+---------------------------------------------------+");
        
    }
    
    public int getSubMenuChoice() {
        
        String userInput;
        int menuChoice;
        
        AssTeamSubSysUI.displaySubMenu();       // Fetch sub-menu
        System.out.print("Please select an action: ");
        userInput = scan.nextLine();

        menuChoice = Validation.validateIntegerInput(userInput);

        while (menuChoice < 0 || menuChoice > 9) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            menuChoice = Validation.validateIntegerInput(userInput);
        }
        
        System.out.println();
        
        return menuChoice;
    }
    
    public int createTeam() throws AWTException {
        
        String teamName, userInput;
        int minCapacity, maxCapacity, progChoice, tutGrpChoice;
        Programme selectedProg;
        TutorialGroup selectedTutGrp;
        SetListInterface<TutorialGroup> filteredTutGrpList = new ArraySetList<>();

        AssTeamSubSysUI.displayCreateTeamTitle();
        System.out.println("+---------------------------------------------------+");
        System.out.println("|  INFO: Please fill in the new team's basic info.  |");
        System.out.println("+---------------------------------------------------+");
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        
        System.out.println("New Team ID: " + "T" + (AssignmentTeam.getTeamIDCount() + 1));
        
        /* Get team name */
        System.out.print("Enter Team Name (E.g. Team 1): ");
        teamName = scan.nextLine();
        
        while (teamName.length() < 1 || teamName.length() > 10) {
            if (teamName.length() < 1) {
                System.out.print("Team name cannot be blank. Please re-enter: ");
            }
            else {
                System.out.print("Team name limit is 10 characters. Please re-enter: ");
            }
            teamName = scan.nextLine();
        }
        
        if (teamName.compareTo("0") == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        /* Get min. capacity */
        System.out.print("Enter Min. Capacity (Must have at least 1 student): ");
        userInput = scan.nextLine();
        
        minCapacity = Validation.validateIntegerInput(userInput);
        
        while (minCapacity < 0 || minCapacity > 6) {
            if (minCapacity < 0) {
                System.out.print("Invalid input. Please re-enter: ");
            }
            else {
                System.out.print("A team cannot have more than 6 students. Please re-enter: ");
            }
            userInput = scan.nextLine();
            minCapacity = Validation.validateIntegerInput(userInput);
        }
        
        if (minCapacity == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        /* Get max. capacity */
        System.out.print("Enter Max. Capacity (Cannot have more than 6 students): ");
        userInput = scan.nextLine();
        
        maxCapacity = Validation.validateIntegerInput(userInput);
        
        while ((maxCapacity != 0 && maxCapacity < minCapacity) || maxCapacity < 0 || maxCapacity > 6) {
            if (minCapacity < 0) {
                System.out.print("Invalid input. Please re-enter: ");
            }
            else if (maxCapacity < minCapacity) {
                System.out.print("Max. capacity cannot be less than min. capacity. Please re-enter: ");
            }
            else {
                System.out.print("A team cannot have more than 6 students. Please re-enter: ");
            }
            userInput = scan.nextLine();
            maxCapacity = Validation.validateIntegerInput(userInput);
        }
        
        if (maxCapacity == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        /* Choose a tutorial group */
        System.out.println();
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|  INFO: Please assign the new team to a tutorial group.  |");
        System.out.println("+---------------------------------------------------------+");
        
        /* Get programme selections */
        AssTeamSubSysUI.displayProgList(progMasterList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a programme: ");
        userInput = scan.nextLine();

        progChoice = Validation.validateIntegerInput(userInput);

        while (progChoice < 0 || progChoice > progMasterList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            progChoice = Validation.validateIntegerInput(userInput);
        }

        if (progChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }

        /* Fetch the programme selected */
        selectedProg = progMasterList.getEntry(progChoice);

        /* Filter tutorial groups by selected programme */
        Iterator tutGrpItr = selectedProg.getTutGrpList().getIterator();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            filteredTutGrpList.add(tutGrpEntry);
        }

        /* Get tutorial group selections */
        System.out.println();
        AssTeamSubSysUI.displayFilterByProgInfoBox(selectedProg);
        AssTeamSubSysUI.displayTutGrpList(filteredTutGrpList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a tutorial group: ");
        userInput = scan.nextLine();

        tutGrpChoice = Validation.validateIntegerInput(userInput);

        while (tutGrpChoice < 0 || tutGrpChoice > filteredTutGrpList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            tutGrpChoice = Validation.validateIntegerInput(userInput);
        }

        if (tutGrpChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }
        
        selectedTutGrp = filteredTutGrpList.getEntry(tutGrpChoice);
        
        /* Create new AssignmentTeam instance */
        AssignmentTeam newAssTeam = new AssignmentTeam(teamName, minCapacity, maxCapacity);
        
        /* Add entry into team master list & into selected tutorial group */
        assTeamMasterList.add(newAssTeam);
        
        int index = 0;
        for (int i = 1; i <= tutGrpMasterList.getNumberOfEntries(); i++) {
            if (selectedTutGrp.equals(tutGrpMasterList.getEntry(i))) {
                index = i;
            }
        }
        
        tutGrpMasterList.getEntry(index).getAssTeamList().enqueue(newAssTeam);
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayChangesSuccess();
        
        return 1;
    }
    
    public int removeTeam() throws AWTException {
        
        String userInput;
        int teamChoice, progChoice, tutGrpChoice;
        Programme selectedProg;
        TutorialGroup selectedTutGrp;
        
        SetListInterface<TutorialGroup> filteredTutGrpList = new ArraySetList<>();
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();
        
        /* Get programme selections */
        AssTeamSubSysUI.displayRemoveTeamTitle();
        AssTeamSubSysUI.displayProgList(progMasterList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a programme: ");
        userInput = scan.nextLine();

        progChoice = Validation.validateIntegerInput(userInput);

        while (progChoice < 0 || progChoice > progMasterList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            progChoice = Validation.validateIntegerInput(userInput);
        }

        if (progChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }

        /* Fetch the programme selected */
        selectedProg = progMasterList.getEntry(progChoice);

        /* Filter tutorial groups by selected programme */
        Iterator tutGrpItr = selectedProg.getTutGrpList().getIterator();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            filteredTutGrpList.add(tutGrpEntry);
        }

        /* Get tutorial group selections */
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayRemoveTeamTitle();
        AssTeamSubSysUI.displayFilterByProgInfoBox(selectedProg);
        AssTeamSubSysUI.displayTutGrpList(filteredTutGrpList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a tutorial group: ");
        userInput = scan.nextLine();

        tutGrpChoice = Validation.validateIntegerInput(userInput);

        while (tutGrpChoice < 0 || tutGrpChoice > filteredTutGrpList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            tutGrpChoice = Validation.validateIntegerInput(userInput);
        }

        if (tutGrpChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }

        selectedTutGrp = filteredTutGrpList.getEntry(tutGrpChoice);

        int index = 0;
        for (int i = 1; i <= tutGrpMasterList.getNumberOfEntries(); i++) {
            if (selectedTutGrp.equals(tutGrpMasterList.getEntry(i))) {
                index = i;
            }
        }

        Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        while (assTeamItr.hasNext()) {
            AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
            
            filteredAssTeamList.add(assTeamEntry);
        }
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayRemoveTeamTitle();
        AssTeamSubSysUI.displayFilterByTutGrpInfoBox(selectedTutGrp);
        AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select an assignment team: ");
        userInput = scan.nextLine();

        teamChoice = Validation.validateIntegerInput(userInput);
        
        while (teamChoice < 0 || teamChoice > filteredAssTeamList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            teamChoice = Validation.validateIntegerInput(userInput);
        }
        
        if (teamChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        /* Search & remove the team from the target tutorial group */
        assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        QueueInterface<AssignmentTeam> wantedAssTeamList = new ArrayQueue<>();
        while (assTeamItr.hasNext()) {
            
            AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();
                
            if (!filteredAssTeamList.getEntry(teamChoice).equals(targetAssTeam)) {

                wantedAssTeamList.enqueue(targetAssTeam);
            }
        }
        
        tutGrpMasterList.getEntry(index).setAssTeamList(wantedAssTeamList);

        /* Clear & reenter entire team master list */
        tutGrpItr = tutGrpMasterList.getIterator();
        assTeamMasterList.clear();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
                
                assTeamMasterList.add(assTeamEntry);
            }
        }

        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayChangesSuccess();
        
        return 1;
    }
    
    public int amendTeamDetails() throws AWTException {
        
        String userInput;
        int progChoice, tutGrpChoice, teamChoice, menuChoice, contYesNo = -1;
        Programme selectedProg;
        TutorialGroup selectedTutGrp;
        SetListInterface<TutorialGroup> filteredTutGrpList = new ArraySetList<>();
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();
        
        /* Get programme selections */
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayAmendTeamTitle();
        AssTeamSubSysUI.displayProgList(progMasterList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a programme: ");
        userInput = scan.nextLine();

        progChoice = Validation.validateIntegerInput(userInput);

        while (progChoice < 0 || progChoice > progMasterList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            progChoice = Validation.validateIntegerInput(userInput);
        }

        if (progChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }

        /* Fetch the programme selected */
        selectedProg = progMasterList.getEntry(progChoice);

        /* Filter tutorial groups by selected programme */
        Iterator tutGrpItr = selectedProg.getTutGrpList().getIterator();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            filteredTutGrpList.add(tutGrpEntry);
        }

        /* Get tutorial group selections */
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayAmendTeamTitle();
        AssTeamSubSysUI.displayFilterByProgInfoBox(selectedProg);
        AssTeamSubSysUI.displayTutGrpList(filteredTutGrpList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a tutorial group: ");
        userInput = scan.nextLine();

        tutGrpChoice = Validation.validateIntegerInput(userInput);

        while (tutGrpChoice < 0 || tutGrpChoice > filteredTutGrpList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            tutGrpChoice = Validation.validateIntegerInput(userInput);
        }

        if (tutGrpChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }

        selectedTutGrp = filteredTutGrpList.getEntry(tutGrpChoice);

        int index = 0;
        for (int i = 1; i <= tutGrpMasterList.getNumberOfEntries(); i++) {
            if (selectedTutGrp.equals(tutGrpMasterList.getEntry(i))) {
                index = i;
            }
        }

        Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        while (assTeamItr.hasNext()) {
            AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
            
            filteredAssTeamList.add(assTeamEntry);
        }
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayAmendTeamTitle();
        AssTeamSubSysUI.displayFilterByTutGrpInfoBox(selectedTutGrp);
        AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select an assignment team: ");
        userInput = scan.nextLine();

        teamChoice = Validation.validateIntegerInput(userInput);
        
        while (teamChoice < 0 || teamChoice > filteredAssTeamList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            teamChoice = Validation.validateIntegerInput(userInput);
        }
        
        if (teamChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        /* Search & locate the team from the target tutorial group */
        int i = 1, position = 1;
        assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        while (assTeamItr.hasNext()) {
            
            AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();
                
            if (filteredAssTeamList.getEntry(teamChoice).equals(targetAssTeam)) {

                position = i;
            }
            else {
                i++;
            }
        }
        
        AssTeamSubSysUI.clearScreen();
        
        do {
            /* Show assignment team details */
            AssTeamSubSysUI.displayAmendTeamTitle();
            //AssTeamSubSysUI.displayAssTeamDetails(assTeamMasterList.getEntry(teamChoice));
            AssTeamSubSysUI.displayAssTeamDetails(filteredAssTeamList.getEntry(teamChoice));

            /* Get field to amend */
            AssTeamSubSysUI.displayAmendmentSubMenu();
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a field to amend: ");
            userInput = scan.nextLine();

            menuChoice = Validation.validateIntegerInput(userInput);

            while (menuChoice < 0 || menuChoice > 3) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                menuChoice = Validation.validateIntegerInput(userInput);
            }

            if (menuChoice == 0) {
                System.out.println();
                AssTeamSubSysUI.displayChangesNotSuccess();
                System.out.println();

                return 0;
            }
            
            AssTeamSubSysUI.clearScreen();
            
            switch(menuChoice) {
                case 1:
                    AssTeamSubSysUI.displayAmendTeamName();
                    //AssTeamSubSysUI.displayAssTeamDetails(assTeamMasterList.getEntry(teamChoice));
                    AssTeamSubSysUI.displayAssTeamDetails(filteredAssTeamList.getEntry(teamChoice));
                    AssTeamSubSysUI.displayActionToReturnPrevScr();
                    System.out.println();
                    contYesNo = changeTeamName(index, position, filteredAssTeamList.getEntry(teamChoice));

                    break;

                case 2:
                    AssTeamSubSysUI.displayAmendMinCap();
                    //AssTeamSubSysUI.displayAssTeamDetails(assTeamMasterList.getEntry(teamChoice));
                    AssTeamSubSysUI.displayAssTeamDetails(filteredAssTeamList.getEntry(teamChoice));
                    AssTeamSubSysUI.displayActionToReturnPrevScr();
                    System.out.println();
                    contYesNo = changeMinCapacity(index, position, filteredAssTeamList.getEntry(teamChoice));

                    break;

                case 3:
                    AssTeamSubSysUI.displayAmendMaxCap();
                    //AssTeamSubSysUI.displayAssTeamDetails(assTeamMasterList.getEntry(teamChoice));
                    AssTeamSubSysUI.displayAssTeamDetails(filteredAssTeamList.getEntry(teamChoice));
                    AssTeamSubSysUI.displayActionToReturnPrevScr();
                    System.out.println();
                    contYesNo = changeMaxCapacity(index, position, filteredAssTeamList.getEntry(teamChoice));

                    break;

                default:
                    break;
            }
            
        } while (contYesNo != 0);
        
        return 1;
    }
    
    /* START: Sub-methods for team details amendment */
    
    public int changeTeamName(int index, int position, AssignmentTeam assTeam) throws AWTException {
        
        String newName;
        
        System.out.print("Enter New Team Name (E.g. Team 1): ");
        newName = scan.nextLine();
        
        while (newName.compareTo(assTeam.getTeamName()) == 0 || newName.length() < 1 || newName.length() > 10) {
            if (newName.length() < 1) {
                System.out.print("Team name cannot be blank. Please re-enter: ");
            }
            else if (newName.length() > 10) {
                System.out.print("Team name limit is 10 characters. Please re-enter: ");
            }
            else {
                System.out.print("New team name cannot be identical to the old team name. Please re-enter: ");
            }
            newName = scan.nextLine();
        }
        
        if (newName.compareTo("0") == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        QueueInterface<AssignmentTeam> wantedAssTeamList = new ArrayQueue<>();
        while (assTeamItr.hasNext()) {
            
            AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();
                
            if (!assTeam.equals(targetAssTeam)) {

                wantedAssTeamList.enqueue(targetAssTeam);
            }
            else {
                targetAssTeam.setTeamName(newName);
                wantedAssTeamList.enqueue(targetAssTeam);
            }
        }
        
        tutGrpMasterList.getEntry(index).setAssTeamList(wantedAssTeamList);
        
        /* Clear & reenter entire team master list */
        Iterator tutGrpItr = tutGrpMasterList.getIterator();
        assTeamMasterList.clear();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
                
                assTeamMasterList.add(assTeamEntry);
            }
        }
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayChangesSuccess();
        
        return 1;
    }
    
    public int changeMinCapacity(int index, int position, AssignmentTeam assTeam) throws AWTException {
        
        String userInput;
        int newMin;
        
        System.out.print("Enter New Min. Capacity (Must have at least 1 student): ");
        userInput = scan.nextLine();
        
        newMin = Validation.validateIntegerInput(userInput);
        
        if (newMin == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        while (newMin == assTeam.getMinCapacity() || newMin < 0 || newMin > 6 || newMin > assTeam.getMaxCapacity()) {
            if (newMin < 0) {
                System.out.print("Invalid input. Please re-enter: ");
            }
            else if (newMin > 6) {
                System.out.print("A team cannot have more than 6 students. Please re-enter: ");
            }
            else if (newMin > assTeam.getMaxCapacity()) {
                System.out.print("New min. capacity cannot be more than max. capacity. Please re-enter: ");
            }
            else {
                System.out.print("New min. capacity cannot be identical to the old min. capacity. Please re-enter: ");
            }
            userInput = scan.nextLine();
            newMin = Validation.validateIntegerInput(userInput);
        }
        
        Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        QueueInterface<AssignmentTeam> wantedAssTeamList = new ArrayQueue<>();
        while (assTeamItr.hasNext()) {
            
            AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();
                
            if (!assTeam.equals(targetAssTeam)) {

                wantedAssTeamList.enqueue(targetAssTeam);
            }
            else {
                targetAssTeam.setMinCapacity(newMin);
                wantedAssTeamList.enqueue(targetAssTeam);
            }
        }
        
        tutGrpMasterList.getEntry(index).setAssTeamList(wantedAssTeamList);
        
        /* Clear & reenter entire team master list */
        Iterator tutGrpItr = tutGrpMasterList.getIterator();
        assTeamMasterList.clear();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
                
                assTeamMasterList.add(assTeamEntry);
            }
        }
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayChangesSuccess();
        
        return 1;
    }
    
    public int changeMaxCapacity(int index, int position, AssignmentTeam assTeam) throws AWTException {
        
        String userInput;
        int newMax;
        
        System.out.print("Enter New Max. Capacity (Cannot have more than 6 students): ");
        userInput = scan.nextLine();
        
        newMax = Validation.validateIntegerInput(userInput);
        
        if (newMax == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();
            
            return 0;
        }
        
        while (newMax == assTeam.getMaxCapacity() || newMax < assTeam.getMinCapacity() || newMax < 0 || newMax > 6) {
            if (newMax < 0) {
                System.out.print("Invalid input. Please re-enter: ");
            }
            else if (newMax > 6) {
                System.out.print("A team cannot have more than 6 students. Please re-enter: ");
            }
            else if (newMax == assTeam.getMaxCapacity()) {
                System.out.print("New max. capacity cannot be identical to the old min. capacity. Please re-enter: ");
            }
            else {
                System.out.print("New max. capacity cannot be less than min. capacity. Please re-enter: ");
            }
            userInput = scan.nextLine();
            newMax = Validation.validateIntegerInput(userInput);
        }
        
        Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        QueueInterface<AssignmentTeam> wantedAssTeamList = new ArrayQueue<>();
        while (assTeamItr.hasNext()) {
            
            AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();
                
            if (!assTeam.equals(targetAssTeam)) {

                wantedAssTeamList.enqueue(targetAssTeam);
            }
            else {
                targetAssTeam.setMaxCapacity(newMax);
                wantedAssTeamList.enqueue(targetAssTeam);
            }
        }
        
        tutGrpMasterList.getEntry(index).setAssTeamList(wantedAssTeamList);
        
        /* Clear & reenter entire team master list */
        Iterator tutGrpItr = tutGrpMasterList.getIterator();
        assTeamMasterList.clear();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
                
                assTeamMasterList.add(assTeamEntry);
            }
        }
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayChangesSuccess();
        
        return 1;
    }
    
    /* END: Sub-methods for team details amendment */
    
    public int addStudToTeam() throws AWTException {
        
        String userInput;
        int progChoice, tutGrpChoice, teamChoice, studChoice, index, position;
        boolean contYesNo1, contYesNo2;
        AssignmentTeam selectedAssTeam = new AssignmentTeam();
        Programme selectedProg;
        TutorialGroup selectedTutGrp = null;
        Student selectedStud = new Student();
        SetListInterface<Student> filteredStudList = new ArraySetList<>();
        SetListInterface<TutorialGroup> filteredTutGrpList = new ArraySetList<>();
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();
        
        do {
            /* Get programme selections */
            AssTeamSubSysUI.displayAddStudTitle();
            AssTeamSubSysUI.displayProgList(progMasterList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a programme: ");
            userInput = scan.nextLine();

            progChoice = Validation.validateIntegerInput(userInput);

            while (progChoice < 0 || progChoice > progMasterList.getNumberOfEntries()) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                progChoice = Validation.validateIntegerInput(userInput);
            }

            if (progChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayChangesNotSuccess();

                return 0;
            }

            /* Fetch the programme selected */
            selectedProg = progMasterList.getEntry(progChoice);

            /* Filter tutorial groups by selected programme */
            Iterator tutGrpItr = selectedProg.getTutGrpList().getIterator();
            while (tutGrpItr.hasNext()) {
                TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();

                filteredTutGrpList.add(tutGrpEntry);
            }

            /* Get tutorial group selections */
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayAddStudTitle();
            AssTeamSubSysUI.displayFilterByProgInfoBox(selectedProg);
            AssTeamSubSysUI.displayTutGrpList(filteredTutGrpList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a tutorial group: ");
            userInput = scan.nextLine();

            tutGrpChoice = Validation.validateIntegerInput(userInput);

            while (tutGrpChoice < 0 || tutGrpChoice > filteredTutGrpList.getNumberOfEntries()) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                tutGrpChoice = Validation.validateIntegerInput(userInput);
            }

            if (tutGrpChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayChangesNotSuccess();

                return 0;
            }

            selectedTutGrp = filteredTutGrpList.getEntry(tutGrpChoice);

            index = 0;
            for (int i = 1; i <= tutGrpMasterList.getNumberOfEntries(); i++) {
                if (selectedTutGrp.equals(tutGrpMasterList.getEntry(i))) {
                    index = i;
                }
            }

            Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();

                filteredAssTeamList.add(assTeamEntry);
            }
            
            /* Ask user which team to add student(s) */
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayAddStudTitle();
            AssTeamSubSysUI.displayFilterByTutGrpInfoBox(selectedTutGrp);
            AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a team: ");
            userInput = scan.nextLine();

            teamChoice = Validation.validateIntegerInput(userInput);

            while (teamChoice < 0 || teamChoice > filteredAssTeamList.getNumberOfEntries()) {

                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                teamChoice = Validation.validateIntegerInput(userInput);
            }

            if (teamChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayChangesNotSuccess();
                
                return 0;
            }
            
            /* Search & locate the team from the target tutorial group */
            int i = 1;
            position = 1;
            assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {

                AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();

                if (filteredAssTeamList.getEntry(teamChoice).equals(targetAssTeam)) {

                    position = i;
                }
                else {
                    i++;
                }
            }
            
            /* Fetch selected assignment team */
            selectedAssTeam = filteredAssTeamList.getEntry(teamChoice);

            if (selectedAssTeam.getMaxCapacity() == selectedAssTeam.getStudList().getNumberOfEntries()) {
                AssTeamSubSysUI.clearScreen();
                System.out.println("+--------------------------------------------------------------------+");
                System.out.println("|  INFO: This team is already full. Please select a different team.  |");
                System.out.println("+--------------------------------------------------------------------+");
                System.out.println();

                contYesNo1 = true;
            }
            else {
                contYesNo1 = false;
            }
        } while (contYesNo1);
        
        if (selectedTutGrp == null) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayAddStudTitle();
            
            System.out.println("+---------------------------------------------------------------------------------------------+");
            System.out.println("|  ERROR: This team has not been assigned a tutorial group. Unable to retrieve student list.  |");
            System.out.println("+---------------------------------------------------------------------------------------------+");
            System.out.println();
            
            return 0;
        }
        else {
            filteredStudList.clear();
            
            Iterator studItr = tutGrpMasterList.getEntry(index).getStudList().getIterator();
            while (studItr.hasNext()) {
                Student studEntry = (Student) studItr.next();

                filteredStudList.add(studEntry);
            }
            
            do {
                /* Get student selections */
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayFilterByTutGrpInfoBox(selectedTutGrp);
                AssTeamSubSysUI.displayStudList(filteredStudList);
                AssTeamSubSysUI.displayActionToReturnPrevScr();
                System.out.println();
                System.out.print("Please select a student: ");
                userInput = scan.nextLine();

                studChoice = Validation.validateIntegerInput(userInput);

                /* Check if the selected student is already in a team */
                while (studChoice < 0 || studChoice > filteredStudList.getNumberOfEntries()) {
                    System.out.print("Invalid input. Please re-enter: ");
                    userInput = scan.nextLine();

                    studChoice = Validation.validateIntegerInput(userInput);
                }

                if (studChoice == 0) {
                    AssTeamSubSysUI.clearScreen();
                    AssTeamSubSysUI.displayChangesNotSuccess();

                    return 0;
                }

                selectedStud = filteredStudList.getEntry(studChoice);

                if (isAssignedUnderOtherTeam(filteredStudList.getEntry(studChoice))) {
                    System.out.println();
                    System.out.println("+----------------------------------------------------------------------------------------+");
                    System.out.println("|  This student is already assigned to another team. Please select a different student.  |");
                    System.out.println("+----------------------------------------------------------------------------------------+");
                    System.out.println();

                    contYesNo2 = true;
                }
                else {
                    contYesNo2 = false;
                }

            } while (contYesNo2);
            
            Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
            QueueInterface<AssignmentTeam> wantedAssTeamList = new ArrayQueue<>();
            while (assTeamItr.hasNext()) {

                AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();

                if (!selectedAssTeam.equals(targetAssTeam)) {

                    wantedAssTeamList.enqueue(targetAssTeam);
                }
                else {
                    targetAssTeam.getStudList().add(selectedStud);
                    wantedAssTeamList.enqueue(targetAssTeam);
                }
            }
            
            tutGrpMasterList.getEntry(index).setAssTeamList(wantedAssTeamList);
            
            /* Clear & reenter entire team master list */
            Iterator tutGrpItr = tutGrpMasterList.getIterator();
            assTeamMasterList.clear();
            while (tutGrpItr.hasNext()) {
                TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();

                assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
                while (assTeamItr.hasNext()) {
                    AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();

                    assTeamMasterList.add(assTeamEntry);
                }
            }

            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesSuccess();
        }
        
        return 1;
    }
    
    /* START: Sub-methods for add student to assignment team */
    
    public boolean isAssignedUnderOtherTeam(Student student) {
        
        for (int i = 1; i <= assTeamMasterList.getNumberOfEntries(); i++) {
            AssignmentTeam teamEntry = assTeamMasterList.getEntry(i);
            SetListInterface<Student> studList = teamEntry.getStudList();
            
            for (int j = 1; j <= studList.getNumberOfEntries(); j++) {
                if (studList.getEntry(j).equals(student)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int removeStudFromTeam() throws AWTException {
        
        String userInput;
        int progChoice, tutGrpChoice, teamChoice, studChoice, index, position, position2;
        boolean contYesNo;
        Programme selectedProg;
        TutorialGroup selectedTutGrp;
        AssignmentTeam selectedTeam;
        SetListInterface<TutorialGroup> filteredTutGrpList = new ArraySetList<>();
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();
        SetListInterface<Student> filteredStudList = new ArraySetList<>();
        
        do {
            filteredTutGrpList.clear();
            filteredAssTeamList.clear();
            filteredStudList.clear();
            
            /* Get programme selections */
            AssTeamSubSysUI.displayRemoveStudTitle();
            AssTeamSubSysUI.displayProgList(progMasterList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a programme: ");
            userInput = scan.nextLine();

            progChoice = Validation.validateIntegerInput(userInput);

            while (progChoice < 0 || progChoice > progMasterList.getNumberOfEntries()) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                progChoice = Validation.validateIntegerInput(userInput);
            }

            if (progChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayChangesNotSuccess();

                return 0;
            }

            /* Fetch the programme selected */
            selectedProg = progMasterList.getEntry(progChoice);

            /* Filter tutorial groups by selected programme */
            Iterator tutGrpItr = selectedProg.getTutGrpList().getIterator();
            while (tutGrpItr.hasNext()) {
                TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();

                filteredTutGrpList.add(tutGrpEntry);
            }

            /* Get tutorial group selections */
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayRemoveStudTitle();
            AssTeamSubSysUI.displayFilterByProgInfoBox(selectedProg);
            AssTeamSubSysUI.displayTutGrpList(filteredTutGrpList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a tutorial group: ");
            userInput = scan.nextLine();

            tutGrpChoice = Validation.validateIntegerInput(userInput);

            while (tutGrpChoice < 0 || tutGrpChoice > filteredTutGrpList.getNumberOfEntries()) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                tutGrpChoice = Validation.validateIntegerInput(userInput);
            }

            if (tutGrpChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayChangesNotSuccess();

                return 0;
            }

            selectedTutGrp = filteredTutGrpList.getEntry(tutGrpChoice);

            index = 0;
            for (int i = 1; i <= tutGrpMasterList.getNumberOfEntries(); i++) {
                if (selectedTutGrp.equals(tutGrpMasterList.getEntry(i))) {
                    index = i;
                }
            }
            
            Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();

                filteredAssTeamList.add(assTeamEntry);
            }
            
            /* Ask user which team to remove student */
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayRemoveStudTitle();
            AssTeamSubSysUI.displayFilterByTutGrpInfoBox(selectedTutGrp);
            AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a team: ");
            userInput = scan.nextLine();

            teamChoice = Validation.validateIntegerInput(userInput);

            while (teamChoice < 0 || teamChoice > assTeamMasterList.getNumberOfEntries()) {

                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                teamChoice = Validation.validateIntegerInput(userInput);
            }

            if (teamChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayChangesNotSuccess();
                
                return 0;
            }
            
            /* Search & locate the team from the target tutorial group */
            int i = 1;
            position = 1;
            assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {

                AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();

                if (filteredAssTeamList.getEntry(teamChoice).equals(targetAssTeam)) {

                    position = i;
                }
                else {
                    i++;
                }
            }

            /* Search for the team from the master list */
            position2 = 1;
            for (i = 1; i <= assTeamMasterList.getNumberOfEntries(); i++) {
                if (assTeamMasterList.getEntry(i).equals(filteredAssTeamList.getEntry(teamChoice))) {
                    position2 = i;
                }
            }

            if (filteredAssTeamList.getEntry(teamChoice).getStudList().getNumberOfEntries() == 0) {
                AssTeamSubSysUI.clearScreen();
                System.out.println("+-----------------------------------------------------------------------------+");
                System.out.println("|  This team does not have any students yet. Please select a different team.  |");
                System.out.println("+-----------------------------------------------------------------------------+");
                System.out.println();

                contYesNo = true;
            }
            else {
                
                contYesNo = false;
            }
            
        } while (contYesNo);
        
        /* Fetch the team selected */
        selectedTeam = filteredAssTeamList.getEntry(teamChoice);

        /* Filter students by selected team */
        for (int i = 1; i <= selectedTeam.getStudList().getNumberOfEntries(); i++) {
            filteredStudList.add(selectedTeam.getStudList().getEntry(i));
        }
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayFilterByAssTeamInfoBox(selectedTeam);
        AssTeamSubSysUI.displayStudList(filteredStudList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a student: ");
        userInput = scan.nextLine();
        
        studChoice = Validation.validateIntegerInput(userInput);
        
        while (studChoice < 0 || studChoice > filteredStudList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            studChoice = Validation.validateIntegerInput(userInput);
        }

        if (studChoice == 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayChangesNotSuccess();

            return 0;
        }
        
        /* Remove the selected student */
        assTeamMasterList.clear();
        Iterator tutGrpItr = tutGrpMasterList.getIterator();
        while (tutGrpItr.hasNext()) {
            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();
            
            Iterator assTeamItr = tutGrpEntry.getAssTeamList().getIterator();
            while (assTeamItr.hasNext()) {
                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();
                
                assTeamMasterList.add(assTeamEntry);
            }
        }
        
        Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
        QueueInterface<AssignmentTeam> wantedAssTeamList = new ArrayQueue<>();
        while (assTeamItr.hasNext()) {

            AssignmentTeam targetAssTeam = (AssignmentTeam) assTeamItr.next();

            if (!assTeamMasterList.getEntry(position2).equals(targetAssTeam)) {

                wantedAssTeamList.enqueue(targetAssTeam);
            }
            else {
                targetAssTeam.getStudList().remove(studChoice);
                wantedAssTeamList.enqueue(targetAssTeam);
            }
        }

        tutGrpMasterList.getEntry(index).setAssTeamList(wantedAssTeamList);
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayChangesSuccess();
        
        return 1;
    }
    
    public int filterTeam() throws AWTException {
        
        String userInput, keyword = "";
        int menuChoice, progChoice, tutGrpChoice;
        Programme selectedProg;
        TutorialGroup selectedTutGrp;
        SetListInterface<TutorialGroup> filteredTutGrpList = new ArraySetList<>();
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();

        do {
            filteredTutGrpList.clear();
            filteredAssTeamList.clear();
            
            AssTeamSubSysUI.displayFilterTeamTitle();
            AssTeamSubSysUI.displayFilterSubMenu();
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select a filter criteria: ");
            userInput = scan.nextLine();

            menuChoice = Validation.validateIntegerInput(userInput);

            while (menuChoice < 0 || menuChoice > 3) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                menuChoice = Validation.validateIntegerInput(userInput);
            }

            if (menuChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                return 0;
            }
            
            AssTeamSubSysUI.clearScreen();
            
            switch (menuChoice) {
                case 1:
                    AssTeamSubSysUI.displayFilterByTeamID();
                    
                    AssTeamSubSysUI.displayActionToReturnPrevScr();
                    System.out.println();
                    System.out.print("Please enter a keyword: ");
                    keyword = scan.nextLine();
                    
                    if (keyword.compareTo("0") != 0) {
                        filterTeamIDByKeyword(keyword);
                    }
                    
                    break;

                case 2:
                    AssTeamSubSysUI.displayFilterByTeamName();
                    
                    AssTeamSubSysUI.displayActionToReturnPrevScr();
                    System.out.println();
                    System.out.print("Please enter a keyword: ");
                    keyword = scan.nextLine();
                    
                    if (keyword.compareTo("0") != 0) {
                        filterTeamNameByKeyword(keyword);
                    }
                    
                    break;

                case 3:
                    AssTeamSubSysUI.displayFilterByTutGrp();
                    
                    /* Get programme selections */
                    AssTeamSubSysUI.displayProgList(progMasterList);
                    AssTeamSubSysUI.displayActionToReturnPrevScr();
                    System.out.println();
                    System.out.print("Please select a programme: ");
                    userInput = scan.nextLine();

                    progChoice = Validation.validateIntegerInput(userInput);

                    while (progChoice < 0 || progChoice > progMasterList.getNumberOfEntries()) {
                        System.out.print("Invalid input. Please re-enter: ");
                        userInput = scan.nextLine();

                        progChoice = Validation.validateIntegerInput(userInput);
                    }

                    if (progChoice == 0) {
                        AssTeamSubSysUI.clearScreen();
                        keyword = "0";
                    }
                    else {
                        selectedProg = progMasterList.getEntry(progChoice);
                        
                        /* Filter tutorial groups by selected programme */
                        Iterator tutGrpItr = selectedProg.getTutGrpList().getIterator();
                        while (tutGrpItr.hasNext()) {
                            TutorialGroup tutGrpEntry = (TutorialGroup) tutGrpItr.next();

                            filteredTutGrpList.add(tutGrpEntry);
                        }
                        
                        /* Get tutorial group selections */
                        AssTeamSubSysUI.displayFilterByTutGrp();
                        AssTeamSubSysUI.clearScreen();
                        AssTeamSubSysUI.displayFilterByProgInfoBox(progMasterList.getEntry(progChoice));
                        AssTeamSubSysUI.displayTutGrpList(filteredTutGrpList);
                        AssTeamSubSysUI.displayActionToReturnPrevScr();
                        System.out.println();
                        System.out.print("Please select a tutorial group: ");
                        userInput = scan.nextLine();

                        tutGrpChoice = Validation.validateIntegerInput(userInput);

                        while (tutGrpChoice < 0 || tutGrpChoice > filteredTutGrpList.getNumberOfEntries()) {
                            System.out.print("Invalid input. Please re-enter: ");
                            userInput = scan.nextLine();

                            tutGrpChoice = Validation.validateIntegerInput(userInput);
                        }

                        if (tutGrpChoice == 0) {
                            AssTeamSubSysUI.clearScreen();
                            keyword = "0";
                        }
                        else {
                            selectedTutGrp = filteredTutGrpList.getEntry(tutGrpChoice);
                            
                            int index = 0;
                            for (int i = 1; i <= tutGrpMasterList.getNumberOfEntries(); i++) {
                                if (selectedTutGrp.equals(tutGrpMasterList.getEntry(i))) {
                                    index = i;
                                }
                            }
                            
                            Iterator assTeamItr = tutGrpMasterList.getEntry(index).getAssTeamList().getIterator();
                            while (assTeamItr.hasNext()) {
                                AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamItr.next();

                                filteredAssTeamList.add(assTeamEntry);
                            }

                            AssTeamSubSysUI.clearScreen();
                            AssTeamSubSysUI.displayFilterByTutGrp();
                            AssTeamSubSysUI.displayFilterByTutGrpInfoBox(tutGrpMasterList.getEntry(index));
                            AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
                            AssTeamSubSysUI.pressEnterToCont();
                            AssTeamSubSysUI.clearScreen();
                        }
                    }
                    
                    break;
                    
                default:
                    break;
            }
            
        } while (keyword.compareTo("0") != 0);
        
        AssTeamSubSysUI.clearScreen();
        
        return 1;
    }
    
    /* START: Sub-methods for filter assignment teams */
    
    public void filterTeamIDByKeyword(String teamIDKey) throws AWTException {
        
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();
        
        Iterator assTeamListItr = assTeamMasterList.getIterator();
        while(assTeamListItr.hasNext()) {
            AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamListItr.next();
            
            if (assTeamEntry.getTeamID().toLowerCase().contains(teamIDKey.toLowerCase())) {
                filteredAssTeamList.add(assTeamEntry);
            }
        }
        
        System.out.println();
        AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
        AssTeamSubSysUI.pressEnterToCont();
        AssTeamSubSysUI.clearScreen();
    }
    
    public void filterTeamNameByKeyword(String teamNameKey) throws AWTException {
        
        SetListInterface<AssignmentTeam> filteredAssTeamList = new ArraySetList<>();
        
        Iterator assTeamListItr = assTeamMasterList.getIterator();
        while(assTeamListItr.hasNext()) {
            AssignmentTeam assTeamEntry = (AssignmentTeam) assTeamListItr.next();
            
            if (assTeamEntry.getTeamName().toLowerCase().contains(teamNameKey.toLowerCase())) {
                filteredAssTeamList.add(assTeamEntry);
            }
        }
        
        System.out.println();
        AssTeamSubSysUI.displayAssTeamList(filteredAssTeamList);
        AssTeamSubSysUI.pressEnterToCont();
        AssTeamSubSysUI.clearScreen();
    }
    
    /* END: Sub-methods for filter assignment team */
    
    
    public void listAllTeams() {
        AssTeamSubSysUI.displayListTeamTitle();
        AssTeamSubSysUI.displayAssTeamList(assTeamMasterList);
    }
    
    public int listAllStudUnderTeam() throws AWTException {
        
        String userInput;
        int teamChoice;
        boolean contYesNo;
        
        do {
            AssTeamSubSysUI.displayListTeamStudTitle();
            AssTeamSubSysUI.displayAssTeamList(assTeamMasterList);
            AssTeamSubSysUI.displayActionToReturnPrevScr();
            System.out.println();
            System.out.print("Please select an assignment team: ");
            userInput = scan.nextLine();

            teamChoice = Validation.validateIntegerInput(userInput);

            while (teamChoice < 0 || teamChoice > assTeamMasterList.getNumberOfEntries()) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                teamChoice = Validation.validateIntegerInput(userInput);
            }
            
            if (teamChoice != 0) {
                AssTeamSubSysUI.clearScreen();
                AssTeamSubSysUI.displayListTeamStudTitle();
                AssTeamSubSysUI.displayFilterByAssTeamInfoBox(assTeamMasterList.getEntry(teamChoice));
                AssTeamSubSysUI.displayStudList(assTeamMasterList.getEntry(teamChoice).getStudList());
                AssTeamSubSysUI.pressEnterToCont();
                
                contYesNo = true; 
            }
            else {
                contYesNo = false;
            }
            
            AssTeamSubSysUI.clearScreen();
            
        } while (contYesNo);
        
        return teamChoice;
    }
    
    public int generateReport() throws AWTException {
        
        String userInput;
        int menuChoice;
        
        do {
            AssTeamSubSysUI.displayGenReportTitle();
            AssTeamSubSysUI.displayReportSubMenu();
            System.out.println();
            System.out.print("Please select a report: ");
            userInput = scan.nextLine();

            menuChoice = Validation.validateIntegerInput(userInput);

            while (menuChoice < 0 || menuChoice > 2) {
                System.out.print("Invalid input. Please re-enter: ");
                userInput = scan.nextLine();

                menuChoice = Validation.validateIntegerInput(userInput);
            }

            if (menuChoice == 0) {
                AssTeamSubSysUI.clearScreen();
                return 0;
            }
            
            switch(menuChoice) {
                case 1:
                    generateOutstandingStudReport();
                    
                    break;
                    
                case 2:
                    generateAssStatusReport();
                    
                    break;
                    
                default:
                    break;
            }
            
        } while (menuChoice != 0);
        
        return 1;
    }
    
    public void generateOutstandingStudReport() throws AWTException {
        
        SetListInterface<Student> assTeamStudList = new ArraySetList<>();
        SetListInterface<Student> filteredStudList = new ArraySetList<>();
        
        for (int i = 1; i <= assTeamMasterList.getNumberOfEntries(); i++) {
            for (int j = 1; j <= assTeamMasterList.getEntry(i).getStudList().getNumberOfEntries(); j++) {
                assTeamStudList.union(assTeamMasterList.getEntry(i).getStudList());
            }
        }
        
        filteredStudList = studMasterList.difference(assTeamStudList);
        
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayGenOutStudReport();
        
        if (!filteredStudList.isEmpty()) {
            System.out.println("+-----------------------------------------------------------------------------+");
            System.out.println("|  REMINDER: Kindly inform the students below to form their assignment teams  |");
            System.out.println("+-----------------------------------------------------------------------------+");
            AssTeamSubSysUI.displayStudList(filteredStudList);
        
        }
        else {
            System.out.println("+--------------------------------------------------------------------+");
            System.out.println("|  INFO: Currently no outstanding students without assignment teams  |");
            System.out.println("+--------------------------------------------------------------------+");
        }
        
        AssTeamSubSysUI.pressEnterToCont();
        AssTeamSubSysUI.clearScreen();
    }
    
    public void generateAssStatusReport() throws AWTException {
        
        String userInput;
        int teamChoice;
        
        /* Filter out assignments based on team */
        AssTeamSubSysUI.clearScreen();
        AssTeamSubSysUI.displayGenAssStatusReport();
        AssTeamSubSysUI.displayAssTeamList(assTeamMasterList);
        AssTeamSubSysUI.displayActionToReturnPrevScr();
        System.out.println();
        System.out.print("Please select a team: ");
        userInput = scan.nextLine();

        teamChoice = Validation.validateIntegerInput(userInput);

        while (teamChoice < 0 || teamChoice > assTeamMasterList.getNumberOfEntries()) {
            System.out.print("Invalid input. Please re-enter: ");
            userInput = scan.nextLine();

            teamChoice = Validation.validateIntegerInput(userInput);
        }

        if (teamChoice != 0) {
            AssTeamSubSysUI.clearScreen();
            AssTeamSubSysUI.displayGenAssStatusReport();
            
            if (!assTeamMasterList.getEntry(teamChoice).getAssList().isEmpty()) {
                AssTeamSubSysUI.displayFilterByAssTeamInfoBox(assTeamMasterList.getEntry(teamChoice));
                AssTeamSubSysUI.displayAssProgList(assTeamMasterList.getEntry(teamChoice).getAssList());
            }
            else {
                
                System.out.println("+--------------------------------------------------------+");
                System.out.println("|  INFO: Currently no assignments assigned to this team  |");
                System.out.println("+--------------------------------------------------------+");
            }
            
            AssTeamSubSysUI.pressEnterToCont();
            AssTeamSubSysUI.clearScreen();
        }
        else {
            AssTeamSubSysUI.clearScreen();
        }
        
    }
    
}
