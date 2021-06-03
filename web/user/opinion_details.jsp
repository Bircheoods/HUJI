<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/5/5
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>详细信息</title>
    <%--  引用公共的资源--%>
    <%@include file="/commons/header.jsp"%>
    <%--    <script src="https://cdn.ckeditor.com/ckeditor5/27.1.0/classic/ckeditor.js"></script>--%>
    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
    <style type="text/css">
        @media (min-width: 768px) {
            #slider_sub{
                width: 200px;
                margin-top: 51px;
                position: absolute;
                z-index: 1;
                height:620px;

            }
            .page_main{
                margin-left: 205px;
                margin-top: -15px;
                height: 620px;
                padding: 5px;
                overflow-x: hidden;
            }
            .page_main::-webkit-scrollbar{
                display: none;
            }
            .answer{
                margin-top: 5px;
                height: 300px;
                padding: 5px;
                overflow-x: hidden;
            }
            .answer::-webkit-scrollbar{
                display: none;
            }
            .myOpinion{
                margin-top: 5px;
                height: 300px;
                padding: 5px;
                overflow-x: hidden;
            }
            .myOpinion::-webkit-scrollbar{
                display: none;
            }
            #slider_sub li{
                margin-top: 20px;
            }
            .panel{
                padding: 5px;
            }
            #sub1{
                margin-left: 10px;
            }
            .alert{
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
<%@include file="/commons/user_public.jsp"%>

<div class="page_main">
    <c:if test="${ ! empty  requestScope.message}">
        <div class="alert alert-success alert-dismissible"  data-dismiss="alert" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示信息!</strong> ${requestScope.message}
        </div>
    </c:if>
    <!--    面包屑导航-->
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
        <li><a href="opinionServlet?action=queryAllOpinion&pageNo=1&pageSize=10">我的意见</a></li>
        <li><span>详细信息</span></li>

    </ol>

    <div class="panel panel-default">
       <div class="panel-body">
           <%--内容显示区域--%>
           <div class="myOpinion">
               <div class="panel panel-default">
                   <div class="panel-heading">
                       我的意见 <small style="margin-left: 20px">提交日期 ：${requestScope.opinion.opinionTime}</small>
                   </div>
                   <div class="panel-body" >
                       <h4 class="text-center">${requestScope.opinion.opinionTitle}</h4>
                       <hr>
                       <p>${requestScope.opinion.opinionContent}</p>
                   </div>
               </div>
           </div>
          <div class="answer">
              <div class="panel panel-default">
                  <div class="panel-heading">回复信息</div>
                  <div class="panel-body">
                      <c:if test="${empty requestScope.opinion.solveContent}">
                          <h3 class="text-danger text-center">您的意见还没有被回复</h3>
                      </c:if>
                      <c:if test="${not empty requestScope.opinion.solveContent}">
                          <h4 class="text-center">${requestScope.opinion.opinionTitle} </h4>
                          <p class="text-center"><small >回复日期： ${requestScope.opinion.solveTime}       回复人：  ${requestScope.myPerson.name}</small></p>
                          <hr>
                          <p>${requestScope.opinion.solveContent}</p>
                      </c:if>
                  </div>
              </div>
          </div>

       </div>
    </div>

</div>
</body>
<script type="text/javascript">
    $(function (){
        //富文本编辑器的初始化
        CKEDITOR.replace("opinionContent",{
            customConfig: '/ckeditor/config.js',
        });

        //判空操作
        $('form').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                noticeTitle: {
                    validators: {
                        notEmpty: {
                            message: '意见标题不能为空'
                        }
                    }
                },
                noticeContent:{
                    validators: {
                        notEmpty: {
                            message: '意见内容不能为空'
                        }
                    }
                }
            }
        })
    })
</script>
</html>