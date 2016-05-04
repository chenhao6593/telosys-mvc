/*
 * Created on 4 May 2016 ( Time 15:51:02 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.cas.bean.UserProfile;
import com.cas.bean.jpa.UserProfileEntity;
import com.cas.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class UserProfileServiceMapperTest {

	private UserProfileServiceMapper userProfileServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		userProfileServiceMapper = new UserProfileServiceMapper();
		userProfileServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'UserProfileEntity' to 'UserProfile'
	 * @param userProfileEntity
	 */
	@Test
	public void testMapUserProfileEntityToUserProfile() {
		// Given
		UserProfileEntity userProfileEntity = new UserProfileEntity();
		userProfileEntity.setType(mockValues.nextString(30));
		
		// When
		UserProfile userProfile = userProfileServiceMapper.mapUserProfileEntityToUserProfile(userProfileEntity);
		
		// Then
		assertEquals(userProfileEntity.getType(), userProfile.getType());
	}
	
	/**
	 * Test : Mapping from 'UserProfile' to 'UserProfileEntity'
	 */
	@Test
	public void testMapUserProfileToUserProfileEntity() {
		// Given
		UserProfile userProfile = new UserProfile();
		userProfile.setType(mockValues.nextString(30));

		UserProfileEntity userProfileEntity = new UserProfileEntity();
		
		// When
		userProfileServiceMapper.mapUserProfileToUserProfileEntity(userProfile, userProfileEntity);
		
		// Then
		assertEquals(userProfile.getType(), userProfileEntity.getType());
	}

}