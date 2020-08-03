import { AbstractEntityData } from "./AbstractEntityData";
import { EntityData } from "./EntityData";

export class ArrayData extends AbstractEntityData{
    attributes: any[] = [];
    
    constructor(json: any[]) {
        super();
        json.forEach(value => {
            if(Array.isArray(value)) {
                this.attributes.push(new ArrayData(value));
            }
            else if(typeof value === 'object' && value !== null) {
                this.attributes.push(new EntityData(value));
            }
            else {
                this.attributes.push(value);
            }
        });
    }

    public serialize(indent: string, stringBuilder: string[]) {
        stringBuilder.push('[\n');
        const indent2 = indent + '  ';
        let cnt = 0;
        this.attributes.forEach(value => {
            if(cnt++ > 0) {
                stringBuilder.push(',\n');
            }
            if(typeof value === 'object' && value !== null && value instanceof AbstractEntityData) {
                stringBuilder.push(indent2);
                value.serialize(indent2, stringBuilder);
            }
            else {
                stringBuilder.push(indent2 + JSON.stringify(value));
            }
        });
        if(cnt > 0) {
            stringBuilder.push('\n');
        }
        stringBuilder.push(indent + ']');
    }
}