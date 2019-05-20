package request

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class AssignmentGetHotelDetails extends Simulation {

  val httpConf = http.baseUrl("http://developer.goibibo.com")

  val appId = "701491b9"
  val appKey = "a33c1f63da644e3ce7ba565599809d0f"
  val hotelcsvFeeder = csv("data/HotelRequestDataFile.csv").circular

  val scn = scenario("HotelDetails").feed(hotelcsvFeeder).exec(http("getHotelList").get("/api/voyager/get_hotels_by_cityid/?app_id=" + appId + "&app_key=" + appKey + "&city_id=${CityId}").check(jsonPath("$.data..hotel_geo_node._id").find.saveAs("myHotelId")))
    .exec(session => {

      val hotelId = session("myHotelId").as[String]

      println(hotelId)

      session
    }

    )

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)
}
