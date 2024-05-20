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
.book-box {
  position: relative;
  display: inline-block;
  flex-direction: column;
  padding: 1em;

  background-color: var(--card-background-color);

  gap: 0.25em;
}

.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
}

.read-now-button{
  position: absolute;
  top: 0;
  left: 0;
  width: 76%;
  height: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: rgba(0, 0, 0, 0.7); /* Blurred black background */
  backdrop-filter: blur(10px); /* Apply blur effect */
  border-radius: 10px; /* Add curved corners */
  z-index: 2;
  opacity: 0; /* Initially hidden */
  transition: opacity 0.3s;
}

.read-now-button button{
  background-color: transparent;
  border: 2px solid #fff;
  color: #fff;
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
}

.book-box:hover .read-now-button {
  opacity: 1;
}
</style>

<template>
  <li class="book-box">
    <div class="book-image">
      <img
          :src="`${bookImagePrefix}/${bookImageFileName(book)}`"
          :alt="book.title"
      />
    </div>
    <div class="read-now-button" v-if="props.book.bookId===1001"><button>Read Now</button></div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <button class="button">Add to Cart</button>
  </li>
</template>
