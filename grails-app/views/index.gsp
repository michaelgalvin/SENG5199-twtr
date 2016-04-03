<!DOCTYPE html>
<html lang="en">
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

<body ng-app="app" role="document">

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            %{--<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"--}%
            %{--aria-expanded="false" aria-controls="navbar">--}%
            %{--<span class="sr-only">Toggle navigation</span>--}%
            %{--<span class="icon-bar"></span>--}%
            %{--<span class="icon-bar"></span>--}%
            %{--<span class="icon-bar"></span>--}%
            %{--</button>--}%
            <a class="navbar-brand" href="#">TWTR</a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container theme-showcase" role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>TWTR: small bird noises</h1>

        <p>This is a sharp app.</p>
    </div>


    <div ng-controller="welcomeController">
        <h2>{{welcomeGreeting}}</h2>
    </div>

    <div ng-controller="searchController">
        <h2>{{searchGreeting}}</h2>

        <div class="input-group">
            <input type="text" ng-model="q"/>
        </div>

        <div id="searchResults">

            <h3>Your tweets</h3>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>User</th>
                    <th>Message</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="message in messages">
                    <td>{{message.author.handle}}</td>
                    <td>{{message.text}}</td>
                </tr>
                </tbody>

            </table>

        </div>
    </div>

</div>
</body>

</html>