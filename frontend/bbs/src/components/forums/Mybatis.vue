<template>
	<div>
		<el-breadcrumb separator=">">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>开源框架</el-breadcrumb-item>
			<el-breadcrumb-item>Mybatis</el-breadcrumb-item>
		</el-breadcrumb>
		<Posts/>
	</div>
</template>

<script>
	import Posts from "@/components/Posts";
	export default {
		name: "Mybatis",
		components: {Posts}
	}
</script>

<style scoped>

</style>
