<script setup lang="ts">

</script>
<style scoped>
.confirmation-page{
  color: white;
  display: flex;
  flex-direction: column; /* Stack elements vertically */
  align-items: center;
  justify-content: center;
  height: 100vh;
}
.confirmation-page p{
  margin-bottom: 20px;
  font-size: 2em;
  font-weight: bold;
}

.confirmation-page button {
  font-size: 1.5em;
  background-color: var(--primary-color-footer);
}
</style>

<template>
  <div class="confirmation-page">
    <p>Your order is confirmed.</p>
    <router-link to="/category/${selectedCategoryName}">
      <button class="button cont-shopping-button">Continue Shopping</button>
    </router-link>
  </div>
</template>