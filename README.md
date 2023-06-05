# The Hangman

## About 

|            |                                              |
| ---------- | -------------------------                    |
| Title:     | The Hangman (Android Java)       |
| Instructor and Author:| Albert Rovira                        |
| *Fall*:      | 2022-2023                                   |
| *Course*:    |    102386 - APPLICATIONS FOR MOBILE DEVICES - [Grau en Tècniques d'Interacció Digital i de Computació](http://www.grauinteraccioicomputacio.udl.cat/ca/index.html) |
| University:     | [University of Lleida](http://www.udl.cat/ca/) - [Campus Igualada](http://www.campusigualada.udl.cat/ca/) - [Escola Politècnica Superior](http://www.eps.udl.cat/ca/)       |

## Versions

### Version 1.0 (28/03)

Initial version of the original and classic game The Hangman.

This code is not completely functional neither optimized. The code is created in order to be clear and easy to solve for the students in their first partial exam.


# 1. Requisits Mínims

## Requisit 1.1: En la pantalla de joc, cal notificar mitjançant l'ús d'un Toast quan la lletra escollida no és vàlida i el motiu pel qual no ho és.

## Requisit 1.2: Afegir una pantalla inicial (Activity) amb un botó per iniciar partida.

El botó ha d'estar centrat vertical i horitzontalment i ha de contenir el text "Start Game".
Quan l'usuari fa clic al botó ha de començar la partida.
Quan s'acaba la partida s'ha de retornar a aquesta pantalla. NOTA: Al finalitzar el joc, no cal esperar cap Delay, simplement retornar.

## Requisit 1.3: Afegir el nom de l'usuari que jugarà. Cal:

Afegir un quadre de text (EditText) a la nova pantalla inicial en la que demani el nom del jugador.
El nom del jugador s'ha d'enviar des de la nova activitat i s'ha de rebre a la Activity del joc.
No s'ha de permetre el nom d'usuari en blanc.
Mostrar el nom del jugador a la pantalla de joc amb un text com Usuari: (nom d'usuari rebut).

## Requisit 1.4: Traduir l'aplicació a un altre idioma. O bé Català o bé Castellà. NOTA: No cal modificar les paraules amagades del joc, només l'idioma de l'aplicació.

# 2. MVVM + databinding + livedata

Completar: requisits mínims per aconseguir el 5.

## Requisit 2: Iniciar la refactorització de l'aplicació cap a l'ús del patró de disseny MVVM amb databinding i livedata.
Refactoritza / re-estructura l'aplicació per seguir el model MVVM.
Afegeix DataBinding + LiveData a la view tvVisibleWord.

Notes:

La resta de views poden seguir actualitzant-se com ho fan actualment. No cal afegir més DataBinding ni LiveData del que es demana.
S'han detectat alguns problemes amb l'última versió de LiveData (la 2.6). En cas d'experimentar problemes, no dubteu en utilitzar l'anterior (2.5).
Al fer la conversió a MVVM, la declaració i inicialització de Game ja no es pot fer a l'activitat.
Per simplificar, el ViewModel pot retornar la instància de Game (amb un getter) per ser utilitzat des de l'activitat per aquells mètodes que no refactoritzeu al MVVM. Per tant, no cal moure totes les validacions del joc ni la mecànica d'actualització de les views fora de l'activitat. Només cal refactoritzar la view indicada.

# 3. Persistència

Completar: Requisits mínims per aconseguir el 5.

## Requisit 3: Al finalitzar el joc, persistir en DB l'estat final del joc.

Mínim de camps a persitir: secretWord, currentRound, playerWon, lettersChosenStr.
És vàlida tant una persistència en local com en remot a Firebase.
Per simplificar, no cal preocupar-se des de quina classe persistir. És a dir, aquí no cal complir el model MVVM.

# 4. RecyclerViews

## Requisit 4.1: Implementar un RecyclerView que mostri l'historial de les partides jugades.

En aquesta part de la tasca, el que es demana és la creació d'un RecyclerView que mostri una llista de totes les partides que ha jugat l'usuari.
Cada element de la llista hauria de contenir, almenys, la paraula secreta de la partida, el nombre de rondes jugades, si l'usuari ha guanyat la partida i la cadena de lletres escollides.

## Requisit 4.2: Implementar un adaptador per a la llista de partides.

Per a que el RecyclerView pugui mostrar la informació correctament, és necessari implementar un adaptador.
Aquest adaptador ha d'assignar les dades de cada partida als elements del layout d'aquesta llista.

## Requisit 4.3: Implementar un gestor de disseny (LayoutManager) per a la llista de partides.

En aquesta part de la tasca, hauràs de triar i implementar un gestor de disseny que s'adapti al disseny que vulguis per a la teva llista de partides.
Aquest pot ser un LinearLayoutManager si vols una llista vertical o horitzontal, un GridLayoutManager si vols una graella, entre altres.

## Requisit 4.4: Implementar un OnClickListener per a cada element de la llista.

Finalment, hauràs de donar a cada element de la llista la capacitat de ser "clickable".
Això vol dir que, quan l'usuari faci clic en una partida de la llista, s'executi una acció determinada. Podria ser, per exemple, mostrar més detalls sobre la partida.
Recorda que per a implementar un RecyclerView correctament, hauràs de definir el RecyclerView en el teu fitxer de layout, inicialitzar-lo en la teva Activity o Fragment, assignar-li un adaptador i un LayoutManager i, finalment, proporcionar-li les dades que vols que mostri.