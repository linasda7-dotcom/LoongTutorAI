<template>
    <div class="container">
        <!-- top -->
        <div class="top">
            <div class="top-left">
                <img class="logo" :src="emergencysupplies" alt="" srcset="">
                <span class="title">应急物资填装模拟实训</span>
            </div>

            <div class="top-center">
                <span>选择物资</span>
                <span>填装模拟</span>
                <span>方案评估</span>
            </div>

            <div class="top-right">
                <span class="text-lg font-bold">帮助说明</span>
                <span>历史记录</span>
                <span>退出实训</span>
            </div>
        </div>

        <!-- body -->
        <div class="body">
            <!-- 物品栏 -->
            <div class="goods-panel">
                <div class="goods-title">
                    可选物资清单
                </div>
                <div class="goods-find">
                    <input placeholder="搜索物资“名称”" v-model="findName" @input="findGoodsByName(findName)" type="text">
                </div>
                <!-- 分类 -->
                <div class="goods-class">
                    <button v-for="item in goodsClass" :key="item" @click="setActive(item)" :class="{
                        active: goodsClassActive === item
                    }">{{ item }}</button>
                </div>
                <div class="goods-list">
                    <div class="goods" v-for="item in goods" :key="item.id">
                        <div class="goods-img">
                            <img :src="item.imgUrl" alt="" srcset="">
                        </div>
                        <div class="goods-param">
                            <span class="goods-name">{{ item.name }}</span>
                            <div class="goods-specification">
                                <span> {{ item.specification.length }}x{{ item.specification.width }}x{{
                                    item.specification.height }} cm</span>
                                <span>重量：{{ item.specification.weight }} kg</span>
                            </div>
                            <div class="goods-type">
                                <span class="goods-heavy" v-if="item.specification.weight! >= 30">重物</span>
                                <span class="g-class">{{ item.type }}</span>
                            </div>
                        </div>
                        <button @click="addGoods(item)">
                            <img :src="click" alt="" srcset="">
                            <span>添加</span>
                        </button>
                    </div>
                </div>
            </div>
            <!-- 3d视图 -->
            <div class="model">
                <div class="model-description">
                    <div class="container-data1">
                        <span class="container-data1-title">
                            装载容器信息
                        </span>
                        <div class="container-data1-view">
                            <img :src="container1" alt="" srcset="">
                            <div class="container-data1-data">
                                <span style="font-weight: 700;">{{ plan.name }}</span>
                                <span style="font-size: 1vh;">内径：{{ plan.specification.length }}x{{
                                    plan.specification.width }}x{{ plan.specification.height }}
                                    cm</span>
                                <span style="font-size: 1vh;">最大载重：{{ plan.maxLoadBearing }} kg</span>
                            </div>
                        </div>
                    </div>
                    <div class="container-data2">
                        <span class="container-data2-title">
                            当前装载情况
                        </span>
                        <div class="container-data2-view">
                            <div class="container-data2-loadVolume">
                                <div class="description">
                                    <img :src="volumeSvg" alt="">
                                    <div class="description-t">
                                        <span style="font: size 1.4vh; font-weight: 500;">已装体积</span>
                                        <span style="font-size:1.5vh;">{{ plan.loadVolume.toFixed(1)
                                        }} m³</span>
                                    </div>
                                </div>
                                <div class="loadProgress">
                                    <span>{{ Number((plan.loadVolume / plan.maxVolume) * 100).toFixed(1) }} %</span>
                                    <div class="progress">
                                        <div class="bar_g" :class="{
                                            bar_n: (plan.loadVolume / plan.maxVolume) > 0.7 && (plan.loadVolume / plan.maxVolume) < 0.9,
                                            bar_l: (plan.loadVolume / plan.maxVolume) >= 0.9 && (plan.loadVolume / plan.maxVolume) <= 1
                                        }" :style="{ width: (plan.loadVolume / plan.maxVolume) * 100 + '%' }">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="container-data2-loadBearing"></div>
                            <div class="container-data2-remaining-volume"></div>
                            <div class="container-data2-remaining-Bearing"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- agent聊天 -->
            <div class="agent">

            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import emergencysupplies from '@/assets/emergencysupplies.svg'
import food from '@/assets/food.png'
import water from '@/assets/water.png'
import fak from '@/assets/fak.png'
import container1 from '@/assets/box2.png'
import container2 from '@/assets/box3.png'
import click from '@/assets/click.svg'
import volumeSvg from '@/assets/volume.svg'
import { ref } from 'vue';
import type { GoodsTemplate, GoodsType } from '../../types/Goods';
import { GoodsInstance } from '../../types/Goods';
import { Plan } from '../../types/Plan'

const goodsClass: GoodsType[] = ["全部", "生活物资", "救援设备", "医疗物资", "防护设备"]
const goodsClassActive = ref<GoodsType>("全部")

const goodsList: GoodsTemplate[] = [
    {
        id: 0,
        name: '应急食品箱',
        imgUrl: food,
        specification: {
            length: 10,
            width: 10,
            height: 3,
            weight: 10
        },
        type: '生活物资'
    },
    {
        id: 1,
        name: '急救箱',
        imgUrl: fak,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 10
        },
        type: '医疗物资'
    },
    {
        id: 2,
        name: '水',
        imgUrl: water,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '医疗物资'
    },
    {
        id: 3,
        name: '应急食品箱',
        imgUrl: food,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '生活物资'
    },
    {
        id: 4,
        name: '急救箱',
        imgUrl: fak,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '医疗物资'
    },
    {
        id: 5,
        name: '水',
        imgUrl: water,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '医疗物资'
    },
    {
        id: 6,
        name: '应急食品箱',
        imgUrl: food,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '生活物资'
    },
    {
        id: 7,
        name: '急救箱',
        imgUrl: fak,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '医疗物资'
    },
    {
        id: 8,
        name: '水',
        imgUrl: water,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '医疗物资'
    },
    {
        id: 9,
        name: '应急食品箱',
        imgUrl: food,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '生活物资'
    },
    {
        id: 10,
        name: '急救箱',
        imgUrl: fak,
        specification: {
            length: 100,
            width: 100,
            height: 100,
            weight: 100
        },
        type: '医疗物资'
    },
    {
        id: 11,
        name: '水',
        imgUrl: water,
        specification: {
            length: 100,
            width: 100,
            height: 10,
            weight: 20,
        },
        type: '医疗物资'
    },
]

let goodsInstance = ref<GoodsInstance[]>([])

let goodsInstance1: GoodsInstance = new GoodsInstance(goodsList[0]);
let Plan1 = ref<Plan>(new Plan(1, "方案1", container2, { length: 10, width: 10, height: 10 }, 75, [...goodsInstance.value]))
Plan1.value.addGoods(goodsInstance1)
let Plan2 = ref<Plan>(new Plan(2, "方案2", container2, { length: 100, width: 100, height: 100 }, 100, [...goodsInstance.value]))
let Plan3 = ref<Plan>(new Plan(3, "方案3", container2, { length: 100, width: 100, height: 100 }, 200, [...goodsInstance.value]))

let plans = ref<Plan[]>([Plan1.value, Plan2.value, Plan3.value])

let plan = ref<Plan>(plans.value[0])
let goods = ref<GoodsTemplate[]>(goodsList);
let findName = ref<string>("")

function findGoodsByName(name: string) {
    if (!name) {
        goods.value = goodsList;
        return;
    }
    const g: GoodsTemplate[] = goodsList.filter(goods => goods.name.includes(name));

    goods.value = g;
}


function addGoods(goods: GoodsTemplate) {
    const goodsInstance: GoodsInstance = new GoodsInstance(goods);
    if (plan.value.addGoods(goodsInstance)) {
        console.log("添加成功");
        alert("添加成功")
    } else {
        console.log("添加失败");
        alert("添加失败")
    }
}

function findGoodsByClass(cls: GoodsType) {
    if (cls === "全部") {
        goods.value = goodsList;
        return;
    }
    const g: GoodsTemplate[] = goodsList.filter(goods => goods.type.includes(cls));
    goods.value = g;
}

function setActive(item: GoodsType) {
    goodsClassActive.value = item;
    findGoodsByClass(item)
}
</script>

<style scoped lang="scss">
@use './index.scss'
</style>