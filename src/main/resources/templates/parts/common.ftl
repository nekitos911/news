<#macro page>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset=UTF-8" />
</head>
<body>
<#nested>
</body>
</html>
</#macro>

<#macro token>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</#macro>