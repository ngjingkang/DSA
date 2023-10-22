/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author sujing
 */
public class Tutor implements Comparable<Tutor>{

    private String id;
    private String name;
    private String email;
    private String phno;
    private String position;
    private Semester sem;

    public Tutor(String id, String name, String email, String phno, String position) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phno = phno;
        this.position = position;
        this.sem = null;
    }
    
    @Override
    public int compareTo(Tutor other) {
        // Compare tutors based on their ID strings
        return this.id.compareTo(other.id);
    }

    @Override
    public int hashCode() {
        int h = 0;

        for (int i = 0; i < id.length(); i++) {
            h = h + id.charAt(i);

        }
        return h;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public Semester getSem() {
        return sem;
    }

    public void setSem(Semester sem) {
        this.sem = sem;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tutor{" + "id=" + id + ", name=" + name + ", email=" + email + ", phno=" + phno + ", sem=" + sem + ", position=" + position + '}';
    }
}
