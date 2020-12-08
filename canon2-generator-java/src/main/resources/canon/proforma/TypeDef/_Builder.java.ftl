<#if entity.fullyQualifiedExternalType??>
<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<@namespace name="javaType" import=entity.fullyQualifiedJavaType/>
<@namespace name="externalType" import=entity.fullyQualifiedExternalType/>
<@namespace name="Nonnull" import="javax.annotation.Nonnull"/>

/**
 * Builder helper function for the external type ${externalType}.
 */
@<@namespace import="javax.annotation.concurrent.Immutable"/>
public class ${entity.camelCapitalizedName}${c}Builder
{
  /**
   * Constructor from a ${javaType} value.
   *
   * @param value the value of the required instance.
   * 
   * @return A ${externalType} deserialized from the given ${javaType} value.
   */
  public static ${externalType} build(@${Nonnull} ${javaType} value)
  {
    return ${externalType}.parse(value);
  }
  
  /**
   * Return the serialized form of the given ${externalType} value.
   * 
   * @param instance An Instant value.
   * 
   * @return The serialized form of the given ${externalType} value.
   */
  public static String to${javaType}(${externalType} instance)
  {
    return instance.toString();
  }
}
<#include "/footer.ftl">
</#if>