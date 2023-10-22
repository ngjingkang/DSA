package client;
import adt.LinkedList;
import adt.LinkedQueue;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Course;
import entity.Programme;
import entity.TutorialGroup;
import java.util.Scanner;

import java.util.Iterator;
/**
 *
 * @author Lee Ee Zian
 */
public class ProgrammeSubSystem {
    
    public static ListInterface<Programme> programmeModule(ListInterface<Programme> progMasterListIN,QueueInterface<TutorialGroup> tutGrpListGroupIN,QueueInterface<Course> courseMasterListIN){
        ListInterface<Programme> progMasterList = progMasterListIN;
        QueueInterface<TutorialGroup> tutGrpListGroup = tutGrpListGroupIN;
        QueueInterface<Course> courseMasterList = courseMasterListIN;
        int choice;
        
        do{
            choice = programmeMenu();
            switch (choice) {
                case 0:
                    System.out.println("\nExited Programme Module\n");
                    break;                   
                case 1:
                    addProgramme(progMasterList);                
                    break;
                case 2:
                    programmeRemove(progMasterList,courseMasterList);
                    break;
                case 3:
                    viewProgramme(progMasterList);
                    break;
                case 4:
                    editProgramme(progMasterList);
                    break;
                case 5:
                    programmeAllDetail(progMasterList);
                    break;
                case 6:
                    addGroupToProgramme(progMasterList,tutGrpListGroup);                    
                    break;
                case 7:
                    removeGroupFromProgramme(progMasterList);
                    break;
                case 8:
                    clearAllTutorialFromProgramme(progMasterList);
                    break;
                case 9:
                    generateProgrammeReport(progMasterList);     
                    break;
                case 10:
                    clearAllProgramme(progMasterList);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
   }
    }while(choice !=0);
        return progMasterList;
}
    public static int programmeMenu(){


        System.out.print("\nEnter Number: ");
        Scanner scanner = new Scanner(System.in);
        int entered = scanner.nextInt();
        
        return entered;
        
    }
    public static void addProgramme(ListInterface<Programme> progMasterList){
        String confirm = "";
        Programme newProgramme = new Programme();

        do{
        System.out.println("----------------------------------------------");
        System.out.println("\nAdd new programme\n");
        System.out.print("Enter new Programme ID: ");
        Scanner scanner = new Scanner(System.in);
        String entered = scanner.nextLine();
        if(containsID(progMasterList, entered)){
            System.out.println("\n----------------------------------------------");
            System.out.println("\nID already Exists\n");
        }else{
        newProgramme.setProgID(entered);
        System.out.print("Enter new Programme Name: ");      
        entered = scanner.nextLine();
        newProgramme.setProgName(entered);
        System.out.print("Enter new Programme Faculty: ");      
        entered = scanner.nextLine();
        newProgramme.setFaculty(entered);
        System.out.print("Enter new Programme Certificate: ");      
        entered = scanner.nextLine();
        newProgramme.setCertificate(entered);
        System.out.print("Enter new Programme Duration: ");      
        int enteredInt = scanner.nextInt();
        
        scanner.nextLine();
        newProgramme.setDuration(enteredInt);
        System.out.print("Enter new Programme Total Credit Hours: ");      
        enteredInt = scanner.nextInt();
        
        scanner.nextLine();
        newProgramme.setTotalCreditHours(enteredInt);
        System.out.print("Enter new Programme Fee: ");      
        double enteredDouble = scanner.nextDouble();
        
        scanner.nextLine();
        newProgramme.setFee(enteredDouble);
        System.out.println("\n----------------------------------------------\n");
        System.out.println("Are you sure you want to add this new programme?\n");
        System.out.println("\n----------------------------------------------\n");
        System.out.println(newProgramme);
        System.out.print("\nConfirm add (Y/N) : ");
        confirm = scanner.nextLine();
        }
        
        
        }while(!("Y".equals(confirm) || "y".equals(confirm)|| "n".equals(confirm)|| "N".equals(confirm)));
        if("Y".equals(confirm) || "y".equals(confirm)){           
            progMasterList.add(newProgramme);
            System.out.println("\n----------------------------------------------\n");
            System.out.println("Added New Programme");
            System.out.println("\n----------------------------------------------\n");
        }else{
        System.out.println("\n----------------------------------------------\n");
        System.out.println("Exited Without Adding");
        System.out.println("\n----------------------------------------------\n");
        }
        
    }
    public static void programmeAllDetail(ListInterface<Programme> progMasterList){
        System.out.println("----------------------------------------------\n");
        for(int i = 1;i < progMasterList.getNumberOfEntries()+1;i++){
            System.out.print("\n"+i+")");
            System.out.print(progMasterList.getEntry(i)+"\n");
             
        }
         System.out.println("\n----------------------------------------------\n"); 
    }
    public static void programmeRemove(ListInterface<Programme> progMasterList,QueueInterface<Course> courseMasterList){
        int entry = programmeList(progMasterList);
        if(entry == -1){

        System.out.println("Operation Cancelled");
        System.out.println("----------------------------------------------\n");
        }else{
        
        updateCourseDB(progMasterList.getEntry(entry),courseMasterList,entry);
        System.out.print(progMasterList.remove(entry));
        System.out.println("\n\n----------------------------------------------");
        System.out.println("This programme is removed");
        System.out.println("----------------------------------------------\n");
        }  
    }
    public static void updateCourseDB(Programme toBeDeleted,QueueInterface<Course> courseMasterList,int entry){
        Iterator<Course> iterator = courseMasterList.getIterator();
     
        while (iterator.hasNext()) {
            Course currentCourse = iterator.next();
            if(currentCourse.getProgList().contains(toBeDeleted)){
                currentCourse.getProgList().remove(entry);
            }
        }
    }
    public static int programmeList(ListInterface<Programme> progMasterList){
        int toBeDisplayed = progMasterList.getNumberOfEntries();
        int finalPage = progMasterList.getNumberOfEntries()%8;
        if(progMasterList.getNumberOfEntries() == 0 && finalPage == 0){
            finalPage = 0;
        }else if(progMasterList.getNumberOfEntries() > 0 && finalPage == 0){
            finalPage = 8;
        }
        int listCount= 0;
        int choice;
        int page =1;
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------");   
        do{
  
        if(toBeDisplayed >=8){       
            for(int i = 1;i < 9;i++){
                System.out.println(""); 
                System.out.print(i+")"); 
                System.out.println("Programme ID   : "+progMasterList.getEntry(listCount+i).getProgID());
                System.out.println("Programme Name : "+progMasterList.getEntry(listCount+i).getProgName());
                toBeDisplayed--;
             
            }        
        }else{
            
             for(int i = 1;i < finalPage+1 ; i++){
                System.out.println(""); 
                System.out.print(i+")"); 
                System.out.println("Programme ID   : "+progMasterList.getEntry(listCount+i).getProgID());
                System.out.println("Programme Name : "+progMasterList.getEntry(listCount+i).getProgName());
                toBeDisplayed--;
             
            }   
            
        }
        System.out.println("");
        System.out.println("----------------------------------------------");
        do{
        if(listCount <=0 && toBeDisplayed >=1){
            System.out.println("9:NEXT (-1):Exit");
            System.out.println("Page: "+page);
            System.out.println("Items Left: "+toBeDisplayed);
            System.out.print("Enter Programme Number : ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
        }else if(listCount <=0 && toBeDisplayed <= 0){
            System.out.println("Page: "+page);
            System.out.println("Items Left: "+toBeDisplayed);
            System.out.println("(-1):Exit");
            System.out.print("Enter Programme Number : ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }else if(listCount >= 8 && toBeDisplayed <= 0){
            System.out.println("0:BACK (-1):Exit");
            System.out.println("Page: "+page);
            System.out.println("Items Left: "+toBeDisplayed);
            System.out.print("Enter Programme Number : ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }else{
            System.out.println("0:BACK 9:NEXT (-1):Exit");
            System.out.println("Page: "+page);
            System.out.println("Items Left: "+toBeDisplayed);
            System.out.print("Enter Programme Number : ");
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        
        System.out.println("----------------------------------------------");
        if(listCount <= 0 && choice <= -1){
            choice =-1;
        }else if(listCount >= 8 && choice == 0 && toBeDisplayed <=0){
            listCount-=8;
            page--;
            toBeDisplayed+= (8+finalPage);

        }else if(listCount >= 8&& choice ==0 && toBeDisplayed >=0){
            listCount-=8;
            page--;
            toBeDisplayed+=16;
        }else if(choice == 9 && toBeDisplayed >=8){
            page++;
            listCount+=8;
            
        }else if(choice == 9 && toBeDisplayed >=1 && toBeDisplayed < 8){
            listCount+=8;
            
            page++;
            
        }else if(choice == 9 && toBeDisplayed == 0 ){
            
            toBeDisplayed = finalPage;
            
        }
        }while(choice != -1 &&choice != 0 &&choice != 1 && choice != 2 &&choice != 3 &&choice != 4 &&choice != 5 &&choice != 6 &&choice != 7 &&choice != 8 &&choice != 9);
        if(choice == -1){
            return -1;
        }
        }while(choice != 1 && choice != 2 &&choice != 3 &&choice != 4 &&choice != 5 &&choice != 6 &&choice != 7 &&choice != 8 );
        return choice+listCount;
    }
    public static void viewProgramme(ListInterface<Programme> progMasterList){
        int entry = programmeList(progMasterList);
        if(entry == -1){

        System.out.println("Operation Cancelled");
        System.out.println("----------------------------------------------\n");
        }else{
        System.out.println(progMasterList.getEntry(entry));
        System.out.println("\n----------------------------------------------");
        if(!progMasterList.getEntry(entry).getTutGrpList().isEmpty()){
        System.out.println("Tutorial Groups under this programme\n");
        TutorialGroupSubSystem.ListAllTutorialGroupOnly(progMasterList.getEntry(entry).getTutGrpList());
        }else{
            System.out.println("No tutorial groups under this programme\n");
        }
        
        System.out.println("\n----------------------------------------------\n");
        }
    }
    public static void clearAllProgramme(ListInterface<Programme> progMasterList){
        progMasterList.clear();
    }
    public static void editProgramme(ListInterface<Programme> progMasterList){
        int entry = programmeList(progMasterList);
        if(entry == -1){

        System.out.println("Operation Cancelled");
        System.out.println("----------------------------------------------\n");
        }else{
        int choice;
        String stringHolder;
        int intHolder;
        double doubleHolder;
        Scanner scanner = new Scanner(System.in);
        do{
            
            System.out.println("1.Programme ID");
            System.out.println("2.Programme Name");
            System.out.println("3.Programme Faculty");
            System.out.println("4.Programme Certificate");
            System.out.println("5.Programme Duration");
            System.out.println("6.Programme Total Credit Hours");
            System.out.println("7.Programme Fee");
            System.out.println("");
            System.out.println("0.Done");
            System.out.print("\nEnter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                     System.out.println("----------------------------------------------");
                    System.out.println("Changes Saved");
                     System.out.println("----------------------------------------------\n");
                    break;
                case 1:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme ID : "+progMasterList.getEntry(entry).getProgID());
                    System.out.print("New Programme ID : ");
                    stringHolder = scanner.nextLine();
                    progMasterList.getEntry(entry).setProgID(stringHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                case 2:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme Name : "+progMasterList.getEntry(entry).getProgName());
                    System.out.print("New Programme Name : ");
                    stringHolder = scanner.nextLine();
                    progMasterList.getEntry(entry).setProgName(stringHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                // ...
                case 3:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme Faculty : "+progMasterList.getEntry(entry).getFaculty());
                    System.out.print("New Programme Faculty : ");
                    stringHolder = scanner.nextLine();
                    progMasterList.getEntry(entry).setFaculty(stringHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                case 4:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme Certificate : "+progMasterList.getEntry(entry).getCertificate());
                    System.out.print("New Programme Certificate : ");
                    stringHolder = scanner.nextLine();
                    progMasterList.getEntry(entry).setCertificate(stringHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                case 5:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme Duration : "+progMasterList.getEntry(entry).getDuration());
                    System.out.print("New Programme Duration : ");
                    intHolder = scanner.nextInt();
                    scanner.nextLine();
                    progMasterList.getEntry(entry).setDuration(intHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                case 6:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme Total Credit Hours : "+progMasterList.getEntry(entry).getTotalCreditHours());
                    System.out.print("New Programme Total Credit Hours : ");
                    intHolder = scanner.nextInt();
                    scanner.nextLine();
                    progMasterList.getEntry(entry).setTotalCreditHours(intHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                case 7:
                    System.out.println("\n----------------------------------------------");
                    System.out.println("\nOld Programme Fee: "+progMasterList.getEntry(entry).getFee());
                    System.out.print("New Programme Fee : ");
                    doubleHolder = scanner.nextDouble();
                    scanner.nextLine();
                    progMasterList.getEntry(entry).setFee(doubleHolder);
                    System.out.println("----------------------------------------------\n");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
            }
        }while(choice != 0);  
        
        }
         
        
        
    }
    public static void removeGroupFromProgramme(ListInterface<Programme> progMasterList){
        int entry = programmeList(progMasterList);
        TutorialGroup currentGroup;
        
        int count = 0;
        if(entry == -1){
            System.out.println("\n----------------------------------------------\n");
            System.out.println("Exited Without Adding");
            System.out.println("\n----------------------------------------------\n");
        }else{
        Programme selectedProgramme = progMasterList.getEntry(entry);
            Scanner scanner = new Scanner(System.in);
            int choice;
            int max = selectedProgramme.getTutGrpList().CountEntries();
            if(selectedProgramme.getTutGrpList().isEmpty()){
            
            System.out.println("There is no tutorial groups");
            
            }else{        
            do{
                QueueInterface<TutorialGroup> temp = new LinkedQueue();
                System.out.println("Select a tutorial group to be removed");
                System.out.println("\n----------------------------------------------\n");
                TutorialGroupSubSystem.ListAllTutorialGroupOnly(selectedProgramme.getTutGrpList());
                System.out.println("\n----------------------------------------------\n");
                System.out.print("Enter Number (-1:Exit): ");
                choice = scanner.nextInt();
                scanner.nextLine();
                if(choice >= 1 && choice <= max){
                    Iterator<TutorialGroup> GroupIterator = selectedProgramme.getTutGrpList().getIterator();
                    while (GroupIterator.hasNext()) {
                        currentGroup = GroupIterator.next();
                        count++;    
                        
                        if(choice != count){
                              temp.enqueue(currentGroup);
                          
                        }
                    }
                    count = 0;
                    selectedProgramme.setTutGrpList(temp);
                            System.out.println("\n----------------------------------------------\n");
                            System.out.println("Tutorial Group Remove");
                            System.out.println("\n----------------------------------------------\n");  
                    
                }
                
                
            }while(choice != -1);
        }}
    }
    public static void addGroupToProgramme(ListInterface<Programme> progMasterList , QueueInterface<TutorialGroup> tutGrpListGroup) {
        int entry = programmeList(progMasterList);
        TutorialGroup currentGroup;
        TutorialGroup currentGroupProgramme;
        int count = 0;
        if(entry == -1){
            System.out.println("\n----------------------------------------------\n");
            System.out.println("Exited Without Adding");
            System.out.println("\n----------------------------------------------\n");
        }else{
            Programme selectedProgramme = progMasterList.getEntry(entry);
            Scanner scanner = new Scanner(System.in);
            int choice;
            int max = tutGrpListGroup.CountEntries();
            if(tutGrpListGroup.isEmpty()){
                
            System.out.println("There is no tutorial groups");

            }else{        
            do{
                System.out.println("Add a tutorial group to the programme");
                TutorialGroupSubSystem.ListAllTutorialGroupOnly(tutGrpListGroup);
                System.out.println("\n----------------------------------------------\n");
                System.out.print("Enter Number (-1:Exit): ");
                choice = scanner.nextInt();
                scanner.nextLine();
                if(choice >= 1 && choice <= max){
                    Iterator<TutorialGroup> GroupIterator = tutGrpListGroup.getIterator();
                    Iterator<TutorialGroup> ProgrammeIterator = progMasterList.getEntry(entry).getTutGrpList().getIterator();
                    
                    while (GroupIterator.hasNext()) {
                        currentGroup = GroupIterator.next();
                        count++;            
                        if(choice == count){
                            boolean add = true;
                            while (ProgrammeIterator.hasNext()&& !progMasterList.getEntry(entry).getTutGrpList().isEmpty()) {
                                currentGroupProgramme = ProgrammeIterator.next();
                                if(currentGroupProgramme.equals(currentGroup)){
                                    add = false;
                                    System.out.println("\n----------------------------------------------\n");
                                    System.out.println("Tutorial Group Already Exists");
                                    System.out.println("\n----------------------------------------------\n");  
                                }
                            }
                            if(add){
                             selectedProgramme.addGroupToProgramme(currentGroup);
                            System.out.println("\n----------------------------------------------\n");
                            System.out.println("Tutorial Group Added");
                            System.out.println("\n----------------------------------------------\n");    
                            }
                                                   
                        }
                    }
                    count = 0;
                    
                }
            }while(choice != -1);
        
        }
        
        }
        
    }
    public static boolean containsID(ListInterface<Programme> progMasterList,String ID){     
        for(int i = 1 ; i < progMasterList.getNumberOfEntries()+1;i++){
            if(progMasterList.getEntry(i).getProgID().equals(ID)){
                return true;
            } 
        }
        return false;
    }
    public static void clearAllTutorialFromProgramme(ListInterface<Programme> progMasterList){
        int entry = programmeList(progMasterList);
        if(entry == -1){
            System.out.println("\n----------------------------------------------\n");
            System.out.println("Exited Without Adding");
            System.out.println("\n----------------------------------------------\n");
        }else{
        Programme selected = progMasterList.getEntry(programmeList(progMasterList));
        while(!selected.getTutGrpList().isEmpty()){
            selected.getTutGrpList().dequeue();
        }
        }
    }
    public static int countEmptyTutorialGroupProgramme(ListInterface<Programme> progMasterList){
        int count = 0;
        for(int i = 1 ; i<progMasterList.getNumberOfEntries()+1;i++){
            if(progMasterList.getEntry(i).getTutGrpList().isEmpty()){
                count++;
            }
        }
        return count;
    }
    public static void generateProgrammeReport(ListInterface<Programme> progMasterList) {
        int count = 1;
        int count2 = 1;
        System.out.println("\n----------------------------------------------\n");
        System.out.println("Programmes with tutorial groups : "+(progMasterList.getNumberOfEntries()-countEmptyTutorialGroupProgramme(progMasterList)));
        System.out.println("\n----------------------------------------------\n"); 
        for(int i = 1 ; i<progMasterList.getNumberOfEntries()+1;i++){
            if(!(progMasterList.getEntry(i).getTutGrpList().isEmpty())){
                System.out.print(count+")");
                count++;
                System.out.println(progMasterList.getEntry(i).getProgID()+"\n"+progMasterList.getEntry(i).getProgName()+"\n");
                System.out.println("Tutorial Groups In Programme"); 
                System.out.println("-----------------------------");  
                TutorialGroupSubSystem.ListAllTutorialGroupOnly(progMasterList.getEntry(i).getTutGrpList());
                System.out.println("----------\n"); 
            }
        }
        System.out.println("\n----------------------------------------------\n");
        System.out.println("Programmes without tutorial groups : "+countEmptyTutorialGroupProgramme(progMasterList));
        System.out.println("\n----------------------------------------------\n"); 
        for(int j = 1 ; j<progMasterList.getNumberOfEntries()+1;j++){
            
            if(progMasterList.getEntry(j).getTutGrpList().isEmpty()){
                 System.out.print(count2+")");
                 count2++;
                System.out.println(progMasterList.getEntry(j).getProgID()+"\n"+progMasterList.getEntry(j).getProgName()+"\n");
            }
        }
        System.out.println("\n----------------------------------------------\n");
    }
    
    

}
