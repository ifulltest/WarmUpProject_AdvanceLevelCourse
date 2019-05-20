package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicGetScript_Open_heavisideUsers extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }


  setUp(scn.inject(heavisideUsers(20) during(10 seconds))).protocols(httpConf).maxDuration(40 seconds)

  //  setUp(scn.inject(rampUsers(20) during(10 seconds))).protocols(httpConf).maxDuration(40 seconds)
}
