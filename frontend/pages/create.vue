<template>
  <div class="app">
    <header class="app-header">
      <h1>CRM Schedule Management</h1>
    </header>
    <main>
      <h2>Create User</h2>
      <form @submit.prevent="createUser" class="create-user-form">
        <div>
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="newUser.name" required />
        </div>
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="newUser.email" required />
        </div>
        <div>
          <label for="role">Role:</label>
          <input type="text" id="role" v-model="newUser.role" required />
        </div>
        <button type="submit">Submit</button>
      </form>
    </main>
  </div>
</template>

<script>
import UserList from '../components/UserList.vue';

export default {
  components: {
    UserList,
  },
  data() {
    return {
      newUser: {
        name: '',
        email: '',
        role: '',
      },
      users: [
        { id: 1, name: 'John Doe', email: 'john.doe@example.com', role: 'Admin' },
        { id: 2, name: 'Jane Smith', email: 'jane.smith@example.com', role: 'User' },
        { id: 3, name: 'Alice Johnson', email: 'alice.johnson@example.com', role: 'User' },
      ],
    };
  },
  methods: {
    createUser() {
      const newUserId = this.users.length + 1; // Generate a new ID
      this.users.push({ id: newUserId, ...this.newUser }); // Add new user
      this.newUser = { name: '', email: '', role: '' }; // Reset form fields
    },
  },
};
</script>

<style scoped>
.create-user-form {
  margin-bottom: 20px;
}

.create-user-form div {
  margin-bottom: 10px;
}

.create-user-form label {
  display: block;
  margin-bottom: 5px;
}

.create-user-form input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  padding: 10px 15px;
  background-color: blue;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: darkblue;
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

h2 {
  font-size: 20px;
  color: #495057;
}
</style>
