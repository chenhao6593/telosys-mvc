/*
 * Created on 4 May 2016 ( Time 15:50:04 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.cas.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "user_profile"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="user_profile", catalog="ssd" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="UserProfileEntity.countAll", query="SELECT COUNT(x) FROM UserProfileEntity x" )
} )
public class UserProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long       id           ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Column(name="type", nullable=false, length=30)
    private String     type         ;



    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------
    @ManyToMany(mappedBy="listOfUserProfile", targetEntity=AppUserEntity.class)
    private List<AppUserEntity> listOfAppUser;


    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public UserProfileEntity() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Long id ) {
        this.id = id ;
    }
    public Long getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    //--- DATABASE MAPPING : type ( VARCHAR ) 
    public void setType( String type ) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------
    public void setListOfAppUser( List<AppUserEntity> listOfAppUser ) {
        this.listOfAppUser = listOfAppUser;
    }
    public List<AppUserEntity> getListOfAppUser() {
        return this.listOfAppUser;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        sb.append(id);
        sb.append("]:"); 
        sb.append(type);
        return sb.toString(); 
    } 

}
