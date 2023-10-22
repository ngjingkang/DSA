package other;

import adt.ArrayList;
import adt.ArrayQueue;
import adt.LinkedList;
import adt.LinkedQueue;
import adt.ListInterface;
import adt.QueueInterface;
import entity.Course;
import entity.Programme;
import entity.Semester;
import entity.Student;
import entity.Tutor;
import entity.TutorialGroup;

/**
 *
 * @author Yap Ying Sin
 */
public class TempDatabase {
    
    // 1. Student List
    public static ListInterface<Student> initializeStudents() {
        
        ListInterface<Student> studList = new ArrayList();
        
        studList.add(new Student("2309401", "Ali bin Abu", "aliba@student.tarc.edu.my", "010-0000000", 3.25,"G1001"));
        studList.add(new Student("2309402", "Sum Ting Wong", "sumtw@student.tarc.edu.my", "011-1111111", 3.89,"G1003"));
        studList.add(new Student("2309403", "Wai So Dim", "waisd@student.tarc.edu.my", "012-2222222", 2.43,"G1005"));
        studList.add(new Student("2309404", "Hitam bin Putih", "hitambp@student.tarc.edu.my", "013-3333333", 3.57,"G1002"));
        studList.add(new Student("2309405", "Dam Gai", "damg@student.tarc.edu.my", "014-4444444", 3.60,"G1008"));
        studList.add(new Student("2309406", "Kum Hia Nao", "kumhn@student.tarc.edu.my", "015-5555555", 3.74,"G1005"));
        studList.add(new Student("2309407", "William Yu Kum Nao", "waiykn@student.tarc.edu.my", "016-6666666", 3.33,"G1007"));
        studList.add(new Student("2309408", "Mutu A/L Kalakalappu", "mutuk@student.tarc.edu.my", "017-7777777", 4.00,"G1007"));
        studList.add(new Student("2309409", "Soh Fan Nie", "sohfn@student.tarc.edu.my", "018-8888888", 3.48,"G1006"));
        studList.add(new Student("2309410", "Miso Soh Ya Mi", "misosym@student.tarc.edu.my", "019-9999999", 2.99,"G1001"));
        studList.add(new Student("2309411", "Baek Wan Ton", "baekwt@student.tarc.edu.my", "012-3456789", 3.92,"G1002"));
        studList.add(new Student("2309412", "Rantai Solong binti Toolong", "rantaisbt@student.tarc.edu.my", "013-2456789", 3.08,"G1003"));
        studList.add(new Student("2309413", "Ambatu Kam Mei Lan", "ambatukml@student.tarc.edu.my", "014-2356789", 4.00,"G1002"));
        studList.add(new Student("2309414", "Lee Ee Zian", "leeez@student.tarc.edu.my", "015-2346789", 3.75,"G1008"));
        studList.add(new Student("2309415", "Lee Jun Wen", "leejw@student.tarc.edu.my", "016-2345789", 3.21,"G1008"));
        studList.add(new Student("2309416", "Yap Ying Sin", "yapys@student.tarc.edu.my", "017-2345689", 3.90,"G1007"));
        studList.add(new Student("2309417", "Ng Jing Kang", "ngjk@student.tarc.edu.my", "018-2345679", 3.33,"G1006"));
        studList.add(new Student("2309418", "Tan Su Jing", "tansj@student.tarc.edu.my", "019-2345678", 3.63,"G1003"));
        studList.add(new Student("2309419", "Jeremy Liew", "jeremyl@student.tarc.edu.my", "012-9876543", 3.90,"G1001"));
        studList.add(new Student("2309420", "Wong Yik Xiang", "wongyx@student.tarc.edu.my", "013-9876542", 3.42,"G1004"));
        studList.add(new Student("2309421", "Bryan Hoh", "bryanh@student.tarc.edu.my", "014-9876532", 3.66,"G1005"));
        studList.add(new Student("2309422", "Sean Woon", "seanw@student.tarc.edu.my", "015-9876432", 3.19,"G1008"));
        // ADD MORE HERE
        
        return studList;
    }
    
    // 1.1 Student Queue
    public static Student initializeStudents_QueueInterface() {
        
        Student studList = new Student();
        
        studList.enqueue(new Student("2309401", "Ali bin Abu", "aliba@student.tarc.edu.my", "010-0000000", 3.25,"G1001"));
        studList.enqueue(new Student("2309402", "Sum Ting Wong", "sumtw@student.tarc.edu.my", "011-1111111", 3.89,"G1003"));
        studList.enqueue(new Student("2309403", "Wai So Dim", "waisd@student.tarc.edu.my", "012-2222222", 2.43,"G1005"));
        studList.enqueue(new Student("2309404", "Hitam bin Putih", "hitambp@student.tarc.edu.my", "013-3333333", 3.57,"G1002"));
        studList.enqueue(new Student("2309405", "Dam Gai", "damg@student.tarc.edu.my", "014-4444444", 3.60,"G1008"));
        studList.enqueue(new Student("2309406", "Kum Hia Nao", "kumhn@student.tarc.edu.my", "015-5555555", 3.74,"G1005"));
        studList.enqueue(new Student("2309407", "William Yu Kum Nao", "waiykn@student.tarc.edu.my", "016-6666666", 3.33,"G1007"));
        studList.enqueue(new Student("2309408", "Mutu A/L Kalakalappu", "mutuk@student.tarc.edu.my", "017-7777777", 4.00,"G1007"));
        studList.enqueue(new Student("2309409", "Soh Fan Nie", "sohfn@student.tarc.edu.my", "018-8888888", 3.48,"G1006"));
        studList.enqueue(new Student("2309410", "Miso Soh Ya Mi", "misosym@student.tarc.edu.my", "019-9999999", 2.99,"G1001"));
        studList.enqueue(new Student("2309411", "Baek Wan Ton", "baekwt@student.tarc.edu.my", "012-3456789", 3.92,"G1002"));
        studList.enqueue(new Student("2309412", "Rantai Solong binti Toolong", "rantaisbt@student.tarc.edu.my", "013-2456789", 3.08,"G1003"));
        studList.enqueue(new Student("2309413", "Ambatu Kam Mei Lan", "ambatukml@student.tarc.edu.my", "014-2356789", 4.00,"G1002"));
        studList.enqueue(new Student("2309414", "Lee Ee Zian", "leeez@student.tarc.edu.my", "015-2346789", 3.75,"G1008"));
        studList.enqueue(new Student("2309415", "Lee Jun Wen", "leejw@student.tarc.edu.my", "016-2345789", 3.21,"G1008"));
        studList.enqueue(new Student("2309416", "Yap Ying Sin", "yapys@student.tarc.edu.my", "017-2345689", 3.90,"G1007"));
        studList.enqueue(new Student("2309417", "Ng Jing Kang", "ngjk@student.tarc.edu.my", "018-2345679", 3.33,""));
        studList.enqueue(new Student("2309418", "Tan Su Jing", "tansj@student.tarc.edu.my", "019-2345678", 3.63,"G1003"));
        studList.enqueue(new Student("2309419", "Jeremy Liew", "jeremyl@student.tarc.edu.my", "012-9876543", 3.90,"G1001"));
        studList.enqueue(new Student("2309420", "Wong Yik Xiang", "wongyx@student.tarc.edu.my", "013-9876542", 3.42,"G1004"));
        studList.enqueue(new Student("2309421", "Bryan Hoh", "bryanh@student.tarc.edu.my", "014-9876532", 3.66,"G1005"));
        studList.enqueue(new Student("2309422", "Sean Woon", "seanw@student.tarc.edu.my", "015-9876432", 3.19,"G1008"));
        // ADD MORE HERE
        
        return studList;
    }
    
    
    // 2. Tutorial Group List
    public static ListInterface<TutorialGroup> initializeTutGrps() {
        
        ListInterface<TutorialGroup> tutGrpList = new ArrayList();
        
        tutGrpList.add(new TutorialGroup("G1001", 24));
        tutGrpList.add(new TutorialGroup("G1002", 24));
        tutGrpList.add(new TutorialGroup("G1003", 24));
        tutGrpList.add(new TutorialGroup("G1004", 24));
        tutGrpList.add(new TutorialGroup("G1005", 24));     
        tutGrpList.add(new TutorialGroup("G1006", 24));
        tutGrpList.add(new TutorialGroup("G1007", 24));
        tutGrpList.add(new TutorialGroup("G1008", 24));
        // ADD MORE HERE
        
        return tutGrpList;
    }
    
     // 2.1 Tutorial Group Queue
    public static QueueInterface<TutorialGroup> initializeTutGrps_QueueInterface() {
        
        QueueInterface<TutorialGroup> tutGrpList = new LinkedQueue();
        
        tutGrpList.enqueue(new TutorialGroup("G1001", 24));
        tutGrpList.enqueue(new TutorialGroup("G1002", 24));
        tutGrpList.enqueue(new TutorialGroup("G1003", 24));
        tutGrpList.enqueue(new TutorialGroup("G1004", 24));
        tutGrpList.enqueue(new TutorialGroup("G1005", 24));     
        tutGrpList.enqueue(new TutorialGroup("G1006", 24));
        tutGrpList.enqueue(new TutorialGroup("G1007", 24));
        tutGrpList.enqueue(new TutorialGroup("G1008", 24));
        // ADD MORE HERE
        
        return tutGrpList;
    }
        
    // 3. Programme list
    public static ListInterface<Programme> initializeProgrammes() {
        
        ListInterface<Programme> progList = new LinkedList();
        
        progList.add(new Programme("DAC", "Diploma in Accounting", "Faculty of Accountancy, Finance and Business (FAFB)", "Diploma", 2, 90, 18000.00));
        progList.add(new Programme("DCS", "Diploma in Computer Science", "Faculty of Computing and Information Technology (FOCS)", "Diploma", 2, 90, 17600.00));
        progList.add(new Programme("DGD", "Diploma in Graphic Design", "Faculty of Communication and Creative Industries (FCCI)", "Diploma", 2, 90, 18500.00));
        progList.add(new Programme("RFI", "Bachelor of Finance and Investment", "Faculty of Accountancy, Finance and Business (FAFB)", "Bachelor Degree", 3, 120, 33800.00));
        progList.add(new Programme("RSW", "Bachelor of Software Engineering", "Faculty of Computing and Information Technology (FOCS)", "Bachelor Degree", 3, 120, 34600.00));
        progList.add(new Programme("RBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBC1", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBC2", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBC2", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RB6C", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("R3BC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBC5", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RsBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBdC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBAC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBsgC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBsC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RSDGBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBAGC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RAGSBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
       progList.add(new Programme("RSDGBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RBAGC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
        progList.add(new Programme("RSDGBC", "Bachelor of Communication in Broadcasting", "Faculty of Communication and Creative Industries (FCCI)", "Bachelor Degree", 3, 120, 34300.00));
       
       
        
        // ADD MORE HERE
        
        return progList;
    }
        
    // 4. Tutor List
    public static ListInterface<Tutor> initializeTutors() {
        
        ListInterface<Tutor> tutorList = new ArrayList();
        
        tutorList.add(new Tutor("3901", "John Ling How Kiet", "johnlhk@tarc.edu.my", "010-1234567", "Senior Lecturer"));
        tutorList.add(new Tutor("3902", "Noor Dalili binti Nizam", "noordbn@tarc.edu.my", "011-2345678", "Junior Lecturer"));
        tutorList.add(new Tutor("3903", "Teoh Zi Yee", "teohzy@tarc.edu.my", "012-3456789", "Associate Professor"));
        tutorList.add(new Tutor("3904", "Ganesh A/L Padathan", "ganeshalp@tarc.edu.my", "013-4567890", "Senior Lecturer"));
        tutorList.add(new Tutor("3905", "Xiang Fen Fen", "xiangff@tarc.edu.my", "014-5678901", "Professor"));
        // ADD MORE HERE
        
        return tutorList;
    }
        
    // 5. Course List
    public static QueueInterface<Course> initializeCourses() {
        
        QueueInterface<Course> courseList = new ArrayQueue<Course>();
        
        // General subjects for all faculties
        courseList.enqueue(new Course("ABCD1001", "Civic Consciousness and Volunteerism", "Diploma", 2));
        courseList.enqueue(new Course("ABCD1002", "English for Tertiary Studies", "Diploma", 3));
        courseList.enqueue(new Course("BBCD2001", "Music Appreciation", "Bachelor Degree", 2));
        courseList.enqueue(new Course("BBCD2002", "Contemporary Malaysian Issues", "Bachelor Degree", 3));
        // ADD MORE HERE
        
        return courseList;
    }
        
    // 6. Semester List
    public static ListInterface<Semester> initializeSemesters() {
        
        ListInterface<Semester> semList = new ArrayList();
        
        semList.add(new Semester(2023, 5));
        // ADD MORE HERE
        // However, do note that our ass. ques. paper requires that our system handle only one sem enough
        
        return semList;
    }
    
}
