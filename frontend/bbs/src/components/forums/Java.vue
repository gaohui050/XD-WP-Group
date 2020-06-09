<template>
	<div>
		<el-breadcrumb separator=">">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>编程语言</el-breadcrumb-item>
			<el-breadcrumb-item>Java</el-breadcrumb-item>
		</el-breadcrumb>

		<Posts/>

	</div>
</template>

<script>
	import Posts from "@/components/Posts";

	export default {
		name: "Java",
		components: {Posts}
	}
</script>

<style scoped>

</style>
