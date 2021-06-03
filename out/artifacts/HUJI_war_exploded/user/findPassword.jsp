<!--page指令-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>找回密码</title>
  <%@include file="/commons/header.jsp"%>>
  <style>
    *{
      margin: 0;
      padding: 0;
    }
    body{
      background: url("${pageContext.request.contextPath}/img/huji3.jpg");
    }
    .findPassword{
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
      margin: 40px 0;
    }
  </style>
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-4 col-sm-push-4 findPassword">
        <div class="panel panel-default">
          <div class="panel-heading">您正在进行找回密码操作，点此处可返回<a href="${pageContext.request.contextPath}/index.jsp" class="text-success">登录页面</a></div>
            <div class="panel-body">

              <form action="userServlet" class="form-horizontal" method="post">
                <input type="hidden" name="action" value="findPassword">
                <div class="form-group">
                  <label for="inputProblem" class="col-sm-3 control-label">密保问题</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="userProblem" id="inputProblem" placeholder="请输入注册时提交的密保问题">
                  </div>
                </div>

                <div class="form-group">
                  <label for="inputAnswer" class="col-sm-3 control-label">密保答案</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="userAnswer" id="inputAnswer" placeholder="请输入注册时设置的密保答案">
                  </div>
                </div>

                <div class="form-group">
                  <label for="inputNewPassword" class="col-sm-3 control-label">新密码</label>
                  <div class="col-sm-9">
                    <input type="password" class="form-control" name="newPassword" id="inputNewPassword" placeholder="请输入新的密码">
                  </div>
                </div>

                <div class="form-group">
                  <label for="sureNewPassword" class="col-sm-3 control-label">确认密码</label>
                  <div class="col-sm-9">
                    <input type="password" class="form-control" name="sureNewPassword" id="sureNewPassword" placeholder="请确认新密码">
                  </div>
                </div>

                <div class="form-group">
                  <div class="col-sm-offset-4 col-sm-4 text-center">
                    <button type="submit" class="btn btn-success ">找回密码</button>
                  </div>
                </div>

              </form>

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
                },
                newPassword: {
                    validators: {
                        notEmpty: {
                            message: '新密码不能为空'
                        }
                    }
                },
                sureNewPassword: {
                    validators: {
                        notEmpty: {
                            message: '确认密码不能为空'
                        }
                    }
                }
            }
        })
    })
</script>
</html>