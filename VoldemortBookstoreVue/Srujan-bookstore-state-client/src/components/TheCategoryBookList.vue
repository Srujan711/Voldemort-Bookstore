<script setup lang="ts">
import CategoryBookListItem from "@/components/CategoryBookListItem.vue";
import { useBookStore } from "@/stores/book";
const bookStore = useBookStore();

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
    <template v-for="book in bookStore.bookList" :key="book.bookId">
      <category-book-list-item :book="book"></category-book-list-item>
    </template>
  </ul>
</template>
