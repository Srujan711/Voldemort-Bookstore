<script setup lang="ts">
import type { BookItem } from "@/types";
import TheCategoryNav from "@/components/TheCategoryNav.vue";
import TheCategoryBookList from "@/components/TheCategoryBookList.vue";
import { useRoute } from "vue-router";
const route = useRoute();
let response = await fetch(`http://localhost:8080/SrujanBookstoreFetch/api/categories/name/${route.params.name}/books/`);
let data = await response.json();
let bookList = data as BookItem[];
</script>

<style scoped></style>
<template>
  <div>
    <the-category-nav></the-category-nav>
    <the-category-book-list :bookList="bookList"> </the-category-book-list>
  </div>
</template>