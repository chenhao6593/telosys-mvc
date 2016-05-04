/*
 * Created on 4 May 2016 ( Time 15:51:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.cas.bean.AppUserUserProfile;
import com.cas.bean.jpa.AppUserUserProfileEntity;
import com.cas.bean.jpa.AppUserUserProfileEntityKey;
import com.cas.business.service.AppUserUserProfileService;
import com.cas.business.service.mapping.AppUserUserProfileServiceMapper;
import com.cas.data.repository.jpa.AppUserUserProfileJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AppUserUserProfileService
 */
@Component
@Transactional
public class AppUserUserProfileServiceImpl implements AppUserUserProfileService {

	@Resource
	private AppUserUserProfileJpaRepository appUserUserProfileJpaRepository;

	@Resource
	private AppUserUserProfileServiceMapper appUserUserProfileServiceMapper;
	
	@Override
	public AppUserUserProfile findById(Long userId, Long userProfileId) {
		AppUserUserProfileEntityKey id = new AppUserUserProfileEntityKey(userId, userProfileId);
		AppUserUserProfileEntity appUserUserProfileEntity = appUserUserProfileJpaRepository.findOne(id);
		return appUserUserProfileServiceMapper.mapAppUserUserProfileEntityToAppUserUserProfile(appUserUserProfileEntity);
	}

	@Override
	public List<AppUserUserProfile> findAll() {
		Iterable<AppUserUserProfileEntity> entities = appUserUserProfileJpaRepository.findAll();
		List<AppUserUserProfile> beans = new ArrayList<AppUserUserProfile>();
		for(AppUserUserProfileEntity appUserUserProfileEntity : entities) {
			beans.add(appUserUserProfileServiceMapper.mapAppUserUserProfileEntityToAppUserUserProfile(appUserUserProfileEntity));
		}
		return beans;
	}

	@Override
	public AppUserUserProfile save(AppUserUserProfile appUserUserProfile) {
		return update(appUserUserProfile) ;
	}

	@Override
	public AppUserUserProfile create(AppUserUserProfile appUserUserProfile) {
		AppUserUserProfileEntityKey id = new AppUserUserProfileEntityKey(appUserUserProfile.getUserId(), appUserUserProfile.getUserProfileId());
		AppUserUserProfileEntity appUserUserProfileEntity = appUserUserProfileJpaRepository.findOne(id);
		if( appUserUserProfileEntity != null ) {
			throw new IllegalStateException("already.exists");
		}
		appUserUserProfileEntity = new AppUserUserProfileEntity();
		appUserUserProfileServiceMapper.mapAppUserUserProfileToAppUserUserProfileEntity(appUserUserProfile, appUserUserProfileEntity);
		AppUserUserProfileEntity appUserUserProfileEntitySaved = appUserUserProfileJpaRepository.save(appUserUserProfileEntity);
		return appUserUserProfileServiceMapper.mapAppUserUserProfileEntityToAppUserUserProfile(appUserUserProfileEntitySaved);
	}

	@Override
	public AppUserUserProfile update(AppUserUserProfile appUserUserProfile) {
		AppUserUserProfileEntityKey id = new AppUserUserProfileEntityKey(appUserUserProfile.getUserId(), appUserUserProfile.getUserProfileId());
		AppUserUserProfileEntity appUserUserProfileEntity = appUserUserProfileJpaRepository.findOne(id);
		appUserUserProfileServiceMapper.mapAppUserUserProfileToAppUserUserProfileEntity(appUserUserProfile, appUserUserProfileEntity);
		AppUserUserProfileEntity appUserUserProfileEntitySaved = appUserUserProfileJpaRepository.save(appUserUserProfileEntity);
		return appUserUserProfileServiceMapper.mapAppUserUserProfileEntityToAppUserUserProfile(appUserUserProfileEntitySaved);
	}

	@Override
	public void delete(Long userId, Long userProfileId) {
		AppUserUserProfileEntityKey id = new AppUserUserProfileEntityKey(userId, userProfileId);
		appUserUserProfileJpaRepository.delete(id);
	}

	public AppUserUserProfileJpaRepository getAppUserUserProfileJpaRepository() {
		return appUserUserProfileJpaRepository;
	}

	public void setAppUserUserProfileJpaRepository(AppUserUserProfileJpaRepository appUserUserProfileJpaRepository) {
		this.appUserUserProfileJpaRepository = appUserUserProfileJpaRepository;
	}

	public AppUserUserProfileServiceMapper getAppUserUserProfileServiceMapper() {
		return appUserUserProfileServiceMapper;
	}

	public void setAppUserUserProfileServiceMapper(AppUserUserProfileServiceMapper appUserUserProfileServiceMapper) {
		this.appUserUserProfileServiceMapper = appUserUserProfileServiceMapper;
	}

}