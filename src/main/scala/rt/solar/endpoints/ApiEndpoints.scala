package rt.solar.endpoints

import rt.solar.dto.Response
import sttp.tapir._
import sttp.tapir.json.circe._

trait ApiEndpoints {

  protected val searchEndpoint: Endpoint[Seq[String], Unit, Response, Nothing] =
    endpoint.get.in("search").in(query[Seq[String]]("tag")).out(jsonBody[Response]).summary("Searches SOF site by the given tags").tag("Search")

}
