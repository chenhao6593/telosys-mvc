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
import com.cas.bean.UserProfile;
import com.cas.test.UserProfileFactoryForTest;

//--- Services 
import com.cas.business.service.UserProfileService;


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
public class UserProfileControllerTest {
	
	@InjectMocks
	private UserProfileController userProfileController;
	@Mock
	private UserProfileService userProfileService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;

	private UserProfileFactoryForTest userProfileFactoryForTest = new UserProfileFactoryForTest();


	private void givenPopulateModel() {
	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<UserProfile> list = new ArrayList<UserProfile>();
		when(userProfileService.findAll()).thenReturn(list);
		
		// When
		String viewName = userProfileController.list(model);
		
		// Then
		assertEquals("userProfile/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = userProfileController.formForCreate(model);
		
		// Then
		assertEquals("userProfile/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((UserProfile)modelMap.get("userProfile")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/userProfile/create", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		Long id = userProfile.getId();
		when(userProfileService.findById(id)).thenReturn(userProfile);
		
		// When
		String viewName = userProfileController.formForUpdate(model, id);
		
		// Then
		assertEquals("userProfile/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfile, (UserProfile) modelMap.get("userProfile"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/userProfile/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		UserProfile userProfileCreated = new UserProfile();
		when(userProfileService.create(userProfile)).thenReturn(userProfileCreated); 
		
		// When
		String viewName = userProfileController.create(userProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/userProfile/form/"+userProfile.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfileCreated, (UserProfile) modelMap.get("userProfile"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = userProfileController.create(userProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("userProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfile, (UserProfile) modelMap.get("userProfile"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/userProfile/create", modelMap.get("saveAction"));
		
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

		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		
		Exception exception = new RuntimeException("test exception");
		when(userProfileService.create(userProfile)).thenThrow(exception);
		
		// When
		String viewName = userProfileController.create(userProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("userProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfile, (UserProfile) modelMap.get("userProfile"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/userProfile/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "userProfile.error.create", exception);
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		Long id = userProfile.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		UserProfile userProfileSaved = new UserProfile();
		userProfileSaved.setId(id);
		when(userProfileService.update(userProfile)).thenReturn(userProfileSaved); 
		
		// When
		String viewName = userProfileController.update(userProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/userProfile/form/"+userProfile.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfileSaved, (UserProfile) modelMap.get("userProfile"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = userProfileController.update(userProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("userProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfile, (UserProfile) modelMap.get("userProfile"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/userProfile/update", modelMap.get("saveAction"));
		
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

		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		
		Exception exception = new RuntimeException("test exception");
		when(userProfileService.update(userProfile)).thenThrow(exception);
		
		// When
		String viewName = userProfileController.update(userProfile, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("userProfile/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(userProfile, (UserProfile) modelMap.get("userProfile"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/userProfile/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "userProfile.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		Long id = userProfile.getId();
		
		// When
		String viewName = userProfileController.delete(redirectAttributes, id);
		
		// Then
		verify(userProfileService).delete(id);
		assertEquals("redirect:/userProfile", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		UserProfile userProfile = userProfileFactoryForTest.newUserProfile();
		Long id = userProfile.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(userProfileService).delete(id);
		
		// When
		String viewName = userProfileController.delete(redirectAttributes, id);
		
		// Then
		verify(userProfileService).delete(id);
		assertEquals("redirect:/userProfile", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "userProfile.error.delete", exception);
	}
	
	
}
