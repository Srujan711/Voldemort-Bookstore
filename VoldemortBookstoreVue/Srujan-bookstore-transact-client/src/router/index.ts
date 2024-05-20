import {createRouter, createWebHistory} from "vue-router";
import HomeView from "../views/HomeView.vue";
import CategoryView from "@/views/CategoryView.vue";
import CartView from "@/views/CartView.vue";
import CheckoutView from "@/views/CheckoutView.vue";
import ConfirmationView from "@/views/ConfirmationView.vue";
import {useBookStore} from "@/stores/book";
import NotFoundView from "@/views/NotFoundView.vue";
import BookView from "@/views/BookView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "home",
            component: HomeView,
            alias: ["/home", "/index.html"],
        },
        {
            path: "/category/:name?",
            name: "category-view",
            component: CategoryView,
            beforeEnter: async (to, from, next) => {
                const bookStore = useBookStore();
                const category =
                    to.params.name || bookStore.currentCategory || "Sci-Fi";
                //to.params.name = category; // this won't update the browser url
                if (to.params.name) next(); // to avoid infinite route loop
                else
                    next({
                        name: "category-view",
                        params: { name: category },
                        replace: true, // use replace to update URL without adding a new entry to the history stack
                    });
            },
        },
        {
            path: "/cart",
            name: "cart-view",
            component: CartView,
        },
        {
            path: "/checkout",
            name: "checkout-view",
            component: CheckoutView,
        },
        {
            path: "/confirmation",
            name: "confirmation-view",
            component: ConfirmationView,
        },
        { path: "/:pathMatch(.*)*", name: "not-found", component: NotFoundView },
        {
            path:"/book-description",
            name: "book-view",
            component: BookView,
        },
    ],
});

export default router;
