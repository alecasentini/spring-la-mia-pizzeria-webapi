import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import PizzaShow from '../views/PizzaShow.vue'
import PizzaEdit from '../views/PizzaEdit.vue'
import PizzaCreate from '../views/PizzaCreate.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/pizza/:id',
      name: 'PizzaShow',
      component: PizzaShow,
      props: true
    },
    {
      path: '/edit-pizza/:id',
      name: 'PizzaEdit',
      component: PizzaEdit,
      props: true
    },
    {
      path: '/create-pizza',
      name: 'PizzaCreate',
      component: PizzaCreate,
      props: true
    }
  ]
})

export default router

