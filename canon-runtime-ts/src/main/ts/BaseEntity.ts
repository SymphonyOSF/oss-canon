import { inherits } from "util";
import { EntityData } from "./EntityData";

export class BaseEntity {
    readonly allAttributes: Map<string, any>;
    readonly unknownAttributes: Map<string, any>;


    constructor(entityData: EntityData) {
        this.allAttributes = entityData.allAttributes;
        this.unknownAttributes = entityData.unknownAttributes;
    }

    // protected init() {
    //     console.log('BaseEntity.init');
    // }

    // public serialize() {
    //     return 'this.serializeObject(this)';
    // }

    // public static serializeObject(obj: object) {
    //     let values = [];

    //     values.push('{');
    //     for (let [key, value] of BaseEntity.serializeObjectToMap(obj)) {
    //         values.push('"' + key + '": ' + value);
    //     }

    //     values.push('}');

    //     return values.join();
    // }

}