<#if entity.externalType??>
<#include "/copyrightHeader.ftl"/>
<#include "/macros.ftl"/>
<@namespace name="Nonnull" import="javax.annotation.Nonnull"/>

/**
 * Builder helper function for the external type ${entity.externalType}.
 */
@<@namespace import="javax.annotation.concurrent.Immutable"/>
public class ${entity.camelCapitalizedName}${c}Builder
{
  /**
   * Constructor from a ${entity.externalType} value.
   *
   * @param value the value of the required instance.
   * 
   * @return A ${entity.externalType} deserialized from the given ${entity.persistedType} value.
   */
  public static ${entity.externalType} build(@${Nonnull} ${entity.persistedType} value)
  {
    return ${entity.externalType}.parse(value);
  }
  
  /**
   * Return the serialized form of the given ${entity.externalType} value.
   * 
   * @param instance An Instant value.
   * 
   * @return The serialized form of the given ${entity.externalType} value.
   */
  public static String to${entity.persistedType}(${entity.externalType} instance)
  {
    return instance.to${entity.simplePersistedType}();
  }
}
<#include "/footer.ftl">
</#if>