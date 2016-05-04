package com.cas.data.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.cas.bean.jpa.AppUserEntity;

/**
 * Repository : AppUser.
 */
public interface AppUserJpaRepository extends PagingAndSortingRepository<AppUserEntity, Long> {
	AppUserEntity findBySsoId(String ssoId);
}
