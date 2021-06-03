<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/5/4
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>出生证明办理</title>
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
<body>

<!--静态包含导航条和侧边栏-->
<%@include file="/commons/user_public.jsp" %>


<div class="page_main">
    <c:if test="${ ! empty  requestScope.message}">
        <div class="alert alert-danger alert-dismissible" data-dismiss="alert" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示信息!</strong> ${requestScope.message}
        </div>
    </c:if>

    <!--    面包屑导航-->
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
        <li><span>户籍迁入办理</span></li>
    </ol>

    <div class="panel panel-default" style="width: 100%">
        <div class="panel-body">
            <blockquote>
                <small>户籍迁入：外来人员迁入到本户口。需要手动为外来人口设置与户主之间的关系，同时将会清除迁入人口原本的信息，并修改迁入人员的户籍地，户籍号，与新户主之间的关系等信息</small>
            </blockquote>
            <form class="form-horizontal" action="migrationServlet" method="post">
                <input type="hidden" name="action" value="Immigration">
<%--                迁入人员姓名--%>
                <div class="form-group">
                    <label for="userName" class="col-sm-1 control-label">人员姓名</label>
                    <div class="col-sm-5">
                        <input type="text" id="userName" class="form-control" name="userName" placeholder="请输入迁入人员姓名">
                    </div>
                </div>
<%--                迁入人员身份证号--%>
                <div class="form-group">
                    <label for="userIdNum" class="col-sm-1 control-label">身份证号</label>
                    <div class="col-sm-5">
                        <input type="text" id="userIdNum" class="form-control" placeholder="请输入迁入人员身份证号" name="userIdNum">
                    </div>
                </div>
<%--                与户主之间的关系--%>
                <div class="form-group">
                    <label for="relation" class="col-sm-1 control-label">关系</label>
                    <div class="col-sm-3" id="relation">
                        <select class="marriage selectpicker" name="relation" data-live-search="true">
                            <option value="长子">长子</option>
                            <option value="长女">长女</option>
                            <option value="次子">次子</option>
                            <option value="次女">次女</option>
                            <option value="配偶">配偶</option>
                            <option value="其他">其他</option>
                        </select>
                    </div>
                </div>
<%--                提交按钮--%>
                <div class="form-group">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-primary pull-right">申请办理</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

</body>
</html>