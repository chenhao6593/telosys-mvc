package com.cas.test;

import com.cas.bean.jpa.AppUserEntity;

public class AppUserEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public AppUserEntity newAppUserEntity() {

		Long id = mockValues.nextLong();

		AppUserEntity appUserEntity = new AppUserEntity();
		appUserEntity.setId(id);
		return appUserEntity;
	}
	
}
