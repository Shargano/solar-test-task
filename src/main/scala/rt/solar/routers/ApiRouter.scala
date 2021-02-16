package rt.solar.routers

import cats.effect.{ContextShift, Sync}
import cats.implicits._
import org.http4s.HttpRoutes
import rt.solar.endpoints.ApiEndpoints
import rt.solar.service.SearchService
import sttp.tapir.docs.openapi._
import sttp.tapir.openapi.circe.yaml._
import sttp.tapir.server.http4s._
import sttp.tapir.swagger.http4s.SwaggerHttp4s

class ApiRouter[F[_]: Sync: ContextShift](service: SearchService[F]) extends ApiEndpoints {

  def searchRoute: HttpRoutes[F] =
    searchEndpoint.toRoutes { tags =>
      service.searchInfoByTags(tags).map(_.asRight)
    }

  val docsRoute: HttpRoutes[F] = {
    val info = searchEndpoint.toOpenAPI("Test", "1.0.0")
    new SwaggerHttp4s(info.toYaml).routes
  }

  val routes: HttpRoutes[F] = searchRoute <+> docsRoute

}

object ApiRouter {

  def apply[F[_]: Sync: ContextShift](service: SearchService[F]): ApiRouter[F] =
    new ApiRouter(service)

}
