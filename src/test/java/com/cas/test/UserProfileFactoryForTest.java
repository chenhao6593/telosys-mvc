package com.cas.test;

import com.cas.bean.UserProfile;

public class UserProfileFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public UserProfile newUserProfile() {

		Long id = mockValues.nextLong();

		UserProfile userProfile = new UserProfile();
		userProfile.setId(id);
		return userProfile;
	}
	
}
