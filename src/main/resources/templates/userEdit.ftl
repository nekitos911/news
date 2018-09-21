<#import "parts/common.ftl" as c>
<@c.page>userId
User editor <br><br>
<form method="post" action="/user">
    <input type="text" name="username" value="${user.username}">
    <#list roles as role>
        <div>
            <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
        </div>
    </#list>
    <input type="hidden" value="${user.id}" name="userId">
     <@c.token />
    <button type="submit">Save</button>
</form>
</@c.page>