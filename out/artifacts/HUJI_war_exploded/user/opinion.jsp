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
        <li><span>意见管理</span></li>

    </ol>
    <%--内容显示区域--%>
    <div class="panel panel-default">
        <div class="panel-body">
            <form class="form-horizontal" action="opinionServlet" method="post">
                <input type="hidden" name="action" value="queryAllOpinion">
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
                <table class="table table-hover table-condensed table-bordered" >
                    <thead>
                    <tr>
                        <th width="6%" class="text-center">序号</th>
                        <th width="10%" class="text-center">标题</th>
                        <th width="34%" class="text-center">内容</th>
                        <th width="10%" class="text-center">申请日期</th>
                        <th width="10%" class="text-center">当前状态</th>
                        <th width="20%" class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody id="a">
                    <c:forEach items="${requestScope.opinion.items}" var="opinion" varStatus="pageIndex">
                        <tr class="${empty opinion.solveTime ? "bg-danger" :"bg-success"}">
                            <td class="text-center" >${pageIndex.index+1}</td>
                            <td class="text-center" >${opinion.opinionTitle}</td>
                            <td class="text-center" >${opinion.opinionContent}</td>
                            <td class="text-center" >${opinion.opinionTime}</td>
                            <td class="text-center " >${empty opinion.solveTime ? "未回复" :"已回复"}</td>
                            <td class="text-center">
                                <a href="opinionServlet?action=findOpinionById&opinionId=${opinion.id}&name=search"  type="button"  class="btn btn-primary btn-xs" >
                                    <span class="glyphicon glyphicon-search"></span> 查看详情
                                </a>
                                <a ${empty opinion.solveTime ? "":"disabled"} data-toggle="tooltip" data-placement="bottom"
                                                                              title="${empty opinion.solveTime ? "" : "已经回复的意见不能被修改"}"
                                        href="opinionServlet?action=findOpinionById&opinionId=${opinion.id}&name=update" type="button" class="btn btn-success btn-xs " >
                                    <span class="glyphicon glyphicon-pencil"></span> 修改
                                </a>
                                <a href="opinionServlet?action=deleteOpinion&opinionId=${opinion.id}" type="button" class="btn btn-danger btn-xs " >

                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
<%--                &lt;%&ndash;                模态框&ndash;%&gt;--%>
<%--                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
<%--                    <div class="modal-dialog" role="document">--%>
<%--                        <div class="modal-content">--%>
<%--                            <div class="modal-header">--%>
<%--                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--                                <h4 class="modal-title" id="myModalLabel">意见详情</h4>--%>
<%--                            </div>--%>
<%--                            <div class="modal-body">--%>
<%--                                <div class="page-header">--%>
<%--                                    <h4 class="text-center">意见内容<small>提交日期</small></h4>--%>
<%--                                    <p>${requestScope.myOpinion.opinionContent}</p>--%>
<%--                                </div>--%>
<%--                                <div class="page-header">--%>
<%--                                    <h4 class="text-center">反馈内容<small>反馈日期</small></h4>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                分页条--%>
                <nav aria-label="Page navigation" class="pull-right">
                    <ul class="pagination">
                        <li class="${requestScope.opinion.pageNo == 1 ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.opinion.pageNo == 1 ? "当前已经是第一页" : ""}">
                            <a href="opinionServlet?action=queryAllOpinion&pageSize=10&pageNo=${requestScope.opinion.pageNo-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;上一页</span>
                            </a>
                        </li>
                        <li class="${requestScope.opinion.pageNo == requestScope.opinion.pageTotal ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.opinion.pageNo == requestScope.opinion.pageTotal ? "当前已经是最后一页" : ""}">
                            <a href="opinionServlet?action=queryAllOpinion&pageSize=10&pageNo=${requestScope.opinion.pageNo+1}" aria-label="Next">
                                <span aria-hidden="true">下一页&raquo;</span>
                            </a>
                        </li>
                    </ul>
                </nav>
        </div>
    </div>

</div>
</body>
<script type="text/javascript">
    $(function (){

    })
</script>
</html>