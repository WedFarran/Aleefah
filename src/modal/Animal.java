
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modal;

import javax.persistence.*;

/**
 *
 * @author wedfa
 */
@Entity
@Table(name="animal")
public class Animal {
   @Id 
   @Column(name="idanimal")
   private int animalID; 
   
   @Column(name="name")
   private String name;
   
   @Column(name="city")
   private String city;
   
   @Column(name="age")
   private String age;
   
   @Column(name="gender")
   private String gender;
   
   @Column(name="Description")
   private String Description;
   
   @Column(name="contact")
   private String contact;
   
   @Column(name="type")
   private String type;
   
   @Column(name="imageUrl")
   private String imageUrl;
   
   @Column(name="userID")
   private int userId;

    public Animal(String name, String city, String age, String gender, String Description, String contact, String type, String imageUrl, int userId) {
        this.name = name;
        this.city = city;
        this.age = age;
        this.gender = gender;
        this.Description = Description;
        this.contact = contact;
        this.type = type;
        this.imageUrl = imageUrl;
        this.userId = userId;
    }

   

    public Animal() {
        
    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
   
}
