<template>
	<el-container class="home-wrapper">
		<Header/>
		<el-main>

			<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
				<el-menu-item index="1">个人中心</el-menu-item>
			</el-menu>
			<div class="line"></div>
			<el-card class="box-card" shadow="always">
				<div slot="header" class="card-header clearfix">
					<span class="header-text">个人资料</span>
				</div>
				<ul class="userinfo">
					<li class="item">用户名：{{userInfo.username}}</li>
					<li class="item">邮箱：{{userInfo.email}}</li>
					<li class="item">角色：{{userInfo.role}}</li>
					<li v-show="!isDefaultRole" class="item">板块：{{userInfo.forum}}</li>
					<li class="item"><el-link type="primary" @click="$router.push('/modifyProfile')" :underline="false">修改个人信息</el-link></li>
				</ul>
			</el-card>
		</el-main>
		<el-footer>Theme By 贾瑞刚. ©2020</el-footer>
	</el-container>
</template>

<script>
	import Header from "@/components/Header";

	export default {
		name: "Profile",
		components: {Header},
		created(){
			if (sessionStorage.getItem("logged") !== "true"){
				this.$message("请先登录!");
				this.$router.push("/login");
			}
			this.isDefaultRole = sessionStorage.getItem("role") === "普通用户";
			this.$http.get("/user/profile/"+sessionStorage.getItem("username"))
				.then(res => {
					if (res.status === 200){
						this.userInfo = res.data;
					}
				})
		},
		data() {
			return {
				isDefaultRole: true,
				activeIndex: '1',
				userInfo: {}
			};
		},
		methods: {
			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			}
		}
	}
</script>

<style scoped>
	.userinfo {
		list-style: none;
		position: relative;
		top: -60px;
	}
	.item {
		color: #333333;
		font-size: 20px;
		height: 30px;
		text-align: left;
		margin: 0 auto;
		width: 50%;
	}
	.text {
		font-size: 14px;
	}

	.item {
		margin-bottom: 18px;
	}

	.header-text {
		position: relative;
		top: -70px
	}
	.card-header {
		height: 16px;
	}

	.box-card {
		width: 100%;
		margin-top: 30px;
	}


	.el-link {
		font-size: 18px;
		font-weight: bolder;
		/*color: #4b4b4b;*/
	}

	.el-header, .el-footer {
		/*background-color: #fbf9ff;*/
		color: #333;
		text-align: center;
		line-height: 60px;
	}
	.el-header {
		padding: 0;
	}
	.el-footer {
		background-color: #fff;
		color: #4b4b4b;
		position: absolute;
		bottom: 0;
		width: 100%;
	}
	.header-wrapper {
		background-color: #fff;
		margin: 0;
	}
	.main-body {
		margin-top: 40px;
	}
	.el-main {
		/*background-color: #E9EEF3;*/
		color: #333;
		text-align: center;
		line-height: 160px;
		width: 60%;
		margin: 0 auto;
	}

	body > .el-container {
		margin-bottom: 40px;
		background-color: #F0F2F5;
	}

	.el-container:nth-child(5) .el-aside,
	.el-container:nth-child(6) .el-aside {
		line-height: 260px;
	}

	.el-container:nth-child(7) .el-aside {
		line-height: 320px;
	}

	.home-wrapper {
		/*margin: 0 150px;*/
	}
	.login-container{
		height: 100%;

		.login-box {
			width: 450px;
			height: 300px;
			border-radius: 3px;
			background-color: #fff;

			position: absolute;
			left: 50%;
			top: 40%;
			transform: translate(-50%,-50%);

			.login-form {
				position: absolute;
				bottom: 48px;
				width: 100%;
				padding: 0 30px;
				box-sizing: border-box;
			}
			.btns {
				display: flex;
				justify-content: flex-end;
			}

		}
			position: absolute;
			top: 50px;
			left: 50%;
			transform: translate(-50%,-50%);
		}
</style>
