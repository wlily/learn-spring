<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<%--<a href="<%=basePath%>">basePath</a>--%>
<ul>
    <li><a href="user/register.html">user/register.html</a></li>

    <li><a href="user/showUser/123.html">user/123.html</a></li>

    <li><a href="user/handle71.html">user/handle71.html</a></li>

    <li><a href="user/handle42/1.html">user/handle42/1.html</a></li>

    <li><a href="user/register.html">user/register.html</a></li>

    <li><a href="user/register/2?url=usr/handle61">user/register2.jsp?url=usr/handle61</a></li>

    <li><a href="user/handle81.html?user=tom:1234:tomson">user/handle81.html?user=tom:1234:tomson</a></li>

    <li><a href="user/register/2?target=/chapter15/user/handle91">user/register2.jsp?url=user/handle91</a></li>

    <li><a href="user/register/2?target=/chapter15/user/handle92">user/register2.jsp?url=user/handle92</a></li>

    <%--<li><a href="user/showUserListByXml">user/showUserListByXml</a></li>--%>
    <li><a href="user/showUserList">user/showUserList</a></li>
    <li><a href="user/register3">user/register3</a></li>
    <li><a href="user/register4">user/register4</a></li>
    <li><a href="user/showUserListByFtl">user/showUserListByFtl</a></li>
    <li><a href="user/showUserListByXls">user/showUserListByXls</a></li>
    <li><a href="user/showUserListByPdf">user/showUserListByPdf</a></li>
    <li><a href="user/showUserListByXml">user/showUserListByXml</a></li>
    <li><a href="user/showUserListByJson">user/showUserListByJson</a></li>
</ul>

</body>
</html>
