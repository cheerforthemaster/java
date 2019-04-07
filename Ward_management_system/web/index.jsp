<%--
  Created by IntelliJ IDEA.
  User: TheMaster
  Date: 2018/10/23
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <title>病房管理系统</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
  </head>
  <body>
  常用查询：
  <select name="select" id="select">
      <option value="department_select" >科室查询</option>
      <option value="doctor_select" >医护人员查询</option>
      <option value="patient_select" >病人查询</option>
  </select>
  名称: <input type="text" name="name" id="demo">(科室查询输入科名，医护人员和病人查询输入姓名)
  <button id="btn" name="btn" onclick="Select()">确定</button><br/>
  <select id="table">
      <option value="病房" >病房</option>
      <option value="科室" >科室</option>
      <option value="医护人员" >医护人员</option>
      <option value="住院病人" >住院病人</option>
  </select>
  <button id="btn2" onclick="gettable()">确定</button><br/>
  <select id="op">
      <option value="select" >查询</option>
      <option value="delete" >删除</option>
      <option value="insert" >添加</option>
  </select>
  <button id="btn3" onclick="operation()">确定</button><br/>
  <ul id="p1">

  </ul>
  <ul id="p2">

  </ul>

  <script>
      function Select() {
          var obj =document.getElementById("select");
          var chose = obj.options[obj.selectedIndex].value;
          var name=document.getElementById("demo").value;
          var url="http://localhost:8080/m_select?select="+chose+"&name="+name;
          $.ajax({
              url:url,
              type:'get',
              dataType:'text',
              //async: false,
              success: function (data) {
                  alert("success");
                  $("#p1").html(data);
                  //alert(data);
              },
              error:function(data) {
                  alert("error "+data.status);
              }
          });
      }
      function gettable() {//
          var obj =document.getElementById("table");
          var chose = obj.options[obj.selectedIndex].value;
          var html;
          if(chose=="病房")
          {
              html="病床数: <input type=\"text\" id=\"Ske\">" +
                  "所属科: <input type=\"text\" id=\"Sre\">" +
                  "病房号: <input type=\"text\" id=\"Sbf\">" +
                  "床号: <input type=\"text\" id=\"Sfa\">" +
                  "病人: (必填)<input type=\"text\" id=\"Sch\">";
              $("#p1").html(html);

          }
          if(chose=="科室")
          {
              html="科名: (必填)<input type=\"text\" id=\"Kke\">" +
                  "主任: <input type=\"text\" id=\"Kzh\">" +
                  "科地址: <input type=\"text\" id=\"Kdi\">" +
                  "电话: <input type=\"text\" id=\"Kdh\">" +
                  "医生: <input type=\"text\" id=\"Kdo\">";
              $("#p1").html(html);
          }
          if(chose=="医护人员")
          {
              html="姓名: (必填)<input type=\"text\" id=\"Yna\">" +
                  "技术职称: <input type=\"text\" id=\"Yji\">" +
                  "所属科: <input type=\"text\" id=\"Ydi\">";
              $("#p1").html(html);
          }
          if(chose=="住院病人")
          {
              html="姓名: (必填)<input type=\"text\" id=\"Zna\">" +
                  "病历号: <input type=\"text\" id=\"Zbl\">" +
                  "年龄: <input type=\"text\" id=\"Zag\">" +
                  "性别: <input type=\"text\" id=\"Zxi\">" +
                  "诊断: <input type=\"text\" id=\"Zzd\">" +
                  "主管医生: <input type=\"text\" id=\"Zzh\">"+
                  "科: <input type=\"text\" id=\"Zke\">" +
                  "房间号: <input type=\"text\" id=\"Zfj\">" +
                  "病床号: <input type=\"text\" id=\"Zbc\">";
              $("#p1").html(html);
          }
          $("#p2").html("");
      }
      function operation() {
          var obj =document.getElementById("table");
          var chose = obj.options[obj.selectedIndex].value;
          var oper=document.getElementById("op").options[document.getElementById("op").selectedIndex].value;
          var html;
          if(chose=="病房")
          {
              html=document.getElementById("Ske").value +","+
                  document.getElementById("Sre").value +","+
                  document.getElementById("Sbf").value +","+
                  document.getElementById("Sfa").value +","+
                  document.getElementById("Sch").value;
          }
          if(chose=="科室")
          {
              html=document.getElementById("Kke").value +","+
                  document.getElementById("Kzh").value +","+
                  document.getElementById("Kdi").value +","+
                  document.getElementById("Kdh").value +","+
                  document.getElementById("Kdo").value;
          }
          if(chose=="医护人员")
          {
              html=document.getElementById("Yna").value +","+
                  document.getElementById("Yji").value +","+
                  document.getElementById("Ydi").value;
          }
          if(chose=="住院病人")
          {
              html=document.getElementById("Zna").value +","+
                  document.getElementById("Zbl").value +","+
                  document.getElementById("Zag").value +","+
                  document.getElementById("Zxi").value +","+
                  document.getElementById("Zzd").value +","+
                  document.getElementById("Zzh").value +","+
                  document.getElementById("Zke").value +","+
                  document.getElementById("Zfj").value +","+
                  document.getElementById("Zbc").value;
          }
          //alert(document.getElementById("Sch").value);
          var url="http://localhost:8080/operation?table="+chose+"&op="+oper+"&value="+html;
          $.ajax({
              url:url,
              type:'get',
              dataType:'text',
              success: function (data) {
                  //alert("success");
                  $("#p2").html(data);
                  //alert(data);
              },
              error:function(data) {
                  alert("error "+data.status);
                  $("#p2").html("操作失败！");
              }
          });
      }
  </script>
  </body>
</html>
