import { BaseEntity } from "./BaseEntity";
import { EntityData } from "./EntityData";
import { IllegalArgumentException } from "./IllegalArgumentException";
import { Entity } from "./Entity";

export class ExampleSuperEntity extends Entity {
    public readonly name: string;
    public readonly value: bigint ;
    
    constructor(entityData: EntityData) {
        super(entityData);

        console.log('ExampleEntity.init');

        this.name = entityData.getString('name');
        this.value = entityData.get('value');
    }

    static AbstractBuilder = class {
        data: any = {
            "_type":"com.symphony.oss.canon.test.typeCheck.SimpleObject",
            "_version":"1.0" 
        };

        public withName(name: string) {
            this.data.name = name;

            return this;
        }

        public withValue(value: bigint) {
            this.data.value = value;

            return this;
        }
    }
}