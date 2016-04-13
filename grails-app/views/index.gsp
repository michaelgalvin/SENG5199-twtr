<!DOCTYPE html>
<html lang="en" ng-app="app">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>TWTR: small bird noises</title>
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="application.css"/>
    <style>
    tbody {
        height: 240px;
        overflow-y: auto;
    }
    </style>
</head>

<body role="document">

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="#/home" id="home-link">Home</a></li>
                <li><a href="#/login" id="login-link">Login</a></li>
                <li><a href="#/search" id="search-link">Search</a></li>
                <li><a href="#/logout" id="logout-link">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container theme-showcase" role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>TWTR: small bird noises</h1>

        <p>This app is for the birds.</p>
    </div>

    <div ng-view></div>

</div>
</body>

</html>
