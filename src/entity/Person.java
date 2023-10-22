package entity;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public abstract class Person {
    
    protected String ID;
    protected String name;
    protected String email;
    protected String phNo;
    
    public Person() {
        
    }

    public Person(String ID, String name, String email, String phNo) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.phNo = phNo;
    }
    
    public abstract void setID(String ID);
    public abstract void setName(String name);
    public abstract void setEmail(String email);
    public abstract void setPhNo(String phNo);
    
    public abstract String getID();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getPhNo();
    
}
