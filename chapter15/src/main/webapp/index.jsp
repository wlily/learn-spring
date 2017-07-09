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
    <meta http-equiv="Keep-Alive" content="0">
    <meta http-equiv="content-type" content="text/html">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<ul>
    <li>15.2 annotation-based controller
        <ul>
            <li>15.2.1 @RequestMapping
                <ul>
                    <li><a href="user/register.html">注册(value,method,param)</a></li>
                    <li><a href="user/show.html?userId=1">查看(consumes)</a></li>
                </ul>
            </li>
            <li>15.2.2 controller method signature
                <ul>
                    <li><a href="user/showUser/1"> user/handle1.html----@PathVariable</a></li>
                    <li><a href="user/handle1.html?userName=ww&password=sss">user/handle1.html----@RequestParam</a></li>
                    <li><a href="user/handle2.html?userName=ww&password=sss">user/handle2.html----@CookieValue and @RequestHeader</a></li>
                    <li><a href="user/handle3.html?userName=ww&password=sss">user/handle3.html----Command or Form Object</a></li>
                    <li><a href="user/handle4.html?userName=ww&password=sss">user/handle4.html----HttpServletRequest</a></li>
                    <li><a href="user/handle5.html?userName=ww&password=sss">user/handle5.html----HttpServletRequest,HttpServletResponse</a></li>
                    <li><a href="user/handle6.html?userName=ww&password=sss">user/handle6.html----HttpSession</a></li>
                    <li><a href="user/handle7.html?userName=ww&password=sss">user/handle7.html----WebRequest</a></li>
                    <li><a href="user/handle8.html?userName=ww&password=sss">user/handle8.html----OutputStream</a></li>
                </ul>
            </li>
            <li>15.2.3 HttpMessageConverter
                <ul>
                    <li><a href="user/register/2?target=/chapter15/user/handle9.html">@RequestBody</a></li>
                    <li><a href="user/handle10/1">@ResponseBody</a></li>
                    <li><a href="user/register/2?target=/chapter15/user/handle11.html">HttpEntity</a></li>
                    <li><a href="user/handle12/1">ResponseEntity</a></li>
                    <li><!--<a href="user/handle13.html?userName=ww&password=sss">-->Xml & Json MessageConverter, check the test</a></li>
                </ul>
            </li>

            <li>15.2.4 how spring deal with model
                <ul>
                    <li><a href="user/register/2?target=/chapter15/user/handle31">ModelAndView</a></li>
                    <li><a href="user/register/2?target=/chapter15/user/handle32">@ModelAttribute on parameter</a></li>
                    <li><a href="user/register/2?target=/chapter15/user/handle33">@ModelAttribute on method</a></li>
                    <li><a href="user/register/2?target=/chapter15/user/handle34">ModelMap</a></li>
                    <li><a href="user/register/2?target=/chapter15/user/handle71">@SessionAttributes</a></li>
                </ul>
            </li>
        </ul>
    </li>

    <li>15.3 how spring use DataBind to bind data to controller method parameter
        <ul>
            <li><a href="user/handle81.html?user=tom:1234:tomson">converter</a></li>
            <li><a href="user/register/2?target=/chapter15/user/handle91">formatter</a></li>
            <li><a href="user/register/2?target=/chapter15/user/handle91">validator1</a></li>
            <li><a href="user/register/2?target=/chapter15/user/handle92">validator2</a></li>
        </ul>
    </li>

    <li>15.4 how spring use ViewResolver to render various View
        <ul>
            <li><a href="user/showUserList">user/showUserListByJsp</a></li>
            <li><a href="user/showUserListByFtl">user/showUserListByFtl</a></li>
            <li><a href="user/showUserListByXls">user/showUserListByXls</a></li>
            <li><a href="user/showUserListByPdf">user/showUserListByPdf</a></li>
            <li><a href="user/showUserListByXml">user/showUserListByXml</a></li>
            <li><a href="user/showUserListByJson">user/showUserListByJson</a></li>
            <li><a href="user/showUserListByI18n">user/showUserListByI18n</a></li>
        </ul>
    </li>

    <li><a href="user/register/2?url=usr/handle61">user/register2.jsp?url=usr/handle61</a></li>

    <li><a href="user/register/2?target=/chapter15/user/handle92">user/register2.jsp?url=user/handle92</a></li>

    <li><a href="user/register3">user/register3</a></li>
    <li><a href="user/register4">user/register4</a></li>

</ul>

</body>
</html>
