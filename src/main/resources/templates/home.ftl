<#import "parts/common.ftl" as c>
<@c.page>
<div class="form-row">
    <form method="get" action="/home" class="form-inline">
        <input type="text" name="filter" value="${filter!}">
        <button class="btn btn-primary" type="submit">Найти</button>
    </form>
</div>
<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение" />
        <input type="text" name="tag" placeholder="Тэг">
        <@c.token />
        <button type="submit">Добавить</button>
    </form>
</div>

<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
<#else>
No message
</#list>
</@c.page>