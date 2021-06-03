<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/30
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>权限管理</title>
    <%--  引用公共的资源--%>
    <%@include file="/commons/header.jsp" %>
    <style type="text/css">
        @media (min-width: 768px) {
            #slider_sub {
                width: 200px;
                margin-top: 51px;
                position: absolute;
                z-index: 1;
                height: 620px;

            }

            .page_main {
                margin-left: 205px;
                margin-top: -15px;
                height: 620px;
                padding: 5px;
                overflow-x: hidden;
            }

            .page_main::-webkit-scrollbar {
                display: none;
            }

            #slider_sub li {
                margin-top: 20px;
            }

            .panel {
                padding: 5px;
                float: none;
                display: inline-block;
                vertical-align: middle;
            }

            .table {
                text-align: center;
                table-layout: fixed;
                word-break: break-all;
            }

            .table > tbody > tr > td {
                vertical-align: middle;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
            }

            #sub1 {
                margin-left: 10px;
            }

            .alert {
                position: absolute;
                z-index: 1;
                width: 100%;
                text-align: left;
            }

        }
    </style>
</head>
<body><!--静态包含导航条和侧边栏-->
<%@include file="/commons/admin_public.jsp" %>
<div class="page_main">
    <c:if test="${ ! empty  requestScope.message}">
        <div class="alert alert-success alert-dismissible" data-dismiss="alert" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示信息!</strong> ${requestScope.message}
        </div>
    </c:if>
    <!--    面包屑导航-->
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/admin/admin_index.jsp">首页</a></li>
        <li><span>权限管理</span></li>
    </ol>
    <div class="panel panel-default" style="width: 100%">
        <div class="panel-body">

            <form class="form-horizontal" action="userServlet" method="post">
                <input type="hidden" name="action" value="queryUserByLimit">
                <input type="hidden" name="pageNo" value="1">
                <input type="hidden" name="pageSize" value="10">
                <div class="input-group col-md-3" style="margin-bottom:5px ;positon:relative">
                    <input type="text" name="userName" class="form-control" placeholder="请输入姓名">
                    <span class="input-group-btn">
               <button class="btn btn-info btn-search">搜索</button>
<%--               <button class="btn btn-info btn-search" style="margin-left:3px">添加</button>--%>
            </span>
                </div>
            </form>

            <div class="btn-group" role="group" aria-label="" style="width: 100%">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th width="4%" class="text-center">序号</th>
                        <th width="8%" class="text-center">姓名</th>
                        <th width="10%" class="text-center">身份证号</th>
                        <th width="10%" class="text-center">手机号</th>
                        <th width="10%" class="text-center">邮箱</th>
                        <th width="7%" class="text-center">密码</th>
                        <th width="8%" class="text-center">当前身份</th>
                        <th width="8%" class="text-center">人员状态</th>
                        <th width="14%" class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="a">
                    <c:forEach items="${requestScope.user.items}" var="user" varStatus="userIndex">
                        <tr class="${user.power == 0 ?"bg-warning" :"bg-success"}"}>
                            <td>${userIndex.index+1}</td>
                            <td>${requestScope.person[userIndex.index].name}</td>
                            <td>${requestScope.person[userIndex.index].identityNum}</td>
                            <td>${user.phone}</td>
                            <td>${user.email}</td>
                            <td>${user.userPassword}</td>
                            <td>${user.power == 0 ? "普通用户" : "管理人员"}</td>
                            <td>${requestScope.person[userIndex.index].status == 0 ? "正常" : "已注销"}</td>
                            <td data-toggle="tooltip" data-placement="bottom"
                                title="${user.power == 1 ? "您不能对管理员进行操作" : ""}">
                                <a href="userServlet?action=updateUserPower&id=${user.id}" type="button"
                                        class="btn btn-primary btn-xs ${user.power == 1 || requestScope.person[userIndex.index].status == 1 ? "disabled" : ""}">
                                    <span class="glyphicon glyphicon-pencil"></span> 设为管理员
                                </a>
                                <a href="userServlet?action=deleteUserById&id=${user.id}" type="button"
                                        class="btn btn-danger btn-xs ${user.power == 1 ? "disabled" : ""}">

                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <nav aria-label="Page navigation" class="pull-right">
                    <ul class="pagination">
                        <li class="${requestScope.user.pageNo == 1 ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.user.pageNo == 1 ? "当前已经是第一页" : ""}">
                            <a href="userServlet?action=queryUserByLimit&pageSize=10&pageNo=${requestScope.user.pageNo-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;上一页</span>
                            </a>
                        </li>
                        <li class="${requestScope.user.pageNo == requestScope.user.pageTotal ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.user.pageNo == 1 ? "当前已经是最后一页" : ""}">
                            <a href="userServlet?action=queryUserByLimit&pageSize=10&pageNo=${requestScope.user.pageNo+1}" aria-label="Next">
                                <span aria-hidden="true">下一页&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</div>
</body>
</html>
