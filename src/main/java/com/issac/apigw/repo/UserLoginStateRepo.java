/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.apigw.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.issac.apigw.entity.UserLoginState;

/**
 * @author issac
 *
 */
@Repository
public interface UserLoginStateRepo extends JpaRepository<UserLoginState, String> {

}
