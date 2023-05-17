<%--
  Created by IntelliJ IDEA.
  User: Yang Shuailing
  Date: 2023/5/16
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据可视化</title>
<%--    <script src="${pageContext.request.contextPath}/echart/echarts.js"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/echarts@5.4.2/dist/echarts.min.js"></script>
</head>

<body>
<!-- 在 HTML 中添加一个具有指定高度和宽度的容器 -->
<div id="myChart" style="width: 600px; height:400px;">Hello，World</div>

</body>
<script type="text/javascript">

    window.onload=ajaxStuList();
    function ajaxStuList(){
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function (){
            if (xhr.readyState == 4){
                alert(this.responseText)
                let parse = JSON.parse(this.responseText);
                alert(parse[1])

                if (xhr.status >= 200 && xhr.status < 300){
                    drawCircle(parse)
                }
            }
        }
        xhr.open("GET","${pageContext.request.contextPath}/stu/data",true)
        xhr.send()
    }


    function drawCircle(data){
        var myChart = echarts.init(document.getElementById('myChart'));
        alert(data)
        let list = [];
        let name = [];
        for (let key in data){
            alert("test1:"+data[key]);
            alert("test1_1:"+data[key].name);
            alert("test1_2:"+data[key].value);
            name.push(data[key].name)
            list.push({
                name:data[key].name,
                value:data[key].value
            })
            console.log(data[key].name)
        }
        alert(list)
        var option = {
            title: {
                text: '饼状图示例'
            },
            tooltip: {},
            legend: {                 //旁边的小标图
                orient: 'vertical',
                right: 10,
                top: 300,
                //添加物品名
                data: name
            },

            series: [{
                type: 'pie',
                data: list
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }

</script>
</html>
