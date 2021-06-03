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
    <title>意见管理</title>
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
        <li><span>意见管理</span></li>
    </ol>
    <%--内容显示区域--%>
    <div class="panel panel-default" style="width: 100%">
        <div class="panel-body">


            <div class="btn-group" role="group" aria-label="" style="width: 100%">
                <table class="table table-hover table-bordered" >
                    <thead>
                    <tr>
                        <th width="6%" class="text-center">序号</th>
                        <th width="10%" class="text-center">标题</th>
                        <th width="40%" class="text-center">内容</th>
                        <th width="10%" class="text-center">提交人员</th>
                        <th width="10%" class="text-center">提交日期</th>
                        <th width="10%" class="text-center">当前状态</th>
                        <th width="14%" class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="a">
                    <c:forEach items="${requestScope.myOpinion.items}" var="opinion" varStatus="pageIndex">
                        <tr class="${empty opinion.solveTime ? "bg-danger":"bg-success"}">
                            <td class="text-center" >${pageIndex.index+1}</td>
                            <td class="text-center" >${opinion.opinionTitle}</td>
                            <td class="text-center" >${opinion.opinionContent}</td>
                            <td class="text-center" >${requestScope.person[pageIndex.index].name}</td>
                            <td class="text-center" >${opinion.opinionTime}</td>
                            <td class="text-center" >${empty opinion.solveTime ? "未处理":"已处理"}</td>
                            <td class="text-center" data-placement="bottom" title="${sessionScope.user.userId != notice.releaseId ? "非本人发布公告不能进行修改": ""}">
                                <a href="opinionServlet?action=findOpinionById&opinionId=${opinion.id}&name=opinionDetails" type="button"  class="btn ${empty opinion.solveTime ? "btn-success":"btn-primary"} btn-xs" >
                                    <span class="glyphicon  ${empty opinion.solveTime ? "glyphicon-pencil":"glyphicon-search"}"></span> ${empty opinion.solveTime ? "意见处理":"查看详情"}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <nav aria-label="Page navigation" class="pull-right">
                    <ul class="pagination">
                        <li class="${requestScope.myOpinion.pageNo == 1 ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.myOpinion.pageNo == 1 ? "当前已经是第一页" : ""}">
                            <a href="opinionServlet?action=queryAllOpinionAllUser&pageSize=10&pageNo=${requestScope.myOpinion.pageNo-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;上一页</span>
                            </a>
                        </li>
                        <li class="${requestScope.myOpinion.pageNo == requestScope.myOpinion.pageTotal ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.myOpinion.pageNo == requestScope.myOpinion.pageTotal ? "当前已经是最后一页" : ""}">
                            <a href="opinionServlet?action=queryAllOpinionAllUser&pageSize=10&pageNo=${requestScope.myOpinion.pageNo+1}" aria-label="Next">
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
