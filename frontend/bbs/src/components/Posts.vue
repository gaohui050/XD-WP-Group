<template>

	<el-card class="card-box">
		<div slot="header" style="height: 20px" class=" e-header clearfix">
			<el-link style="font-size: 20px" type="primary" @click="addPost">发帖</el-link>
		</div>
		<div v-if="!flag">该版块下暂无帖子！</div>
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

</template>


<script>
	import {cutString} from '../assets/js/filters'

	export default {
		name: "Posts",
		data() {
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
		created() {
			let s = this.$route.path.split("/");
			let forumName = s[s.length - 1];
			this.getPosts(forumName);
		},
		methods: {
			nextPage(){
				this.pageNum++;
				let s = this.$route.path.split("/");
				let forumName = s[s.length - 1];
				this.$http.get("/essay/forum", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						forum: forumName
					}
				}).then(res => {
					this.postList = res.data.list;
					this.flag = this.postList.length < 1 ? 0 : 1;
				})
			},
			prevPage(){
				this.pageNum--;
				let s = this.$route.path.split("/");
				let forumName = s[s.length - 1];
				this.$http.get("/essay/forum", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						forum: forumName
					}
				}).then(res => {
					this.postList = res.data.list;
					this.flag = this.postList.length < 1 ? 0 : 1;
				})
			},
			getPosts(forumName) {
				this.$http.get("/essay/forum", {
					params: {
						pageNum: this.pageNum,
						pageSize: this.pageSize,
						forum: forumName
					}
				}).then(res => {
					this.postList = res.data.list;
					this.total = res.data.total;
					this.flag = this.postList.length < 1 ? 0 : 1;
					// console.log(this.postList)
				})
			},
			addPost() {
				let logged = sessionStorage.getItem("logged");
				if (logged === "true"){
					this.$router.push('/newPost');
				}else {
					this.$message.error("请先登录！");
					this.$router.push("/login");
				}
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
	.card-box {
		margin-top: 45px;
		margin-left: 20px;

		background-color: #fff;
		overflow: fragments;
		width: 90%;
	}

	.post {
		margin: 5px 20px 5px auto;
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
