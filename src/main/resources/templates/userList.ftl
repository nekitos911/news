<#import "parts/common.ftl" as c>
<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/user" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>
<#--List of users-->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col">Editable</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
        <tr>
            <td>${user.username!}</td>
            <td><#list user.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/user/${user.id!}">EDIT</a> </td>
        </tr>
        </#list>
        </tbody>
    </table>
</@c.page>