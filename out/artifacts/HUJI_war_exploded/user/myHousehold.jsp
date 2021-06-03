<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/5/6
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的户籍</title>
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

            .table tr td,th {
                width: 150px;
                text-align: center;
            }

            .userinfo {
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
        <div class="alert alert-success alert-dismissible" data-dismiss="alert" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示信息!</strong> ${requestScope.message}
        </div>
    </c:if>

    <!--    面包屑导航-->
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
        <li><span>我的户籍</span></li>
    </ol>

    <div class="panel panel-default">
        <div class="panel-body">
<%--            我的信息--%>
            <div class="panel panel-default">
                <div class="panel-heading">我的信息</div>
                <div class="panel-body">
                   <table class="table table-condensed table-bordered">
                       <tbody>
                       <tr>
                           <td class="userinfo" colspan="2">姓名</td>
                           <td colspan="2">${requestScope.myPerson.name}</td>
                           <td class="userinfo" colspan="2">曾用名</td>
                           <td colspan="2">${requestScope.myPerson.oldName}</td>
                           <td class="userinfo" colspan="2">性别</td>
                           <td colspan="2">${requestScope.myPerson.sex == "0" ? "女":"男"}</td>
                       </tr>
                       <tr>
                           <td class="userinfo" colspan="2">当前户号</td>
                           <td colspan="6">${requestScope.household.householdNum}</td>
                           <td class="userinfo" colspan="2">户主或与户主关系</td>
                           <td colspan="2">${requestScope.household.householdRelation}</td>
                       </tr>
                       <tr>
                           <td class="userinfo" colspan="2">籍贯</td>
                           <td colspan="6">${requestScope.myPerson.nativePlace}</td>
                           <td class="userinfo" colspan="2">民族</td>
                           <td colspan="2">${requestScope.myPerson.nation}</td>
                       </tr>
                       <tr>
                           <td class="userinfo" colspan="2">出生地</td>
                           <td colspan="6">${requestScope.myPerson.address}</td>
                           <td class="userinfo" colspan="2">出生日期</td>
                           <td colspan="2">${requestScope.myPerson.birthday}</td>
                       </tr>
                       <tr>
                           <td class="userinfo" colspan="2">身份证号</td>
                           <td colspan="2">${requestScope.myPerson.identityNum}</td>
                           <td class="userinfo" colspan="2">宗教</td>
                           <td colspan="2">${requestScope.myPerson.religious}</td>
                           <td class="userinfo" colspan="2">血型</td>
                           <td colspan="2">${requestScope.myPerson.bloodType}</td>
                       </tr>
                       <tr>
                           <td class="userinfo" colspan="2">文化程度</td>
                           <td colspan="2">${requestScope.myPerson.education}</td>
                           <td class="userinfo" colspan="2">婚姻状况</td>
                           <td colspan="2">${requestScope.myPerson.marriage == 0 ? "未婚":(requestScope.myPerson.marriage == 1 ?"已婚":"离异")}</td>
                           <td class="userinfo" colspan="2">兵役状况</td>
                           <td colspan="2">${requestScope.myPerson.militaryService == 0 ?"未服兵役":"已服兵役"}</td>
                       </tr>
                       </tbody>
                   </table>
                </div>
            </div>
<%--            家庭成员--%>
            <div class="panel panel-default">
                <div class="panel-heading">家庭成员</div>
                <div class="panel-body">
                   <table class="table table-condensed table-bordered">
                       <thead>
                       <tr>
                           <th>序号</th>
                           <th>姓名</th>
                           <th>身份证号</th>
                           <th>当前户号</th>
                           <th>与户主关系</th>
                           <th>操作</th>
                       </tr>
                       </thead>
                       <tbody>
                       <c:forEach items="${requestScope.person}" var="person" varStatus="index">
                           <tr>
                               <td>${index.index+1}</td>
                               <td>${person.name}</td>
                               <td>${person.identityNum}</td>
                               <td>${requestScope.household.householdNum}</td>
                               <td>${person.address}</td>
                               <td>
                                   <a href="#" type="button" class="btn btn-success btn-xs"
                                      data-toggle="modal" data-target="#exampleModal" data-id="${person.id}" data-name="${person.name}">
                                       <span class="glyphicon glyphicon-pencil"></span> 修改关系
                                   </a>
                               </td>
                           </tr>
                       </c:forEach>
                       </tbody>
                   </table>

                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="exampleModalLabel">要修改的人员</h4>
                                </div>
                                <div class="modal-body">
                                    <form class="form-horizontal" action="" method="post">
                                        <div class="form-group">
                                            <label for="recipient-name" class="control-label col-sm-2">姓名:</label>
                                            <div class="col-sm-6">
                                                <input type="text" readonly class="form-control" id="recipient-name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="newRelation" class="control-label col-sm-2">新关系:</label>
                                            <div class="col-sm-6">
                                                <select class=" nowProvinces selectpicker" id="newRelation" name="newRelation"
                                                        data-live-search="true">--%>
                                                    <option value="户主" aria-checked="true">户主</option>
                                                    <option value="长子">长子</option>
                                                    <option value="长女">长女</option>
                                                    <option value="次子">次子</option>
                                                    <option value="次女">次女</option>
                                                    <option value="配偶">配偶</option>
                                                    <option value="其他">其他</option>
                                                </select>
                                            </div>

                                        </div>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">
                                        <span class="glyphicon glyphicon-trash"></span> 取消</button>
                                    <button type="button" class="btn btn-success" id="updateRelation">
                                        <span class="glyphicon glyphicon-pencil"></span> 确认修改</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
<script type="text/javascript">
    $(function () {
        let personId;
        let newRelation;
        $('#exampleModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget) // Button that triggered the modal
            var recipient = button.data('name') // Extract info from data-* attributes
            // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
            // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
            var modal = $(this)
            modal.find('.modal-title').text('修改"' + recipient+'"与户主之间的关系')
            modal.find('.modal-body input').val(recipient)
            personId = button.data('id');
        })


        $("#updateRelation").click(function (){
            newRelation = $("#newRelation").val();
            $.ajax({
                url: "<%=basePath%>"+"/householdServlet",
                data:{
                    action: "updateRelation",
                    personId: personId,
                    newRelation: newRelation
                },
                dataType: "JSON",
                type: "post",
                success: function (data){
                    alert(data.message)
                    window.location = "<%=basePath%>"+"/householdServlet?action=queryHouseholdById&name=myHousehold"
                    $("#exampleModal").modal('hide');
                },
                error: function (e){
                    console.log(e)
                }
            })
        })
    })
</script>
</html>