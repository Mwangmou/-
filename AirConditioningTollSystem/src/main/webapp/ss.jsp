<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Bean.*" %>
<%@ page import="Dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="Front.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房间管理</title>
<link rel="stylesheet" href="./css/TeacherMainStyle.css">
</head>
<body>
<%! roommanagementinfo stu = null; %>
<%
try {
    Object id1 = session.getAttribute("RoomNumber");
    if (id1 != null) {
        int id = (int) id1;
        stu = roommanagementdao.getStudent(id);
    } else {
//        out.println("Session attribute 'RoomNumber' is null");
    }
} catch (Exception e) {
    out.println("Error: " + e.getMessage());
}
%>
<%
int pageNum = 1;
int pageRows = roommanagementdao.getPageRows();
StringBuffer sb = CourseFrontPage.ShowInfo(pageNum, pageRows);
%>
<%
int pageSize = CourseFrontPage.pageSize;
int totalPage = CourseFrontPage.TotalPage;
int LouDong = 0;
int UnitNumber = 0;
try {
    if (request.getParameter("LouDong") != null) {
        LouDong = Integer.parseInt(request.getParameter("LouDong"));
    }
    if (request.getParameter("UnitNumber") != null) {
        UnitNumber = Integer.parseInt(request.getParameter("UnitNumber"));
    }
} catch (NumberFormatException e) {
    // 处理异常，参数解析失败
    e.printStackTrace();
}
%>

<div class="box">
    <div class="left">
        <div id="" class="title">
            空调收费系统
        </div>
        <ul>
            <li class="a"><img src="./img/试卷图标.jpg" class="course"><a href="ss.jsp">房间管理</a></li>
            <li class="a"><a href="CheckCourse.jsp"><img src="./img/试卷图标.jpg" class="course" />抄表录入</a></li>
            <li class="a"><a href="StuRevisePassword.jsp"><img src="./img/用户图标.png" class="user" />收费管理</a></li>
        </ul>
    </div>
    <div class="right">
        <div class="title">房间管理</div>
        <a href="Login.jsp" class="off">退出登录</a>
        <div class="main">
            <form action="" method="get" class="sousuo">
                <table>
                    <tr>
                        <td><input type="text" placeholder="楼栋号" name="LouDong" id="LouDong"></td>
                        <td><input type="text" placeholder="单元号" name="UnitNumber" id="UnitNumber"></td>
                        <td><input type="submit" id="searchBtn" value="搜索"></td>
                    </tr>
                </table>
            </form>
            <div id="Courselist"></div>
            <%= sb %>
        </div>
    </div>
</div>

<script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
<script>
var pageNum = 1;
var pageSize = "<%= pageSize %>";
var LouDong = "<%= LouDong %>";
var UnitNumber = "<%= UnitNumber %>";
<% if (stu != null) { %>
var RoomNumber = <%= stu.getRoomNumber() %>;
<% } else { %>
var RoomNumber = "undefined";
alert("无法获取房间号，请重新登录。");
<% } %>
var count = 0;

$(function(){
    getlistwithPage();
    ClickSearch();
});

function ClickSearch(){
    $('#searchBtn').click(function(event){
        event.preventDefault();
        LouDong = $('#LouDong').val();
        UnitNumber = $('#UnitNumber').val();
        pageNum = 1; // 每次搜索从第一页开始
        getlistwithPage();
    })
}

function getlistwithPage(){
    var content = [];
    content.push("<table style='width:90%;' cellspacing='0'  bordercolor='#ccc' border='1' cellspelling='0'>");
    content.push("<thead class='tableH'><td style='width:10%;text-align:center;'>ID</td><td style='width:20%;text-align:center;'>楼栋</td><td style='width:10%;text-align:center;'>单元号</td><td style='width:10%;text-align:center;'>房间号</td><td style='width:10%;text-align:center;'>余额</td><td style='width:10%;text-align:center;'>操作</td></thead>");
    content.push("<tbody>");
    $.ajax({
        type: "get",
        async: true,
        url: "StuCourseServlet?pageSize=" + pageSize + "&pageNum=" + pageNum + "&LouDong=" + LouDong + "&UnitNumber=" + UnitNumber,
        dataType: "json",
        success: function(result){
            var result = JSON.stringify(result);
            var dateArr = jQuery.parseJSON(result);
            if (dateArr.length > 0) {
                for (var i = 0; i < dateArr.length; i++) {
                    content.push("<tr style='height:50px;line-height:50px;' class='tabledate'>");
                    content.push("<td style='text-align:center;'>" + dateArr[i].ID + "</td>");
                    content.push("<td style='text-align:center;'>" + dateArr[i].louDong + "</td>");
                    content.push("<td style='text-align:center;'>" + dateArr[i].unitNumber + "</td>");
                    content.push("<td style='text-align:center;'>" + dateArr[i].roomNumber + "</td>");
                    content.push("<td style='text-align:center;'>" + dateArr[i].balance + "</td>");
                    content.push("<td style='text-align:center;'><a id='selectcoursebtn' href='StuAddCourseServlet?id="+dateArr[i].ID+"' style='display:inline-block;height:30px;width:50px;background-color:#2d6fb7;color:white;line-height:30px;'>充值</a></td>");
                    content.push("</tr>");
                }
            }
            content.push("</tbody>");
            content.push("</table>");
            $('#Courselist').html(content.join(''));
            $('#pageNum').html(pageNum);
            $('table .tabledate:even').css("background-color","#f2f2f2");
        },
        error: function(){
            alert("error");
        }
    })
}

function prvePage(){
    if (pageNum == 1) {
        alert("已经是第一页了");
        return;
    }
    pageNum--;
    getlistwithPage();
}

function nextPage(){
    if (pageNum == <%= totalPage %>) {
        alert("已经是最后一页了");
        return;
    }
    pageNum++;
    getlistwithPage();
}
</script>
</body>
</html>



