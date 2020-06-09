<template>
	<el-container class="main-body">
		<el-aside :width="'200px'">
			<el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
				<el-radio-button :label="false">展开</el-radio-button>
				<el-radio-button :label="true">收起</el-radio-button>
			</el-radio-group>
			<el-menu
				unique-opened router :collapse-transition="false"  default-active="/forum/cpp" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
				<el-submenu index="1">
					<template slot="title">
						<i class="el-icon-edit"/>
						<span slot="title">编程语言</span>
					</template>
					<el-menu-item-group>
<!--						<span slot="title">分组一</span>-->
						<el-menu-item index="/forum/cpp">C/C++</el-menu-item>
						<el-menu-item index="/forum/java">Java</el-menu-item>
						<el-menu-item index="/forum/js">JavaScript</el-menu-item>
						<el-menu-item index="/forum/py">Python</el-menu-item>
						<el-menu-item index="/forum/go">Go</el-menu-item>
						<el-menu-item index="/forum/kotlin">Kotlin</el-menu-item>
						<el-menu-item index="/forum/otherlang">其他</el-menu-item>
					</el-menu-item-group>
				</el-submenu>
				<el-submenu index="2">
					<template slot="title">
						<i class="el-icon-cpu"/>
						<span slot="title">开源框架</span>
					</template>
					<el-menu-item-group>
						<!--						<span slot="title">分组一</span>-->
						<el-menu-item index="/forum/spring">Spring</el-menu-item>
						<el-menu-item index="/forum/springmvc">Spring MVC</el-menu-item>
						<el-menu-item index="/forum/springboot">Spring Boot</el-menu-item>
						<el-menu-item index="/forum/springcloud">Spring Cloud</el-menu-item>
						<el-menu-item index="/forum/dubbo">Dubbo</el-menu-item>
						<el-menu-item index="/forum/mybatis">Mybatis</el-menu-item>
						<el-menu-item index="/forum/otherframe">其他</el-menu-item>
					</el-menu-item-group>
				</el-submenu>
				<el-menu-item index="/forum/share">
					<i class="el-icon-chat-dot-round"/>
					<span slot="title">经验分享</span>
				</el-menu-item>
				<el-menu-item index="/forum/help">
					<i class="el-icon-s-promotion"/>
					<span slot="title">新手求助</span>
				</el-menu-item>
				<el-menu-item index="/forum/transaction">
					<i class="el-icon-menu"/>
					<span slot="title">论坛事务</span>
				</el-menu-item>
			</el-menu>
		</el-aside>
		<div class="main">
			<router-view/>
		</div>
		<el-backtop target=".page-component__scroll .el-scrollbar__wrap" :bottom="100">
			<div
				style="{
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0,0,0, .12);
        text-align: center;
        line-height: 40px;
        color: #1989fa;
      }"
			>
				UP
			</div>
		</el-backtop>
	</el-container>
</template>

<script>
	export default {
		name: "Aside",
		data(){
			return {
				isCollapse: false
			}
		},
	}
</script>

<style scoped>
	.main {
		width: 90%;
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
		margin-top: 20px;
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
