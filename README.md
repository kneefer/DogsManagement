Dogs Management
=

Management Psów Hotelowych

Projekt w Javie, wykorzystujący technologie:
- GlassFish 4 (jako serwer aplikacji)
- MySql (bazy danych)
- Primefaces (facelety JSF aplikacji)

Istnieją 4 tabele, powiązane ze sobą kluczami obcymi:
- Psy
- Właściciele
- Rasy
- Typy właścicieli


Pies jest połączony w relacji @ManyToOne z właścicielami i rasami

Właściciel jest połączony w relacji @ManyToOne z typami właścicieli
