import Vue from 'vue'
import VueRouter from 'vue-router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Element from 'less/lib/less/tree/element'
import Home from "@/components/Home";
import Login from "@/components/user/Login";
import Register from "@/components/user/Register";
import Cpp from "@/components/forums/Cpp";
import Java from "@/components/forums/Java";
import JavaScript from "@/components/forums/JavaScript";
import Python from "@/components/forums/Python";
import Go from "@/components/forums/Go";
import Kotlin from "@/components/forums/Kotlin";
import OtherLang from "@/components/forums/OtherLang";
import Spring from "@/components/forums/Spring";
import SpringMVC from "@/components/forums/SpringMVC";
import SpringBoot from "@/components/forums/SpringBoot";
import SpringCloud from "@/components/forums/SpringCloud";
import Dubbo from "@/components/forums/Dubbo";
import Mybatis from "@/components/forums/Mybatis";
import OtherFrame from "@/components/forums/OtherFrame";
import Share from "@/components/forums/Share";
import Help from "@/components/forums/Help";
import Transaction from "@/components/forums/Transaction";
import About from "@/components/About";
import NewPost from "@/components/user/NewPost";
import Admin from "@/components/admin/Admin";
import UserManage from "@/components/admin/UserManage";
import FindPassword from "@/components/user/FindPassword";
import Profile from "@/components/user/Profile";
import ModifyProfile from "@/components/user/ModifyProfile";
import Content from "@/components/Content";
import Search from "@/components/Search";

Vue.use(ElementUI);
Vue.use(VueRouter);

  const routes = [
    {
      path: '/',
      redirect: '/forum'

    },
    {
      path: '/forum',
      component: Home,
      redirect: '/forum/cpp',
      children: [
        {
          path: '/forum/cpp',
          component: Cpp,
          meta: {
            title: "论坛-C++"
          }
        },
        {
          path: '/forum/java',
          component: Java
        },
        {
          path: '/forum/js',
          component: JavaScript
        },
        {
          path: '/forum/py',
          component: Python
        },
        {
          path: '/forum/go',
          component: Go
        },
        {
          path: '/forum/kotlin',
          component: Kotlin
        },
        {
          path: '/forum/otherlang',
          component: OtherLang
        },
        {
          path: '/forum/spring',
          component: Spring
        },
        {
          path: '/forum/springmvc',
          component: SpringMVC
        },
        {
          path: '/forum/springboot',
          component: SpringBoot
        },
        {
          path: '/forum/springcloud',
          component: SpringCloud
        },
        {
          path: '/forum/dubbo',
          component: Dubbo
        },
        {
          path: '/forum/mybatis',
          component: Mybatis
        },
        {
          path: '/forum/otherframe',
          component: OtherFrame
        },
        {
          path: '/forum/share',
          component: Share
        },
        {
          path: '/forum/help',
          component: Help
        },
        {
          path: '/forum/transaction',
          component: Transaction
        }
      ]
    },
    {
      path: '/about',
      component: About
    },
    {
      path : '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/newPost',
      component: NewPost
    },
    {
      path: '/admin',
      component: Admin,
      redirect: '/admin/usermanage',
      children: [
        {
          path: '/admin/usermanage',
          component: UserManage
        }
      ]
    },
    {
      path: "/findPassword",
      component: FindPassword
    },{
      path: "/profile",
      component: Profile
    }, {
      path: "/modifyProfile",
      component: ModifyProfile
    }, {
      path: '/content',
      name: 'content',
      component: Content
    }, {
      path: '/search',
      name: 'search',
      component: Search
    }

];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});
router.beforeEach((to,from,next) => {
  document.title = "西电技术交流论坛";
  if (to.path === "/admin/usermanage"){
    if (sessionStorage.getItem("role") !== "超级管理员"){
      alert("没有权限！");
      next("/");
    }
    document.title = "后台管理中心";
  }
  next();
});
router.afterEach((to,from) => {
  document.title = "西电技术交流论坛";
});
export default router
