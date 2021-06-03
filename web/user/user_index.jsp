<!--page指令-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>用户首页</title>
  <%--  引用公共的资源--%>
  <%@include file="/commons/header.jsp"%>
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
    <li><a href="${pageContext.request.contextPath}/admin/admin_index.jsp">首页</a></li>
    <li><span>最新公告</span></li>
  </ol>
  <%--内容显示区域--%>
  <div class="panel panel-default">
    <div class="panel-heading text-center row">
      <div class="col-sm-1">
        <c:if test="${requestScope.page.pageNo > 1}">
          <a href="noticeServlet?action=queryOneNoticeByLimitToUser&pageNo=${requestScope.page.pageNo-1}&pageSize=1">上一条</a>
        </c:if>
      </div>
      <div class="col-sm-8">
        <p style="font-size: 20px">${requestScope.page.items[0].noticeTitle}</p>
        <p>发布日期: <span>${requestScope.page.items[0].noticeTime}</span>  发布人员： <span>${requestScope.person[0].name}</span></p>
      </div>
      <div class="col-sm-1">
        <c:if test="${requestScope.page.getPageTotalCount != requestScope.page.pageNo}">
          <a href="noticeServlet?action=queryOneNoticeByLimitToUser&pageNo=${requestScope.page.pageNo+1}&pageSize=1">下一条</a>
        </c:if>
      </div>

    </div>

    <div class="panel-body" style="overflow-x: hidden">
      ${requestScope.page.items[0].noticeContent}
    </div>
  </div>
</div>
</body>
</html>