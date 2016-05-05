package com.cas.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cas.bean.AppUser;
import com.cas.bean.jpa.AppUserEntity;
import com.cas.bean.jpa.UserProfileEntity;
import com.cas.business.service.AppUserService;
import com.cas.business.service.mapping.AppUserServiceMapper;
import com.cas.data.repository.jpa.AppUserJpaRepository;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Resource
	private AppUserJpaRepository appUserJpaRepository;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId)
			throws UsernameNotFoundException {
		AppUserEntity user = appUserJpaRepository.findBySsoId(ssoId);	
		System.out.println("User : "+user);
		if(user==null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found"); 
		}
			return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(), 
				 user.getState().equals("Active"), true, true, true, getGrantedAuthorities(user));
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(AppUserEntity appUserEntity){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfileEntity userProfile : appUserEntity.getListOfUserProfile()){
			System.out.println("UserProfile : "+userProfile);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		System.out.print("authorities :"+authorities);
		return authorities;
	}
	
}
