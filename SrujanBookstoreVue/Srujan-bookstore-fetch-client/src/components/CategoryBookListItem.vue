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
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 1em;
  max-width: calc(200px + 0.8rem);
  background-color: var(--card-background-color);

  gap: 0.25em;
}
.book-image{
  height: auto;
  width: auto;
}
.book-image img{
  height: 200px;
  width: auto;
}
.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
}


.read-now-button-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 10%;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  background-color: rgba(0, 0, 0, 0); /* Transparent background by default */
  transition: background-color 0.3s;
}


.read-now-circle {
  width: 50px; /* Adjust the width as needed for the circle */
  height: 50px; /* Adjust the height as needed for the circle */
  background-color: var(--primary-color-footer); /* Circle background color */
  border-radius: 50%;
  color: #fff;
  text-align: center;
  font-size: 15px;
  cursor: pointer;
  transition: opacity 0.3s;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  white-space: nowrap;
  visibility: visible;
}

.read-now-button-container:hover .read-now-circle {
  opacity: 0;
  visibility: hidden;/* Semi-transparent background on hover */
}

.read-now-button {
  position: absolute;
  display: none;
  background-color: rgb(0,0,0,0.7);/* Button background color */
  color: #fff;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

.read-now-button-container:hover .read-now-button {
  display: block; /* Show the button on hover */
}

.read-now-button-container:hover .read-now-button:hover {
  background-color: var(--primary-color-footer);/* Change button color on hover */
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
    <div class="read-now-button-container" v-if="props.book.isPublic===true">
      <div class="read-now-circle">Read<br>Now</div>
      <button class="read-now-button">Read Now</button>
    </div>
    <div class="book-title">{{ book.title }}</div>
    <div class="book-author">{{ book.author }}</div>
    <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
    <button class="button"><i class="fas fa-shopping-cart" aria-hidden="true"></i>Add to Cart</button>
  </li>
</template>
