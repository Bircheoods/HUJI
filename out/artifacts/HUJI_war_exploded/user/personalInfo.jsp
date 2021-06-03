<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/5/6
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的信息</title>
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

            .table tr td {
                width: 150px;
                text-align: center;
            }

            .userinfo {
                background: #ddd;
            }
        }
    </style>
</head>
<body>
<!--静态包含导航条和侧边栏-->
<%@include file="/commons/user_public.jsp" %>
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
        <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
        <li><span>我的信息</span></li>
    </ol>

    <div class="panel panel-default">
        <div class="panel-body">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6 col-sm-push-3 ">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <form class="form-horizontal" action="userServlet" method="post">
                                    <input type="hidden" name="action" value="updateUserInfo">
                                    <div class="form-group">
                                        <label for="userName" class="col-sm-3 control-label">姓名</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="userName" class="form-control" name="userName"
                                                   readonly value="${requestScope.person.name}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="userPower" class="col-sm-3 control-label">当前身份</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="userPower" class="form-control" name="userPower"
                                                   readonly value="${requestScope.user.power == "1" ? "管理人员":"普通用户"}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="userIdNum" class="col-sm-3 control-label">身份证号</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="userIdNum" class="form-control" name="userIdNum"
                                                   readonly value="${requestScope.person.identityNum}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="userPhone" class="col-sm-3 control-label">手机号</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="userPhone" class="form-control" name="userPhone"
                                                   value="${requestScope.user.phone}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="userEmail" class="col-sm-3 control-label">邮箱</label>
                                        <div class="col-sm-9">
                                            <input type="email" id="userEmail" class="form-control" name="userEmail"
                                                   value="${requestScope.user.email}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="userPassword" class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-9">
                                            <input type="password" id="userPassword" class="form-control"
                                                   name="userPassword" value="${requestScope.user.userPassword}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="userProblem" class="col-sm-3 control-label">密保问题</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="userProblem" class="form-control"
                                                   name="userProblem" value="${requestScope.user.userProblem}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="userAnswer" class="col-sm-3 control-label">密保答案</label>
                                        <div class="col-sm-9">
                                            <input type="text" id="userAnswer" class="form-control"
                                                   name="userAnswer" value="${requestScope.user.userAnswer}">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-4 col-sm-4 text-center">
                                            <button type="submit" class="btn btn-success ">确认修改</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
<script type="text/javascript">
    $(function () {
        $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                userPassword: {
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        }
                    }
                },
                userPhone: {
                    validators: {
                        notEmpty: {
                            message: '手机号不能为空'
                        },
                        regexp: {
                            regexp: /^1\d{10}$/,
                            message: '手机号格式错误'
                        }
                    }
                },
                userEmail: {
                    validators: {
                        notEmpty: {
                            message: '邮箱不能为空'
                        },
                        emailAddress: {
                            message: '邮箱地址格式有误'
                        }
                    }
                }
            }
        })
    })
</script>
</html>