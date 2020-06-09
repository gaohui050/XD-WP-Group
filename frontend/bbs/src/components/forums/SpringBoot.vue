<template>
	<div>
		<el-breadcrumb separator=">">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>开源框架</el-breadcrumb-item>
			<el-breadcrumb-item>Spring Boot</el-breadcrumb-item>
		</el-breadcrumb>
		<Posts/>
	</div>
</template>

<script>
	import Posts from "@/components/Posts";
	export default {
		name: "SpringBoot",
		components: {Posts}
	}
</script>

<style scoped>

</style>
