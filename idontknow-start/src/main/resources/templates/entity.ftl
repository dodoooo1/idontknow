package ${packageName};

<#if imports??>
<#list imports as import>
import ${import};
</#list>
</#if>

/**
 * ${tableComment!''}
 * @author ${author!''}
 * @date ${.now?string('yyyy-MM-dd')}
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "${tableName}")
public class ${className} extends BaseEntity {
    public static final String TABLE_NAME = "${tableName}";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<#if fields??>
<#list fields as field>
    <#if field.comment??>
    /**
     * ${field.comment}
     */
    </#if>
    <#if field.nullable == false>
    @Column(name = "${field.columnName}", nullable = false)
    <#else>
    @Column(name = "${field.columnName}")
    </#if>
    private ${field.type} ${field.name};

</#list>
</#if>
} 