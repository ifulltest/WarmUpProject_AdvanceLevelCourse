package sessionpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicGetBusDetails_Check extends Simulation {
//curl -v  -X GET "http://developer.goibibo.com/api/bus/search/?app_id=701491b9&app_key=a33c1f63da644e3ce7ba565599809d0f&format=json&source=bangalore&destination=hyderabad&dateofdeparture=20190426"

  val httpConf = http.baseUrl("http://developer.goibibo.com")

  val appId="701491b9"
  val appKey="a33c1f63da644e3ce7ba565599809d0f"
  val dateofDepart="20190426"

  val csvfeeder_bus= csv("data/busdetails.csv").circular

  val scn = scenario("GetBusDetails").feed(csvfeeder_bus)
    .exec(http("getbusrequest").get("/api/bus/search/?app_id="+ appId+"&app_key="+ appKey+"&format=json&source=${source}&destination=${destination}&dateofdeparture="+ dateofDepart)
    .check(jsonPath("$.data..skey").find.saveAs("searchKey")))
//     .check(jsonPath("$.data..skey").find(occurrence = 5).saveAs("searchKey")))
//     .check(jsonPath("$.data..skey").findAll.saveAs("searchKey")))
      .exec(session =>
      {
        println(session)
        session
      })

  setUp(scn.inject(atOnceUsers(2))).protocols(httpConf)
}
