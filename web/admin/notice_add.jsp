<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/29
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告添加</title>
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
<%@include file="/commons/admin_public.jsp"%>

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
        <li><a href="${pageContext.request.contextPath}/admin/admin_index.jsp">首页</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/notice.jsp">公告管理</a></li>
        <li><span>公告添加</span></li>
    </ol>
    <%--内容显示区域--%>
    <div class="panel panel-default">
<%--        公告信息添加--%>
        <form class="form-horizontal" action="noticeServlet" method="post">
<%--            <c:if test="${empty requestScope.notice}">--%>
                <input type="hidden" name="action" value="${empty requestScope.notice ? "insertNotice":"updateNoticeById"}">
                <input type="hidden" name="id" value="${requestScope.notice.id}">
<%--            </c:if>--%>
<%--            <c:if test="${not empty requestScope.notice}">--%>
<%--                <input type="hidden" name="action" value="updateNoticeById?noticeId="+${requestScope.notice.id}">--%>
<%--            </c:if>--%>


            <div class="form-group">
                <label for="noticeTitle" class="col-sm-1 control-label">公告标题</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="noticeTitle" value="${requestScope.notice.noticeTitle}" name="noticeTitle" placeholder="请输入标题">
                </div>
            </div>
            <div class="form-group" >
                <label for="noticeContent" class="col-sm-1 control-label">公告内容</label>
                <div class="col-sm-11" >
                    <textarea class="form-control" id="noticeContent" name="noticeContent" rows="10" >
                        ${requestScope.notice.noticeContent}
                    </textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-6">
                    <button type="submit" class="btn btn-primary pull-right">${requestScope.notice == null ? "发布公告" : "修改公告" }</button>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
<script type="text/javascript">
    $(function (){
        //富文本编辑器的初始化
        CKEDITOR.replace("noticeContent",{
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
                            message: '公告标题不能为空'
                        }
                    }
                },
                noticeContent:{
                    validators: {
                        notEmpty: {
                            message: '公告内容不能为空'
                        }
                    }
                }
            }
        })
    })
</script>
</html>