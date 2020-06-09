<template>
	<el-container class="home-wrapper">
		<HeaderR/>
		<el-main>
			<div>
				<div class="register-wrapper">
					<div id="register">
						<p class="title">注册</p>
						<el-form
							:model="ruleForm2"
							status-icon
							:rules="rules2"
							ref="ruleForm2"
							label-width="0"
							class="demo-ruleForm"
						>
							<el-form-item prop="username">
								<el-input v-model="ruleForm2.username" auto-complete="off" placeholder="用户名"/>
							</el-form-item>
							<el-form-item prop="email">
								<el-input v-model="ruleForm2.email" auto-complete="off" placeholder="邮箱"/>
							</el-form-item>

							<el-form-item prop="password">
								<el-input type="password" v-model="ruleForm2.password" auto-complete="off" placeholder="输入密码"/>
							</el-form-item>
							<el-form-item prop="checkPassword">
								<el-input type="password" v-model="ruleForm2.checkPassword" auto-complete="off" placeholder="确认密码"/>
							</el-form-item>
							<el-form-item>
								<el-button type="primary" @click="submitForm('ruleForm2')" style="width:100%;">注册</el-button>
								<p class="login" @click="gotoLogin">已有账号？立即登录</p>
							</el-form-item>
						</el-form>
					</div>
				</div>
			</div>
		</el-main>
		<el-footer>Theme By 贾瑞刚. ©2020</el-footer>
	</el-container>


</template>

<script>
	import HeaderR from "@/components/HeaderR";
	import Aside from "@/components/Aside";

	export default {
		name: "Register",

		components: {HeaderR,Aside},
		data() {
			//  <!--验证码是否为空-->
			let checkUsername = (rule, value, callback) => {
				if (value === '' || value.length < 5) {
					callback(new Error('用户名至少5位'))
				} else {
					callback()
				}
			};
			// 验证邮箱
			let checkEmail = (rule,value,callback) => {
				const re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
				if (!re.test(value)){
					callback(new Error('邮箱格式不合法'));
				}else {
					callback();
				}
			};
			// <!--验证密码-->
			let validatePassword = (rule, value, callback) => {
				if (value === "" || value.length < 8) {
					callback(new Error("密码长度不小于8位"))
				} else {
					if (this.ruleForm2.checkPassword !== "") {
						this.$refs.ruleForm2.validateField("checkPassword");
					}
					callback()
				}
			};
			// <!--二次验证密码-->
			let validatePassword2 = (rule, value, callback) => {
				if (value === "") {
					callback(new Error("请再次输入密码"));
				} else if (value !== this.ruleForm2.password) {
					callback(new Error("两次输入密码不一致!"));
				} else {
					callback();
				}
			};
			return {
				ruleForm2: {
					password: "",
					checkPassword: "",
					username: "",
					email: ""
				},
				rules2: {
					password: [{ validator: validatePassword, trigger: 'change' }],
					checkPassword: [{ validator: validatePassword2, trigger: 'change' }],
					username: [{ validator: checkUsername, trigger: 'change' }],
					email: [{ validator: checkEmail, trigger: 'change' }]
				},
				flag: true
			}
		},
		methods: {
			submitForm(formName) {
				this.$refs[formName].validate(valid => {
					if (valid) {
						let infoStr = JSON.stringify(this.ruleForm2);
						this.register(infoStr)
					} else {
						console.log("error submit!!");
						return false;
					}
				})
			},
			register(infoStr){
				this.$http.post("/user/register",infoStr)
					.then(res => {
						console.log(res);
						if (res.status === 200){
							this.$message({
								type: "success",
								message: "注册成功！",
								duration: 1500
							});
							this.$router.push("/login");
						}else {
							this.$message.error("注册失败,请更换用户名！")
						}
					}).catch(err => {
						this.$message({
							type: "error",
							message: "注册失败！"
						})
				})
				// console.log(infoStr)
			},
			gotoLogin() {
				this.$router.push({
					path: "/login"
				});
			},
		}
	}
</script>

<style scoped>
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
	.loading-wrapper {
		/*position: fixed;*/
		/*top: 200px;*/
		/*right: 0;*/
		/*left: 0;*/
		/*bottom: 0;*/
		background: #aedff8;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	/*.register-wrapper img {*/
	/*	position: absolute;*/
	/*	z-index: 2;*/
	/*}*/
	/*.register-wrapper {*/
	/*	position: fixed;*/
	/*	top: 0;*/
	/*	right: 0;*/
	/*	left: 0;*/
	/*	bottom: 0;*/
	/*}*/
	#register {
		max-width: 340px;
		margin: 30px auto;
		background: #fff;
		padding: 20px 40px;
		border-radius: 10px;
		position: relative;
		z-index: 9;
	}
	.title {
		font-size: 26px;
		line-height: 50px;
		font-weight: bold;
		margin: 10px;
		text-align: center;
	}
	.el-form-item {
		text-align: center;
	}
	.login {
		margin-top: 10px;
		font-size: 14px;
		line-height: 22px;
		color: #1ab2ff;
		cursor: pointer;
		text-align: left;
		text-indent: 8px;
		width: 160px;
	}
	.login:hover {
		color: #2c2fd6;
	}
	.code >>> .el-form-item__content {
		display: flex;
		align-items: center;
		justify-content: space-between;
	}
	.code button {
		margin-left: 20px;
		width: 140px;
		text-align: center;
	}
	.el-button--primary:focus {
		background: #409EFF;
		border-color: #409EFF;
		color: #fff;
	}
</style>

