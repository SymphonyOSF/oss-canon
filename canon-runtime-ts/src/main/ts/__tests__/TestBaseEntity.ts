import { BaseEntity } from "../BaseEntity";
import { Entity } from "../Entity";
import { EntityData } from "../EntityData";

test('BaseEntity test', () => {
    console.log('Hello Test v2');

    var json2 = {
        lastName: 'Skingle',
        firstName: 'Bruce',
        address: {
            line4: 'United Kingdom',
            line2: 'Short Lane',
            line3: 'Somewhereshire',
            line1: '999 Letsbe Avenue'
        },
        anArray: [1, 1, 2, 3, 5, 8],
        aSet: [11, 1, 7, 3, 5]
    };

    let incoming = "{\"_type\":\"com.symphony.oss.canon.test.typeCheck.ObjectWithInts\",\"_version\":\"1.0\",\"lsstName\":\"Bloggs\",\"firstName\":\"Fred\",\"intListField\":[3,2,7,3],\"intSetField\":[7,3,2,7,7,3]}";
    let json = JSON.parse(incoming);
    let entityData = new EntityData(json);
    
    const entity: Entity = new Entity(entityData);
    //     //JSON.parse('{ "myString": "string", "myNumber": 4 }');
    //     new BaseEntity();
    //      // obj as unknown as BaseEntity;

    // console.log('entity=' + entity);
    // console.log('entity=' + JSON.stringify(entity));
    // console.log('entity.canonType=' + entity.canonType);
    // console.log('entity.canonMajorVersion=' + entity.canonMajorVersion);
    // console.log('entity.canonMinorVersion=' + entity.canonMinorVersion);

    // console.log('Unknown attributes');
    // for (let [key, value] of entity.unknownAttributes) {
    //     console.log(key + '=' + value);
    // }
});
