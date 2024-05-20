<script setup lang="ts">
import TheHeaderDropdown from "@/components/TheHeaderDropdown.vue";
</script>

<style scoped>
header {
  background: var(--secondary-background-color);
  display: flex;
  padding: 1em;
  gap: 1em;
}

.bookstore-logo {
  text-align: left;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1em;
}

.header-tools {
  text-align: left;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: baseline;
  gap: 1em;
  margin-top: 0.8rem;
}

.flex-box{
  display:flex;
  justify-content: space-between;
  align-items: baseline;
  gap: 1rem;
}
.text-logo {
  font-family: var(--title-font-family);
  text-align: left;
  font-size: 2rem;
}

.text-logo-voldemort {
  font-size: 1.2em;
  padding-left: 1.3em;
  margin-bottom: -1em;
}

.text-logo-bookshop {
  font-size: 1.2em;
  padding-left: 1.3em;
  margin-top: 0.5em;
}

.search {
  width: 38%;
  text-align: -webkit-center;
  align-self: center;
}

.search-bar {
  display: flex;
  align-items: center;
  background-color: white;
  padding: 5px 10px;
  width: 50%;
  height: 40px;
  border-radius: 10px;
}

.search-input {
  border: none;
  background: none;
  outline: none;
  width: 100%;
  padding-left: 10px;
  color: black;
}

.search-input::placeholder {
  color: black;
}
.shopping-cart{
  width: 1.2rem;
}
.cart-icon {
  color: black;
  position: relative;
  width: 5rem;
}

.cart-count {
  color: black;
  font-weight: bold;
  transform: translateY(-35px);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  font-size: 0.9rem;
  align-items: baseline;
  background-color: white;
}


@media (max-width: 600px) {
  header {
    flex-direction: column;
  }
}
</style>

<template>
  <header class="container">
    <section class="bookstore-logo">
      <router-link to="/">
        <img
            src="/site-images/Book.png"
            alt="Voldemort Book shop logo"
            width="80"
            height="80"
        />
      </router-link>
      <div class="text-logo">
        <p class="text-logo-voldemort">VOLDEMORT</p>
        <p class="text-logo-bookshop">BOOK SHOP</p>
      </div>

    </section>
    <section class="search">
    <div class="search-bar">
      <input
          class="search-input"
          type="text"
          placeholder="Search books, Search Authors, ..."
          aria-label="search field"
      />
     <i class="fas fa-search search-icon"></i>
    </div>
    </section>
    <section class="header-tools">
      <div class="flex-box">
        <div class="login">Hi Srujan!</div>
        <the-header-dropdown></the-header-dropdown>
        <div class="shopping-cart">
          <div class="cart-icon icon-button">
            <i class="fas fa-shopping-cart" aria-hidden="true"></i>
          </div>
          <div class="cart-count">4</div>
        </div>
      </div>

    </section>

  </header>
</template>
