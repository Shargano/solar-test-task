package rt.solar.endpoints

import rt.solar.dto.{SOFErrorResponse, SOFResponse}
import sttp.tapir._
import sttp.tapir.json.circe._
import sttp.tapir.server.ServerDefaults.StatusCodes

trait SOFEndpoints {

  protected val searchEndpoint: Endpoint[(String, Int, String, String, String), SOFErrorResponse, SOFResponse, Nothing] =
    endpoint.get
      .in("2.2" / "search")
      .in(query[String]("tagged"))
      .in(query[Int]("pagesize"))
      .in(query[String]("site"))
      .in(query[String]("order"))
      .in(query[String]("sort"))
      .errorOut(oneOf(statusMapping(StatusCodes.error, jsonBody[SOFErrorResponse])))
      .out(jsonBody[SOFResponse])
      .summary("Searches SOF site by the given tags")
      .tag("Search")

}
