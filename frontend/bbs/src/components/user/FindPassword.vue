<template>
	<el-container class="home-wrapper">
		<Header/>
		<el-main>
			<div class="login-container">
				<div class="login-box">
					<div class="avatar-box">
						<div class="login-text">找回密码</div>
					</div>
					<!--表单-->
					<el-form ref="formRef" :model="pwdForm" :rules="pwdRules" label-width="100px" class="login-form">
						<!--				用户名 prop是绑定验证规则-->
						<el-form-item label="用户名" prop="username">
							<el-input ref="userInput" v-model="pwdForm.username" prefix-icon="iconfont icon-user"
							          placeholder="请输入用户名" auto-complete="off" clearable/>
						</el-form-item>
						<!--				密码-->
						<el-form-item label="邮箱" prop="email">
							<el-input ref="emailInput" v-model="pwdForm.email" prefix-icon="iconfont icon-3702mima"
							          type="text" placeholder="请输入邮箱" clearable />
						</el-form-item>
						<!--				按钮-->
						<div class="btns">
							<el-button type="primary" @click="findPassword">确认</el-button>
							<!--					<el-button type="info" @click="resetLoginForm('loginForm')">清空</el-button>-->
							<el-button type="info" @click="resetPwdForm">清空</el-button>
						</div>
					</el-form>
				</div>
			</div>
		</el-main>
		<el-footer>Theme By 贾瑞刚. ©2020</el-footer>
	</el-container>
</template>

<script>
	import Header from "@/components/Header";
	export default {
		name: "FindPassword",

		components: {Header},
		methods: {
			resetPwdForm(){
				// 两种方法
				// resetLoginForm(formName){
				// this.$refs[formName].resetField();
				// console.log(this);
				this.$refs.formRef.resetFields();
			},
			findPassword(){
				let info = JSON.stringify(this.pwdForm);
				this.$http.post("/user/findPassword",info)
					.then(res => {
						if (res.status === 200){
							this.$message({
								type: "success",
								message: "密码已经发送到您的邮箱",
								showClose:true
							});
							location.replace("/login")
						}else {
							this.$message("请检查您的用户名或者邮箱是否正确！");
						}
					}).catch(err => {
					this.$message("请检查您的用户名或者邮箱是否正确！");
				})
			}
		},
		data(){
			let checkEmail = (rule,value,callback) => {
				const re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
				if (!re.test(value)){
					callback(new Error('邮箱格式不合法'));
				}else {
					callback();
				}
			};
			return {
				//绑定数据
				pwdForm: {
					username:'',
					email:''
				},
				//验证规则
				pwdRules: {
					username: [
						{required: true, message: '请输入用户名', trigger: 'blur' },
					],
					email: [
						{required: true, message: '请输入邮箱', trigger: 'blur'},
						{min: 6, max:16,message:'请正确输入您的邮箱',trigger: 'blur'},
						{ validator: checkEmail, trigger: 'change' }
					]
				}
			}
		}
	}
</script>

<style scoped>

	.login-text {
		font-size: 25px;
		font-weight: bolder;
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
		position: absolute;
		bottom: 0;
		width: 80%;
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

	.home-wrapper {
		/*margin: 0 150px;*/
	}
	.login-container{
		height: 100%;
		width: 35%;
		margin: 0 auto;

		.login-box {
			width: 450px;
			height: 300px;
			border-radius: 3px;
			background-color: #fff;

			position: absolute;
			left: 50%;
			top: 40%;
			transform: translate(-50%,-50%);

			.login-form {
				position: absolute;
				bottom: 48px;
				width: 100%;
				padding: 0 30px;
				box-sizing: border-box;
			}
			.btns {
				display: flex;
				justify-content: flex-end;
			}

		}
		.avatar-box {
			/*width: 120px;*/
			/*height: 120px;*/

			position: absolute;
			top: 50px;
			left: 50%;
			transform: translate(-50%,-50%);

		}
	}
</style>
