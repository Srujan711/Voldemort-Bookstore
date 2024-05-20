<script setup lang="ts">
import {inject} from "vue";
import type {CategoryItem} from "@/types";

const categoryList = inject("categoryList") as CategoryItem[];

</script>

<style scoped>
.category-buttons {
  display: flex;
  flex-direction: row;
  background-color: rgb(0 0 0 / 67%);
  text-align: center;
  transition-timing-function: ease;
  transition-duration: 0.9s;
  transition-delay: 0s;
}

.button.category-button.router-link-active {
  background-color: black;
}

.button.category-button {
  background-color: var(--neutral-color);
}

.button.category-button:hover,
.button.category-button:active {
  background-color: var(--primary-color-footer);
}

.sort-button-container {
  position: relative;
}

.sort-button {
  background-color: var(--primary-color-footer);
  color: white;
  border: none;
  cursor: pointer;
  padding: 8px 12px;
  position: revert;
  right: 0;
  z-index: 2;
}

.sort-dropdown {
  display: none;
  position: absolute;
  top: 100%;
  right: 0;
  color: black;
  background-color: rgb(113, 54, 36, 0.75);
  border: 1px solid #000000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 1;
}

.sort-dropdown li {
  color: black;
  padding: 8px 12px;
  list-style: none
}

.sort-dropdown li:hover{
  color: #f0f0f0;
  background-color: #333;
}

.sort-button-container:hover .sort-dropdown {
  display: block;
}

</style>

<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <li v-for="category in categoryList" :key="category.categoryId">
        <router-link
            :to="'/category/' + category.name"
            class="button category-button"
        >
          {{ category.name }}
        </router-link>
      </li>
      <li class="sort-button-container">
        <button class="button categories-button sort-button">
          Sort By: <i class="fas fa-caret-down"></i>
        </button>
        <ul class="sort-dropdown">
          <li><router-link to="/">Most Relevant</router-link></li>
          <li><router-link to="/">Price (Low to High)</router-link></li>
          <li><router-link to="/">Price (High to Low)</router-link></li>
        </ul>
      </li>
    </ul>

  </nav>
</template>
