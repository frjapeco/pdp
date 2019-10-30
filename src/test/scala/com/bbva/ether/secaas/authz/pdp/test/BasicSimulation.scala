import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class BasicSimulation extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")
    .contentTypeHeader("application/json")

  object DecisionController {

    val evaluate: ChainBuilder = exec(
      http("DecisionEndpoint")
        .post("/api/v1/decision")
        .body(RawFileBody("payload.json")).asJson
    )

  }

  val sampleScenario: ScenarioBuilder = scenario("sampleScenario").exec(DecisionController.evaluate)

  setUp(
    sampleScenario.inject(atOnceUsers(1000))
      .protocols(httpProtocol)
  )

}