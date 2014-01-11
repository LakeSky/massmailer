<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="appMassMailer">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="static/css/bootstrap.css"/>
    <link rel="stylesheet" href="static/css/toastr.css"/>

    <style>
        body {
            padding-top: 60px;
        }
    </style>
    <title>GetBookmarks : Submit Story</title>
</head>
<body>

       <div  >
                <ul class="nav" role="navigation">
                    <li>
                        <a href="#users">Users</a>
                    </li>
                </ul>
            </div>
<div class="container" ng-view>
 
</div>
     <script src="https://code.jquery.com/jquery.js"></script>
<script src="static/js/bootstrap.js"></script>
<script src="static/js/angular.min.js"></script>
<script src="static/js/angular-route.min.js"></script>
<script src="static/js/angular-resource.js"></script>
<script src="static/js/toastr.js"></script>
<script src="static/js/massmailer.js"></script>

</body>
</html>
