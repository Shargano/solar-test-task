package rt.solar.service

import cats.effect.Concurrent
import cats.implicits._
import fs2.Stream
import org.slf4j.LoggerFactory
import rt.solar.config.SOFApiConfig
import rt.solar.converter.ResponseConverter
import rt.solar.dto.{Response, SOFErrorResponse, SOFResponse}

class SearchService[F[_]: Concurrent](config: SOFApiConfig, client: SOFClient[F]) {

  private val log = LoggerFactory.getLogger(this.getClass)

  def searchInfoByTags(tags: Seq[String]): F[Response] =
    parallelFor(tags).compile.toList.map { cc =>
      val withoutErrors = collectIgnoringErrors(cc)
      ResponseConverter.convert(withoutErrors)
    }

  private def parallelFor(tags: Seq[String]): Stream[F, Either[SOFErrorResponse, SOFResponse]] =
    Stream[F, String](tags: _*).parEvalMap(config.limit)(client.searchByTag)

  private def collectIgnoringErrors(seq: Seq[Either[SOFErrorResponse, SOFResponse]]): Seq[SOFResponse] =
    seq.foldLeft(Seq.empty[SOFResponse]) { (acc, el) =>
      el.fold(handleError(acc), _ +: acc)
    }

  // TODO -- yeah, such a bad code below...;
  // It should be wrapped into Writer monad;
  private def handleError[A](toRet: A)(error: SOFErrorResponse): A = {
    log.warn("Retrieved error from SOF API [errorId = {}, msg = {}]", error.error_id, error.error_message)
    toRet
  }

}

object SearchService {

  def apply[F[_]: Concurrent](config: SOFApiConfig, client: SOFClient[F]): SearchService[F] =
    new SearchService(config, client)

}
