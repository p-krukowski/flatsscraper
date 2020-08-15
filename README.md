# Flats Scraper
App that allows web scraping flats sale offers and saving them into PostgreSQL database.

## Getting started
1. Set database connection info in application.properties.
2. Create "flats" table in your database.
3. Inject bean
```
MorizonFlatScraper
```
4. Use function:
```
fetchFlatsAfterDate(LocalDate limitingDate)
```
limitingDate - minimum date of offer


App will fetch flats offers from morizon.pl and save it to table "flats".
