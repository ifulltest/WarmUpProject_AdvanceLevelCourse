package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class BasicGetScript_session extends Simulation {

  val httpConf =http.baseUrl("https://api.openbrewerydb.org")

  val scn = scenario("basicgetscenario").exec(http("basicgetrequest").get("/breweries")
    .check(bodyString.saveAs("myresponse")))
      .exec {session =>

        val response1 = session("myresponse").as[String]

        println(response1)

        session}

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)

}
