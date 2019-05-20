package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class RawFileBody_Post extends Simulation {

  val httpConf= http.baseUrl("https://api.rebrandly.com/").header("apikey","9d02943b6f854cd893ea667e6a5a40ac")
  val scn =scenario("CreateLink")
      .exec(http("CreateLinkRequest").post("/v1/links").body(RawFileBody("bodies/myFile.json")).asJson)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)
}