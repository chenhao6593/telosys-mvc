package com.cas.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.cas.bean.jpa.AppUserUserProfileEntity;
import com.cas.bean.jpa.AppUserUserProfileEntityKey;

/**
 * Repository : AppUserUserProfile.
 */
public interface AppUserUserProfileJpaRepository extends PagingAndSortingRepository<AppUserUserProfileEntity, AppUserUserProfileEntityKey> {

}
