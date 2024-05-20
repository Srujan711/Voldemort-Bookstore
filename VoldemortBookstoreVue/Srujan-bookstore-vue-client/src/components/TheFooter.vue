<script setup lang="ts"></script>

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
  padding-top: 0.5;
  padding-bottom: 0.5;
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
    <section class="copyright">&copy; 2023 VOLDEMORT BOOK SHOP</section>
    <section class="links">
      <a href="index.html">about</a> |
      <a href="index.html">contact</a> |
      <a href="index.html">directions</a>
    </section>
    <section class="social-media-icons">
      <a class="button icon-button">
        <i class="fab fa-facebook-square"></i></a>
      <a class="button icon-button"><i class="fab fa-twitter-square"></i></a>
    </section>
  </footer>
</template>
