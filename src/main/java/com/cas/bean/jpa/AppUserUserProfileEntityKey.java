/*
 * Created on 4 May 2016 ( Time 15:50:04 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.bean.jpa;
import java.io.Serializable;

import javax.persistence.*;

/**
 * Composite primary key for entity "AppUserUserProfileEntity" ( stored in table "app_user_user_profile" )
 *
 * @author Telosys Tools Generator
 *
 */
 @Embeddable
public class AppUserUserProfileEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY KEY ATTRIBUTES 
    //----------------------------------------------------------------------
    @Column(name="user_id", nullable=false)
    private Long       userId       ;
    
    @Column(name="user_profile_id", nullable=false)
    private Long       userProfileId ;
    

    //----------------------------------------------------------------------
    // CONSTRUCTORS
    //----------------------------------------------------------------------
    public AppUserUserProfileEntityKey() {
        super();
    }

    public AppUserUserProfileEntityKey( Long userId, Long userProfileId ) {
        super();
        this.userId = userId ;
        this.userProfileId = userProfileId ;
    }
    
    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR KEY FIELDS
    //----------------------------------------------------------------------
    public void setUserId( Long value ) {
        this.userId = value;
    }
    public Long getUserId() {
        return this.userId;
    }

    public void setUserProfileId( Long value ) {
        this.userProfileId = value;
    }
    public Long getUserProfileId() {
        return this.userProfileId;
    }


    //----------------------------------------------------------------------
    // equals METHOD
    //----------------------------------------------------------------------
	public boolean equals(Object obj) { 
		if ( this == obj ) return true ; 
		if ( obj == null ) return false ;
		if ( this.getClass() != obj.getClass() ) return false ; 
		AppUserUserProfileEntityKey other = (AppUserUserProfileEntityKey) obj; 
		//--- Attribute userId
		if ( userId == null ) { 
			if ( other.userId != null ) 
				return false ; 
		} else if ( ! userId.equals(other.userId) ) 
			return false ; 
		//--- Attribute userProfileId
		if ( userProfileId == null ) { 
			if ( other.userProfileId != null ) 
				return false ; 
		} else if ( ! userProfileId.equals(other.userProfileId) ) 
			return false ; 
		return true; 
	} 


    //----------------------------------------------------------------------
    // hashCode METHOD
    //----------------------------------------------------------------------
	public int hashCode() { 
		final int prime = 31; 
		int result = 1; 
		
		//--- Attribute userId
		result = prime * result + ((userId == null) ? 0 : userId.hashCode() ) ; 
		//--- Attribute userProfileId
		result = prime * result + ((userProfileId == null) ? 0 : userProfileId.hashCode() ) ; 
		
		return result; 
	} 


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() {
		StringBuffer sb = new StringBuffer(); 
		sb.append(userId); 
		sb.append("|"); 
		sb.append(userProfileId); 
        return sb.toString();
    }
}