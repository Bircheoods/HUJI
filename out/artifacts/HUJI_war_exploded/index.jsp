<!--page指令-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>户籍管理系统</title>
    <!--  <link href="../css/bootstrap.min.css" type="text/css" rel="stylesheet">-->
    <%@include file="/commons/header.jsp" %>
<%--    <%--%>
<%--        Object message = request.getAttribute("message");--%>
<%--        request.setAttribute("message", message);--%>
<%--        System.out.println(message);--%>
<%--    %>--%>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    body {
        background: url("${pageContext.request.contextPath}/img/huji3.jpg");
        text-align: center;
    }

    .login {
        margin-top: 2%;
    }

    h2 {
        margin-top: 10px;
        margin-bottom: 15px;
    }

    .thumbnail {
        height: 600px;
        opacity: 0.9;
    }

    .form-group {
        margin: 40px 0;
    }

    .shenfen {
        width: 50%;
    }

    .btn {
        width: 100%;
    }
    .alert{
        position: absolute;
        z-index: 1;
        width: 100%;
        text-align: left;
    }
    .kaptcha{
        display: none;
    }
</style>
<body>

<div class="container-fluid">

    <div class="row text-center">

        <c:if test="${ ! empty  requestScope.message}">
            <div class="alert alert-success alert-dismissible"  data-dismiss="alert" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <strong>提示信息!</strong> ${requestScope.message}
            </div>
        </c:if>

        <div class="col-sm-4 col-sm-push-4 login">



            <div class="thumbnail">
                <img src="img/university1.jpg" alt="biaozhi">
                <div class="caption">
                    <h2>户籍管理系统</h2>

                    <form class="form-horizontal " action="userServlet" method="post">
                        <input type="hidden" name="action" value="userLogin">
                        <img src="<%=basePath%>kaptcha.jpg" name="kaptcha" class="kaptcha" alt="">
                        <div class="form-group has-feedback">
                            <label for="inputNum" class="col-xs-2 control-label">账号</label>
                            <div class="col-xs-10">
                                <input type="text" class="form-control" name="userIdNum" id="inputNum"
                                       placeholder="请输入身份证号/手机号/邮箱">
                                <span class="glyphicon glyphicon-ok form-control-feedback sr-only"></span>
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-xs-2 control-label">密码</label>
                            <div class="col-xs-10">
                                <input type="password" class="form-control" name="userPassword" id="inputPassword"
                                       placeholder="请输入密码">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputRole" class="col-xs-2 control-label">身份</label>
                            <div id="inputRole" class=" col-xs-10">
                                <select class="form-control shenfen" name="userRole">
                                    <option value="0">普通用户</option>
                                    <option value="1">管理人员</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="col-xs-offset-3 col-xs-3">
                                <button type="submit" class="btn btn-success">登录</button>
                            </div>
                            <div class="col-xs-3">
                                <a href="user/register.jsp" type="register" class="btn btn-success">注册</a>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-4 col-xs-offset-8">
                                <a href="user/findPassword.jsp">忘记密码？</a>
                            </div>
                        </div>
                    </form>

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
                userIdNum: {
                    message: '用户名验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                userPassword: {
                    validators: {
                        notEmpty: {
                            message: '邮箱地址不能为空'
                        }
                    }
                }
            }
        })
    })
</script>
</html>