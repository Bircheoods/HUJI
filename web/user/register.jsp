<!--page指令-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>注册</title>
 <%@include file="/commons/header.jsp"%>
  <style>
    *{
      margin: 0;
      padding: 0;
    }
    body{
      background: url("${pageContext.request.contextPath}/img/huji3.jpg");
    }
    .register{
      margin-top: 2%;
    }
    .btn{
      width: 100%;
    }
    .panel{
      opacity: 0.9;
      height: 600px;
    }
    .form-group{
      margin: 10px 0;
    }
    .alert{
      position: absolute;
      z-index: 1;
      width: 100%;
      text-align: left;
    }
  </style>
</head>
<body>
<c:if test="${ ! empty  requestScope.message}">
    <div class="alert alert-danger alert-dismissible"  data-dismiss="alert" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong>错误!</strong> ${requestScope.message}
    </div>
</c:if>

  <div class="container-fluid">
    <div class="row">
        <div class="col-sm-4 col-sm-push-4 register">
          <div class="panel panel-default">
            <div class="panel-heading">欢迎您注册户籍系统，如果您已有账号，可点击此处进行<a href="${pageContext.request.contextPath}/index.jsp" class="text-success">登录</a></div>
            <div class="panel-body">

              <form class="form-horizontal" action="userServlet" method="post">
                <input type="hidden" name="action" value="userRegister">
                <div class="form-group">
                  <label for="inputIDNum" class="col-sm-3 control-label">身份证号</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="identityNum" id="inputIDNum" placeholder="请输入身份证号">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword" class="col-sm-3 control-label">密码</label>
                  <div class="col-sm-9">
                    <input type="password" class="form-control" name="userPassword" id="inputPassword" placeholder="请输入密码">
                  </div>
                </div>
                <div class="form-group">
                  <label for="suerUserPassword" class="col-sm-3 control-label">确认密码</label>
                  <div class="col-sm-9">
                    <input type="password" class="form-control" name="suerUserPassword" id="suerUserPassword" placeholder="请确认密码">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPhone" class="col-sm-3 control-label">手机号</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="userPhone" id="inputPhone" placeholder="请输入手机号">
                  </div>
                </div>

                <div class="form-group">
                  <label for="inputEmail" class="col-sm-3 control-label">邮箱</label>
                  <div class="col-sm-9">
                    <input type="email" class="form-control" name="userEmail" id="inputEmail" placeholder="请输入您的邮箱">
                  </div>
                </div>

                <div class="form-group">
                  <label for="inputProblem" class="col-sm-3 control-label">密保问题</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="userProblem" id="inputProblem" placeholder="请设置找回密码时的问题">
                  </div>
                </div>

                <div class="form-group">
                  <label for="inputAnswer" class="col-sm-3 control-label">密保答案</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="userAnswer" id="inputAnswer" placeholder="请设置找回密码时的答案">
                  </div>
                </div>

              <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4 text-center">
                  <button type="submit" class="btn btn-success ">注册</button>
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
        identityNum: {
          validators: {

            notEmpty: {
              message: '身份证号不能为空'
            },
            stringLength: {
              min: 18,
              max: 18,
              message: '身份证号长度必须为18位'
            }

          }
        },
        userPassword: {
          validators: {
            notEmpty: {
              message: '密码不能为空'
            }
          }
        },
        suerUserPassword: {
          validators: {
            notEmpty: {
              message: '确认密码不能为空'
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
        },
        userProblem: {
          validators: {
            notEmpty: {
              message: '密保问题不能为空'
            }
          }
        },
        userAnswer: {
          validators: {
            notEmpty: {
              message: '密保答案不能为空'
            }
          }
        }
      }
    })
  })
</script>
</html>