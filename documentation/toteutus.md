# Toteutus

## Ohjelman luokat

Tällä hetkellä ohjelma koostuu pääohjelmasta ja yhdeksästä luokasta, joita käytetään labyrinttien muodostamisessa ja ratkaisemisessa. Lisäksi ohjelma sisältää input-output-luokan sekä käyttöliittymästä vastaavan luokan. Luokat on jaettu kuuteen pakkaukseen sen mukaan, mihin ohjelman osa-alueeseen ne kuuluvat. Tällä hetkellä luokat ovat seuraavat:

- Pakkaus *main*
    - **Main** pääohjelmalle
- Pakkaus *maze*
    - **Cell** ruuduille, joista labyrintti muodostuu
    - **Maze** labyrintin muodostamiseen annetuista ruuduista
    - **Wall** ruutujen välisille seinille
- Pakkaus *maze.io*
    - **MazeIo** syötetteiden ja tulosteiden käsittelyyn
- Pakkaus *maze.ui*
    - **MazeUi** käyttöliittymän toteuttamiseen
- Pakkaus *mazegeneration*
    - **DepthfirstSearch** labyrintin luomiseen satunnaistetun syvyyshaun avulla
    - **KruskalsAlgorithm** labyrintin luomiseen satunnaistetun Kruskalin algoritmin avulla
    - **LoopedMaze** paljon luuppeja sisältävän labyrintin luomiseen Trémauxin algoritmin testaamista varten
- Pakkaus *mazesolving*
    - **WallFollower** labyrintin ratkaisemiseen wall-follower-algoritmin avulla
    - **BreadthfirstSearch** labyrintin ratkaisemiseen ja lyhimmän polun etsimiseen leveyshaun avulla
    - **TremauxsAlgorithm** labyrintin ratkaisemiseen Trémauxin algoritmin avulla



## Ohjelman puutteet ja ongelmat

- Algoritmien vertailu kirjoittamatta
- Trémauxin algoritmi ei toimi
- Omat tietorakenteet toteuttamatta
- Cucumber-testit pahasti kesken
- Käyttöliittymästä toteutettu vasta hyvin yksinkertainen komentoriviltä toimiva versio
- Syötteiden tarkastusta ei ole vielä toteutettu
- Labyrintin tulostus ei ole vielä input-output-luokan vastuulla
- Käyttöliittymässä ei voi (vielä) visualisoida algoritmien toimintaa
