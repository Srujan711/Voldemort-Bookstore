<script setup lang="ts">
import CartTable from "@/components/CartTable.vue";
import { useCartStore } from "@/stores/cart";
const cartStore = useCartStore();
const clearCart = function () {
  cartStore.clearCart();
};

const calculateTotalItems = () => {
  let totalItems = 0;
  for (const item of cartStore.cart.items) {
    totalItems += item.quantity;
  }
  return totalItems;
};



</script>
<style scoped>
.title{
  order: 1;
}
.cart-tools{
  order: 2;
  display: flex;
  flex-direction: row;
  align-items: center;
  text-align: center;
  justify-content: center;
  margin-bottom: 1em;
}
.checkout-button{
  background: linear-gradient(to right, #5E2E19, #A55C33);
  color: white;
  border: none;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  width: 200px;
  margin-left: 16em;
}
.cont-shopping-button{
  background: #A67568;
  color: black;
}
.clear-cart{
  order: 2;
  display: flex;
  flex-direction: row;
  text-align: center;
  justify-content: center;
  margin-bottom: 1em;
}
.clear-cart-button{
  background: var(--primary-color-footer);
  color: white;
  margin-top: 10px;

}

.empty-cart{
  color: white;
  display: flex;
  flex-direction: column; /* Stack elements vertically */
  align-items: center;
  justify-content: center;
  height: 100vh;
}
.empty-cart p{
  margin-bottom: 20px;
  font-size: 2em;
  font-weight: bold;
}

.empty-cart button{
  font-size: 1.5em;
}
</style>

<template>
  <div v-if="calculateTotalItems() > 0">
  <div class="title">
    <span  style="color: white; font-size: 2em;">Your Cart</span>
    <br>
    <span style="color: white; font-size: 1.2em;">Total Books: {{ calculateTotalItems() }}</span>
  </div>
  <div class="cart-tools">
  <router-link :to="{ name: 'category-view' }">
    <button class="button cont-shopping-button">Continue Shopping</button>
  </router-link>
  <router-link to="/checkout"><button class="button checkout-button">Checkout</button></router-link>
  </div>
  <cart-table></cart-table>
  <div class="clear-cart">
    <button class="button clear-cart-button" @click="clearCart()">Clear Cart</button>
  </div>
  </div>
  <div v-else class="empty-cart">
    <p>Your cart is empty.</p>
    <router-link :to="{ name: 'category-view' }">
      <button class="button cont-shopping-button">Continue Shopping</button>
    </router-link>
  </div>
</template>