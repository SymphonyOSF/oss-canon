export abstract class AbstractEntityData {
    public abstract serialize(indent: string, stringBuilder: string[]): void;

    public toString() {

        let stringBuilder: string[] = [];

        this.serialize('', stringBuilder);
        stringBuilder.push('\n');

        return stringBuilder.join('');
    }
}