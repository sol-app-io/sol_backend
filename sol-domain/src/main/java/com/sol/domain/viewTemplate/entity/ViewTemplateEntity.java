package com.sol.domain.viewTemplate.entity;

import com.rcore.domain.commons.entity.BaseEntity;
import com.sol.domain.view.entity.View;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * View&#39;s templates
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class ViewTemplateEntity extends BaseEntity<String> {
    public enum Language {
        ENGLISH, I18
    }

    public enum OwnerType {
        BY_ADMIN, USERS
    }

    public enum Status {
        DRAFT, ACTIVE
    }

    /**
     * Owner
     */
    protected String ownerId;
    /**
     * Заголовок
     */
    protected String title;
    /**
     * description
     */
    protected String description;
    /**
     * Parent view
     */
    protected String createdFromViewId;
    /**
     * Status
     */
    protected Status status = Status.DRAFT;
    /**
     * Owner type
     */
    protected OwnerType ownerType = OwnerType.BY_ADMIN;
    /**
     * language
     */
    protected Language language = Language.ENGLISH;
    /**
     * view
     */
    protected View view = new View();
    /**
     * added by default
     */
    protected Boolean addByDefault = false;
    protected Boolean canEdit = false;


    public ViewTemplateEntity(String id) {
        this.id = id;
    }

}
