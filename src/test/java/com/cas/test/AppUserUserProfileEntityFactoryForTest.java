package com.cas.test;

import com.cas.bean.jpa.AppUserUserProfileEntity;

public class AppUserUserProfileEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AppUserUserProfileEntity newAppUserUserProfileEntity() {

		Long userId = mockValues.nextLong();
		Long userProfileId = mockValues.nextLong();

		AppUserUserProfileEntity appUserUserProfileEntity = new AppUserUserProfileEntity();
		appUserUserProfileEntity.setUserId(userId);
		appUserUserProfileEntity.setUserProfileId(userProfileId);
		return appUserUserProfileEntity;
	}
	
}
