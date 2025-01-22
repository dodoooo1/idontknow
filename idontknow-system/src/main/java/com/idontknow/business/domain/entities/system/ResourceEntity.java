package com.idontknow.business.domain.entities.system;

import com.idontknow.business.domain.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = ResourceEntity.TABLE_NAME)
public class ResourceEntity extends BaseEntity {
    public static final String TABLE_NAME = "sys_resource";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "component", nullable = false)
    private String component;

    @Column(name = "is_route")
    private Boolean isRoute;

    @Column(name = "component_name", nullable = false)
    private String componentName;

    @Column(name = "redirect")
    private String redirect;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "icon")
    private String icon;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    @Column(name = "keep_alive")
    private Boolean keepAlive;

    @Column(name = "hidden")
    private Boolean hidden;

    @Column(name = "hide_tab")
    private Boolean hideTab;

    @Column(name = "description")
    private String description;

    @Column(name = "remarks")
    private String remarks;


    @Column(name = "open_type")
    private String openType;


    // getters and setters
}
