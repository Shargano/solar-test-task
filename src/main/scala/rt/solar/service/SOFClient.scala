package rt.solar.service

import cats.effect.Sync
import cats.implicits._
import rt.solar.config.SOFApiConfig
import rt.solar.dto.{SOFErrorResponse, SOFResponse}
import rt.solar.endpoints.SOFEndpoints
import sttp.client.{NothingT, SttpBackend}
import sttp.tapir.client.sttp._

class SOFClient[F[_]: Sync](config: SOFApiConfig)(implicit
                                                  backend: SttpBackend[F, Nothing, NothingT]
) extends SOFEndpoints {

  private val baseUrl = config.getUri
  private val site    = "stackoverflow"
  private val order   = "desc"
  private val sort    = "creation"

  def searchByTag(tag: String): F[Either[SOFErrorResponse, SOFResponse]] =
    searchEndpoint.toSttpRequestUnsafe(baseUrl).apply(tag, config.pagesize, site, order, sort).send().map(_.body)

}

object SOFClient {

  def apply[F[_]: Sync](config: SOFApiConfig)(implicit
                                              backend: SttpBackend[F, Nothing, NothingT]
  ): SOFClient[F] =
    new SOFClient(config)
}
