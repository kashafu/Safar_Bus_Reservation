package com.safar.repository;

import com.safar.entity.CurrentAdminSession;
import com.safar.entity.CurrentUserSession;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@EnableScan
@Repository
public interface CurrentUserSessionRepository extends PagingAndSortingRepository<CurrentUserSession, Integer> {
    public CurrentUserSession findByUuid(Integer uuid);
}
