<script setup lang="ts">
import type {BookItem} from "@/types";
const props = defineProps<{
    book: BookItem;
}>();
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images`;
const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
</script>

<style scoped>
</style>

<template>
    <div class="book-description">
        <div class="book-image">
        <img
          :src="`${bookImagePrefix}/${bookImageFileName(book)}`"
          :alt="book.title"
          width="200"
          height="auto"
        />
        </div>
    </div>
</template>