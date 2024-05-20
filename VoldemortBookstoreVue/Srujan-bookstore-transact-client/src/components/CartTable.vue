<script setup lang="ts">
import type { BookItem } from "@/types";
import { useCartStore } from "@/stores/cart";
const cartStore = useCartStore();

const bookImageFileName = function (book: BookItem): string {
  let name = book.title.toLowerCase();
  name = name.replace(/ /g, "-");
  name = name.replace(/'/g, "");
  return `${name}.gif`;
};
const bookImagePrefix = `${import.meta.env.BASE_URL}/book-images`;

const calculateCartTotal = () => {
  let total = 0;
  for (const item of cartStore.cart.items) {
    total += item.book.price * item.quantity;
  }
  return (total / 100).toFixed(2); // Convert cents to dollars and cents
};
const calculateTotalItems = () => {
  let totalItems = 0;
  for (const item of cartStore.cart.items) {
    totalItems += item.quantity;
  }
  return totalItems;
};
const updateCart = function (book: BookItem, quantity: number) {
  cartStore.cart.update(book, quantity);
};


</script>

<style scoped>
.cart-table {
  display: grid;
  grid-template-columns: max-content minmax(10em, 20em) repeat(3, max-content);
  row-gap: 1em;
  width: fit-content;
  margin: 0 auto;
  background-color: var(--card-background-color);
}

ul {
  display: contents;
}

ul > li {
  display: contents;
}

.table-heading {
  background-color: var(--primary-color-footer);
  color: white;
}

.table-heading > * {
  background-color: var(--primary-color-footer);
  padding: 0.5em;
  font-weight: bold;
}

.heading-book {
  grid-column: 1 / 2;
  text-align: center;
}
.heading-title {
  grid-column: 2 / 3;
  text-align: left;
}

.heading-price {
  grid-column: 3 / 4;
  text-align: right;
}
.heading-quantity {
  grid-column: 4 / 5;
  text-align: center;
}

.heading-subtotal {
  text-align: right;
  grid-column: -2 / -1;
}

.cart-book-image {
  padding: 0 1em;
}

.cart-book-image > * {
  margin-left: auto;
  margin-right: 0;
}

img {
  display: block;
  width: auto;
  height: 100px;
}

.rect {
  background-color: var(--primary-color-dark);
}

.narrow-rect {
  width: 75px;
  height: 100px;
}

.square {
  width: 100px;
  height: 100px;
}

.wide-rect {
  width: 125px;
  height: 100px;
}

.cart-book-price {
  padding-left: 1em;
  text-align: right;
}

.cart-book-quantity {
  padding-left: 1em;
  padding-right: 1em;
}

.cart-book-subtotal {
  text-align: right;
  padding-left: 1em;
  padding-right: 1em;
}

/* Row separators in the table */

.line-sep {
  display: block;
  height: 2px;
  background-color: black;
  grid-column: 1 / -1;
}

/* Increment/decrement buttons */

.icon-button {
  border: none;
  cursor: pointer;
}

.inc-button {
  font-size: 1.125rem;
  color: var(--primary-color-footer);
  margin-right: 0.25em;
}

.inc-button:hover {
  color: var(--primary-color-dark);
}

.dec-button {
  font-size: 1.125rem;
  color: var(--card-background-color);
}

.dec-button:hover {
  color: black;
}

/* Chevron buttons */

.dec-arrow-button,
.inc-arrow-button {
  font-size: 1rem;
  color: var(--primary-color);
}

.dec-arrow-button:hover,
.inc-arrow-button:hover {
  color: var(--primary-color-dark);
}

input[type="number"] {
  width: 4em;
}

select {
  background-color: var(--primary-color);
  color: white;
  border: 2px solid var(--primary-color-dark);
  border-radius: 3px;
}

</style>

<template>
  <div class="cart-table">
    <ul>
      <li class="table-heading">
        <div class="heading-book">Book</div>
        <div class="heading-title">Title</div>
        <div class="heading-price">Price</div>
        <div class="heading-quantity">Quantity </div>
        <div class="heading-subtotal">Amount</div>
      </li>

      <template v-for="item in cartStore.cart.items" :key="item.book.bookId">
        <li>
          <div class="cart-book-image">
            <img
              :src="`${bookImagePrefix}/${bookImageFileName(item.book)}`"
              :alt="item.book.title"
              width="auto"
              height="100px"
            />
          </div>
          <div class="cart-book-title">{{ item.book.title }}</div>
          <div class="cart-book-price">
            ${{ (item.book.price / 100).toFixed(2) }}
          </div>
          <div class="cart-book-quantity">
            <span class="quantity">{{ item.quantity }}</span
            >&nbsp;
            <button
              class="icon-button inc-button"
              @click="updateCart(item.book, item.quantity + 1)"
            >
              <i class="fas fa-plus-circle"></i>
            </button>
            <button
              class="icon-button dec-button"
              @click="updateCart(item.book, item.quantity - 1)"
            >
              <i class="fas fa-minus-circle"></i>
            </button>
          </div>
          <div class="cart-book-subtotal">
            ${{ (item.book.price * item.quantity / 100).toFixed(2) }}
          </div>
        </li>
        <li class="line-sep"></li>

      </template>
      <li class="cart-summary">
        <div class="cart-book-image"></div>
        <div class="cart-book-title">
        </div>
        <div class="cart-book-price"></div>
        <div class="cart-book-quantity">
          Total Books: {{ calculateTotalItems() }}
        </div>
        <div class="cart-book-subtotal">
          Sub-total: ${{ calculateCartTotal() }}
        </div>
      </li>
    </ul>
  </div>
</template>
