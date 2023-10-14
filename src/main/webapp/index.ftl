<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Сайт о животных</title>
  <link rel="stylesheet" type="text/css" href="styles/styles-group2.css" />
</head>

<body>
<header>
  <h1>Сайт о животных</h1>
  <nav>
    <a href="#">Млекопитающие</a>
    <a href="#">Птицы</a>
    <a href="#">Рыбы</a>
    <a href="#">Рептилии</a>
    <a href="#">Другие</a>
  </nav>
</header>


<div class="container">
  <h2>Добро пожаловать на наш сайт!</h2>
  <p>Здесь вы найдете много интересной информации о животных.</p>

  <div class="animal-feed">
    <div class="animal-card">
      <h3>${lion.name}</h3>
      <img src="${lion.image}" alt="${lion.name}" width="180px">
      <p>${lion.description}</p>
      <a href="articles?animal=lion"><input type="button" class="button" value="Перейти"></a>
    </div>

    <div class="animal-card">
      <h3>${cat.name}</h3>
      <img src="${cat.image}" alt="${cat.name}" width="180px">
      <p>${cat.description}</p>
      <a href="articles/cat"><input type="button" class="button" value="Перейти"></a>
    </div>

    <div class="animal-card">
      <h3>${fish.name}</h3>
      <img src="${fish.image}" alt="${fish.name}" width="180px">
      <p>${fish.description}</p>
      <a href="articles/fish"><input type="button" class="button" value="Перейти"></a>
    </div>
  </div>
</div>

<footer>
  &copy; 2023 Сайт о животных
</footer>
</body>

</html>
