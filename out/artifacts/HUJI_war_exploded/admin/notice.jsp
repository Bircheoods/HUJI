<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/29
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公告管理</title>
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
            .table{
                text-align:center;
                table-layout:fixed;
                word-break:break-all;
            }
            .table>tbody>tr>td{
                vertical-align: middle;
                overflow:hidden;
                white-space:nowrap;
                text-overflow:ellipsis;
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
        <li><span>公告管理</span></li>
    </ol>
    <%--内容显示区域--%>
    <div class="panel panel-default" style="width: 100%">
        <div class="panel-body">
            <form class="form-horizontal" action="noticeServlet" method="post">
                <input type="hidden" name="action" value="queryOneNoticeByLimit">
                <input type="hidden" name="pageNo" value="1">
                <input type="hidden" name="pageSize" value="10">
                <div class="input-group col-md-3" style="margin-bottom:5px ;positon:relative">
                    <input type="text" name="param" class="form-control" placeholder="请输入标题或者内容">
                    <span class="input-group-btn">
               <button class="btn btn-info btn-search">搜索</button>
<%--               <button class="btn btn-info btn-search" style="margin-left:3px">添加</button>--%>
            </span>
                </div>
            </form>

            <div class="btn-group" role="group" aria-label="" style="width: 100%">
                <table class="table table-hover table-bordered" >
                    <thead>
                    <tr>
                        <th width="6%" class="text-center">序号</th>
                        <th width="10%" class="text-center">标题</th>
                        <th width="40%" class="text-center">内容</th>
                        <th width="10%" class="text-center">查看详情</th>
                        <th width="10%" class="text-center">发布日期</th>
                        <th width="10%" class="text-center">发布作者</th>
                        <th width="14%" class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="a">
                    <c:forEach items="${requestScope.page.items}" var="notice" varStatus="pageIndex">
                        <tr >
                            <td class="text-center" >${pageIndex.index+1}</td>
                            <td class="text-center" >${notice.noticeTitle}</td>
                            <td class="text-center" >${notice.noticeContent}</td>
                            <td class="text-center" >
                                <a href="noticeServlet?action=queryOneNoticeByLimit&pageNo=${pageIndex.index+1}&pageSize=1" class="btn btn-primary btn-xs">查看详情</a>
                            </td>
                            <td class="text-center" >${notice.noticeTime}</td>
                            <td class="text-center" >${requestScope.person[pageIndex.index].name}</td>
                            <td class="text-center" data-placement="bottom" title="${sessionScope.user.userId != notice.releaseId ? "非本人发布公告不能进行修改": ""}">
                                <a href="noticeServlet?action=findNoticeById&noticeId=${notice.id}" type="button"  class="btn btn-primary btn-xs ${sessionScope.user.userId != notice.releaseId ? "disabled" : ""}" >
                                    <span class="glyphicon glyphicon-pencil"></span> 修改
                                </a>
                                <a href="noticeServlet?action=deleteOneNotice&noticeId=${notice.id}" type="button" class="btn btn-danger btn-xs ${sessionScope.user.userId != notice.releaseId ? "disabled" : ""}" >

                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <nav aria-label="Page navigation" class="pull-right">
                    <ul class="pagination">
                        <li class="${requestScope.page.pageNo == 1 ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.page.pageNo == 1 ? "当前已经是第一页" : ""}">
                            <a href="noticeServlet?action=queryOneNoticeByLimit&pageSize=10&pageNo=${requestScope.page.pageNo-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;上一页</span>
                            </a>
                        </li>
                        <li class="${requestScope.page.pageNo == requestScope.page.pageTotal ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.page.pageNo == requestScope.page.pageTotal ? "当前已经是最后一页" : ""}">
                            <a href="noticeServlet?action=queryOneNoticeByLimit&pageSize=10&pageNo=${requestScope.page.pageNo+1}" aria-label="Next">
                                <span aria-hidden="true">下一页&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

    </div>
<%--    模态框--%>

</div>
</body>
<script type="text/javascript">
    $(function (){
        $('[data-toggle="tooltip"]').tooltip()
        $('.btn').mouseover(function (){

        })
    })
</script>
</html>
