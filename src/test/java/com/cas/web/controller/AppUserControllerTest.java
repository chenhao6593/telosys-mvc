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
import com.cas.bean.AppUser;
import com.cas.bean.UserProfile;
import com.cas.test.AppUserFactoryForTest;
import com.cas.test.UserProfileFactoryForTest;

//--- Services 
import com.cas.business.service.AppUserService;
import com.cas.business.service.UserProfileService;

//--- List Items 
import com.cas.web.listitem.UserProfileListItem;

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
public class AppUserControllerTest {
	
	@InjectMocks
	private AppUserController appUserController;
	@Mock
	private AppUserService appUserService;
	@Mock
	private MessageHelper messageHelper;
	@Mock
	private MessageSource messageSource;
	@Mock
	private UserProfileService userProfileService; // Injected by Spring

	private AppUserFactoryForTest appUserFactoryForTest = new AppUserFactoryForTest();
	private UserProfileFactoryForTest userProfileFactoryForTest = new UserProfileFactoryForTest();

	List<UserProfile> userProfiles = new ArrayList<UserProfile>();

	private void givenPopulateModel() {
		UserProfile userProfile1 = userProfileFactoryForTest.newUserProfile();
		UserProfile userProfile2 = userProfileFactoryForTest.newUserProfile();
		List<UserProfile> userProfiles = new ArrayList<UserProfile>();
		userProfiles.add(userProfile1);
		userProfiles.add(userProfile2);
		when(userProfileService.findAll()).thenReturn(userProfiles);

	}

	@Test
	public void list() {
		// Given
		Model model = new ExtendedModelMap();
		
		List<AppUser> list = new ArrayList<AppUser>();
		when(appUserService.findAll()).thenReturn(list);
		
		// When
		String viewName = appUserController.list(model);
		
		// Then
		assertEquals("appUser/list", viewName);
		Map<String,?> modelMap = model.asMap();
		assertEquals(list, modelMap.get("list"));
	}
	
	@Test
	public void formForCreate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		// When
		String viewName = appUserController.formForCreate(model);
		
		// Then
		assertEquals("appUser/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertNull(((AppUser)modelMap.get("appUser")).getId());
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/appUser/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<UserProfileListItem> userProfileListItems = (List<UserProfileListItem>) modelMap.get("listOfUserProfileItems");
		assertEquals(2, userProfileListItems.size());
		
	}
	
	@Test
	public void formForUpdate() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		Long id = appUser.getId();
		when(appUserService.findById(id)).thenReturn(appUser);
		
		// When
		String viewName = appUserController.formForUpdate(model, id);
		
		// Then
		assertEquals("appUser/form", viewName);
		
		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUser, (AppUser) modelMap.get("appUser"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/appUser/update", modelMap.get("saveAction"));
		
	}
	
	@Test
	public void createOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AppUser appUserCreated = new AppUser();
		when(appUserService.create(appUser)).thenReturn(appUserCreated); 
		
		// When
		String viewName = appUserController.create(appUser, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/appUser/form/"+appUser.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserCreated, (AppUser) modelMap.get("appUser"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void createBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = appUserController.create(appUser, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUser/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUser, (AppUser) modelMap.get("appUser"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/appUser/create", modelMap.get("saveAction"));
		
		@SuppressWarnings("unchecked")
		List<UserProfileListItem> userProfileListItems = (List<UserProfileListItem>) modelMap.get("listOfUserProfileItems");
		assertEquals(2, userProfileListItems.size());
		
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

		AppUser appUser = appUserFactoryForTest.newAppUser();
		
		Exception exception = new RuntimeException("test exception");
		when(appUserService.create(appUser)).thenThrow(exception);
		
		// When
		String viewName = appUserController.create(appUser, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUser/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUser, (AppUser) modelMap.get("appUser"));
		assertEquals("create", modelMap.get("mode"));
		assertEquals("/appUser/create", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "appUser.error.create", exception);
		
		@SuppressWarnings("unchecked")
		List<UserProfileListItem> userProfileListItems = (List<UserProfileListItem>) modelMap.get("listOfUserProfileItems");
		assertEquals(2, userProfileListItems.size());
		
	}

	@Test
	public void updateOk() {
		// Given
		Model model = new ExtendedModelMap();
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		Long id = appUser.getId();

		BindingResult bindingResult = mock(BindingResult.class);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		AppUser appUserSaved = new AppUser();
		appUserSaved.setId(id);
		when(appUserService.update(appUser)).thenReturn(appUserSaved); 
		
		// When
		String viewName = appUserController.update(appUser, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("redirect:/appUser/form/"+appUser.getId(), viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUserSaved, (AppUser) modelMap.get("appUser"));
		assertEquals(null, modelMap.get("mode"));
		assertEquals(null, modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"save.ok"));
	}

	@Test
	public void updateBindingResultErrors() {
		// Given
		Model model = new ExtendedModelMap();
		
		givenPopulateModel();
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
		
		// When
		String viewName = appUserController.update(appUser, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUser/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUser, (AppUser) modelMap.get("appUser"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/appUser/update", modelMap.get("saveAction"));
		
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

		AppUser appUser = appUserFactoryForTest.newAppUser();
		
		Exception exception = new RuntimeException("test exception");
		when(appUserService.update(appUser)).thenThrow(exception);
		
		// When
		String viewName = appUserController.update(appUser, bindingResult, model, redirectAttributes, httpServletRequest);
		
		// Then
		assertEquals("appUser/form", viewName);

		Map<String,?> modelMap = model.asMap();
		
		assertEquals(appUser, (AppUser) modelMap.get("appUser"));
		assertEquals("update", modelMap.get("mode"));
		assertEquals("/appUser/update", modelMap.get("saveAction"));
		
		Mockito.verify(messageHelper).addException(model, "appUser.error.update", exception);
		
	}
	

	@Test
	public void deleteOK() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		Long id = appUser.getId();
		
		// When
		String viewName = appUserController.delete(redirectAttributes, id);
		
		// Then
		verify(appUserService).delete(id);
		assertEquals("redirect:/appUser", viewName);
		Mockito.verify(messageHelper).addMessage(redirectAttributes, new Message(MessageType.SUCCESS,"delete.ok"));
	}

	@Test
	public void deleteException() {
		// Given
		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
		
		AppUser appUser = appUserFactoryForTest.newAppUser();
		Long id = appUser.getId();
		
		Exception exception = new RuntimeException("test exception");
		doThrow(exception).when(appUserService).delete(id);
		
		// When
		String viewName = appUserController.delete(redirectAttributes, id);
		
		// Then
		verify(appUserService).delete(id);
		assertEquals("redirect:/appUser", viewName);
		Mockito.verify(messageHelper).addException(redirectAttributes, "appUser.error.delete", exception);
	}
	
	
}
