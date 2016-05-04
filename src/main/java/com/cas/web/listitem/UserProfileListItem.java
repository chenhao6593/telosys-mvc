/*
 * Created on 4 May 2016 ( Time 15:52:09 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.web.listitem;

import com.cas.bean.UserProfile;
import com.cas.web.common.ListItem;

public class UserProfileListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public UserProfileListItem(UserProfile userProfile) {
		super();

		this.value = ""
			 + userProfile.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = userProfile.toString();
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getLabel() {
		return label;
	}

}