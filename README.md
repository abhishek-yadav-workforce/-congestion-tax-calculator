# congestion-tax-calculator

This project is for city congestion tax calculation.
There are number of table in which product owner can feed data depending on the cities.

Right now the project contains hardcoded data but for product owner we can also expose api to feed tax related data.

| City                         | TaxChargesPlans    |
| ------------------------------| -------------       |
| String CityName               | String cityId       |
| List exemptTaxVehicle         | Date endTime        |
| List weekendDays              | Date startTime      |
| List publicHolidays           | BigDecimal charges  |
| boolean dayBeforeConcession   |       
| List noTaxMonths              |         
| List vehicleTypes             |         
| List taxChargesPlans          |    

### Request to calucate congestion tax
HTTP Method - POST
  #curl -i -H 'Accept: application/json' http://localhost:8080/api/calculate-tax
  
      Request Body
      {
          "vehicleType": "CAR",
          "cityName": "gothenburg",
          "entryTimes": [
              "2018-09-04T10:00:00",
              "2018-09-04T12:44:46",
              "2018-09-04T15:44:46",
              "2018-09-04T18:44:46"
          ]
      }

       Response Body
       {
          "totalTax": 57.00
       }
      

