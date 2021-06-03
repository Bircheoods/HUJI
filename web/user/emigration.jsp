<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/5/4
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>户籍迁出办理</title>
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
        }
    </style>
</head>
<body>

<!--静态包含导航条和侧边栏-->
<%@include file="/commons/user_public.jsp" %>


<div class="page_main">
    <c:if test="${ ! empty  requestScope.message}">
        <div class="alert alert-warning alert-dismissible" data-dismiss="alert" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>提示信息!</strong> ${requestScope.message}
        </div>
    </c:if>

    <!--    面包屑导航-->
    <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
        <li><span>户籍迁出办理</span></li>
    </ol>

    <div class="panel panel-default" style="width: 100%">
        <div class="panel-body">
            <blockquote>
                <small>户籍迁出：本户口人员迁出到外地或者指定户口，如迁出到外地需要选择迁出地址，同时会新生成户主号。如果迁出到指定户口，
                    需要手动输入迁出户口号并指明与新户主之间的关系，如有需要，用户可到
                    <mark>“个人信息中心-我的户籍”</mark>
                    中修改与原户主之间的关系</small>
            </blockquote>
            <%--            迁出类型的选择--%>
            <form class="form-horizontal">
                <label for="migrationType" class="control-label col-sm-1">迁出类型</label>
                <div class="form-group" id="migrationType">
                    <label class="radio-inline">
                        <input type="radio" value="sureHousehold" name="migrationType">指定户口
                    </label>
                    <label class="radio-inline">
                        <input type="radio" value="otherPlace" name="migrationType">外地迁出
                    </label>
                </div>
            </form>
            <%--指定户籍号迁出--%>
            <div id="sureHousehold">
                <%--                    指定户号的form表单--%>
                <form action="migrationServlet" method="post" class="form-horizontal">
                    <input type="hidden" name="action" value="emigrationSureHousehold">
                    <%--                        迁出人员--%>
                    <div class="form-group">
                        <label for="emigrationPerson" class="control-label col-sm-1">迁出人员</label>
                        <div class="col-sm-3" id="emigrationPerson">
                            <select class=" nowProvinces selectpicker" name="emigrationPerson"
                                    data-live-search="true">--%>
                                <option value="" disabled>请选择迁出人员</option>
                                <c:forEach items="${requestScope.person}" var="person">
                                    <option value="${person.id}">${person.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <%--                        新户号--%>
                    <div class="form-group">
                        <label for="houseNum" class="control-label col-sm-1">新户号</label>
                        <div class="col-sm-3">
                            <input type="text" id="houseNum" class="form-control" name="newNum"
                                   placeholder="请输入新户号">
                        </div>
                    </div>
                    <%--                    新关系--%>
                    <div class="form-group">
                        <label for="newRelation" class="control-label col-sm-1">新关系</label>
                        <div class="col-sm-3" id="newRelation">
                            <select class=" nowProvinces selectpicker" name="newRelation"
                                    data-live-search="true">--%>
                                <option value="长子">长子</option>
                                <option value="长女">长女</option>
                                <option value="次子">次子</option>
                                <option value="次女">次女</option>
                                <option value="配偶">配偶</option>
                                <option value="其他">其他</option>
                            </select>
                        </div>
                    </div>
                    <%--                提交按钮--%>
                    <div class="form-group">
                        <div class="col-sm-6">
                            <button type="submit" class="btn btn-primary pull-right">申请办理</button>
                        </div>
                    </div>
                </form>
            </div>

            <%--            指定地址迁出--%>
            <div id="otherPlace">
                <form action="migrationServlet" method="post" class="form-horizontal">
                    <input type="hidden" name="action" value="emigrationSurePlace">
                    <%--                        迁出人员--%>
                    <div class="form-group">
                        <label for="emigrationPerson1" class="control-label col-sm-1">迁出人员</label>
                        <div class="col-sm-3" id="emigrationPerson1">
                            <select class=" nowProvinces selectpicker" name="emigrationPerson1"
                                    data-live-search="true">--%>
                                <option value="" disabled>请选择迁出人员</option>
                                <c:forEach items="${requestScope.person}" var="person">
                                    <option value="${person.id}">${person.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
<%--                        迁出地址--%>
                    <div class="form-group">
                        <label for="nativePlace" class="col-sm-1 control-label">迁徙地</label>
                        <div class="col-sm-10" id="nativePlace">
                            <select class=" provinces selectpicker" name="provinces" id="provinces"
                                    data-live-search="true">
                                <option value="">请选择省份</option>

                            </select>
                            <select class=" sex selectpicker" name="city" data-live-search="true" id="city">
                                <option value="">请选择市区</option>

                            </select>
                            <select class=" sex selectpicker" data-live-search="true" name="country" id="country">
                                <option value="">请选择县区</option>
                            </select>
                            <input type="text" class="form-control" name="detailPlace" placeholder="详细地址"
                                   style="margin-top: 3px">
                        </div>
                    </div>
                    <%--                提交按钮--%>
                    <div class="form-group">
                        <div class="col-sm-6">
                            <button type="submit" class="btn btn-primary pull-right">申请办理</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#sureHousehold").css("display", "none")
        $("#otherPlace").css("display", "none")

        $("#migrationType").change(function () {

            if ($("#migrationType input:radio:checked").val() == "sureHousehold") {

                $("#sureHousehold").css("display", "")
                $("#otherPlace").css("display", "none")
            } else if ($("#migrationType input:radio:checked").val() == "otherPlace") {
                //初始化省份信息

                $.ajax({
                    url: "<%=basePath%>"+"/personServlet",
                    data: {
                        action: "initProvinces"
                    },
                    type:"POST",
                    success: function (data){
                        //清空city下拉框
                        $("#provinces").empty()
                        $("#provinces").append("<option value=''>请选择省份</option>")
                        //重新生成下拉选项
                        $.each(data,function (i,item) {
                            //$("#city").append(" <option value='" + item.areaId + "'>" + item.areaName + "</option>");
                            $("#provinces").append("<option value='"+item.areaId+"'>"+item.areaName+"</option>")
                        })
                        $("#provinces").selectpicker('refresh');
                        //render方法强制重新渲染引导程序 - 选择ui。
                        $("#provinces").selectpicker('render');
                    },
                    error: function (e){
                        alert("出错了")
                    },
                    dataType:'JSON'
                })

                $("#sureHousehold").css("display", "none")
                $("#otherPlace").css("display", "")
            }
        })


        /*---------------籍贯--------------------*/
        //监听省份下拉框的change事件
        $("#provinces").change(function () {//使用ajax请求局部刷新市区信息
            let provincesNum=$("#provinces").val()//获取当前省份的地区id
            $.ajax({
                url: "<%=basePath%>"+"/personServlet",
                data: {
                    action: "initCity",
                    provincesNum: provincesNum
                },
                type:"POST",
                success: function (data){
                    //清空city下拉框
                    $("#city").empty()
                    $("#city").append("<option value=''>请选择市区</option>")
                    //重新生成下拉选项
                    $.each(data,function (i,item) {
                        //$("#city").append(" <option value='" + item.areaId + "'>" + item.areaName + "</option>");
                        $("#city").append("<option value='"+item.areaId+"'>"+item.areaName+"</option>")
                    })
                    $("#city").selectpicker('refresh');
                    //render方法强制重新渲染引导程序 - 选择ui。
                    $("#city").selectpicker('render');
                },
                dataType:'JSON'
            })
        })




        //监听市区下拉框的change事件
        $("#city").change(function (){
            //获取当前省份和市区的地区id
            let provincesNum=$("#provinces").val()//获取当前省份的地区id
            let cityNum = $("#city").val()//获取当前市区的地区id
            $.ajax({//使用ajax异步获取县区信息
                url: "<%=basePath%>"+"/personServlet",
                data: {
                    action: "initCounty",
                    provincesNum: provincesNum,
                    cityNum: cityNum
                },
                type:"POST",
                success: function (data){
                    //重新生成下拉选项
                    $("#country").empty()
                    $("#country").append("<option value=''>请选择县区</option> ")
                    $.each(data,function (i,item) {
                        //$("#city").append(" <option value='" + item.areaId + "'>" + item.areaName + "</option>");
                        $("#country").append("<option value='"+item.areaId+"'>"+item.areaName+"</option>")
                    })
                    $("#country").selectpicker('refresh');
                    //render方法强制重新渲染引导程序 - 选择ui。
                    $("#country").selectpicker('render');
                },
                dataType:'JSON'
            })
        })
    })
</script>
</html>
