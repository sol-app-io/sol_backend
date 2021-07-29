package com.sol.domain.solUser.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Sol User
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SolUserEntity extends BaseEntity<String> {

    /************************************ Fields ************************************/

    /**
     * Email 
     */
    protected String email;
    /**
     * Username 
     */
    protected String username;
    /**
     * First Name 
     */
    protected String firstName;
    /**
     * Last Name 
     */
    protected String lastName;
    /**
     * User Pic 
     */
    protected String userPicId;
    /**
     * Profile settings 
     */
    protected Setting setting = new Setting();
    /**
     * Creds 
     */
    protected List<Credential> credentials = new ArrayList<>();

    /************************************ Constructors ************************************/

    public SolUserEntity(String id) {

        this.id = id;

    }

    /************************************ Methods ************************************/


}
