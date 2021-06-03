<%--
  Created by IntelliJ IDEA.
  User: 95179
  Date: 2021/4/24
  Time: 3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--获取base路径--%>
<%
    String basePath = request.getScheme()
                      + "://"
                      + request.getServerName()
                      + ":"
                      + request.getServerPort()
                      + request.getContextPath()
                      + "/";
%>
<%--抽取公共的引用资源--%>
<base href="<%=basePath%>>">



<link rel='icon' href='${pageContext.request.contextPath}/img/university1.jpg' type='image/x-ico' />
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.min.css">--%>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-select.min.css">--%>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.js"></script>
<%--<script src="${pageContext.request.contextPath}/css/bootstrap/js/bootstrap.min.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/bootstrap-select.min.js"></script>--%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" >
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
<link href="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.2/css/bootstrapValidator.min.css" rel="stylesheet">
<style type="text/css">
    .kaptcha{
        display: none;
    }
</style>
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.2/js/bootstrapValidator.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script type="text/javascript">
    $(function () {
        $('select').selectpicker();
    })
</script>