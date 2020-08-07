<#if ! model.isAbstract?? || ! model.isAbstract?c>
<#include "/template/ts/Object/ObjectHeader.ftl">

<#list model.fields as field>
  <@setJavaType field/>
  readonly ${field.camelName}: ${fieldType};
</#list>

  /**
   * Constructor.
   * 
   * @param entityData Parsed JSON.
   */
  constructor(entityData: EntityData) {
    super(entityData);
    
<#list model.fields as field>
    <@setJavaType field/>
    this.${field.camelName} = entityData.get('${field.camelName}');
<#if requiresChecks>
<@checkLimits "    " field  field.camelName/>
 
<#else>
  <#if field.required>
    if(${field.camelName} == null)
      throw new IllegalArgumentException("${field.camelName} is required.");
  </#if>
</#if>
</#list>
  }
  

<#list model.fields as field>
  <@setJavaType field/>
  
  public get${field.camelCapitalizedName}(): ${fieldType}
  {
    return this.${field.camelName};
  }
  <#switch field.elementType>
    <#case "OneOf">
      
  public class ${field.camelCapitalizedName}Entity
  {
    private final discriminator: string;
    private final payload: any;
  
    public ${field.camelCapitalizedName}Entity(Object payload)
    {
      if(payload == null)
      {
        throw new IllegalArgumentException("OneOf payload cannot be null");
      }
      <#list field.children as ref>
      else if(payload instanceof ${fieldType})
      {
        <@setJavaType ref/>
        <@checkLimits "        " ref "(${fieldType})payload"/>
        _payload_ = ${javaTypeCopyPrefix}payload${javaTypeCopyPostfix};
        _discriminator_ = "${ref.name}";
      }
      </#list>
      else
      {
        throw new IllegalArgumentException("Unknown payload type \"" + payload.getClass().getName() + "\"");
      }
    }
    public Object getPayload()
    {
      return _payload_;
    }
    
    public String getDiscriminator()
    {
      return _discriminator_;
    }
  }
      <#break>
    </#switch>
    
</#list>

<#include "/template/ts/Object/ObjectBody.ftl">
  

<#------------------------------------------------------------------------------------------------------------------------------

 Factory
 
-------------------------------------------------------------------------------------------------------------------------------> 
  
  /**
   * Factory class for ${modelJavaClassName}.
   */
  static Factory = class {
    /**
     * Return the type identifier (_type JSON attribute) for entities created by this factory.
     * 
     * @return The type identifier for entities created by this factory.
     */
    public getCanonType()
    {
      return ${modelJavaClassName}Entity.TYPE_ID;
    }
    
    /**
     * Return the type version (_version JSON attribute) for entities created by this factory.
     * 
     * @return The type version for entities created by this factory.
     */
    public getCanonVersion()
    {
      return ${modelJavaClassName}Entity.TYPE_VERSION;
    }
    
    /**
     * Return the major type version for entities created by this factory.
     * 
     * @return The major type version for entities created by this factory.
     */
    public getCanonMajorVersion()
    {
      return ${modelJavaClassName}Entity.TYPE_MAJOR_VERSION;
    }
    
    /**
     * Return the minjor type version for entities created by this factory.
     * 
     * @return The minor type version for entities created by this factory.
     */
    public getCanonMinorVersion()
    {
      return ${modelJavaClassName}Entity.TYPE_MINOR_VERSION;
    }
        
    /**
     * Return a new entity instance created from the given JSON serialization.
     * 
     * @param entityData Parsed JSON.
     * 
     * @return An instance of the entity represented by the given serialized form.
     * 
     * @throws IllegalArgumentException If the given JSON is not valid.
     */
    public newInstance(entityData: EntityData): ${model.camelCapitalizedName}
    {
      return new ${model.camelCapitalizedName}(entityData);
    }
  }
 
  
<#------------------------------------------------------------------------------------------------------------------------------

 AbstractBuilder
 
-------------------------------------------------------------------------------------------------------------------------------> 
  <#if model.baseSchema.isGenerateBuilderFacade>
    <#assign AbstractBuilder="Abstract${modelJavaClassName}EntityBuilder"/>
  <#else>
    <#assign AbstractBuilder="Abstract${modelJavaClassName}Builder"/>
  </#if>
  
  /**
   * Abstract builder for ${modelJavaClassName}. If there are sub-classes of this type then their builders sub-class this builder.
   *
   * @param <B> The concrete type of the builder, used for fluent methods.
   * @param <T> The concrete type of the built object.
   */
   static ${AbstractBuilder} = class 
  <#if model.superSchema??>
    extends ${model.superSchema.baseSchema.camelCapitalizedName}.Abstract${model.superSchema.baseSchema.camelCapitalizedName}Builder
  </#if>
  {
    data: any = {
        "_type":${modelJavaClassName}Entity.TYPE_ID,
        "_version":${modelJavaClassName}Entity.TYPE_VERSION 
    };
  <#list model.fields as field>
    <@setJavaType field/>
    protected ${field.camelName}: ${fieldType}${javaBuilderTypeNew};
  </#list>
    
<#-- --------  
    public B withValues(ImmutableJsonObject jsonObject, boolean ignoreValidation, IModelRegistry modelRegistry)
    {
<#if model.superSchema??>
      super.withValues(jsonObject, ignoreValidation, modelRegistry);
</#if>    
<#list model.fields as field>
      if(jsonObject.containsKey("${field.camelName}"))
      {
        IJsonDomNode  node = jsonObject.get("${field.camelName}");
  <@generateCreateFieldFromJsonDomNode "        " field "_${field.camelName}_" "if(!ignoreValidation)" "Mutable"/>
      }
</#list>
      return self();
    }
    
    public void populateAllFields(List<Object> result)
    {
<#if model.superSchema??>
      super.populateAllFields(result);
</#if>    
<#list model.fields as field>
      result.add(_${field.camelName}_);
</#list>
    }
     -->
  <#list model.fields as field>
    <@setJavaType field/>
    
    <#-- public ${fieldType} get${field.camelCapitalizedName}()
    {
      return _${field.camelName}_;
    } -->
  
    public with${field.camelCapitalizedName}(value: ${fieldType})
    {
    <@checkLimits "        " field "value"/>
      this.${field.camelName} = value;
      return this;
    }
    <#if field.isArraySchema && ! field.isComponent>
  
    public with${field.camelCapitalizedName}(value: ${fieldElementType})
    {
    <@checkLimits "        " field "value"/>
      this.${field.camelName}.add(value);
      return this;
    }
    </#if>
    <#if field.isTypeDef>
    
    public with${field.camelCapitalizedName}(value: ${javaFieldClassName})
    {
      this.${field.camelName} = ${javaConstructTypePrefix}value${javaConstructTypePostfix};
      return this;
    }
    </#if>
  </#list>
  
  
    /**
     * Return the type identifier (_type JSON attribute) for entities created by this builder.
     * 
     * @return The type identifier for entities created by this factory.
     */
    public getCanonType()
    {
      return ${modelJavaClassName}Entity.TYPE_ID;
    }
    
    /**
     * Return the type version (_version JSON attribute) for entities created by this builder.
     * 
     * @return The type version for entities created by this factory.
     */
    public getCanonVersion()
    {
      return ${modelJavaClassName}Entity.TYPE_VERSION;
    }
    
  <#-- 
    @Override 
    public ImmutableJsonObject getJsonObject()
    {
      MutableJsonObject jsonObject = new MutableJsonObject();
      
      jsonObject.addIfNotNull(CanonRuntime.JSON_TYPE, ${modelJavaClassName}Entity.TYPE_ID);
      jsonObject.addIfNotNull(CanonRuntime.JSON_VERSION, ${modelJavaClassName}Entity.TYPE_VERSION);

      getJsonObject(jsonObject);
  
      return jsonObject.immutify();
    }
    
    @Override 
    public void getJsonObject(MutableJsonObject jsonObject)
    {
      super.getJsonObject(jsonObject);
  <#list model.fields as field>
    <@setJavaType field/>
  
      if(get${field.camelCapitalizedName}() != null)
      {
        <@generateCreateJsonDomNodeFromField "          " field "jsonObject"/>
      }
  </#list>
    }
        
    /**
     * Return the type id (_type JSON attribute) for this entity.
     * 
     * @return The type id for this entity.
     */
    public getCanonType()
    {
      return TYPE_ID;
    }
    
    /**
     * Return the type version (_version JSON attribute) for this entity.
     * 
     * @return The type version for this entity.
     */
    public getCanonVersion()
    {
      return TYPE_VERSION;
    }
    
    /**
     * Return the major type version for entities created by this factory.
     * 
     * @return The major type version for entities created by this factory.
     */
    public getCanonMajorVersion()
    {
      return TYPE_MAJOR_VERSION;
    }
    
    /**
     * Return the minjor type version for entities created by this factory.
     * 
     * @return The minor type version for entities created by this factory.
     */
    public getCanonMinorVersion()
    {
      return TYPE_MINOR_VERSION;
    } -->
  }
  
  
  
<#------------------------------------------------------------------------------------------------------------------------------

 Builder
 
------------------------------------------------------------------------------------------------------------------------------->
   
  /**
   * Builder for ${modelJavaClassName}
   *
   */
  static Builder = class extends ${modelJavaClassName}.Abstract${modelJavaClassName}Builder
  {

<#-------------
    /**
     * Constructor initialised from another object instance.
     * 
     * @param initial An instance of the built type from which values are to be initialised.
     */
    public Builder(I${modelJavaClassName}Entity initial)
    {
      super(Builder.class, initial);
    }
---------------> 
    protected construct()
    {
      return new ${model.camelCapitalizedName}(new EntityData(this));
    }
  }
}

<#include "/template/ts/canon-template-ts-Epilogue.ftl">
</#if>