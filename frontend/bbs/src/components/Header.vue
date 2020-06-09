<template>
	<el-header>
		<div class="header-wrapper">
			<el-row class="header-menu" :gutter="20">
				<el-col  :span="4">
					<img src="../assets/logo.png" width="150px" height="30px" alt="">
				</el-col>
				<el-col :span="2">
					<el-link class="header-text"  type="info" @click="$router.push('/')">首页</el-link>
				</el-col>
<!--				<el-col :span="2">-->
<!--					<el-link type="info" class="header-text" href="/doc">文档</el-link>-->
<!--				</el-col>-->
				<el-col :span="2">
					<el-link type="info" class="header-text" @click="$router.push('/about')">关于</el-link>
				</el-col>
				<el-col :push="4" :span="3">
					<el-input @keyup.enter.native="searchPost" v-model="queryData" placeholder="搜索帖子"/>
				</el-col>
<!--				<el-col :push="4" :span="2">-->
<!--					<el-button type="primary" @click="searchPost" plain icon="el-icon-search">搜索</el-button>-->
<!--				</el-col>-->
				<el-col v-show="logged" :push="isAdmin ? 4 :5" :span="2">
<!--					<el-link type="info" class="header-text" href="/profile">个人中心</el-link>-->
					<el-link type="info" class="header-text" @click="toProfile">个人中心</el-link>
				</el-col>
				<el-col v-show="isAdmin" :push="3" :span="4">
<!--					<el-link type="info" class="header-text" href="/admin">管理中心</el-link>-->
					<el-link type="info" class="header-text" @click="toAdminHome">管理中心</el-link>
				</el-col>
				<el-col :push="5" :span="4">
					<div>
						<el-button v-show="!logged" @click="toLogin" type="primary" plain>登录</el-button>
						<el-button v-show="!logged" @click="toRegister" type="primary" plain>注册</el-button>
					</div>
				</el-col>
				<el-col :push="isAdmin ? 1 : 4" :span="4">
					<div>
						<el-button type="primary" v-show="logged" @click="logout" plain>退出</el-button>
					</div>
				</el-col>
			</el-row>
		</div>
	</el-header>
</template>

<script>
export default {
	name: 'Header',
	methods: {
		logout(){
			window.sessionStorage.clear();
			// this.$router.replace("/");
			location.replace("/");
		},
		toLogin() {
			this.$router.push("/login")
		},
		toRegister(){
			this.$router.push("/register")
		},
		toAdminHome(){
			this.$router.push("/admin")
		},
		toProfile(){
			this.$router.push("/profile")
		},
		searchPost(){
			sessionStorage.setItem('query', this.queryData);
			let paths = this.$route.path.split("/");
			let path = paths[paths.length - 1];
			if (path === 'search'){
				window.location.replace('/search');
			}else{
				this.$router.replace({name:'search'});
			}
		}
	},
	data(){
		return {
			isAdmin:false,
			logged: false,
			queryData: ''
		}
	},
	created(){
		let item = sessionStorage.getItem("logged");
		this.logged = item === "true";
		this.isAdmin = sessionStorage.getItem("role") === "超级管理员";
	}
}
</script>

<style scoped lang="less">
	/*.logo-box {*/
	/*	position: relative;*/
	/*}*/
	/*.logo {*/
	/*	position: absolute;*/
	/*	top: 50%;*/
	/*}*/
	.header-text {
		color: #4b4b4b;
	}
	a {
		text-decoration: none;
		color: skyblue;
	}
	.el-link {
		font-size: 16px;
		font-weight: bolder;
		/*color: #4b4b4b;*/
	}
	.header-wrapper {
		background-color: #fff;
		margin: 0;
	}
</style>
