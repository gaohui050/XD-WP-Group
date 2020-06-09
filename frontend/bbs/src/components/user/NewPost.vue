<template>
	<el-container class="home-wrapper">
		<Header/>
		<el-main>
			<div class="tip-top">发表文章</div>
			<div class="tip-text">文章标题</div>
			<el-input
				placeholder="请输入内容"
				v-model="title"
				clearable>
			</el-input>
			<div class="tip-text">标签</div>
			<el-input
				placeholder="以逗号分隔"
				v-model="tags"
				clearable>
			</el-input>
			<div class="tip-text">文章分类</div>
			<el-select v-model="sort" placeholder="请选择">
				<el-option
					v-for="item in options"
					:key="item.value"
					:label="item.label"
					:value="item.value">
				</el-option>
			</el-select>
			<div class="tip-text">正文</div>
			<quill-editor
				v-model="content"
				class="quill-editor"
				ref="myQuillEditor"
				:options="editorOption"
				@blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
				@change="onEditorChange($event)">
			</quill-editor>
			<el-button class="submit" type="primary" @click="saveHtml($event)" round>发表</el-button>
		</el-main>
		<el-footer>Theme By 贾瑞刚. ©2020</el-footer>
	</el-container>
</template>

<script>
	import { quillEditor } from "vue-quill-editor"; //调用编辑器
	import Header from '@/components/Header';
	import 'quill/dist/quill.core.css';
	import 'quill/dist/quill.snow.css';
	import 'quill/dist/quill.bubble.css';

	export default {
		name: "NewPost",
		components: {Header,quillEditor},
		mounted(){
			this.editor = this.$refs.myQuillEditor.quill;
		},
		data() {
			return {
				options: [
					{
					value: 'cpp',
					label: 'C/C++'
				}, {
					value: 'java',
					label: 'Java'
				}, {
					value: 'js',
					label: 'JavaScript'
				}, {
					value: 'py',
					label: 'Python'
				}, {
						value: 'go',
						label: 'Golang'
					},{
					value: 'kotlin',
					label: 'Kotlin'
				}, {
					value: 'otherlang',
					label: '其他语言'
				},{
						value: 'spring',
						label: 'Spring'
					},{
						value: 'springmvc',
						label: 'SpringMVC'
					},{
						value: 'springboot',
						label: 'Spring Boot'
					},{
						value: 'springcloud',
						label: 'Spring Cloud'
					},{
						value: 'dubbo',
						label: 'Dubbo'
					},{
						value: 'mybatis',
						label: 'Mybatis'
					},
					{
						value: 'otherframe',
						label: '其他框架'
					},{
						value: 'share',
						label: '经验分享'
					},{
						value: 'help',
						label: '新手求助'
					},{
						value: 'transaction',
						label: '论坛事务'
					},],
				sort: '',
				title: '',
				tags: '',
				content: `<br>`,
				editorOption: {
					modules:{
						toolbar:[
							['bold', 'italic', 'underline', 'strike'], //加粗，斜体，下划线，删除线
							['blockquote', 'code-block'],  //引用，代码块


							[{ 'header': 1 }, { 'header': 2 }],  // 标题，键值对的形式；1、2表示字体大小
							[{ 'list': 'ordered'}, { 'list': 'bullet' }],  //列表
							[{ 'script': 'sub'}, { 'script': 'super' }], // 上下标
							[{ 'indent': '-1'}, { 'indent': '+1' }],  // 缩进
							[{ 'direction': 'rtl' }],    // 文本方向


							[{ 'size': ['small', false, 'large', 'huge'] }], // 字体大小
							[{ 'header': [1, 2, 3, 4, 5, 6, false] }],  //几级标题


							[{ 'color': [] }, { 'background': [] }],  // 字体颜色，字体背景颜色
							[{ 'font': [] }],  //字体
							[{ 'align': [] }], //对齐方式


							['clean'], //清除字体样式
							['image','video'] //上传图片、上传视频

						]
					},
					theme:'snow'
				}
			}
		},
		computed: {
			editor() {
				return this.$refs.myQuillEditor.quill;
			},
		},
		methods: {
			onEditorReady(editor) { // 准备编辑器
			},
			onEditorBlur(){

			}, // 失去焦点事件
			onEditorFocus(val){
				console.log(val); // 富文本获得焦点时的内容
				this.editor().enable(false); // 在获取焦点的时候禁用
			}, // 获得焦点事件
			onEditorChange(){

			},
			saveHtml:function(event){
				// alert(this.title + this.sort +" "  + this.content)
				let obj = {
					username: sessionStorage.getItem("username"),
					title: this.title,
					forum: this.sort,
					content: this.content,
					tags: this.tags
				};
				this.$http.post("/essay/add",JSON.stringify(obj))
					.then(res => {
						if (res.status === 200){
							this.$message({
								type: "success",
								message: "发表成功!"
							})
							this.$router.push("/forum/"+this.sort)
							}else {
							this.$message.error("发表失败");
						}
					}).catch(err => {
					this.$message.error("发表失败");
				})

			}
		}
	}
</script>

<style scoped>
	.tip-top {
		text-align: center;
		font-size: x-large;
		font-weight: bold;
		background-color: #94cbef;
	}
	.submit {
		margin: 7px 0 7px 0;
	}
	.tip-text {
		font-size: 17px;
		font-weight: bold;
		margin: 10px auto 10px;
	}
	.quill-editor {
		background-color: #fff;
	}
	.edit_container {
		width: 80%;
		margin: 10px auto;
		/*height: 600px;*/
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
		margin-top: 20px;
	}
	.header-wrapper {
		background-color: #fff;
		margin: 0;
	}
	.main-body {
		margin-top: 40px;
	}
	/*.el-main {*/
	/*	!*background-color: #E9EEF3;*!*/
	/*	color: #333;*/
	/*	text-align: center;*/
	/*	line-height: 160px;*/
	/*}*/
	.el-main {
		width: 70%;
		margin: 0 auto;
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

	.home-wrapper {
		/*margin: 0 150px;*/
	}

</style>
