/*
 * Created on 4 May 2016 ( Time 15:52:08 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.cas.web.listitem;

import com.cas.bean.AppUser;
import com.cas.web.common.ListItem;

public class AppUserListItem implements ListItem {

	private final String value ;
	private final String label ;
	
	public AppUserListItem(AppUser appUser) {
		super();

		this.value = ""
			 + appUser.getId()
		;

		//TODO : Define here the attributes to be displayed as the label
		this.label = appUser.toString();
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
