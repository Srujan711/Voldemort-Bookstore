<script setup lang="ts">
</script>

<style scoped>
footer {
  background: var(--primary-color-footer);
  display: flex;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  gap: 1em;
}

footer.container {
  padding-top: 0.5em;
  padding-bottom: 0.5em;
}

.links {
  text-align: center;
  gap: inherit;
}

.social-media-icons {
  display: flex;
  justify-content: space-between;
  gap: inherit;
}

.social-media-icons {
  display: flex;
  justify-content: flex-end;
  text-align: center;
  gap: 0.25em;
}

.copyright {
  font-size: 0.875rem;
}

</style>

<template>
  <footer class="container">
    <section class="copyright" style="font-weight: bold;">&copy; 2023 VOLDEMORT BOOK SHOP</section>
    <section class="links">
      <router-link to="/">about</router-link> |
      <router-link to="/">contact</router-link> |
      <router-link to="/">directions</router-link>
    </section>
    <section class="social-media-icons">
      <a class="button icon-button">
        <i class="fab fa-facebook-square"></i></a>
      <a class="button icon-button"><i class="fab fa-twitter-square"></i></a>
    </section>
  </footer>
</template>
