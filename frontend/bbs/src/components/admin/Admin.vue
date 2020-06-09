<template>
	<el-container class="wrap">
		<el-header class="title">
			<span>后台管理中心</span>
			<el-link class="back-index" :underline="false" @click="$router.push('/')" target="_self">返回首页</el-link>
			<el-link class="logout" :underline="false" @click="logout" target="_self">退出</el-link>
		</el-header>
		<el-container>
			<el-aside width="200px">
				<el-menu
					default-active="/admin/usermanage"
					class="el-menu-vertical-demo"
					router
					@open="handleOpen"
					@close="handleClose"
					background-color="#545c64"
					text-color="#fff"
					active-text-color="#409EFF">
					<el-menu-item index="/admin/usermanage">
						<i class="el-icon-menu"/>
						<span slot="title">用户管理</span>
					</el-menu-item>
				</el-menu>
			</el-aside>
			<el-main>
				<router-view/>
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	export default {
		name: "Admin",
		created(){

		},
		methods: {
			logout(){
				window.sessionStorage.clear();
				this.$router.push('/');
			}
		}
	}
</script>

<style scoped>
	.logout {
		font-size: 18px;
		color: #B3C0D1;
		position: absolute;
		right: 30px;
	}
	.back-index {
		font-size: 18px;
		margin-left: 10px;
		color: #B3C0D1;
	}
	.el-header, .el-footer {
		background-color: #B3C0D1;
		color: #333;
		text-align: center;
		line-height: 60px;
	}
	.title {
		font-size: 20px;
		color: #B3C0D1;
		text-align: left;
		background-color: #373D41;
		position: relative;
	}
	.el-aside {
		background-color: #333;
		color: #333;
		text-align: center;
		line-height: 200px;
		height: 650px;
	}

	.el-main {
		background-color: #E9EEF3;
		color: #333;
		text-align: center;
		line-height: 20px;
	}

	.el-container {
		margin-bottom: 40px;

	}
	.wrap {
		margin: 0;
	}
	.el-container:nth-child(5) .el-aside,
	.el-container:nth-child(6) .el-aside {
		line-height: 260px;
	}

	.el-container:nth-child(7) .el-aside {
		line-height: 320px;
	}
</style>
