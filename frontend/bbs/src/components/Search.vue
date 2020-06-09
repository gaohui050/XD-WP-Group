<template>
	<div class="home-wrapper">
		<Header/>
		<div class="main">
			<el-card class="card-box">
				<div v-if="!flag" style="text-align:center">暂无内容！</div>
				<ul  class="post" v-for="post in postList">
					<li @click="toContent(post)" class="item title">{{post.title}}</li>
					<li class="item summary" v-html="post.smr"/>
					<li class="item info">
						<span class="info-item el-icon-user-solid">{{post.username}}</span>
						<span class="info-item el-icon-date">{{post.postTime}}</span>
						<span class="info-item el-icon-collection-tag">
					<el-tag ype="success" v-for="tag in post.tags.split(',')" :key="tag">{{tag}}</el-tag>
				</span>
					</li>
				</ul>
				<div v-show="flag" class="pagination">
					<el-button :disabled="pageNum<2" @click="prevPage">上一页</el-button>
					<el-button :disabled="last" @click="nextPage">下一页</el-button>
				</div>
			</el-card>
		</div>
		<div class="footer">Theme By 贾瑞刚. ©2020</div>
	</div>
</template>

<script>
	import Header from "@/components/Header";

	export default {
		name: "Search",
		components: {Header},
		data(){
			return {
				postId: 1,
				last:false,
				postList: [],
				tags: [],
				flag: false,
				pageNum: 1,
				pageSize: 7
			}
		},
		created(){
			this.getPosts();
		},
		methods: {
			nextPage(){
				this.pageNum++;
				this.$http.get("/essay/search", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						query: sessionStorage.getItem("query")
					}
				}).then(res => {
					this.postList = res.data.list;
					// this.flag = this.postList.length < 1 ? 0 : 1;
				})
			},
			prevPage(){
				this.pageNum--;
				this.$http.get("/essay/search", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						query: sessionStorage.getItem("query")
					}
				}).then(res => {
					this.postList = res.data.list;
				})
			},
			getPosts() {
				this.$http.get("/essay/search", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						query: sessionStorage.getItem("query")
					}
				}).then(res => {
					this.postList = res.data.list;
					this.total = res.data.total;
					this.flag = this.postList.length < 1 ? 0 : 1;
					console.log(this.postList)
				})
			},
			toContent(post) {
				// location.href = '/content/'+id;
				this.$router.push({name:"content",params: {post : post}})
				// this.$router.push({path:"content",query: {post : post}})
			}
		}
	}
</script>

<style scoped>
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
		/*background-color: #fff;*/
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

	.card-box {
		/*margin: 5px auto;*/

		/*!*background-color: #fff;*!*/
		/*overflow: fragments;*/
		/*width: 100%;*/
	}

	.post {
		margin: 3px 20px 5px auto;
		padding-top: 10px;
		padding-bottom: 10px;
		box-shadow: 0 1px 3px #B3C0D1;
		padding-right: 20px;
	}

	.item {
		margin: 3px auto;

	}

	.title {
		font-size: 20px;
		font-weight: bold;

	}
	.title:hover {
		color: #1ab2ff;
		cursor: pointer;
	}
	.summary {
		font-size: 16px;
		color: #B3C0D1;
	}

	.info {
		width: 100%;
		text-align: left;
		margin-left: -10px;
	}

	.info-item {
		margin-left: 10px;
	}
	ul {
		list-style: none;
		margin-top: 10px;
	}

	.pagination {
		margin-top: 20px;
		text-align: center;
	}
</style>
