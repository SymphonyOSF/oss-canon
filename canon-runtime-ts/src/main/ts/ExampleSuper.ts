import { BaseEntity } from "./BaseEntity";
import { EntityData } from "./EntityData";
import { IllegalArgumentException } from "./IllegalArgumentException";
import { ExampleSuperEntity } from "./ExampleSuperEntity";

export class ExampleSuper extends ExampleSuperEntity {
    constructor(entityData: EntityData) {
        super(entityData);

        console.log('ExampleSuper.init');
    }

    static Builder = class extends ExampleSuperEntity.AbstractBuilder {

        public withExtra(extra: string) {
            this.data.extra = extra;

            return this;
        }

        build(): ExampleSuper {
            return new ExampleSuper(new EntityData(this.data));
        }
    }
}