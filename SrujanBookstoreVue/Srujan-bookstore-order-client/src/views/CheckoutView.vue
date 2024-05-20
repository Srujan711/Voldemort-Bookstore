<script setup lang="ts">
import {reactive, ref} from "vue";
import { computed} from "vue";
import useVuelidate from "@vuelidate/core";
import { helpers, maxLength, minLength, required } from "@vuelidate/validators";
import { useCartStore } from "@/stores/cart";
import { isCreditCard, isMobilePhone } from "@/validators";
import CheckoutFieldError from "@/components/CheckoutFieldError.vue";
import { useRouter } from "vue-router";
import type {OrderDetails, ServerErrorResponse} from "@/types";

const router = useRouter();
const cartStore = useCartStore();
const cart = cartStore.cart;

const defaultServerErrorMessage = "An unexpected error occurred, please try again."
const serverErrorMessage = ref(defaultServerErrorMessage);

const months: string[] = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

const form = reactive({
  name: "",
  address: "",
  phone: "",
  email: "",
  ccNumber: "",
  ccExpiryMonth: new Date().getMonth() + 1,
  ccExpiryYear: new Date().getFullYear(),
  checkoutStatus: "",
});

const rules = {
  name: {
    required: helpers.withMessage("Please provide a name.", required),
    minLength: helpers.withMessage(
        "Name must have at least 4 letters.",
        minLength(4)
    ),
    maxLength: helpers.withMessage(
        "Name can have at most 45 letters.",
        maxLength(45)
    ),
  },
  address: {
    required: helpers.withMessage("Please provide an address.", required),
    minLength: helpers.withMessage(
        "Address must have at least 10 characters.",
        minLength(10)
    ),
  },
  phone: {
    required: helpers.withMessage("Please provide a phone number.", required),
    isMobilePhone: helpers.withMessage("Please provide a valid phone number.", isMobilePhone),

  },
  email: {
    required: helpers.withMessage("Please provide an email.", required),
    email: helpers.withMessage("Please provide a valid email address.", (value: string) => /\S+@\S+\.\S+/.test(value)),
  },
  ccNumber: {
    required: helpers.withMessage("Please provide a credit card number.", required),
    isCreditCard: helpers.withMessage("Please provide a valid credit card number.", isCreditCard),
  },
  ccExpiryMonth: {
    validExpiryMonth: helpers.withMessage("Expiry month is in the past.", (value: number) => {
      const currentYear = new Date().getFullYear();
      const currentMonth = new Date().getMonth() + 1;
      return !(form.ccExpiryYear === currentYear && value < currentMonth);
    }),
  },
  ccExpiryYear: {
    validExpiryYear: helpers.withMessage("Expiry year is in the past.", (value: number) => {
      const currentYear = new Date().getFullYear();
      return value >= currentYear;
    }),
  },


  //   TODO: Add more validations for these and other fields that need more validation.
};
const v$ = useVuelidate(rules, form);

async function submitOrder() {
  console.log("Submit order");
  const isFormCorrect = await v$.value.$validate();
  if (!isFormCorrect) {
    form.checkoutStatus = "ERROR";
  } else {
    try {
      form.checkoutStatus = "PENDING";
      serverErrorMessage.value = defaultServerErrorMessage;

      const placeOrderResponse: OrderDetails | ServerErrorResponse =
          await cartStore
              .placeOrder({
                name: form.name,
                address: form.address,
                phone: form.phone,
                email: form.email,
                ccNumber: form.ccNumber,
                ccExpiryMonth: form.ccExpiryMonth,
                ccExpiryYear: form.ccExpiryYear,
              })

      if ("error" in placeOrderResponse) {
        form.checkoutStatus = "SERVER_ERROR";
        serverErrorMessage.value = placeOrderResponse.message
        console.log("Error placing order", placeOrderResponse);
      } else {
        form.checkoutStatus = "OK";
        await router.push({name: "confirmation-view"});
      }

    } catch (e) {
      form.checkoutStatus = "SERVER_ERROR";
      serverErrorMessage.value = defaultServerErrorMessage;
      console.log("Error placing order", e);
    }
  }
}
/* NOTE: For example yearFrom(0) == <current_year> */
function yearFrom(index: number) {
  return new Date().getFullYear() + index;
}
const formattedSubtotal = computed(() => {
  return (cart.subtotal / 100).toFixed(2);
});

const formattedSurcharge = computed(() => {
  return (cart.surcharge / 100).toFixed(2);
});

const formattedTotal = computed(() => {
  return (cart.total / 100).toFixed(2);
});
</script>

<style scoped>
.checkout-page {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 2em;
  gap: 2em;
}

form {
  display: flex;
  flex-direction: row;
  gap: 2rem;
}

form > div > div > div {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
  align-items: center;
}

form > div > div > div > :not(label) {
  margin-left: auto;
}


.checkout-details, .checkout-total {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.checkout-details {
  flex: 3;
}

.checkout-total {
  flex: 1;
  align-items: flex-start;  /* To make sure the cart total stays at the top */
}

.personal-info, .billing-info {
  background-color: #f5f5f5;
  padding: 1em;
  border-radius: 8px;
  margin-right: 1em; /* Space between boxes */
}

.totals {
  background-color: #f5f5f5;
  padding: 1em;
  border-radius: 8px;
  height: fit-content;
}

input, select {
  padding: 0.5em;
  border-radius: 4px;
  border: 1px solid #ccc;
  margin-top: 0.5em;
  align-self: flex-end;
}

.submit-button {
  margin-top: 1em;
  background-color: #713624;
  color: white;
  border: none;
  border-radius: 4px;
  padding: 10px 20px;
}

.submit-button:hover {
  background-color: rgb(0, 0, 0);
}

.checkoutStatusBox{
  background-color: var(--card-background-color);
  box-shadow: 0 0 0 1px black;
  margin-top: 1em;
}
</style>

<template>
  <div class="checkout-page">
    <!-- TODO: Add an HTML section to display when checking out with an empty cart -->
    <template v-if="cart.empty">
      <div class="empty-cart-message">
        Your cart is empty. Please add items before checking out.
      </div>
    </template>


    <section class="checkout-page-body" v-if="!cart.empty">
      <form @submit.prevent="submitOrder">
        <div class="checkout-details">
          <div class="personal-info">
            <h2>Personal Information</h2>
            <div>
              <label for="name">Name:</label>
              <input type="text" size="20" id="name" name="name" v-model.lazy="v$.name.$model" />
            </div>
            <CheckoutFieldError :field-name="v$.name" />

            <div>
              <label for="name">Mobile Number:</label>
              <input type="text" size="20" id="phone" name="phone" v-model.lazy="v$.phone.$model" />
            </div>
            <CheckoutFieldError :field-name="v$.phone" />

            <div>
              <label for="address">Address:</label>
              <input type="text" size="20" id="address" name="address" v-model.lazy="v$.address.$model" />
            </div>
            <CheckoutFieldError :field-name="v$.address" />
          </div>
        <!-- TODO: Add address input and validation messages -->

          <div class="billing-info">
            <h2>Billing Details</h2>
            <div>
              <label for="name">Name on Card:</label>
              <input type="text" size="20" id="name" name="name" v-model.lazy="v$.name.$model" />
            </div>
            <CheckoutFieldError :field-name="v$.name" />
            <div>
              <label for="email">Email:</label>
              <input type="text" size="20" id="email" name="email" v-model.lazy="v$.email.$model" />
            </div>
            <!-- TODO: Add email validation message(s) -->
            <CheckoutFieldError :field-name="v$.email" />

            <!-- Credit Card -->
            <div>
              <label for="ccNumber">Credit card:</label>
              <input type="text" size="20" id="ccNumber" name="ccNumber" v-model.lazy="v$.ccNumber.$model" />
            </div>
            <CheckoutFieldError :field-name="v$.ccNumber" />

            <!-- Expiry Date -->
            <div style="display: flex; align-items: center; gap: 8px;">
              <label>Exp Date:</label>
              <select v-model="form.ccExpiryMonth">
                <option
                    v-for="(month, index) in months"
                    :key="index"
                    :value="index + 1"
                >
                  {{ month }}
                </option>
              </select>
              <select v-model="form.ccExpiryYear">
                <option
                    v-for="year in Array.from({ length: 10 }, (_, i) => yearFrom(i))"
                    :key="year"
                    :value="year"
                >
                  {{ year }}
                </option>
              </select>
            </div>
            <CheckoutFieldError :field-name="v$.ccExpiryMonth" />
          </div>
        </div>
        <!-- TODO (style): Use a single label for both month and date and put the on the same line. -->
        <!-- TODO (style): For example: Exp Date {Month} {Year}, with space between month/year selectors. -->
        <div class="checkout-total">
        <div class="totals">
          <h2> Your Total</h2><br>
          <div>Subtotal: ${{ formattedSubtotal }}</div>
          <div>Surcharge: ${{ formattedSurcharge }}</div>
          <div>Total: ${{ formattedTotal }}</div>
          <input
              type="submit"
              name="submit"
              class="button submit-button"
              :disabled="form.checkoutStatus === 'PENDING'"
              value="Complete Purchase"
          />
          <br>
          <section v-show="form.checkoutStatus !== ''" class="checkoutStatusBox">
            <div v-if="form.checkoutStatus === 'ERROR'" style="color: red;">
              Error: Please fix the problems above and try again.
            </div>

            <div v-else-if="form.checkoutStatus === 'PENDING'">Processing...</div>

            <div v-else-if="form.checkoutStatus === 'OK'">Order placed...</div>

            <div v-else>{{ serverErrorMessage }}</div>
          </section>
        </div>
        </div>



        <!-- TODO (style): The submit button should not take up the entire width of the form. -->
        <!-- TODO (style): The submit button should be styled consistent with your own site. -->
      </form>
      <!-- TODO (style): Fix error message placement so they nearer to the correct fields. -->
      <!-- TODO (style): HINT: Use another <div> and label, input, and error, and use flexbox to style. -->

      <!-- TODO: Display the cart total, subtotal and surcharge. -->


    </section>
  </div>
</template>

