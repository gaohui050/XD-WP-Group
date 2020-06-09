<template>
	<div>
		<!--		卡片视图  -->
		<el-card>
			<el-table
				v-loading="loading"
				:data="userList"
				border
				:cell-style="'text-align:center;'"
				:header-cell-style="'text-align:center;'"
				style="width: 100%">
				<el-table-column
					label="编号"
					type="index"
					width="100">
				</el-table-column>
				<el-table-column
					prop="username"
					label="用户名"
					width="180">
				</el-table-column>
				<el-table-column
					prop="email"
					label="邮箱"
					width="180">
				</el-table-column>
				<el-table-column
					prop="role"
					label="角色">
				</el-table-column>
					<el-table-column label="操作">
						<template v-slot="slotScope">
						<el-tooltip effect="dark" content="删除用户"  placement="top-start" :enterable="false">
							<el-button type="danger" @click="deleteUser(slotScope.row)" icon="el-icon-delete" size="mini"/>
						</el-tooltip>
						<el-tooltip effect="dark" content="设置角色" placement="top-start" :enterable="false">
							<el-button type="warning" @click="showSettingWindows(slotScope.row)" icon="el-icon-setting" size="mini"/>
						</el-tooltip>
						</template>
					</el-table-column>
<!--				设置角色的对话框-->
				<el-dialog title="设置角色" :visible.sync="dialogShow" append-to-body>
					<el-form :model="formRole">
						<el-form-item label="选择角色" :label-width="formLabelWidth">
							<el-select v-model="formRole.role" placeholder="请选择角色">
								<el-option label="普通用户" value="普通用户"/>
								<el-option label="版主" value="版主"/>
							</el-select>
						</el-form-item>
						<el-form-item v-show="formRole.role==='版主'" label="选择版块" :label-width="formLabelWidth">
							<el-select  v-model="formRole.forum" placeholder="请选择版块">
								<el-option label="Java" value="java"/>
								<el-option label="C/C++" value="cpp"/>
								<el-option label="Golang" value="go"/>
								<el-option label="Python" value="python"/>
								<el-option label="JavaScript" value="javascript"/>
								<el-option label="Kotlin" value="kotlin"/>
								<el-option label="其他语言" value="otherlang"/>
								<el-option label="Spring" value="spring"/>
								<el-option label="Spring MVC" value="springmvc"/>
								<el-option label="Spring Boot" value="springboot"/>
								<el-option label="Dubbo" value="dubbo"/>
								<el-option label="Mybatis" value="mybatis"/>
								<el-option label="Spring Cloud" value="springcloud"/>
								<el-option label="其他框架" value="otherframe"/>
								<el-option label="经验分享" value="share"/>
								<el-option label="新手求助" value="help"/>
								<el-option label="论坛事务" value="transaction"/>
							</el-select>
						</el-form-item>
					</el-form>
					<div slot="footer" class="dialog-footer">
						<el-button @click="dialogShow = false">取 消</el-button>
						<el-button type="primary" @click="setRole">确 定</el-button>
					</div>
				</el-dialog>
			</el-table>
			<!--			分页    -->
<!--			<div class="page">-->
<!--				<el-pagination-->
<!--					@size-change="handleSizeChange"-->
<!--					@current-change="handleCurrentChange"-->
<!--					:current-page="queryInfo.pageNum"-->
<!--					:page-sizes="[5, 10, 15, 20]"-->
<!--					:page-size="queryInfo.pageSize"-->
<!--					layout="total, sizes, prev, pager, next"-->
<!--					:total="total">-->
<!--				</el-pagination>-->
<!--			</div>-->
		</el-card>

	</div>
</template>

<script>
	export default {
		name: "UserManage",
		data(){
			return {
				loading: false,
				formLabelWidth: '120px',
				dialogShow: false,
				username: '',
				formRole: {
					role:'',
					forum: ''
				},
				//  做为分页时使用
				queryInfo: {
					// query: '',
					// // 当前页数
					pageNum: 1,
					// 每页显示多少数据
					pageSize: 0
				},
				userList: [{
					username: 'wangxiaohu',
					email: 'wang@qq.com',
					role: '普通用户',
				}],
				total: 0,
			}
		},
		created () {
			this.loading = true;
			this.getUserList();
			this.loading = false;
		},
		methods: {
			setRole(){
				this.dialogShow = false;
				let setInfo = this.formRole;
				if (setInfo.forum === ''){
					delete setInfo.forum;
				}
				setInfo.username = this.username;
				console.log(setInfo);
				this.$http.post("/user/setRole",JSON.stringify(setInfo),{
					headers: {"Content-Type":"application/json;charset=UTF-8"}
				}).then(res => {
					if (res.status === 200){
						this.$message({
							type: "success",
							message: "设置成功！"
						});
						this.getUserList();
					}else {
						this.$message.error("设置失败");
					}
				}).catch(err => {
					this.$message.error("设置失败！");
				})
			},
			showSettingWindows(userInfo){
				this.dialogShow = !this.dialogShow;
				this.username = userInfo.username;

			},
			tableRowClassName({row, rowIndex}) {
				if (rowIndex === 1) {
					return 'warning-row';
				} else if (rowIndex === 3) {
					return 'success-row';
				}
				return '';
			},
			getUserList () {
				this.$http.post("/user/getUsers",JSON.stringify(this.queryInfo)).then(res => {
					if (res.status === 200){
						this.userList = res.data.list;
						this.total = res.data.total;
					}
				})
			},
			// // 监听 pagesize改变的事件
			handleSizeChange (newSize) {
				// console.log(newSize)
				this.queryInfo.pageSize = newSize;
				this.getUserList()
			},
			// 监听 页码值 改变事件
			handleCurrentChange (newSize) {
				// console.log(newSize)
				this.queryInfo.pageNum = newSize;
				this.getUserList()
			},
			// 删除用户
			deleteUser(userInfo){
				this.$http.post("/user/deleteUser/"+userInfo.username).then(res => {
					if (res.status === 200){
						this.getUserList();
					}else {
						this.$message.error("删除失败！");
					}
				}).catch(err => {
					this.$message.error("删除失败！");
				})
			},
		}
	}
</script>

<style scoped lang="less">

	/deep/  .el-table-column {
		text-align: center;
	}

	.page {
		margin-top: 15px;
	}
</style>
