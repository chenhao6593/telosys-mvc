/*
 * Created on 4 May 2016 ( Time 15:52:09 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class AppUserUserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Long userId;
    @NotNull
    private Long userProfileId;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    


    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setUserId( Long userId ) {
        this.userId = userId ;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserProfileId( Long userProfileId ) {
        this.userProfileId = userProfileId ;
    }

    public Long getUserProfileId() {
        return this.userProfileId;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------

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
