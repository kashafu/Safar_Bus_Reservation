package com.safar.repository;

import com.safar.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@EnableScan
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    public User findByEmail(String email);

}
