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
				<el-col v-show="logged" :push="7" :span="2">
					<el-link type="info" class="header-text" @click="$router.push('/profile')">个人中心</el-link>
				</el-col>
				<el-col :push="4" :span="3">
					<el-input @keyup.enter.native="searchPost" v-model="queryData" placeholder="搜索帖子"/>
				</el-col>
<!--				<el-col :push="4" :span="2">-->
<!--					<el-button type="primary" @click="searchPost" plain icon="el-icon-search">搜索</el-button>-->
<!--				</el-col>-->
				<el-col :push="6" :span="4">
					<el-button type="primary" @click="$router.push('/register')" plain>注册</el-button>
				</el-col>
				<el-col :push="7" :span="4">
					<div>
						<el-button type="primary" v-if="logged" @click="logout" plain>注销</el-button>
					</div>
				</el-col>
			</el-row>
		</div>
	</el-header>
</template>

<script>
	export default {
		name: "HeaderLR",
		methods: {
			logout(){
				window.sessionStorage.clear();
				this.$router.push('/');
			},
			searchPost(){
				// this.$http.get("/search",{
				// 	params: {
				// 		q : this.queryData
				// 	}
				// }).then(res => {
				// 	if (res.status == 200)
				// })
				sessionStorage.setItem("query",this.queryData);
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
				logged: false,
				queryData: ''
			}
		}
	}
</script>

<style scoped>
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
		width: 100%;
	}
</style>
