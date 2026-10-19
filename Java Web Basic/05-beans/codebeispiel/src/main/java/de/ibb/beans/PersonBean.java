package de.ibb.beans;
import java.io.Serializable;

/**
 * JavaBean für jsp:useBean und jsp:setProperty im Formular.
 */
public class PersonBean implements Serializable {
  private String firstName, lastName, email; private int age;
  public PersonBean() {}
  public String getFirstName(){return firstName;} public void setFirstName(String v){firstName=v;}
  public String getLastName(){return lastName;} public void setLastName(String v){lastName=v;}
  public String getEmail(){return email;} public void setEmail(String v){email=v;}
  public int getAge(){return age;} public void setAge(int v){age=v;}
}
