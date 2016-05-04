/*
 * Created on 4 May 2016 ( Time 15:51:01 )
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
import com.cas.bean.AppUser;
import com.cas.bean.jpa.AppUserEntity;
import com.cas.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class AppUserServiceMapperTest {

	private AppUserServiceMapper appUserServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		appUserServiceMapper = new AppUserServiceMapper();
		appUserServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'AppUserEntity' to 'AppUser'
	 * @param appUserEntity
	 */
	@Test
	public void testMapAppUserEntityToAppUser() {
		// Given
		AppUserEntity appUserEntity = new AppUserEntity();
		appUserEntity.setSsoId(mockValues.nextString(30));
		appUserEntity.setPassword(mockValues.nextString(100));
		appUserEntity.setFirstName(mockValues.nextString(30));
		appUserEntity.setLastName(mockValues.nextString(30));
		appUserEntity.setEmail(mockValues.nextString(30));
		appUserEntity.setState(mockValues.nextString(30));
		
		// When
		AppUser appUser = appUserServiceMapper.mapAppUserEntityToAppUser(appUserEntity);
		
		// Then
		assertEquals(appUserEntity.getSsoId(), appUser.getSsoId());
		assertEquals(appUserEntity.getPassword(), appUser.getPassword());
		assertEquals(appUserEntity.getFirstName(), appUser.getFirstName());
		assertEquals(appUserEntity.getLastName(), appUser.getLastName());
		assertEquals(appUserEntity.getEmail(), appUser.getEmail());
		assertEquals(appUserEntity.getState(), appUser.getState());
	}
	
	/**
	 * Test : Mapping from 'AppUser' to 'AppUserEntity'
	 */
	@Test
	public void testMapAppUserToAppUserEntity() {
		// Given
		AppUser appUser = new AppUser();
		appUser.setSsoId(mockValues.nextString(30));
		appUser.setPassword(mockValues.nextString(100));
		appUser.setFirstName(mockValues.nextString(30));
		appUser.setLastName(mockValues.nextString(30));
		appUser.setEmail(mockValues.nextString(30));
		appUser.setState(mockValues.nextString(30));

		AppUserEntity appUserEntity = new AppUserEntity();
		
		// When
		appUserServiceMapper.mapAppUserToAppUserEntity(appUser, appUserEntity);
		
		// Then
		assertEquals(appUser.getSsoId(), appUserEntity.getSsoId());
		assertEquals(appUser.getPassword(), appUserEntity.getPassword());
		assertEquals(appUser.getFirstName(), appUserEntity.getFirstName());
		assertEquals(appUser.getLastName(), appUserEntity.getLastName());
		assertEquals(appUser.getEmail(), appUserEntity.getEmail());
		assertEquals(appUser.getState(), appUserEntity.getState());
	}

}