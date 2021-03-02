package com.amtinez.api.rest.crud.daos;

import com.amtinez.api.rest.crud.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public interface RoleDao extends JpaRepository<RoleModel, Long> {

}
