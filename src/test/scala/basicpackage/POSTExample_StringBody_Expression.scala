package basicpackage

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class POSTExample_StringBody_Expression extends Simulation {


  val apiKey = "9d02943b6f854cd893ea667e6a5a40ac"
  val linkshorten = csv("data/linkshortenUpdated.csv").circular //this csv is stored at data folder
  val title = "mytitle241" //keep it as it is and add to session as attribute using set method
  val slashtag="mytaghundredthree"
  var destination=""
  //Create http configuration
  val httpProtocol = http.baseUrl("https://api.rebrandly.com").header("apiKey", apiKey)


  val scn = scenario("CreateLink")
    .feed(linkshorten)
    .exec(session=>
    {
      destination=session("destination").as[String]
      println(s"""{"destination":"$destination","slashtag":"$slashtag","title":"$title"}""")
      session
    })
    .exec(http("createnewlink").post("/v1/links").body(StringBody( session=> s"""{"destination":"$destination","slashtag":"$slashtag","title":"$title"}""")).asJson) //send post request

  //inject user to send http request
  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol) //inject one user
}
