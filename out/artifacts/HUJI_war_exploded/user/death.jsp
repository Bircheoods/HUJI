<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/27
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>死亡信息办理</title>
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
            .table tr td{
                width: 150px;
                text-align: center;
            }
            .userinfo{
                background: #ddd;
            }
        }
    </style>
</head>
<body>
<!--静态包含导航条和侧边栏-->
<%@include file="/commons/user_public.jsp" %>
<div class="page_main">
    <c:if test="${ ! empty  requestScope.message}">
        <div class="alert alert-danger alert-dismissible" data-dismiss="alert" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示信息!</strong> ${requestScope.message}
        </div>
    </c:if>

    <!--    面包屑导航-->
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
        <li><span>死亡证明办理</span></li>
    </ol>

    <div class="panel panel-default">
        <div class="panel-heading">基本信息</div>
        <div class="panel-body">

            <form class="form-horizontal" action="personServlet" method="post">
                <input type="hidden" name="action" value="death">
                <div class="form-group">
                    <label for="householdPerson" class="col-sm-1 control-label">注销人员</label>
                    <div class="col-sm-3">
                        <select class="form-control nation selectpicker" id="householdPerson" name="householdPerson"
                                data-live-search="true">
                            <option value="">请选择注销人员</option>
                            <c:forEach items="${requestScope.person}" var="person">
                                <option value="${person.id}">${person.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <hr>
                <div id="personDetails">
                    <table class="table table-bordered table-condensed" width="50%">
                        <caption class="text-center userinfo">注销人员详细信息<small>&nbsp;&nbsp;请认真核对</small></caption>
                        <tbody>
                        <tr>
                            <td class="userinfo" colspan="1">姓名</td>
                            <td id="userName" colspan="2"></td>
                            <td class="userinfo">性别</td>
                            <td id="sex" colspan="2"></td>
                        </tr>
                        <tr>
                            <td class="userinfo">出生日期</td>
                            <td colspan="2" id="birthday"></td>
                            <td class="userinfo">民族</td>
                            <td colspan="2" id="nation"></td>
                        </tr>
                        <tr>
                            <td class="userinfo">身份证号</td>
                            <td id="identityNum" colspan="5"></td>
                        </tr>
                        <tr>
                            <td class="userinfo">籍贯</td>
                            <td id="nativePlace" colspan="5"></td>
                        </tr>
                        <tr>
                            <td class="userinfo">出生地</td>
                            <td id="address" colspan="5"></td>
                        </tr>
                        <tr>
                            <td class="userinfo">所在户号</td>
                            <td id="householdNum" colspan="5"></td>
                        </tr>
                        <tr>
                            <td class="userinfo">与户主关系</td>
                            <td id="relation" colspan="5"></td>
                        </tr>
                        </tbody>

                    </table>
                </div>

                <div class="form-group">
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-primary pull-right">确认申请</button>
                    </div>
                </div>
            </form>


        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#personDetails").css("display", "none");
        $("#householdPerson").change(function () {
            $("#personDetails").css("display", "");
            let personId = $("#householdPerson").val();
            if (personId !== "") {
                $.ajax({
                    url: "<%=basePath%>" + "/personServlet",
                    data: {
                        action: "deathQueryPersonById",
                        personId: personId
                    },
                    type: "POST",
                    success: function (data) {
                        console.log(data)
                        $("#userName").text(data.person.name)
                        $("#oldName").text(data.person.oldName)
                        $("#sex").text(data.person.sex === 0 ? "女" : "男")
                        $("#birthday").text(data.birthday)
                        $("#nation").text(data.person.nation)
                        $("#identityNum").text(data.person.identityNum)
                        $("#nativePlace").text(data.person.nativePlace)
                        $("#address").text(data.person.address)
                        $("#householdNum").text(data.household.householdNum)
                        $("#relation").text(data.household.householdRelation)
                    },
                    error: function (message) {
                        console.log(message)
                        console.log(222)
                    },
                    dataType: "JSON"
                })
            }
        })
    })
</script>
</html>
