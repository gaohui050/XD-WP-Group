<template>
	<el-container class="home-wrapper">
		<Header/>
		<div class="wrapper">
			<div class="detail">
				<el-row><div class="title">{{post.title}}</div></el-row>
				<el-row class="info">
					<div class="el-icon-user">{{post.username}}</div>
					发布于
						<div class="el-icon-date">{{post.postTime}} </div>
						<span class="tags el-icon-collection-tag">
							<el-tag ype="success" v-for="tag in post.tags.split(',')" :key="tag">{{tag}}
							</el-tag>
						</span>
						<el-button class="tags" @click="deletePost" type="danger" v-show="isWebMaster && forumShow">删除</el-button>
				</el-row>
				<el-divider/>
				<p class="content" v-html="post.content"/>
				<el-divider/>
			</div>
			<div class="comment">
				<span style="font-weight: bold;font-size: 20px">评论：</span><br>
				<el-input
					class="text"
					type="textarea"
					:rows="6"
					placeholder="请输入您的评论"
					v-model="textarea">
				</el-input>
				<br>
				<el-button @click="submitCmt">提交</el-button>
			</div>

			<div class="comments">
				<el-divider/>
				<ul v-for="comment in comments" :key="comment.commentId">
						<el-row class="cmt-header cmt-color">
							<span class="name">{{comment.username+': '}}</span>
							<li class="cmt-content">{{comment.cmtContent}}</li>
						</el-row>
						<el-row class="cmt-color">
							<li class="p-time">{{comment.postTime}}</li>
							<el-button type="danger" @click="deleteComment(comment.commentId)" v-show="isWebMaster && forum!== ''">删除</el-button>
						</el-row>
						<el-divider/>
				</ul>
			</div>
		</div>
		<el-footer>Theme By 贾瑞刚. ©2020</el-footer>
	</el-container>
</template>

<script>
	import Header from '@/components/Header';
	export default {
		name: "Content",
		data() {
			return {
				forumShow: false,
				isWebMaster: false,
				article: {},
				textarea: "",
				isLogin: false,
				comments: [],
				textarea: "",
				username: ''
			};
		},
		components: { Header },
		methods: {
			deleteComment(id){
			this.$http.get("/comment/deleteComment",{
				params: {cmtId: id}
			}).then(res => {
				if (res.status === 200){
					this.$message({
						type: "success",
						message: "删除成功！"
					})
				}
			}).catch(err => {
				this.$message.error("删除失败！");
			})
			},
			deletePost(){
				this.$http.get("/essay/delete",{
					params: {essayId: this.post.essayId}
				}).then(res => {
					if (res.status === 200){
						this.$message({
							type: "success",
							message: "删除成功！"
						});
						this.$router.push("/");
					}else {
						this.$message.error("删除失败！");
					}
				}).catch(err => {
					this.$message.error("删除失败！");
				})
			},
			toLogin() {
				this.$router.push({ path: "/login" });
			},
			submitCmt(){
				let logged = sessionStorage.getItem("logged");
				if (logged !== "true"){
					this.$message.error("请先登录！");
					this.$router.push("/login");
					return;
				}
				let obj = {
					essayId: this.post.essayId,
					username: this.username,
					cmtContent: this.textarea
				};
				this.$http.post("/comment/addComment",JSON.stringify(obj))
					.then(res => {
						if (res.status === 200){
							this.$message({
								type: "success",
								message: "评论成功"
							})
						}
					}).catch(err => {
						this.$message.error("评论失败");
				})
			}
		},
		created(){
			this.username = sessionStorage.getItem("username");
			let role = sessionStorage.getItem("role");
			let forum = sessionStorage.getItem("forum");
			if (role === "超级管理员" || role === "版主"){
				this.isWebMaster = true;
				// this.forum = this.$route.params.post.forum;
				// alert(this.forum)
				this.forumShow = forum === this.$route.params.post.forum || forum === "all";
			}
			this.post = this.$route.params.post;
			this.$http.get("/comment/comments",{
				params: {essayId: this.post.essayId}
			}).then(res => {
				this.comments = res.data;
			})
		}

	}
</script>

<style scoped>
	.home-wrapper {
		/*margin: 0 150px;*/
	}
	.wrapper {
		background-color: #fff;
		width: 80%;
		margin: 30px auto 2px;
		padding-left: 20px;
	}

	.title {
		font-size: 25px;
		font-weight: bold;
		margin-left: 30px;
		margin-top: 20px;
		margin-right: 30px;
	}

	.tags {
		margin-left: 15px;
	}
	.info {
		margin-left: 30px;
		margin-top: 20px;
		color: #B3C0D1;
	}

	.content {
		font-size: 20px;
		color: #333333;
		width: 95%;
		margin: 0 30px 0 auto;
	}

	img {
		width: 600px;

	}
	.comment {
		margin: 0 20px;
	}
	.cmt-content {
		font-size: 18px;
	}
	.comments {
		margin: 0 -10px;
	}
	.p-time {
		margin-right: 20px;
	}
	.cmy-header {
		margin-bottom: 10px;
	}
	.name {
		font-size: 18px;
		font-weight: bold;
	}
	.text {
		width: 80%;
	}
	.header-text {
		text-decoration: none;
		color: #333333;
	}
	.el-link {
		font-size: 18px;
		font-weight: bolder;
		/*color: #4b4b4b;*/
	}

	.el-button {
		padding: 7px;
	}

	.el-header, .el-footer {
		/*background-color: #fbf9ff;*/
		color: #333;
		text-align: center;
		line-height: 60px;
	}
	.el-header {
		padding: 0;
	}
	ul {
		list-style: none;
	}
	ul li {
		display: inline-block;
	}
	.el-footer {
		background-color: #fff;
		color: #4b4b4b;
		margin-top: 20px;
		width: 100%;
	}
	.header-wrapper {
		background-color: #fff;
		margin: 0;
	}
	.main-body {
		margin-top: 40px;
	}
	.el-main {
		/*background-color: #E9EEF3;*/
		color: #333;
		text-align: center;
		line-height: 160px;
	}

	body > .el-container {
		margin-bottom: 40px;
		background-color: #F0F2F5;
	}

	.el-container:nth-child(5) .el-aside,
	.el-container:nth-child(6) .el-aside {
		line-height: 260px;
	}

	.el-container:nth-child(7) .el-aside {
		line-height: 320px;
	}


</style>
