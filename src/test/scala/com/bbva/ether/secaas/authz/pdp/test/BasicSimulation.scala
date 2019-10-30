import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("application/json")
  }

  object DecisionEndpoint {

    val decisionEndpoint = exec(http("DecisionEndpoint")
        .post("/api/v1/decision")
        .body(StringBody("{}")))
  }

  val sampleScenario = scenario("MyScenario").exec(DecisionEndpoint.decisionEndpoint)



}