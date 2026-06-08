type GoodsType = "全部" | "生活物资" | "救援设备" | "医疗物资" | "防护设备"
/**
 *物品规格
 */
interface Specification {
    length: number;
    width: number;
    height: number;
    weight?: number;
}
/**
 * 物品信息
 */
interface GoodsTemplate {
    id: number;
    name: string;
    type: GoodsType;
    imgUrl: string;
    specification: Specification
}

export class GoodsInstance {
    instanceId: string;
    template: GoodsTemplate;

    position = { x: 0, y: 0, z: 0 };
    rotation = { x: 0, y: 0, z: 0 };

    constructor(template: GoodsTemplate) {
        this.instanceId = this.instanceId =
            Date.now().toString() +
            Math.random().toString(16).slice(2);
        this.template = template
    }

    /** 
     * 获取体积 
     * @returns 体积
     */
    getVolume(): number {
        const spec: Specification = this.template.specification;

        return (spec.length * spec.weight! * spec.height)
    }

    //移动 
    move(x: number, y: number, z: number) {
        this.position.x = x
        this.position.y = y
        this.position.z = z
    }

    //旋转
    rotate(x: number, y: number, z: number) {
        this.rotation.x = x
        this.rotation.y = y
        this.rotation.z = z
    }
}

export type { GoodsTemplate, Specification, GoodsType }  