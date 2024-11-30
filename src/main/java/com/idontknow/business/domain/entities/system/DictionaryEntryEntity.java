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
@Table(name = DictionaryEntryEntity.TABLE_NAME)
public class DictionaryEntryEntity extends BaseEntity {
    public static final String TABLE_NAME = "sys_dictionary_entry";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    @Column(name = "entry_value", nullable = false)
    private String entryValue;

    @Column(name = "entry_label", nullable = false)
    private String entryLabel;

    @Column(name = "sort", nullable = false)
    private Integer sort;

    @Column(name = "remarks")
    private String remarks;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionary_id", nullable = false)
    private DictionaryEntity dictionary;
    // getters and setters
}
