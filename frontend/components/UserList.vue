<template>
  <div class="user-list">
    <div class="user-card" v-for="user in usersConfig">
      <p>Email: {{ user.userSetting.user.email }}</p>
      <p>Name: {{ user.userSetting.user.name }}</p>
      <p>Report Name: {{ user.userSetting.report.reportName }}</p>
      <p>Description: {{ user.userSetting.report.description }}</p>
      <p>Day Of Month: {{ user.userSetting.dayOfMonth }} Month</p>
      <p>Day Of Week: {{ user.userSetting.dayOfWeek }} Week</p>
      <p>Frequency: {{ user.userSetting.frequency }}</p>
      <p>Time Of Day: {{ user.userSetting.timeOfDay }} Day</p>
      <button class="btn-view">Xem Chi Tiết</button>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      usersConfig: [],
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const axios = await import('axios'); // Sử dụng dynamic import
        const response = await axios.get('http://localhost:9999/api/report');
        this.usersConfig = response.data.content;
        console.log(response.data.content)
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    },
  }
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
