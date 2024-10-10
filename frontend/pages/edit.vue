<template>
  <div class="container">
    <div class="detail-card">
      <h1>Edit</h1>
      <button class="my-button">Save</button>
      <div class="user-info">
        <h2>Tên: <span>{{ user.name }}</span></h2>
        <p>Email: <span>{{ user.email }}</span></p>
        <p>Vai trò: <span>{{ user.role }}</span></p>
        <button class="btn-back" @click="goBack">Quay Lại</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        name: 'John Doe',
        email: 'john.doe@example.com',
        role: 'Quản trị viên',
      },
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1); // Quay lại trang trước đó
    },
  },
};
</script>

<style scoped>
.my-button {
  padding:2%;
  border-radius:100px;
  width:100%;
  border: 5px solid blue;
  font-weight: bold;
}
.my-button:hover {
  padding:2%;
  border-radius:100px;
  width:100%;
  border: 5px solid blue;
  background:blue;
  color:white;
  font-weight: bold;
}
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin:0 auto;
}

.detail-card {
  background-color: #ffffff;
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  width: 300px;
  height: 500px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.detail-card h1 {
  margin: 0 0 20px;
  font-size: 24px;
  color: #495057;
  text-align: center;
}

.user-info h2,
.user-info p {
  margin: 10px 0;
  color: #6c757d;
}

.btn-back {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  width: 100%;
}

.btn-back:hover {
  background-color: #0056b3;
}
</style>
