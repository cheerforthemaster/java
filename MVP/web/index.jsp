<%--
  Created by IntelliJ IDEA.
  User: TheMaster
  Date: 2018/10/17
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
  </head>
  <body>
  <select name="select" id="select">
      <option value="County" >County</option>
      <option value="MetalLevel" >Metal Level</option>
      <option value="IssuerName" >Issuer Name</option>
  </select>
  <button id="btn" name="btn" onclick="Select()">确定</button>
  <div id='main' style='width:600px;height:400px;'></div>
  <script src='echarts.js'></script>
  <script>
      var myChart;
    function Select() {
        var obj =document.getElementById("select");
        var chose = obj.options[obj.selectedIndex].value;
        var url="http://localhost:8080/back_end?select="+chose;
        $.ajax({
            url:url,
            type:'get',
            dataType:'text',
            //async: false,
            success: function (data) {
                alert("success");
                alert(data);
                draw(data);
            },
            error:function(data) {
                alert("error "+data.status);
            }
            });
    }
    function draw(data) {
        var fanl = JSON.parse(data);
        var arr1=[];
        var arr2=[];
        for (var p in fanl){
            arr1.push(fanl[p].name);
            arr2.push(fanl[p].value)
        }
        var option = {
            title:{
                text:'医疗数据统计'
            },
            tooltip:{},
            legend:{
                data:[' ']
            },
            xAxis:{
                data:arr1
            },
            yAxis:{

            },
            series:[{
                name:'数量',
                type:'bar',
                data:arr2
            }]
        };
        myChart = echarts.init(document.getElementById('main'));
        myChart.setOption(option);
    }


  </script>
  </body>
</html>
