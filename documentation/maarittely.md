# Määrittelydokumentti

Tekijä: Julia Palorinne (TKT)

Käytetty ohjelmointikieli: Java

Koodin kieli: englanti

Dokumentaation kieli: suomi

<br>

Työni tarkoitus on tarkastella ja vertailla algoritmeja, joilla luodaan ja ratkaistaan labyrintteja.

<br>

## Algoritmit

### Algoritmit labyrinttien luomiseen

Tarkastelen ja vertailen seuraavia labyrinttien luomiseen käytettäviä algoritmeja:
* Satunnaistettu syvyyshaku (Randomized depth-first search)
* Satunnaistettu Kruskalin algoritmi (Randomized Kruskal's algorithm)

<br>

### Algoritmit labyrinttien ratkaisuun

Tarkastelen työssäni seuraavia labyrinttien ratkaisuun tarkoitettuja algoritmeja:
* Wall follower
* Trémaux's algorithm
* Shortest path algorithm

<br>

## Syötteet

Labyrinttien luomisessa käytetään satunnaislukuja.
Labyrinttien ratkaisussa syötteenä käytetään eri luomisalgoritmeilla tuotettuja labyrintteja.

<br>

## Luomisessa käytettävien algoritmien esittely

Labyrinttien luomisessa käytetään suuntaamattomien verkkojen läpikäymiseen tarkoitettuja algoritmeja, joissa jokainen labyrintin ruutu käsitellään solmuna (n) ja ruutujen välinen reitti kaarena (m). Eri algoritmeilla voidaan tuottaa rakenteeltaan ja monimutkaisuudeltaan erilaisia labyrintteja. Tarkoituksena on tuottaa sekä yksinkertaisia labyrintteja, jotka eivät sisällä syklejä, että monimutkaisempia, syklejä sisältäviä labyrintteja.


### Satunnaistettu syvyyshaku (Randomized depth-first search)
Aikavaativuus: O(n + m)

### Satunnaistettu Kruskalin algoritmi (Randomized Kruskal's algorithm)
Aikavaativuus: O(n + m log n)

<br>

## Ratkaisussa käytettävien algoritmien esittely

### Wall follower
Algoritmi aloittaa labyrintin toisesta reunasta ja seuraa oikean- tai vasemmanpuoleista seinää, kunnes löytää reitin ulos. Tämä toimii vain yksinkertaisessa labyrintissa, jossa ei ole syklejä.


### Trémaux's algorithm
Algoritmi merkitsee, missä osissa labyrinttia on jo käynyt. Jos se saapuu samaan paikkaan uudestaan, se sulkee pois tämän reitin, sillä sen on täytynyt saavuttaa jossakin umpikuja. Tämä algoritmi löytää aina reitin labyrintin läpi, mutta se ei välttämättä löydä lyhintä reittiä.


### Shortest path algorithm
Esim. jokin leveyshaun tai A*-algoritmin sovellus. Tämän algoritmin valinta on vielä kesken.


<br>

## Lähteet

* Antti Laaksonen: Tietorakenteet ja algoritmit (kevät 2020)
* https://en.wikipedia.org/wiki/Maze_generation_algorithm (luettu 23.7.2021)
* https://en.wikipedia.org/wiki/Maze-solving_algorithm (luettu 23.7.2021)

<br>
