

<style scoped>
.home-page {
  display: flex;
  width: 55rem;
}


.best-seller-section {
  display: flex;
  align-items: baseline;
  margin-top: 20px;
  padding: 20px;
  background-color: var(--neutral-color);
  /* backdrop-filter: blur(30px); */
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  transition-timing-function: ease;
  transition-duration: 0.9s;
  transition-delay: 0s;
  height: 68vh;
}

.best-seller-book {
  flex-grow: 1;
  display: contents;/* Take up the entire left side */
}

.best-seller-book img {
  max-width: 100%;
  height: auto;
}

.best-seller-description {
  flex-grow: 0; /* Do not grow; take only the necessary space */
  padding-left: 20px;
  font-size: 48px;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.best-seller-description h2 {
  font-size: 30px;
  margin-top: 0;
}

.best-seller-description p {
  margin: 5px 0;
  font-size: 20px;
}

.shop-now-button{
  background: linear-gradient(to right, #5E2E19, #A55C33);
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  width: 200px;
}
/* Style for the cart button underneath the login button */


</style>
<template>

      <section class="best-seller-section container">
        <div class="home-page">
        <div class="best-seller-book">
          <img src="/site-images/fire&blood.jpeg" alt="Best Seller Book Cover" />
        </div>
          <div class="best-seller-description">
          <h1>Our Best Seller</h1>
          <h2>Fire & Blood</h2>
          <p>Author: George R. R. Martin</p>
          <p>Price: $19.99</p>
          <router-link to="/category/Fiction">
          <button class="button shop-now-button">SHOP NOW!</button>
          </router-link>
          </div>
      </div>
    </section>

</template>
<script setup lang="ts">
</script>