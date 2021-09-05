# Toteutus

## Ohjelman luokat

Ohjelma koostuu pääohjelmasta ja seitsemästä luokasta, joita käytetään labyrinttien muodostamisessa ja ratkaisemisessa. Lisäksi ohjelma sisältää input-output-luokan, käyttöliittymästä vastaavan luokan sekä suorituskykytestaukseen tarkoitetun luokan. Luokat on jaettu kuuteen pakkaukseen sen mukaan, mihin ohjelman osa-alueeseen ne kuuluvat. Tällä hetkellä luokat ovat seuraavat:

- Pakkaus *main*
    - **Main** pääohjelmalle
    - **PerformanceTester** algoritmien suorituskyvyn testaamiseen
- Pakkaus *maze*
    - **Cell** ruuduille, joista labyrintti muodostuu
    - **Maze** labyrintin muodostamiseen annetuista ruuduista
    - **Wall** ruutujen välisille seinille
- Pakkaus *maze.io*
    - **MazeIo** syötetteiden ja tulosteiden käsittelyyn
- Pakkaus *maze.ui*
    - **MazeUi** käyttöliittymän toteuttamiseen
- Pakkaus *mazegeneration*
    - Yläluokka **MazeGenerationAlgorithm** labyrintin luomiseen tarkoitettujen algoritmien yhteisten metodien toteuttamiseen
    - **DepthfirstSearch** labyrintin luomiseen satunnaistetun syvyyshaun avulla
    - **KruskalsAlgorithm** labyrintin luomiseen satunnaistetun Kruskalin algoritmin avulla
- Pakkaus *mazesolving*
    - Yläluokka **MazeSolvingAlgorithm** labyrintin ratkaisemiseen tarkoitettujen algoritmien yhteisten metodien toteuttamiseen
    - **WallFollower** labyrintin ratkaisemiseen wall-follower-algoritmin avulla
    - **BreadthfirstSearch** labyrintin ratkaisemiseen ja lyhimmän polun etsimiseen leveyshaun avulla



## Ohjelman puutteet ja ongelmat sekä toteuttamatta jääneet tavoitteet

- Algoritmien vertailu kirjoittamatta
- Omat tietorakenteet toteuttamatta
- Käyttöliittymästä toteutettu vain hyvin yksinkertainen komentoriviltä toimiva versio, joka tulostaa labyrintin merkkejä *#* ja *o* sekä välilyöntiä hyödyntäen
- Syötteiden tarkastusta ei toteutettu
- Käyttöliittymässä ei voi visualisoida algoritmien toimintaa
