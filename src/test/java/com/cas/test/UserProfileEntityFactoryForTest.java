package com.cas.test;

import com.cas.bean.jpa.UserProfileEntity;

public class UserProfileEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public UserProfileEntity newUserProfileEntity() {

		Long id = mockValues.nextLong();

		UserProfileEntity userProfileEntity = new UserProfileEntity();
		userProfileEntity.setId(id);
		return userProfileEntity;
	}
	
}
