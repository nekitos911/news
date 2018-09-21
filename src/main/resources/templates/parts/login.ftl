<#macro login path isRegisterForm>
<form action="${path}" method="post" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"
      xmlns="http://www.w3.org/1999/html">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Username:</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="username">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-3">
            <input type="password" class="form-control" placeholder="Password">
        </div>
    </div>
        <#if !isRegisterForm>
        <input type="submit" class="btn btn-primary" value="Sign In">
        <input value="Registration" class="btn btn-primary ml-2" type="button" onclick="location.href='/registration'" />
        <#else>
        <input type="submit" class="btn btn-primary" value="Sign Up">
        </#if>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</form>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-primary">Sign Out</button>
        </form>
</#macro>