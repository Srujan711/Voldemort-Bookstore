<script setup lang="ts">
import {defineProps} from "vue"; // needed if you don't want an error below
import type {BookItem} from "@/types";
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";

const props = defineProps<{
  bookList: BookItem[];
}>();
</script>

<style scoped>
ul {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1em;
  gap: 1em;
}
</style>

<template>
  <ul>
    <template v-for="book in props.bookList" :key="book.bookId">
      <category-book-list-item :book="book"></category-book-list-item>
    </template>
  </ul>
</template>
