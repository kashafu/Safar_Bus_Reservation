package com.safar.repository;

import com.safar.entity.Admin;
import com.safar.entity.User1;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableScan
@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {

    List<Admin> findByEmail(String email);

}
