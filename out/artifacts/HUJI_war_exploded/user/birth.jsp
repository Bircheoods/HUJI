<!--page指令-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>出生证明办理</title>
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


  <div class="page_main"  >
    <c:if test="${ ! empty  requestScope.message}">
      <div class="alert alert-danger alert-dismissible"  data-dismiss="alert" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <strong>提示信息!</strong> ${requestScope.message}
      </div>
    </c:if>

    <!--    面包屑导航-->
    <ol class="breadcrumb">
      <li><a href="${pageContext.request.contextPath}/user/user_index.jsp">首页</a></li>
      <li><span>出生证明办理</span></li>
    </ol>

    <div class="panel panel-default">
      <form class="form-horizontal" action="personServlet" method="post">
        <input type="hidden" name="action" value="insertOnePerson">
<%--        当前户主、与户主之间的关系--%>
        <div class="form-group">
          <label for="household" class="col-sm-1 control-label">所在户</label>
          <div class="col-sm-2">
            <input type="text" class="form-control" value="${requestScope.holdNum}" name="household" id="household" readonly>
          </div>
          <label for="relation" class="col-sm-2 control-label">与户主关系</label>
          <div class="col-sm-3" id="relation">
            <select class="political selectpicker" name="relation" data-live-search="true">
              <option value="长子" >长子</option>
              <option value="次子">次子</option>
              <option value="次女">长女</option>
              <option value="长孙">长孙</option>
              <option value="长孙女">长孙女</option>
            </select>
          </div>
        </div>
<!--        第一行，姓名、性别-->
        <div class="form-group">
          <label for="inputPersonName" class="col-sm-1 control-label">姓名</label>
          <div class="col-sm-2">
            <input type="text" class="form-control" name="personName" id="inputPersonName" placeholder="请输入姓名">
          </div>
          <label for="inputOldName" class="col-sm-1 control-label">曾用名</label>
          <div class="col-sm-2">
            <input type="text" class="form-control" name="oldName" id="inputOldName" placeholder="请输入曾用名">
          </div>
          <label for="inputSex" class="col-sm-1 control-label">性别</label>
          <div class="col-sm-2" id="inputSex">
            <select class="form-control sex selectpicker" name="sex" data-live-search="true">
              <option value="" disabled>性别</option>
              <option value="0">女</option>
              <option value="1">男</option>
            </select>
          </div>

        </div>
<!--        第二行，出生年月日，民族-->
        <div class="form-group">
          <label for="inputBirth" class="col-sm-1 control-label">出生日期</label>
          <div class="col-sm-2">
            <input type="date" class="form-control" name="birth" id="inputBirth">
          </div>
          <label for="inputNation" class="col-sm-1 control-label">民族</label>
          <div class="col-sm-2" id="inputNation">
            <select class="form-control nation selectpicker" name="nation" data-live-search="true">
              <option value="汉族">汉族</option>
              <option value="满族">满族</option>
              <option value="蒙古族">蒙古族</option>
              <option value="回族">回族</option>
              <option value="藏族">藏族</option>
              <option value="维吾尔族">维吾尔族</option>
              <option value="苗族">苗族</option>
              <option value="彝族">彝族</option>
              <option value="壮族">壮族</option>
              <option value="布依族">布依族</option>
              <option value="侗族">侗族</option>
              <option value="瑶族">瑶族</option>
              <option value="白族">白族</option>
              <option value="土家族">土家族</option>
              <option value="哈尼族">哈尼族</option>
              <option value="哈萨克族">哈萨克族</option>
              <option value="傣族">傣族</option>
              <option value="黎族">黎族</option>
              <option value="傈僳族">傈僳族</option>
              <option value="佤族">佤族</option>
              <option value="畲族">畲族</option>
              <option value="高山族">高山族</option>
              <option value="拉祜族">拉祜族</option>
              <option value="水族">水族</option>
              <option value="东乡族">东乡族</option>
              <option value="纳西族">纳西族</option>
              <option value="景颇族">景颇族</option>
              <option value="柯尔克孜族">柯尔克孜族</option>
              <option value="土族">土族</option>
              <option value="达斡尔族">达斡尔族</option>
              <option value="仫佬族">仫佬族</option>
              <option value="羌族">羌族</option>
              <option value="布朗族">布朗族</option>
              <option value="撒拉族">撒拉族</option>
              <option value="毛南族">毛南族</option>
              <option value="仡佬族">仡佬族</option>
              <option value="锡伯族">锡伯族</option>
              <option value="阿昌族">阿昌族</option>
              <option value="朝鲜族">朝鲜族</option>
              <option value="塔吉克族">塔吉克族</option>
              <option value="怒族">怒族</option>
              <option value="乌孜别克族">乌孜别克族</option>
              <option value="俄罗斯族">俄罗斯族</option>
              <option value="鄂温克族">鄂温克族</option>
              <option value="德昂族">德昂族</option>
              <option value="保安族">保安族</option>
              <option value="裕固族">裕固族</option>
              <option value="京族">京族</option>
              <option value="塔塔尔族">塔塔尔族</option>
              <option value="独龙族">独龙族</option>
              <option value="鄂伦春族">鄂伦春族</option>
              <option value="赫哲族">赫哲族</option>
              <option value="门巴族">门巴族</option>
              <option value="珞巴族">珞巴族</option>
              <option value="基诺族">基诺族</option>
            </select>
          </div>
        </div>
<!--        籍贯-->
        <div class="form-group">
          <label for="nativePlace" class="col-sm-1 control-label">籍贯</label>
          <div class="col-sm-9">
            <input type="text" value="${requestScope.person.nativePlace}" readonly id="nativePlace" class="form-control" name="nativePlace">
          </div>



<%--          <div class="col-sm-10" id="nativePlace">--%>
<%--            <select class=" provinces selectpicker" name="provinces" id="provinces" data-live-search="true">--%>
<%--              <option value="">请选择省份</option>--%>
<%--              <c:forEach items="${requestScope.areas}" var="area">--%>
<%--                <option value="${area.areaId}">${area.areaName}</option>--%>
<%--              </c:forEach>--%>
<%--            </select>--%>
<%--            <select class=" sex selectpicker" name="city" data-live-search="true" id="city">--%>
<%--              <option value="">请选择市区</option>--%>

<%--            </select>--%>
<%--            <select class=" sex selectpicker" data-live-search="true" name="country" id="country">--%>
<%--              <option value="">请选择县区</option>--%>
<%--            </select>--%>
<%--            <input type="text" class="form-control" name="detailPlace" placeholder="详细地址" style="margin-top: 3px">--%>
<%--          </div>--%>
        </div>
<%--        当前居住地--%>
        <div class="form-group">
          <label for="address" class="col-sm-1 control-label">出生地</label>
          <div id="address" class="col-sm-9">
            <input type="text" value="${requestScope.person.address}" readonly class="form-control" name="address">
          </div>

<%--          <div class="col-sm-10" id="address">--%>
<%--            <select class=" nowProvinces selectpicker" name="nowProvinces" id="nowProvinces" data-live-search="true">--%>
<%--              <option value="">当前居住省份</option>--%>
<%--              <c:forEach items="${requestScope.areas}" var="area">--%>
<%--                <option value="${area.areaId}">${area.areaName}</option>--%>
<%--              </c:forEach>--%>
<%--            </select>--%>
<%--            <select class="nowCity selectpicker" name="nowCity" id="nowCity" data-live-search="true">--%>
<%--              <option value="">当前居住市区</option>--%>
<%--            </select>--%>
<%--            <select class="nowCountry selectpicker" name="nowCountry" id="nowCountry" data-live-search="true">--%>
<%--              <option value="">当前居住县级</option>--%>
<%--            </select>--%>
<%--            <input type="text" class="form-control" name="nowDetailPlace" placeholder="详细地址" style="margin-top: 3px">--%>
<%--          </div>--%>
        </div>
<%--         政治面貌、职业--%>
        <div class="form-group">
          <label for="political" class="col-sm-1 control-label">政治面貌</label>
          <div class="col-sm-5" id="political">
            <select class="political selectpicker" name="political" data-live-search="true">
              <option value="中共党员">中共党员</option>
              <option value="中共预备党员">中共预备党员</option>
              <option value="共青团员">共青团员</option>
              <option value="民革党员">民革党员</option>
              <option value="民盟盟员">民盟盟员</option>
              <option value="民建会员">民建会员</option>
              <option value="民进会员">民进会员</option>
              <option value="农工党党员">农工党党员</option>
              <option value="致公党党员">致公党党员</option>
              <option value="九三学社社员">九三学社社员</option>
              <option value="台盟盟员">台盟盟员</option>
              <option value="无党派人士">无党派人士</option>
              <option value="群众">群众</option>
            </select>
          </div>
<!--        </div>-->

<!--        <div class="form-group">-->
          <label for="professional" class="col-sm-1 control-label">职业</label>
          <div class="col-sm-5" id="professional">
            <select class="professional selectpicker" name="professional" data-live-search="true">
              <option value="国家机关，党群组织，企事业单位的负责人">国家机关，党群组织，企事业单位的负责人</option>
              <option value="专业技术人员">专业技术人员</option>
              <option value="办事人员和有关人员">办事人员和有关人员</option>
              <option value="商业、服务业人员">商业、服务业人员</option>
              <option value="农、林、牧、渔、水利业生产人员">农、林、牧、渔、水利业生产人员</option>
              <option value="生产、运输设备操作人员及有关人员">生产、运输设备操作人员及有关人员</option>
              <option value="军人">军人</option>
              <option value="不便分类的其他从业人员">不便分类的其他从业人员</option>
            </select>
          </div>
        </div>
<%--         文化程度、宗教信仰--%>
        <div class="form-group">
          <label for="education" class="col-sm-1 control-label">文化程度</label>
          <div class="col-sm-5" id="education">
            <select class="education selectpicker" name="education" data-live-search="true">
              <option value="研究生、博士">研究生、博士</option>
              <option value="大学本科">大学本科</option>
              <option value="大学专科和专科学校">大学专科和专科学校</option>
              <option value="中等专业学校或中等技术学校">中等专业学校或中等技术学校</option>
              <option value="技工学校">技工学校</option>
              <option value="高中">高中</option>
              <option value="初中">初中</option>
              <option value="小学">小学</option>
              <option value="文盲或半文盲">文盲或半文盲</option>
            </select>
          </div>
<!--        </div>-->

<!--        <div class="form-group">-->
          <label for="religious" class="col-sm-1 control-label">宗教信仰</label>
          <div class="col-sm-5" id="religious">
            <select class="religious selectpicker" name="religious" data-live-search="true">
              <option value="佛教">佛教</option>
              <option value="道教">道教</option>
              <option value="基督教">基督教</option>
              <option value="天主教">天主教</option>
              <option value="伊斯兰教">伊斯兰教</option>
            </select>
          </div>
        </div>
<%--         血型、兵役、婚姻--%>
        <div class="form-group">
          <label for="bloodType" class="col-sm-1 control-label">血型</label>
          <div class="col-sm-3" id="bloodType">
            <select class="bloodType selectpicker" name="bloodType" data-live-search="true">
              <option value="A型">A型</option>
              <option value="B型">B型</option>
              <option value="AB型">AB型</option>
              <option value="O型">O型</option>
              <option value="RH阴性">RH阴性</option>
              <option value="RH阳性">RH阳性</option>
            </select>
          </div>
<!--        </div>-->

<!--        <div class="form-group">-->
          <label for="militaryService" class="col-sm-1 control-label">兵役</label>
          <div class="col-sm-3" id="militaryService">
            <select class="militaryService selectpicker" name="militaryService" data-live-search="true">
              <option value="0">已服兵役</option>
              <option value="1">未服兵役</option>
            </select>
          </div>
<!--        </div>-->

<!--        <div class="form-group">-->
          <label for="marriage" class="col-sm-1 control-label">婚姻</label>
          <div class="col-sm-3" id="marriage">
            <select class="marriage selectpicker" name="marriage" data-live-search="true">
              <option value="0">已婚</option>
              <option value="1">未婚</option>
              <option value="2">离异</option>
            </select>
          </div>
        </div>

<%--        <div class="form-group">--%>
<%--          <label for="personData" class="col-sm-1 control-label">相关资料</label>--%>
<%--          <div class="col-sm-10">--%>
<%--            <input type="file" class="form-control" id="personData" placeholder="暂时只能支持单文件上传，如有多文件请先合并">--%>
<%--          </div>--%>
<%--        </div>--%>


        <div class="form-group">
          <div class="col-sm-6">
            <button type="submit" class="btn btn-primary pull-right">申请办理</button>
          </div>
<%--          <div class="col-sm-6">--%>
<%--            <button type="reset" class="btn btn-primary pull-left">重置</button>--%>
<%--          </div>--%>
        </div>
      </form>
    </div>

  </div>

</body>
<script type="text/javascript">
  $(function (){

    /*----------------当前居住地--------------------*/
    //监听省份下拉框的change事件
    $("#nowProvinces").change(function () {//使用ajax请求局部刷新市区信息

      let nowProvincesNum=$("#nowProvinces").val()//获取当前省份的地区id
      // alert("当前居住地"+nowProvincesNum)
      $.ajax({
        url: "<%=basePath%>"+"/personServlet",
        data: {
          action: "initNowCity",
          provincesNum: nowProvincesNum
        },
        type:"POST",
        success: function (data){
          //清空city下拉框
          $("#nowCity").empty()
          $("#nowCity").append("<option value=''>请选择市区</option>")
          //重新生成下拉选项
          $.each(data,function (i,item) {
            //$("#city").append(" <option value='" + item.areaId + "'>" + item.areaName + "</option>");
            $("#nowCity").append("<option value='"+item.areaId+"'>"+item.areaName+"</option>")
          })
          $("#nowCity").selectpicker('refresh');
          //render方法强制重新渲染引导程序 - 选择ui。
          $("#nowCity").selectpicker('render');
        },
        dataType:'JSON'
      })
    })
    //监听市区下拉框的change事件
    $("#nowCity").change(function (){
      //获取当前省份和市区的地区id
      let nowProvincesNum=$("#nowProvinces").val()//获取当前省份的地区id
      let nowCityNum = $("#nowCity").val()//获取当前市区的地区id
      $.ajax({//使用ajax异步获取县区信息
        url: "<%=basePath%>"+"/personServlet",
        data: {
          action: "initNowCounty",
          provincesNum: nowProvincesNum,
          cityNum: nowCityNum
        },
        type:"POST",
        success: function (data){
          //重新生成下拉选项
          $("#nowCountry").empty()
          $("#nowCountry").append("<option value=''>请选择县区</option> ")
          $.each(data,function (i,item) {
            //$("#city").append(" <option value='" + item.areaId + "'>" + item.areaName + "</option>");
            $("#nowCountry").append("<option value='"+item.areaId+"'>"+item.areaName+"</option>")
          })
          $("#nowCountry").selectpicker('refresh');
          //render方法强制重新渲染引导程序 - 选择ui。
          $("#nowCountry").selectpicker('render');
        },
        dataType:'JSON'
      })
    })

    /*-----------------判空操作---------------------*/
    $('form').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        personName: {
          validators: {
            notEmpty: {
              message: '人员姓名不能为空'
            }
          }
        },
        oldName: {
          validators: {
            notEmpty: {
              message: '曾用名不能为空，没有请填无'
            }
          }
        },
        detailPlace: {
          validators: {
            notEmpty: {
              message: '详细地址不可为空'
            }
          }
        },
        nowDetailPlace: {
          validators: {
            notEmpty: {
              message: '详细地址不可为空'
            }
          }
        }
      }
    })
  })
</script>
</html>