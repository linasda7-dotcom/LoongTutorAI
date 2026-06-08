import { createRouter, createWebHashHistory, type RouteRecordRaw } from "vue-router";

import HomeView from '../views/home/index.vue'
import Train from '../views/train/index.vue'
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'home',
        component: HomeView,
    },
    {
        path: '/train',
        name: 'train',
        component: Train,
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router;