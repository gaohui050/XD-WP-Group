<template>
	<el-container class="home-wrapper">
		<HeaderL/>
		<el-main>
			<div class="login-container">

				<div class="login-box">
					<div class="avatar-box">
						<div class="login-text">登录</div>
					</div>
					<!--登陆表单-->
					<el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" label-width="70px" class="login-form">
						<!--				用户名 prop是绑定验证规则-->
						<el-form-item label="用户名" prop="username">
							<el-input ref="userInput" v-model="loginForm.username"
							          placeholder="请输入用户名" auto-complete="off" clearable/>
						</el-form-item>
						<!--				密码-->
						<el-form-item label="密码" prop="password">
							<el-input ref="pwdInput" v-model="loginForm.password" class="el-icon-pas"
							          type="password" placeholder="请输入密码" clearable />
						</el-form-item>
						<!--				按钮-->
						<div class="btns">
							<el-button type="primary" @click="gotoLogin">登录</el-button>
							<!--					<el-button type="info" @click="resetLoginForm('loginForm')">清空</el-button>-->
							<el-button type="info" @click="resetLoginForm">清空</el-button>
						</div>
						<p class="find-pwd" @click="toFindPassword">忘记密码？</p>
					</el-form>
				</div>
			</div>
		</el-main>
		<el-footer>Theme By 贾瑞刚. ©2020</el-footer>
	</el-container>


</template>

<script>
	import Header from "@/components/Header";
	import Aside from "@/components/Aside";
	import HeaderL from "@/components/HeaderL";
	export default {
		name: "Login",

		components: {HeaderL, Header,Aside},
		methods: {
			resetLoginForm(){
				// 两种方法
				// resetLoginForm(formName){
				// this.$refs[formName].resetField();
				// console.log(this);
				this.$refs.loginFormRef.resetFields();
			},
			gotoLogin() {
				this.$http.post("/user/login",JSON.stringify(this.loginForm),{
					headers: {"Content-Type": "application/json;charset=UTF-8"}
				}).then(res => {
					if (res.status === 200){
						console.log(res);
						sessionStorage.setItem("token",res.data.token);
						sessionStorage.setItem("username",res.data.username);
						sessionStorage.setItem("logged","true");
						sessionStorage.setItem("role",res.data.role);
						sessionStorage.setItem("forum",res.data.forum);
						// console.log(res.data.token);
						// console.log(res.data.username);
						this.$message({
							message: this.loginForm.username + ",欢迎您！",
							type: "success",
							showClose: true,
							duration: 1500
						});
						this.$router.push("/");
					}else {
						this.$message.error({
							message: "登录失败",
							center: true,
						})
					}
				})
			},
			toFindPassword(){
				this.$router.push("/findPassword")
			}
		},
		created(){
			let logged = sessionStorage.getItem("logged");
			if (logged === "true"){
				this.$router.push('/');
				this.$message({
					type: 'success',
					message: "您已登录！"
				})
			}
		},
		data(){
			return {
				//绑定数据
				loginForm: {
					username:'',
					password:''
				},
				//验证规则
				loginRules: {
					username: [
						{required: true, message: '请输入用户名', trigger: 'blur' },
						{ min: 5, message: '长度至少5个字符', trigger: 'blur' }
					],
					password: [
						{required: true, message: '请输入密码', trigger: 'blur'},
						{min: 6, max:16,message:'长度在8到16个字符',trigger: 'blur'}
					]
				}
			}
		}
	}
</script>

<style scoped lang="less">
	.find-pwd {
		margin-top: 10px;
		margin-bottom: -15px;
		font-size: 16px;
		line-height: 22px;
		color: #1ab2ff;
		cursor: pointer;
		text-align: right;
		text-indent: 8px;
		width: 82%;
	}
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

	.home-wrapper {
		/*margin: 0 150px;*/
	}
	.login-container{
		height: 100%;

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

