<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<#-- 注释部分 -->
<#-- 下面使用插值 -->
<h1>Welcome ${user} !</h1>

<p>We have these animals:
    <u1>
    <#-- 使用FTL指令 -->
    <#list animals as animal>
        <li>${animal.name}: ${animal.price} Euros
    </#list>
    </u1>
</body>
</html>