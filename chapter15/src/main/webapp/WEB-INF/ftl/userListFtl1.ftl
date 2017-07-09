<html>
<head>
    <title><baobaotao</title>
</head>
<body>
<table>
<#list userList as user>
    <tr>
        <td>
            <a href="/user/showUser/${user.userId}.html/>">
            ${user.userName}
            </a>
        </td>
        <td>${user.realName}</td>
        <td> ${user.birthday?string("yyyy-MM-dd")}</td>
    </tr>
</#list>
    <table>
</body>
</html>
