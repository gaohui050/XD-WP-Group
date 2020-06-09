<template>
	<div>
		<el-breadcrumb separator=">">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>编程语言</el-breadcrumb-item>
			<el-breadcrumb-item>C/C++</el-breadcrumb-item>
		</el-breadcrumb>
		<Posts/>
	</div>
</template>

<script>
	import Posts from "@/components/Posts";
	import Header from "@/components/Header";
	export default {
		name: "Cpp",
		components: {Posts,Header},

	}
</script>

<style scoped>

</style>
