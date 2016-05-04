/*
 * Created on 4 May 2016 ( Time 15:52:09 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.business.service;

import java.util.List;

import com.cas.bean.AppUserUserProfile;

/**
 * Business Service Interface for entity AppUserUserProfile.
 */
public interface AppUserUserProfileService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param userId
	 * @param userProfileId
	 * @return entity
	 */
	AppUserUserProfile findById( Long userId, Long userProfileId  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<AppUserUserProfile> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	AppUserUserProfile save(AppUserUserProfile entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	AppUserUserProfile update(AppUserUserProfile entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	AppUserUserProfile create(AppUserUserProfile entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param userId
	 * @param userProfileId
	 */
	void delete( Long userId, Long userProfileId );


}