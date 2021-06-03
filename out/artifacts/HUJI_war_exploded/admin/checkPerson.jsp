<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/5/6
  Time: 16:07
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

            .table {
                text-align: center;
                table-layout: fixed;
                word-break: break-all;
            }

            .table > tbody > tr > td {
                vertical-align: middle;
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
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
            <div class="btn-group" role="group" aria-label="" style="width: 100%">
                <%--                显示的表格--%>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th width="4%" class="text-center">序号</th>
                        <th width="8%" class="text-center">姓名</th>
                        <th width="10%" class="text-center">身份证号</th>
                        <th width="10%" class="text-center">审核类型</th>
                        <%--                        <th width="10%" class="text-center">申请日期</th>--%>
                        <th width="10%" class="text-center">当前状态</th>
                        <th width="14%" class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.page.items}" var="checkPerson" varStatus="index">
                        <tr class="${empty checkPerson.checkOpinion ? "bg-danger":(checkPerson.checkStatus == 0 ? "bg-success":"bg-info")}">
                            <td>${index.index+1}</td>
                            <td>${requestScope.person[index.index].name}</td>
                            <td>${requestScope.person[index.index].identityNum}</td>
                            <td>${requestScope.person[index.index].status == 0 ? "出生审核":"死亡审核"}</td>
                                <%--                            <td>${checkPerson.}</td>--%>
                            <td>${empty checkPerson.checkOpinion ?"未审查":(checkPerson.checkStatus == 0 ? "审查通过":"审查未通过")}</td>
                            <td>
                                <a href="#" type="button"
                                   class="btn ${empty checkPerson.checkOpinion ? "btn-primary":"btn-success"} btn-xs " data-toggle="modal" data-target="#exampleModal"
                                   data-name="${requestScope.person[index.index].name}"
                                   data-id="${requestScope.person[index.index].id}"
                                   data-status="${requestScope.person[index.index].status == 0 ?"出生审核":"死亡审核"}"
                                   data-identityNum="${requestScope.person[index.index].identityNum}"
                                   data-nativePlace="${requestScope.person[index.index].nativePlace}"
                                   data-householdNum="${requestScope.house[index.index].householdNum == null ? "暂无信息":requestScope.house[index.index].householdNum}"
                                   data-householdRelation="${requestScope.house[index.index].householdRelation == null ? "暂无信息":requestScope.house[index.index].householdRelation}"
                                   data-address="${requestScope.person[index.index].address}"
                                   data-birthday="${requestScope.person[index.index].birthday}"
                                   data-checkOpinion="${empty checkPerson.checkOpinion ? null: checkPerson.checkOpinion}"
                                   data-sex="${requestScope.person[index.index].sex == 0 ? "女":"男"}"
                                   data-checkStatus="${checkPerson.checkStatus}"
                                >
                                    <span class="glyphicon ${empty checkPerson.checkOpinion ? "glyphicon-search":"glyphicon-pencil"}"></span>
                                        ${empty checkPerson.checkOpinion ? "审查":"修改"}
                                </a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <%--模态框--%>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="exampleModalLabel">要审查的人员</h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" action="" method="post">
                                    <div class="form-group">
                                        <label for="userName" class="control-label col-sm-2">姓名</label>
                                        <div class="col-sm-2">
                                            <input type="text" readonly class="form-control" id="userName">
                                        </div>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
                                        <label for="birthday" class="control-label col-sm-2">出生日期</label>
                                        <div class="col-sm-2">
                                            <input type="text" readonly class="form-control" id="birthday">
                                        </div>
<%--                                    </div>--%>
<%--                                    <div class="form-group">--%>
                                        <label for="sex" class="control-label col-sm-2">性别：</label>
                                        <div class="col-sm-2">
                                            <input type="text" readonly class="form-control" id="sex">
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <label for="householdNum" class="control-label col-sm-2">当前户号</label>
                                        <div class="col-sm-4">
                                            <input type="text" readonly class="form-control" id="householdNum">
                                        </div>
                                        <label for="householdRelation" class="control-label col-sm-2">户主关系</label>
                                        <div class="col-sm-4">
                                            <input type="text" readonly class="form-control" id="householdRelation">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="nativePlace" class="control-label col-sm-2">籍贯</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly class="form-control" id="nativePlace">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="address" class="control-label col-sm-2">出生地</label>
                                        <div class="col-sm-10">
                                            <input type="text" readonly class="form-control" id="address">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="identityNum" class="control-label col-sm-2">身份证号：</label>
                                        <div class="col-sm-4">
                                            <input type="text" readonly class="form-control" id="identityNum">
                                        </div>
                                        <label for="status" class="control-label col-sm-2">审核类型：</label>
                                        <div class="col-sm-4">
                                            <input type="text" readonly class="form-control" id="status">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="checkStatus" class="control-label col-sm-2">审查结果：</label>
                                        <div class="col-sm-10" id="checkStatus">
                                            <input type="radio" name="checkStatus" id="checkStatus1" value="0">通过
                                            <input type="radio" name="checkStatus" id="checkStatus2" value="1">不通过
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="checkOpinion" class="control-label col-sm-2">审查意见：</label>
                                        <div class="col-sm-10">
                                <textarea rows="10" class="form-control" id="checkOpinion" name="checkOpinion">

                                </textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">
                                    <span class="glyphicon glyphicon-remove"></span> 取消
                                </button>
                                <button type="button" class="btn btn-success" id="updateRelation">
                                    <span class="glyphicon glyphicon-pencil"></span> 提交
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <%--                分页栏--%>
                <nav aria-label="Page navigation" class="pull-right">
                    <ul class="pagination">
                        <li class="${requestScope.page.pageNo == 1 ? "disabled" : ""}"
                            data-placement="bottom" title="${requestScope.page.pageNo == 1 ? "当前已经是第一页" : ""}">
                            <a href="checkPersonServlet?action=queryAllCheckPerson&pageNo=${requestScope.page.pageNo-1}&pageSize=10"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;上一页</span>
                            </a>
                        </li>
                        <li class="${requestScope.page.pageNo == requestScope.page.pageTotal ? "disabled" : ""}"
                            data-placement="bottom"
                            title="${requestScope.page.pageNo == requestScope.page.pageTotal ? "当前已经是最后一页" : ""}">
                            <a href="checkPersonServlet?action=queryAllCheckPerson&pageNo=${requestScope.page.pageNo-1}&pageSize=10"
                               aria-label="Next">
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
    $(function () {
        let checkStatus;
        let personId;
        let opinion;
        $('#exampleModal').on('show.bs.modal', function (event) {
            let button = $(event.relatedTarget)
            let name = button.data('name')
            let birthday = button.data('birthday')
            let identityNum = button.attr('data-identitynum')
            let nativePlace = button.attr('data-nativeplace')
            let address = button.data('address')
            let sex = button.data('sex')
            let checkOpinion = button.attr('data-checkopinion')
            let checkStatus = button.attr('data-checkstatus')
            let householdNum = button.attr("data-householdnum")
            let householdRelation = button.attr("data-householdrelation")
            let status = button.attr('data-status')

            console.log(householdNum)
            console.log(householdRelation)
            personId = button.data('id')

            let modal = $(this)
                if (checkStatus === '1')
                {
                    $('#checkStatus2').prop('checked',true);
                    // $("input[name='checkStatus']").attr("disabled",true)
                }
                else if (checkStatus === '0')
                {
                    // $("input[name='checkStatus']").attr("disabled",true)
                    $('#checkStatus1').prop('checked',true);
                }

            modal.find('.modal-title').text('审查"' + name + '"的信息')
            modal.find('#userName').val(name)
            modal.find('#birthday').val(birthday)
            modal.find('#sex').val(sex)
            modal.find('#nativePlace').val(nativePlace)
            modal.find('#address').val(address)
            modal.find('#checkOpinion').val(checkOpinion)
            modal.find('#identityNum').val(identityNum)
            modal.find('#householdNum').val(householdNum)
            modal.find('#householdRelation').val(householdRelation)
            modal.find(('#nativePlace')).val(nativePlace)
            modal.find('#status').val(status)
        })

        $("#updateRelation").click(function () {
            opinion = $("#checkOpinion").val()
            checkStatus = $("input[name='checkStatus']:checked").val()
            // alert(checkStatus)
            $.ajax({
                url: "<%=basePath%>" + "/checkPersonServlet",
                data: {
                    action: "checkPerson",
                    personId: personId,
                    opinion: opinion,
                    checkStatus: checkStatus
                },
                dataType: "JSON",
                type: "post",
                success: function (data) {
                    alert(data.message)
                    window.location = "<%=basePath%>" + "/checkPersonServlet?action=queryAllCheckPerson&pageNo=1&pageSize=10"
                    $("#exampleModal").modal('hide');
                },
                error: function (e) {
                    console.log(e)
                }
            })
        })
    })
</script>
</html>
