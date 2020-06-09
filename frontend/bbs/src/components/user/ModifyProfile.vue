<template>
	<el-container class="home-wrapper">
		<Header/>
		<el-main>
			<div class="login-container">
				<div class="login-box">
					<div class="avatar-box">
						<div class="login-text">修改个人信息</div>
					</div>
					<!--表单-->
					<el-form :hide-required-asterisk="false" ref="formRef" :model="pwdForm" :rules="pwdRules" label-width="100px" class="login-form">
						<el-form-item label="邮箱" prop="email">
							<el-input ref="emailInput" v-model="pwdForm.email" prefix-icon=""
							          type="text" placeholder="请输入邮箱" clearable />
						</el-form-item>
						<el-form-item label="密码" prop="password">
							<el-input type="password" v-model="pwdForm.password"  auto-complete="off"  placeholder="输入密码"/>
						</el-form-item>
						<el-form-item label="确认密码" prop="checkPassword">
							<el-input type="password" v-model="pwdForm.checkPassword" auto-complete="off" placeholder="确认密码"/>
						</el-form-item>
						<!--				按钮-->
						<div class="btns">
							<el-button type="primary" @click="modify">确认</el-button>
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
		name: "ModifyProfile",
		components: {Header},
		methods: {
			modify(){
				let info = this.pwdForm;
				info.username = sessionStorage.getItem("username");
				this.$http.put("/user/update",JSON.stringify(info),{
					headers: {"Content-Type":"application/json;charset=UTF-8" }
				}).then(res => {
					if (res.status === 200){
						this.$message({
							type: "success",
							message: "修改成功,请重新登录！"
						});
						this.$router.push('/login');

					}else {
						this.$message.error("修改失败！");
					}
				}).catch(err => {
					this.$message.error("修改失败！");
				})
			},
			resetPwdForm(){
				// 两种方法
				// resetLoginForm(formName){
				// this.$refs[formName].resetField();
				// console.log(this);
				this.$refs.formRef.resetFields();
			}
		},
		data() {
			let checkEmail = (rule, value, callback) => {
				const re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
				if (!re.test(value)) {
					callback(new Error('邮箱格式不合法'));
				} else {
					callback();
				}
			};
			let validatePassword = (rule, value, callback) => {
				if (value === "" || value.length < 8) {
					callback(new Error("密码长度不小于8位"))
				} else {
					if (this.pwdForm.checkPassword !== "") {
						this.$refs.ruleForm2.validateField("checkPassword");
					}
					callback()
				}
			};
			// <!--二次验证密码-->
			let validatePassword2 = (rule, value, callback) => {
				if (value === "") {
					callback(new Error("请再次输入密码"));
				} else if (value !== this.pwdForm.password) {
					callback(new Error("两次输入密码不一致!"));
				} else {
					callback();
				}
			};
			return {
				//绑定数据
				pwdForm: {
					password: '',
					email: '',
					checkPassword: ''
				},
				//验证规则
				pwdRules: {
					password: [
						{required: true, message: '请输入密码', trigger: 'blur'},
						{ validator: validatePassword, trigger: 'change' }],
					checkPassword: [
						{required: true, message: '请确认密码', trigger: 'blur'},
						{ validator: validatePassword2, trigger: 'change' }],
					email: [
						{required: true, message: '请输入邮箱', trigger: 'blur'},
						{min: 6, max: 16, message: '请正确输入您的邮箱', trigger: 'blur'},
						{validator: checkEmail, trigger: 'change'}
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
