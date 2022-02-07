<template>
  <footer class="footer">
    <div class="container-fluid">
      <ul class="nav">
        <li class="nav-item">
          <a class="nav-link" href="http://www.google.com">
            ENSA Transfert National
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://www.google.com">
            BOA
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="http://www.google.com/license">
            Licenses
          </a>
        </li>
      </ul>
      <div class="copyright">
        © {{year}} made with <i class="tim-icons icon-heart-2"></i> by
        <a href="https://www.google.com" target="_blank" rel="noopener">OAMAYA</a>
      </div>
    </div>
  </footer>
</template>
<script>
  export default {
    data() {
      return {
        year: new Date().getFullYear()
      }
    }
  };
</script>
<style>
</style>
