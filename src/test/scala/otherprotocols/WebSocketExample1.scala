package otherprotocols


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class WebSocketExample1 extends Simulation {

  //Configuration
  // "http://demos.kaazing.com"
  //ws://demos.kaazing.com/echo?.kl=Y
  val httpProtocol = http.baseUrl("http://demos.kaazing.com").wsBaseUrl("ws://demos.kaazing.com")

  val scn = scenario("testwebsocketscenario")
    .exec(http("firsthttprequest").get("/")).pause(2 seconds)
    .exec(
      ws("opensocket").connect("/echo?.kl=Y")
        .onConnected(exec(ws("sendmessage").sendText("hello")).pause(2 seconds)
          exec(ws("send second text").sendText("I am from qamilestone")
            .await(20)(ws.checkTextMessage("check1").check(regex(".*qamilestone.*").saveAs("mystring")))
         )))
    .exec(session=>
      {
        println("hello"+ session("mystring").as[String])
        session
      }
    )
    .exec(ws("closeconnection").close)

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)

}
