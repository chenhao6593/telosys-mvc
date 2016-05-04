package com.cas.test;

import com.cas.bean.AppUserUserProfile;

public class AppUserUserProfileFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AppUserUserProfile newAppUserUserProfile() {

		Long userId = mockValues.nextLong();
		Long userProfileId = mockValues.nextLong();

		AppUserUserProfile appUserUserProfile = new AppUserUserProfile();
		appUserUserProfile.setUserId(userId);
		appUserUserProfile.setUserProfileId(userProfileId);
		return appUserUserProfile;
	}
	
}
