package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Open_sequencesteps extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  /*
    5 users simultaneously arrive at first
    Nothing happens for 10 seconds
    In another 30 seconds 200 users arrive
    Further to that, 10 users arrive in another 30 seconds
  */
  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }


  setUp(scn.
    inject(atOnceUsers(5),nothingFor(10  seconds),heavisideUsers(200) during(30 seconds),rampUsers(10) during(30 seconds)))
    .protocols(httpConf).maxDuration(70 seconds)

}