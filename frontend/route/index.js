// src/router/index.js
import Vue from 'vue';
import Router from 'vue-router';
import Home from '../pages/index.vue';
import Detail from '../pages/scheduler.vue';
import Edit from '../pages/edit.vue';

Vue.use(Router);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/detail',
    name: 'Detail',
    component: Detail,
  },
  {
    path: '/edit',
    name: 'Edit',
    component: Edit,
  },
];

const router = new Router({
  mode: 'history',
  routes,
});

export default router;
