<script setup lang="ts">
import { watch } from "vue";
import TheCategoryNav from "@/components/TheCategoryNav.vue";
import TheCategoryBookList from "@/components/TheCategoryBookList.vue";
import { useBookStore } from "@/stores/book";
import { useRoute } from "vue-router";
const bookStore = useBookStore();
const route = useRoute();

// watch(x, (newX) => {
//   console.log(`x is ${newX}`)
// })

watch(
    () => route.params.name,
    async (newCategoryName) => {
      await bookStore.fetchBooks(newCategoryName as string);
    },
    { immediate: true }
);
bookStore.fetchBooks(route.params.name as string);
</script>

<style scoped></style>
<template>
  <div>
    <the-category-nav></the-category-nav>
    <the-category-book-list></the-category-book-list>
  </div>
</template>