package com.amtinez.api.rest.crud.daos;

import com.amtinez.api.rest.crud.models.AuthorityModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public interface AuthorityDao extends JpaRepository<AuthorityModel, Long> {

}
