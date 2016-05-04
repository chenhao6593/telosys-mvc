package com.cas.data.repository.jpa;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cas.bean.AppUser;
import com.cas.bean.jpa.UserProfileEntity;

/**
 * Repository : UserProfile.
 */
public interface UserProfileJpaRepository extends PagingAndSortingRepository<UserProfileEntity, Long> {
}
