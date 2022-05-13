Egy japán autókereskedés nyílvántartó programja. Tárolja az autók adatait és árát.

Részei: 
* cars.xml: tárolja az autók adatait két futás között.
* Reader.java: Beolvassa az xml fájlt
* Writer.java: Módosítja az xml tartalmát
* Menu.java: A program menü részét tartalmazza. Itt lehet kiválasztani, hogy mit akar a felhasználó csinálni 
az adatokkal. Tartalmazza a main-t.
* Car.java: Az autók osztálya. Részei: rendszám, márka, típus, gyártási év, szín és ár.
* Brand.java (enum): Az elérhető márkák neveit tartalmazza.
* Típus enumok (Nissan.java, Suzuki.java stb.): Az adott márkán belül elérhető típusok neveit tartalmazzák
* Color.java: Az elérhető színeket tartalmazza.

Működés:
Indításkor megjelenik a menü, 4 dolgot tehetünk: 
* Listázás (listCars): Kiírja az xml-ben lévő autók adatait a képernyőre.
* Hozzáadás (addNewCar): Hozzáad egy új autót az adatbázishoz. A rendszámot be kell gépelni (egy rendszám
csak egyszer szerepelhet), a márkánál egy kis
menüben választhatunk. A választott márka alapján lehet választani az adott márka egyik típusát, 
szintén menüből. Tehát pl a Suzuki márkához nem lehet a Toyota egyik típusát rendelni. A színt is menüből kell választani, 
az árat a felhasználó szabja meg.
* Módosítás (modifyCar): Módosítja az adott autó egy tulajdonságát. Az autót a rendszám alapján keresi meg, 
ezután felhasználó döntheti el mit akar módosítani. A márkát és a típust egyszerre kell módosítani.
* Törlés (deleteCar): Rendszám alapján megkeres egy autót, majd törli az adatbázisból.

