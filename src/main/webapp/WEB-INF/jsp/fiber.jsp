<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <style><%@include file="/css/style_fiber_page.css"%></style>
    <title>Fiber</title>
    <script src="<c:url value="/js/fiber_page_new_comment.js"/>"></script>
    <script src="<c:url value="/js/fiber_page_open_close_buttons.js"/>"></script>
    <script src="<c:url value="/js/fiber_page_change_colors.js"/>"></script>
</head>
<body>
<h5 class="page_title">Fiber</h5>

<div class="buttons">
    <button class="modal-open item_1" data-id="change-colors">Reverse colors</button>
    <button class="modal-open item_16" data-id="new-fiber-modal">New Fiber</button>
</div>

<div id="new-fiber-modal" class="modal" role="dialog" tabindex="-1">
    <div class="modal-inner">
        <div class="modal-header">
            <h1 class="modal-title">New Fiber</h1>
        </div>
        <form id="new_comment">
            <input class="input" name="comment_to" placeholder="Comment to" type="text" required/>
            <br>
            <input class="input" name="comment" placeholder="Text" type="text" required/>
        </form>
        <br>
        <button class="modal-create" data-id="new_comment">Create</button>
        <button class="modal-close" data-id="new-fiber-modal">Close</button>
    </div>
</div>

<div class="container" id="fibers">
    <div class="opening-fiber">
        ${fiber.creationDateToString()}
        #${fiber.getId()}
        <br>${fiber.getSection()}
    </div>
    <c:forEach items="${comments}" var="comment">
        <div class="item">
            ${comment.creationDateToString()}
            #${comment.getId()}
            comment to: #${comment.getCommentTo()}
            <br>${comment.getSection()}
        </div>
    </c:forEach>
</div>
</body>
</html>