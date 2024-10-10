<template>
  <div class="user-list">
    <div class="user-card" v-for="user in users" :key="user.id">
      <h2>{{ user.name }}</h2>
      <p>Email: {{ user.email }}</p>
      <p>Vai trò: {{ user.role }}</p>
      <button class="btn-view">Xem Chi Tiết</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      users: [
        { id: 1, name: 'John Doe', email: 'john.doe@example.com', role: 'Quản trị viên' },
        { id: 2, name: 'Jane Smith', email: 'jane.smith@example.com', role: 'Người dùng' },
        { id: 3, name: 'Alice Johnson', email: 'alice.johnson@example.com', role: 'Người dùng' },
      ],
    };
  },
};
</script>

<style scoped>
.user-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  margin-top: 20px;
}

.user-card {
  background-color: #ffffff;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.user-card:hover {
  transform: scale(1.03);
}

.user-card h2 {
  margin: 0 0 10px;
  font-size: 20px;
  color: #495057;
}

.user-card p {
  margin: 5px 0;
  color: #6c757d;
}

.btn-view {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-view:hover {
  background-color: #0056b3;
}
</style>
