import type { GoodsInstance, Specification } from "./Goods";

class Plan {
    id: number;
    name: string;
    img: string;
    specification: Specification;
    maxLoadBearing: number;
    maxVolume: number;
    goodsList: GoodsInstance[];
    loadBearing: number;
    loadVolume: number = 0;
    constructor(id: number, name: string, img: string, specification: Specification, maxLoadBearing: number, goodsList: GoodsInstance[]) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.specification = specification;
        this.maxLoadBearing = maxLoadBearing;
        this.goodsList = goodsList;
        //初始化荷载
        this.loadBearing = this.countLoadBearing();
        this.maxVolume = this.specification.length * this.specification.width * this.specification.height;
    }

    countLoadBearing(): number {
        let count: number = 0;
        this.goodsList.forEach((item) => {
            count += item.template.specification.weight!
        })
        return count;
    }

    contLoadVolume() {
        let count: number = 0;
        this.goodsList.forEach((item) => {
            count += item.getVolume()
        })
        this.loadVolume = count;
    }

    addGoods(goods: GoodsInstance): boolean {
        console.log("即将添加的商品实例");

        if (goods.template.specification.weight! + this.loadBearing > this.maxLoadBearing || goods.getVolume() + this.loadVolume > this.maxVolume) {
            return false;
        }
        this.goodsList.push(goods)
        this.contLoadVolume();
        console.log("当前装载量" + this.loadVolume);
        return true;
    }

    removeGoods(instanceId: string) {
        this.goodsList = this.goodsList.filter(item => item.instanceId !== instanceId)
        this.contLoadVolume()
    }

    removeAllGoods() {
        this.goodsList = []
        this.contLoadVolume()
    }
}

export { Plan } 