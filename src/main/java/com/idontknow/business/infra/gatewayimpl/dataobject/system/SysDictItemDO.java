package com.idontknow.business.infra.gatewayimpl.dataobject.system;

import com.idontknow.business.infra.gatewayimpl.dataobject.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = SysDictItemDO.TABLE_NAME)
public class SysDictItemDO  extends BaseEntity {
    public static final String TABLE_NAME = "sys_dict_item";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dict_id", nullable = false)
    private SysDictDO dict;

    @Column(name = "item_value", nullable = false)
    private String itemValue;

    @Column(name = "item_label", nullable = false)
    private String itemLabel;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Column(name = "remarks")
    private String remarks;

    // getters and setters
}
