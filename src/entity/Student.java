package entity;

import adt.LinkedQueue;
import adt.QueueInterface;
import java.util.Iterator;

/**
 *
 * @author Ng Jing Kang, Yap Ying Sin, Lee Ee Zian, Lee Jun Wen, Tan Su Jing
 */
public class Student extends Person implements QueueInterface<Object>{

    private int count;
    private Student.Node firstNode; // references node at front of queue (for dequeue)
    private Student.Node lastNode;
    
    private double cgpa;
    private String grpID;

    public Student() {

    }

    public Student(String ID, String name, String email, String phNo, double cgpa, String grpID) {
        super(ID, name, email, phNo);
        this.cgpa = cgpa;
        this.grpID = grpID;
    }

    @Override
    public void setID(String studID) {
        ID = studID;
    }

    @Override
    public void setName(String studName) {
        name = studName;
    }

    @Override
    public void setEmail(String studEmail) {
        email = studEmail;
    }

    @Override
    public void setPhNo(String studPhNo) {
        phNo = studPhNo;
    }

    public void setCGPA(double cgpa) {
        this.cgpa = cgpa;
    }

    public void setGrpID(String grpID) {
        this.grpID = grpID;
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPhNo() {
        return phNo;
    }

    public double getCGPA() {
        return cgpa;
    }

    public String getGrpID() {
        return grpID;
    }

    @Override
    public void enqueue(Object newEntry) {
    Node newNode = new Node(newEntry, null);

    if (isEmpty()) {
      firstNode = newNode;
    } else {
      lastNode.next = newNode;
    }

    lastNode = newNode;
    count++;
  } 
    
    public void enqueue(Student newEntry) {
    Node newNode = new Node(newEntry, null);

    if (isEmpty()) {
      firstNode = newNode;
    } else {
      lastNode.next = newNode;
    }

    lastNode = newNode;
    count++;
  } 

  public Object getFront() {
    Object front = null;

    if (!isEmpty()) {
      front = (Object) firstNode.data.toString();
    }

    return front;
  } 

  public Object dequeue() {
    Object front = null;
    
    if (!isEmpty()) {
      front = (Object) firstNode.data.toString();
      firstNode = firstNode.next;

      if (firstNode == null) {
        lastNode = null;
      }
    } 
    count--;
    return front;
  } // end dequeue
  
  public boolean isEmpty() {
    return (firstNode == null) && (lastNode == null);
  }

    @Override
  public void clear() {
    firstNode = null;
    lastNode = null;
  } 
  
  public Student clear1() {
    firstNode = null;
    lastNode = null;
        return null;
  } 
  public Iterator<Object> getIterator() {
    return new LinkedQueueIterator();
  }

  private class LinkedQueueIterator implements Iterator<Object> {

    private Node currentNode;

    public LinkedQueueIterator() {
      currentNode = firstNode;
    }

    @Override
    public boolean hasNext() {
      return currentNode != null;
    }

    @Override
    public Object next() {
      if (hasNext()) {
        Object returnData = currentNode.data;
        currentNode = currentNode.next;
        return returnData;
      } else {
        return null;
      }
    }
   
  }
  
    
  private class Node {

    private Object data; 
    private Node next; 

    private Node(Object data) {
      this.data = data;
      this.next = null;
    } 

    private Node(Object data, Node next) {
      this.data = data;
      this.next = next;
    } 
  }
  
  @Override
    public String toString() {
        return  getID() + "\t    " + getGrpID() + "\t" + getEmail() + "\t" + getPhNo() + "\t"  +  getCGPA() + "\t" + getName() ;
    }
    
  public int CountEntries() {
        return count;
    }
  
    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student otherStudent = (Student) o;
            if (this.ID == otherStudent.ID) {
                return true;
            }
            else {
                return this.ID.compareTo(otherStudent.ID) == 0;
            }
        }
        return false;
    }
}
