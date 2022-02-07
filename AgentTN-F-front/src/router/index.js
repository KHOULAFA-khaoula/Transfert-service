import VueRouter from "vue-router";
import routes from "./routes";
import {isLogged} from "../utils/auth";

// configure router
const router = new VueRouter({
  routes, // short for routes: routes
  linkExactActiveClass: "active",
  scrollBehavior: (to) => {
    if (to.hash) {
      return {selector: to.hash}
    } else {
      return { x: 0, y: 0 }
    }
  }
});

router.beforeEach((to, from, next) => {
    if (to.matched.some((record) => record.meta.requiresAuth)) {
      //const isUserLogged = isLogged();
      if (isLogged()) {
          next()
      } else {
        next('/')
      }
     }else{
      next()
    }
  })

export default router;
