package com.idontknow.business.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idontknow.business.domain.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;

import static com.idontknow.business.domain.entities.ApiKey.TABLE_NAME;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = TABLE_NAME)
public class ApiKey extends BaseEntity {
  public static final String TABLE_NAME = "api_key";

  @Serial private static final long serialVersionUID = -3552577854495026179L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = TABLE_NAME)
  private Long id;

  @JoinColumn(nullable = false)
  private Long companyId;

  @Column(nullable = false)
  private String name;

  @JsonIgnore
  @Column(unique = true, nullable = false)
  private String key;

  @Column(nullable = false)
  private Boolean isActive;

  @Override
  public Long getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "Apikey{"
        + "id="
        + this.id
        + ", companyId="
        + this.companyId
        + ", name='"
        + this.name
        + "', isActive="
        + this.isActive
        + "', createdBy="
        + this.getCreatedBy()
        + ", updatedBy="
        + this.getUpdatedBy()
        + "', createdAt="
        + this.getCreatedAt()
        + ", updatedAt="
        + this.getUpdatedAt()
        + '}';
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final ApiKey other)) {
      return false;
    }
    return this.getId() != null && this.getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return this.getClass().hashCode();
  }
}
