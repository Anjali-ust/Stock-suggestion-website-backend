package com.ust.stock_userdetails.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usercredentials")
public class UserCredentials {

    @Id
    private String username;
    private String password;
    private String email;
    private String categoryId;

    public UserCredentials(){}

    public UserCredentials(String username, String password,String email,String categoryId){

        this.username=username;
        this.password=password;
        this.email=email;
        this.categoryId=categoryId;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(String categoryId){
        this.categoryId=categoryId;
    }

    public String getUsername(){ return username;}
    public void setUsername(String username){this.username=username;}

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}

}
