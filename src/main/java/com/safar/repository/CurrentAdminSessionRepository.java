package com.safar.repository;

import com.safar.entity.CurrentAdminSession;
import com.safar.entity.CurrentUserSession;
import com.safar.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@EnableScan
@Repository
public interface CurrentAdminSessionRepository extends PagingAndSortingRepository<CurrentAdminSession, Integer> {
    public CurrentAdminSession findByAid(String aid);
}
