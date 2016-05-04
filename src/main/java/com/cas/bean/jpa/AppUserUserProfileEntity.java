/*
 * Created on 4 May 2016 ( Time 15:50:04 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a composite Primary Key  


package com.cas.bean.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;


import javax.persistence.*;

/**
 * Persistent class for entity stored in table "app_user_user_profile"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name="app_user_user_profile", catalog="ssd" )
// Define named queries here
@NamedQueries ( {
  @NamedQuery ( name="AppUserUserProfileEntity.countAll", query="SELECT COUNT(x) FROM AppUserUserProfileEntity x" )
} )
public class AppUserUserProfileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( EMBEDDED IN AN EXTERNAL CLASS )  
    //----------------------------------------------------------------------
	@EmbeddedId
    private AppUserUserProfileEntityKey compositePrimaryKey ;


    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public AppUserUserProfileEntity() {
		super();
		this.compositePrimaryKey = new AppUserUserProfileEntityKey();       
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE COMPOSITE KEY 
    //----------------------------------------------------------------------
    public void setUserId( Long userId ) {
        this.compositePrimaryKey.setUserId( userId ) ;
    }
    public Long getUserId() {
        return this.compositePrimaryKey.getUserId() ;
    }
    public void setUserProfileId( Long userProfileId ) {
        this.compositePrimaryKey.setUserProfileId( userProfileId ) ;
    }
    public Long getUserProfileId() {
        return this.compositePrimaryKey.getUserProfileId() ;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append("["); 
        if ( compositePrimaryKey != null ) {  
            sb.append(compositePrimaryKey.toString());  
        }  
        else {  
            sb.append( "(null-key)" ); 
        }  
        sb.append("]:"); 
        return sb.toString(); 
    } 

}