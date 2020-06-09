<template>
<div class="home-wrapper">
	<Header/>
	<div class="main">
		<Aside/>
	</div>
	<div class="footer">Theme By 贾瑞刚. ©2020</div>
</div>
</template>

<script>
	import Header from "@/components/Header";
	import Aside from "@/components/Aside";
	export default {
		name: "Home",

		components: {Header,Aside},
		methods: {

		}
	}
</script>

<style scoped>

	/*.header-text {*/
	/*	text-decoration: none;*/
	/*	color: #333333;*/
	/*}*/
	/*.el-link {*/
	/*	font-size: 18px;*/
	/*	font-weight: bolder;*/
	/*	!*color: #4b4b4b;*!*/
	/*}*/

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
		margin-bottom: 0;
	}
	.home-wrapper {
		/*background-color: #fff;*/
		margin: 0 auto;
		/*width: 80%;*/
	}
	.main-body {
		margin-top: 20px;
	}

	.main {
		width: 90%;
		margin: 30px auto;
		background-color: #fff;
		padding: 20px;
	}

	.home-wrapper {
		 /*margin: 0 150px;*/
		overflow: hidden;
	 }
	.footer{
		margin-top: 20px;
		margin-left: auto;
		width: 100%;
		background-color: #fff;
		height: 60px;
		text-align: center;
		line-height: 60px;
	}
</style>

