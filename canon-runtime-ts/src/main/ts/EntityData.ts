import { AbstractEntityData } from "./AbstractEntityData";
import { ArrayData } from "./ArrayData";

export class EntityData extends AbstractEntityData{
    allAttributes: Map<string, any>;
    unknownAttributes: Map<string, any>;

    constructor(json: object) {
        super();
        this.allAttributes = EntityData.serializeObjectToMap(json);
        this.unknownAttributes = new Map();

        for (let [key, value] of this.allAttributes) {
            this.unknownAttributes.set(key, value);
        }
    }

    public serialize(indent: string, stringBuilder: string[]) {
        stringBuilder.push('{\n');
        const indent2 = indent + '  ';
        let cnt = 0;
        for (let [key, value] of this.allAttributes) {
            if(cnt++ > 0) {
                stringBuilder.push(',\n');
            }
            if(typeof value === 'object' && value !== null && value instanceof AbstractEntityData) {
                stringBuilder.push(indent2 + '"' + key + '":');
                value.serialize(indent2, stringBuilder);
            }
            else if(typeof value === 'bigint' && value !== null) {
                stringBuilder.push(indent2 + '"' + key + '":' + value);
            }
            else {

                stringBuilder.push(indent2 + '"' + key + '":' + JSON.stringify(value));
            }
        }
        if(cnt > 0) {
            stringBuilder.push('\n');
        }
        stringBuilder.push(indent + '}');
    }

    public static serializeObjectToMap(obj: object) {

        const sortedMap = new Map([...Object.entries(obj)].sort((a, b) => a[0] > b[0] ? 1 : -1));
        // Some sort function comparing keys with a[0] b[0] or values with a[1] b[1]
        // Be sure to return -1 if lower and, if comparing values, return 0 if equal
        // sort values:
        // new Map([...map].sort((a, b) => (a[1] > b[1] && 1) || (a[1] === b[1] ? 0 : -1)))
        // sort keys:
        // new Map([...map].sort((a, b) => a[0] > b[0] ? 1 : -1))
      
        for (let [key, value] of sortedMap) {
            if(Array.isArray(value)) {
                sortedMap.set(key, new ArrayData(value));
            }
            else if(typeof value === 'object' && value !== null) {
                sortedMap.set(key, new EntityData(value));
            }
        }

        return sortedMap;
    }

    get(name: string): any {
        this.unknownAttributes.delete(name);
        let vv = this.allAttributes.get(name);

        return vv;
    }

    getString(name: string): string {
        this.unknownAttributes.delete(name);
        let vv = this.allAttributes.get(name);

        return vv as string;
    }

    getArray(name: string): any[] {
        this.unknownAttributes.delete(name);
        let vv = this.allAttributes.get(name);

        return vv as any[];
    }
}