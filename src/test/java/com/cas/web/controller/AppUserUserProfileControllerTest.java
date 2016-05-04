package com.cas.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//--- Entities
import com.cas.bean.AppUserUserProfile;
import com.cas.test.AppUserUserProfileFactoryForTest;

//--- Services 
import com.cas.business.service.AppUserUserProfileService;


import com.cas.web.common.Message;
import com.cas.web.common.MessageHelper;
import com.cas.web.common.MessageType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RunWith(MockitoJUnitRunner.class)
public class AppUserUserProfileControllerTest {
	
	@InjectMocks
	private AppUserUserProfileController appUserUserProfileController;
	@Mock
	private AppUserUserProfileService appUserUserProfileService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private AppUserUserProfileFactoryForTest appUserUserProfileFactoryForTest = new AppUserUserProfileFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<AppUserUserProfile> list = new ArrayList<AppUserUserProfile>();
		when(appUserUserProfileService.findAll()).thenReturn(list);
		
		// When
		String viewName = appUserUserProfileController.list(model);
		
		// Then
		assertEquals("appUserUserProfile/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = appUserUserProfileController.formForCreate(model);
		
		// Then
		assertEquals("appUserUserProfile/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((AppUserUserProfile)modelMap.get("appUserUserProfile")).getUserId());
		assertNull(((AppUserUserProfile)modelMap.get("appUserUserProfile")).getUserProfileId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/appUserUserProfile/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		Long userId = appUserUserProfile.getUserId();
		Long userProfileId = appUserUserProfile.getUserProfileId();
		when(appUserUserProfileService.findById(userId, userProfileId)).thenReturn(appUserUserProfile);
		
		// When
		String viewName = appUserUserProfileController.formForUpdate(model, userId, userProfileId);
		
		// Then
		assertEquals("appUserUserProfile/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfile, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/appUserUserProfile/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AppUserUserProfile appUserUserProfileCreated = new AppUserUserProfile();
		when(appUserUserProfileService.create(appUserUserProfile)).thenReturn(appUserUserProfileCreated); 
		
		// When
		String viewName = appUserUserProfileController.create(appUserUserProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/appUserUserProfile/form/"+appUserUserProfile.getUserId()+"/"+appUserUserProfile.getUserProfileId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfileCreated, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = appUserUserProfileController.create(appUserUserProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUserUserProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfile, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/appUserUserProfile/create", modelMap.get("saveAction"));
		
	}

	@Test
	public void createException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		
		Exception exception = new RuntimeException("test exception");
		when(appUserUserProfileService.create(appUserUserProfile)).thenThrow(exception);
		
		// When
		String viewName = appUserUserProfileController.create(appUserUserProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUserUserProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfile, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/appUserUserProfile/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "appUserUserProfile.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		Long userId = appUserUserProfile.getUserId();
		Long userProfileId = appUserUserProfile.getUserProfileId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AppUserUserProfile appUserUserProfileSaved = new AppUserUserProfile();
		appUserUserProfileSaved.setUserId(userId);
		appUserUserProfileSaved.setUserProfileId(userProfileId);
		when(appUserUserProfileService.update(appUserUserProfile)).thenReturn(appUserUserProfileSaved); 
		
		// When
		String viewName = appUserUserProfileController.update(appUserUserProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/appUserUserProfile/form/"+appUserUserProfile.getUserId()+"/"+appUserUserProfile.getUserProfileId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfileSaved, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = appUserUserProfileController.update(appUserUserProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUserUserProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfile, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/appUserUserProfile/update", modelMap.get("saveAction"));
		
	}

	@Test
	public void updateException() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		
		Exception exception = new RuntimeException("test exception");
		when(appUserUserProfileService.update(appUserUserProfile)).thenThrow(exception);
		
		// When
		String viewName = appUserUserProfileController.update(appUserUserProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUserUserProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserUserProfile, (AppUserUserProfile) modelMap.get("appUserUserProfile"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/appUserUserProfile/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "appUserUserProfile.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		Long userId = appUserUserProfile.getUserId();
		Long userProfileId = appUserUserProfile.getUserProfileId();
		
		// When
		String viewName = appUserUserProfileController.delete(redirectAttributes, userId, userProfileId);
		
		// Then
		verify(appUserUserProfileService).delete(userId, userProfileId);
		assertEquals("redirect:/appUserUserProfile", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AppUserUserProfile appUserUserProfile = appUserUserProfileFactoryForTest.newAppUserUserProfile();
		Long userId = appUserUserProfile.getUserId();
		Long userProfileId = appUserUserProfile.getUserProfileId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(appUserUserProfileService).delete(userId, userProfileId);
		
		// When
		String viewName = appUserUserProfileController.delete(redirectAttributes, userId, userProfileId);
		
		// Then
		verify(appUserUserProfileService).delete(userId, userProfileId);
		assertEquals("redirect:/appUserUserProfile", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "appUserUserProfile.error.delete", exception);
	}
	
	
}
