<template>
  <div class="app">
    <header class="app-header">
      <h1>CRM Schedule Management</h1>
    </header>
    <main>
      <div class="button-create-schedule">
        <router-link to="/create" class="my-button">Create</router-link>
      </div>
      <UserList :users="users" />
    </main>
  </div>
</template>

<script>
import UserList from '../components/UserList.vue';

export default {
  components: {
    UserList,
  },
  methods: {
    handleClick() {
      alert('Check event');
    },
  },
  data() {
    return {
      users: [
        { id: 1, name: 'John Doe', email: 'john.doe@example.com', role: 'Admin' },
        { id: 2, name: 'Jane Smith', email: 'jane.smith@example.com', role: 'User' },
        { id: 3, name: 'Alice Johnson', email: 'alice.johnson@example.com', role: 'User' },
      ],
    };
  },
};
</script>

<style scoped>
.button-create-schedule {
  padding-bottom:20px
}
.my-button {
  padding:2%;
  border-radius:100px;
  width:200px;
  border: 5px solid blue;
  font-weight: bold;
}
.my-button:hover {
  padding:2%;
  border-radius:100px;
  width:200px;
  border: 5px solid blue;
  background:blue;
  color:white;
  font-weight: bold;
}
.app {
  max-width: 900px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Arial', sans-serif;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.app-header {
  text-align: center;
  padding-bottom: 20px;
}

h1 {
  font-size: 24px;
  color: #343a40;
}
</style>
