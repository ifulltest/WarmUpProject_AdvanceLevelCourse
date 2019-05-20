package injectionprofile

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class Assignment_Staircasepattern extends Simulation {

  val httpConf = http.baseUrl("http://newtours.demoaut.com")

  val scn = scenario("basicgetscenario").forever() {
    pace(2)
      .exec(http("basicgetrequest").get("/mercurycruise.php"))
  }


  setUp(scn.inject(
    atOnceUsers(5)
    ,nothingFor(30 seconds),rampUsers(5)during(10 seconds)
    ,nothingFor(30 seconds),rampUsers(5)during(10 seconds)
    ,nothingFor(30 seconds),rampUsers(5)during(10 seconds)
    ,nothingFor(30 seconds), rampUsers(5)during(10 seconds)
  )).protocols(httpConf).maxDuration(300 seconds)
}
