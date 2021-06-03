<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/24
  Time: 3:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default navbar-static-top navbar-inverse">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#slider_sub"
                aria-controls="slider_sub" aria-expanded="false">
            <!--        <span class="sr-only">Toggle navigation</span>-->
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand">户籍管理系统</a>
    </div>

    <ul class="nav navbar-nav navbar-right" style="margin-right: 20px">
        <li><a>当前系统时间为：<span id="nowTime">这里显示当前系统时间</span></a></li>
        <li><a>当前身份：<span id="role">${sessionScope.role}</span></a></li>
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span class="glyphicon glyphicon-off"></span>&nbsp;退出系统</a>
        </li>
    </ul>

    <!--侧边功能栏-->
    <div class="navbar-default navbar-collapse" id="slider_sub">
        <ul class="nav" aria-expanded="true">
            <li><a href="noticeServlet?action=queryOneNoticeByLimit&pageNo=1&pageSize=1"><span
                    class="glyphicon glyphicon-briefcase"></span>&nbsp;系统首页</a></li>
            <%--            <li><a href="${pageContext.request.contextPath}/admin/notice.jsp"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;公告管理</a></li>--%>
            <li>
                <a href="#sub2" class="collapse" data-toggle="collapse">
                    <span class="glyphicon glyphicon-briefcase"></span>
                    &nbsp;公告管理
                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                </a>
                <ul id="sub2" class="nav collapse">
                    <li><a href="${pageContext.request.contextPath}/admin/notice_add.jsp"><span
                            class="glyphicon glyphicon-plus" style="margin-left: 10px"></span>&nbsp;&nbsp;添加公告</a></li>
                    <li><a href="noticeServlet?action=queryOneNoticeByLimit&pageNo=1&pageSize=10">
                        <sapn class="glyphicon glyphicon-comment" style="margin-left: 10px"></sapn>&nbsp;&nbsp;历史公告</a>
                    </li>
                </ul>
            </li>
            <li><a href="userServlet?action=queryUserByLimit&pageNo=1&pageSize=10"><span
                    class="glyphicon glyphicon-user"></span>&nbsp;权限管理</a></li>

            <li>
                <a href="#sub1" class="collapse" data-toggle="collapse">
                    <span class="glyphicon glyphicon-stats"></span>&nbsp;
                    审核管理
                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                </a>
                <ul id="sub1" class="nav collapse">
                    <li><a href="checkPersonServlet?action=queryAllCheckPerson&pageNo=1&pageSize=10"><span
                            class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;人员审核</a></li>
                    <li><a href="migrationServlet?action=queryAllMigration&pageNo=1&pageSize=10">
                        <sapn class="glyphicon glyphicon-log-out"></sapn>&nbsp;&nbsp;迁徙审核</a></li>
                    <%--                    <li><a href="#"><sapn class="glyphicon glyphicon-log-out"></sapn>&nbsp;&nbsp;户籍审核</a></li>--%>
                    <li><a href="opinionServlet?action=queryAllOpinionAllUser&pageNo=1&pageSize=10">
                        <sapn class="glyphicon glyphicon-log-out"></sapn>&nbsp;&nbsp;意见反馈</a></li>
                </ul>
            </li>
            <%--            <li><a href="#"><span class="glyphicon glyphicon-user"></span>&nbsp;人员信息管理</a></li>--%>
            <li><a href="userServlet?action=queryNowPerson"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;个人信息中心</a>
            </li>
        </ul>
    </div>
</nav>
<script type="text/javascript">

    var t = setTimeout("countdown()", 1000);

    function countdown() {
        clearTimeout(t);

        var myDate = new Date();
        //获取当前年
        var year = myDate.getFullYear();
        //获取当前月
        var month = myDate.getMonth() + 1;
        //获取当前日
        var date = myDate.getDate();
        var h = myDate.getHours();       //获取当前小时数(0-23)
        var m = myDate.getMinutes();     //获取当前分钟数(0-59)
        var s = myDate.getSeconds();
        var now = year + '-' + getNow(month) + "-" + getNow(date) + " " + getNow(h) + ':' + getNow(m) + ":" + getNow(s);
        // 赋值给展示时间
        $('#nowTime').html(now);
        t = setTimeout("countdown()", 1000);
    }

    // 获取当前时间
    function getNow(s) {
        return s < 10 ? '0' + s : s;
    }
</script>
