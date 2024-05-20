<script setup lang="ts"></script>

<style scoped>
.bg-404 {
  position: relative;
  background-attachment: fixed;
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
  min-height: 650px;
}
.dark-overlay {
  background-color: rgba(0, 0, 0, 0.505);
}
.empty-container {
  padding: 150px 0 0 150px;
  min-height: 600px;
}
h1 {
  padding-top: 2px;
  font-size: 3rem;
  color: rgb(242, 242, 242);
  margin-bottom: 20px;
}

.cont-shopping-button{
  background: #A67568;
  color: black;
}
</style>

<template>
  <section class="bg-404">
    <div class="dark-overlay">
      <div class="empty-container">
        <h1>404. Page not found !</h1>
        <router-link :to="{ name: 'category-view' }">
            <button class="button cont-shopping-button">
            <i class="fas fa-arrow-left"></i> Continue shopping
          </button>
        </router-link>
      </div>
    </div>
  </section>
</template>