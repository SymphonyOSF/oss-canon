import { BaseEntity } from "../BaseEntity";
import { Entity } from "../Entity";
import { EntityData } from "../EntityData";
import { ExampleSuper } from "../ExampleSuper";

test('SimpleObject test', () => {
    let incoming = "{\n" + 
    "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.SimpleObject\",\n" + 
    "  \"_version\":\"1.0\",\n" + 
    "  \"name\":\"SquareOfTwo\",\n" + 
    "  \"value\":4\n" + 
    "}\n";
    let json = JSON.parse(incoming);
    let entityData = new EntityData(json);

    // console.log('name=' + entityData.allAttributes.get('name'));
    // console.log('value=' + entityData.allAttributes.get('value'));

    expect(entityData.allAttributes.get('name')).toBe('SquareOfTwo');
    expect(entityData.allAttributes.get('value')).toBe(4);

    let exampleEntity = new ExampleSuper(entityData);

    console.log('exampleEntity = ' + exampleEntity);

    let builderA = new ExampleSuper.Builder()
        .withName('SquareOfThree')
        .withExtra('extraValue')
        ;

    let builderB = builderA
            .withValue(BigInt(9))
        ;
        
    let exampleEntity2: ExampleSuper = builderB.build();

    console.log('exampleEntity2 = ' + exampleEntity2);
  });


  test('NestedObject test', () => {
    let incoming = "{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.AllTheTypes\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"int32Field\":7,\n" + 
        "  \"int64Field\":5,\n" + 
        "  \"intField\":3,\n" + 
        "  \"objectField\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon.test.typeCheck.SimpleObject\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"name\":\"SquareOfTwo\",\n" + 
        "    \"value\":4\n" + 
        "  }\n" + 
        "}\n";
        let json = JSON.parse(incoming);
        let entityData = new EntityData(json);
    
        // console.log('int32Field=' + entityData.allAttributes.get('int32Field'));
        // console.log('objectField=' + entityData.allAttributes.get('objectField'));
    
        expect(entityData.allAttributes.get('int32Field')).toBe(7);
        expect(entityData.allAttributes.get('objectField')).toBeInstanceOf(EntityData);

        console.log('entityData.toString()=>>>>' + entityData.toString() + '<<<<');

        expect(entityData.toString()).toBe("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.AllTheTypes\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"int32Field\":7,\n" + 
        "  \"int64Field\":5,\n" + 
        "  \"intField\":3,\n" + 
        "  \"objectField\":{\n" + 
        "    \"_type\":\"com.symphony.oss.canon.test.typeCheck.SimpleObject\",\n" + 
        "    \"_version\":\"1.0\",\n" + 
        "    \"name\":\"SquareOfTwo\",\n" + 
        "    \"value\":4\n" + 
        "  }\n" + 
        "}\n");
      });


  test('ListAndSet test', () => {
        let incoming = "{\"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\"_version\":\"1.0\",\"lsstName\":\"Bloggs\",\"firstName\":\"Fred\",\"intListField\":[3,2,7,3],\"intSetField\":[7,3,2,7,7,3]}";
        let json = JSON.parse(incoming);
        let entityData = new EntityData(json);
    
        let intListField = entityData.getArray('intSetField');
        //expect(entityData.getArray('intSetField')).toBe([1,3,5]);
        
        console.log('ListAndSet entityData.toString()=>>>>' + entityData.toString() + '<<<<');

        expect(entityData.toString()).toBe("{\n" + 
        "  \"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\n" + 
        "  \"_version\":\"1.0\",\n" + 
        "  \"firstName\":\"Fred\",\n" + 
        "  \"intListField\":[\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"intSetField\":[\n" + 
        "    7,\n" + 
        "    3,\n" + 
        "    2,\n" + 
        "    7,\n" + 
        "    7,\n" + 
        "    3\n" + 
        "  ],\n" + 
        "  \"lsstName\":\"Bloggs\"\n" + 
        "}\n");
      });