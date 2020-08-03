import { BaseEntity } from "./BaseEntity";
import { EntityData } from "./EntityData";
import { IllegalArgumentException } from "./IllegalArgumentException";

export class Entity extends BaseEntity {
    public static readonly JSON_TYPE = "_type";
    public static readonly JSON_VERSION = "_version";

    public readonly canonType: string;
    public readonly canonVersion: string ;
    public readonly canonMajorVersion: number;
    public readonly canonMinorVersion: number;

    constructor(entityData: EntityData) {
        super(entityData);

        console.log('Entity.init');

        this.canonType = entityData.getString(Entity.JSON_TYPE);
        this.canonVersion = entityData.getString(Entity.JSON_VERSION);
        let i = this.canonVersion.indexOf('.');
      
        if(i == -1)
            throw new IllegalArgumentException("Version must be of the form Magor.Minor not \"" + this.canonVersion + "\"");
      
        this.canonMajorVersion = Number(this.canonVersion.substring(0,i));
        this.canonMinorVersion = Number(this.canonVersion.substring(i+1));

        if(isNaN(this.canonMajorVersion) || isNaN(this.canonMinorVersion))
            throw new IllegalArgumentException("Version must be of the form Magor.Minor not \"" + this.canonVersion + "\"");
    }
}