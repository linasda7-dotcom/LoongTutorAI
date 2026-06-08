<template>
    <button @click="goTrain()">进入实训</button>
</template>
<script setup lang="ts">
import { useRouter, type Router } from 'vue-router';
const router: Router = useRouter()
function goTrain() {
    router.push('train')
}
</script>