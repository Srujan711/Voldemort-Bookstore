<script setup lang="ts">
import { useCategoryStore} from "@/stores/category";
const categoryStore = useCategoryStore();

</script>
<style scoped>
.header-dropdown {
  position: relative;
}

.categories-button {
  margin-top: 0.25em;
  background-color: var(--primary-color-dark);
}

.header-dropdown ul {
  display: none;
  background-color: white;
  color: black;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: lightgray;
}

.header-dropdown:hover ul {
  position: absolute;
  z-index: 1;
  display: block;
  min-width: 8em;
  cursor: pointer;
}
</style>

<template>
  <div class="header-dropdown">
    <button class="button categories-button">Categories <i class="fas fa-caret-down"></i></button>
    <ul>
      <li v-for="category in categoryStore.categoryList" :key="category.categoryId">
        <router-link :to="'/category/' + category.name">
          {{ category.name }}
        </router-link>
      </li>
    </ul>
  </div>
</template>
