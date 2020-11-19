package com.amtinez.api.rest.crud.daos;

import com.amtinez.api.rest.crud.models.AuthorityModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amartinezcerro@gmail.com
 */
public interface AuthorityDao extends JpaRepository<AuthorityModel, Long> {

}
