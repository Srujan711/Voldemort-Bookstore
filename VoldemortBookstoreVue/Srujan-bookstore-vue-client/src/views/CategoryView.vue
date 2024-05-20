<script setup lang="ts">
import {provide} from "vue";
import sourceData from "@/db.json";
import type {BookItem} from "@/types";
import TheCategoryNav from "@/components/TheCategoryNav.vue";
import TheCategoryBookList from "@/components/TheCategoryBookList.vue";

const bookList = sourceData.books as BookItem[];
provide("bookList", bookList);
</script>

<style scoped></style>

<template>
  <div>
    <the-category-nav></the-category-nav>
    <the-category-book-list :bookList="bookList"></the-category-book-list>
  </div>
</template>
