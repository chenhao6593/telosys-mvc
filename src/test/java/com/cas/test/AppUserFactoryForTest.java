package com.cas.test;

import com.cas.bean.AppUser;

public class AppUserFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AppUser newAppUser() {

		Long id = mockValues.nextLong();

		AppUser appUser = new AppUser();
		appUser.setId(id);
		return appUser;
	}
	
}
