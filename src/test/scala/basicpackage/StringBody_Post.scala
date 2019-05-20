package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class StringBody_Post extends Simulation {

  val httpConf= http.baseUrl("https://api.rebrandly.com/").header("apikey","9d02943b6f854cd893ea667e6a5a40ac")
  val scn =scenario("CreateLink")
      .exec(http("CreateLinkRequest").post("/v1/links").body(StringBody("""{"destination":"https://www.qamilestone.com/blog/gatling-post-request-body-using-elfilebody-method","slashtag":"newPosttag","title":"QAMilestone Posts"}""")).asJson)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpConf)
}