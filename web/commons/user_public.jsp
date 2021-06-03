<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/24
  Time: 3:12
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
            <li><a href="noticeServlet?action=queryOneNoticeByLimitToUser&pageNo=1&pageSize=1"><span
                    class="glyphicon glyphicon-briefcase"></span>&nbsp;系统首页</a></li>
            <%--            <li><a href="personServlet?action=houseHoldDetails"><span class="glyphicon glyphicon-home"></span>&nbsp;户主信息办理</a></li>--%>

            <li>
                <a href="#sub1" class="collapse" data-toggle="collapse">
                    <span class="glyphicon glyphicon-stats"></span>&nbsp;
                    户口迁徙办理
                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                </a>
                <ul id="sub1" class="nav collapse">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/immigration.jsp">
                            <span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;
                            迁入办理
                        </a>
                    </li>
                    <li><a href="householdServlet?action=queryHouseholdById&name=emigration">
                        <sapn class="glyphicon glyphicon-log-out"></sapn>&nbsp;&nbsp;迁出办理</a></li>
                </ul>
            </li>

            <li>
                <a href="#sub2" class="collapse" data-toggle="collapse">
                    <span class="glyphicon glyphicon-envelope"></span>&nbsp;
                    信息反馈管理
                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                </a>
                <ul id="sub2" class="nav collapse">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/opinion_add.jsp">
                            &nbsp;&nbsp;<span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;
                              新增意见
                        </a>
                    </li>
                    <li><a href="opinionServlet?action=queryAllOpinion&pageNo=1&pageSize=10">&nbsp;&nbsp;<span
                            class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;  我的意见</a></li>
                </ul>
            </li>
            <li><a href="personServlet?action=queryUserInfo"><span class="glyphicon glyphicon-briefcase"></span>&nbsp;出生证明办理</a></li>
            <li><a href="householdServlet?action=queryHouseholdById&name=death"><span class="glyphicon glyphicon-inbox"></span>&nbsp;&nbsp;死亡证明办理</a></li>
            <li>
                <a href="#sub3" class="collapse" data-toggle="collapse">
                    <span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;个人信息中心
                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                </a>

                <ul id="sub3" class="nav collapse">
                    <li><a href="householdServlet?action=queryHouseholdById&name=myHousehold">&nbsp;&nbsp;<span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;  我的户籍</a></li>
                    <li><a href="userServlet?action=queryNowPerson">&nbsp;&nbsp;<span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;  我的信息</a></li>
                </ul>
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