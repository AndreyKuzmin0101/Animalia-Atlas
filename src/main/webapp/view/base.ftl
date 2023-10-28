<html lang="en">

<head>
    <meta charset="UTF-8">
    <title><@title/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/view/styles/styles-group2.css" />
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <@parametrs/>

</head>

<body>
<header>
    <div style="text-align: left">
        <div style="display:inline-block; width: 62%; text-align: right">
            <h1>Сайт о животных</h1>
        </div>
        <div id="header-buttons" style="display: inline-block; width: 37%; text-align: right"></div>
    </div>



    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="/" style="color:#ffffff; padding-left: 6px; padding-right: 6px">Главная</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Дикие животные
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/search?category=Mammals">Млекопитающие</a></li>
                            <li><a class="dropdown-item" href="/search?category=Reptiles">Пресмыкающиеся</a></li>
                            <li><a class="dropdown-item" href="/search?category=Arachnids">Паукообразные</a></li>
                            <li><a class="dropdown-item" href="/search?category=Crustaceans">Ракообразные</a></li>
                            <li><a class="dropdown-item" href="/search?category=Amphibians">Земноводные</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Домашние
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/search?category=Rodents">Грызуны</a></li>
                            <li><a class="dropdown-item" href="/search?category=Rabbits">Кролики</a></li>
                            <li><a class="dropdown-item" href="/search?category=Cats">Кошки</a></li>
                            <li><a class="dropdown-item" href="/search?category=Dogs">Собаки</a></li>
                            <li><a class="dropdown-item" href="/search?category=Horses">Лошади</a></li>
                            <li><a class="dropdown-item" href="/search?category=Exotic">Экзотика</a></li>

                        </ul>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link" href="/search?category=Birds">Птицы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/search?category=Fish">Рыбы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/search?category=Insects">Насекомые</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/users">Пользователи</a>
                    </li>
                    <li class="nav-item">
                        <button class="nav-link" id="favourite">Понравившиеся ❤️</button>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" id="search-input" style="width: 150px">
                    <button class="btn btn-outline-success" type="button" id="search-button">Search</button>
                    <select class="filter" id="filter">
                        <option value="">Фильтр</option>
                        <option value="Mammals">Млекопитающиеся</option>
                        <option value="Reptiles">Пресмыкающиеся</option>
                        <option value="Arachnids">Паукообразные</option>
                        <option value="Crustaceans">Ракообразные</option>
                        <option value="Amphibians">Земноводные</option>
                        <option value="Rodents">Грызуны</option>
                        <option value="Rabbits">Кролики</option>
                        <option value="Cats">Кошки</option>
                        <option value="Dogs">Собаки</option>
                        <option value="Horses">Лошади</option>
                        <option value="Exotic">Экзотика</option>
                        <option value="Birds">Птицы</option>
                        <option value="Fish">Рыбы</option>
                        <option value="Insects">Насекомые</option>
                        <option value="Extinct">Вымершие</option>
                    </select>
                </form>
            </div>
        </div>
        <script src="/view/scripts/favourite.js"></script>
    </nav>

</header>
<script src="/view/scripts/header.js"></script>

<@content/>

<footer>

    <div style="text-align: left">
        <div style="display:inline-block; width: 55%; text-align: right">
            &copy; 2023 Сайт о животных
        </div>
        <div id="header-buttons" style="display: inline-block; width: 44%; text-align: right;"><a href="/view/about_us.ftl" style="text-decoration: none; color: #4caf50;">О НАС</a></div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="/view/scripts/search.js"></script>

</body>

</html>
