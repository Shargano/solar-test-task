package rt.solar

import cats.Applicative
import cats.effect._
import cats.syntax.flatMap._
import cats.syntax.functor._
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import rt.solar.config.{Config, ConfigKeeper}
import rt.solar.routers.ApiRouter
import rt.solar.service.{SOFClient, SearchService}
import sttp.client.asynchttpclient.cats.AsyncHttpClientCatsBackend

object App extends IOApp {

  implicit def clock[F[_]: Sync]: Clock[F] = Clock.create

  def config[F[_]: Applicative]: Resource[F, Config] =
    Resource.liftF(ConfigKeeper.getFull[F])

  def init[F[_]: Concurrent: ContextShift](cfg: Config): F[HttpRoutes[F]] =
    AsyncHttpClientCatsBackend[F]().map { implicit backend =>
      val client: SOFClient[F]      = SOFClient(cfg.SOFApi)
      val service: SearchService[F] = SearchService(cfg.SOFApi, client)

      ApiRouter(service).routes
    }

  def runProgram[F[_]: ConcurrentEffect: Timer: ContextShift]: F[ExitCode] =
    config.use { cfg =>
      init(cfg).flatMap { route =>
        BlazeServerBuilder[F]
          .bindHttp(cfg.server.port, cfg.server.host)
          .withHttpApp(Router("/" -> route).orNotFound)
          .serve
          .compile
          .drain
          .as(ExitCode.Success)
      }
    }

  override def run(args: List[String]): IO[ExitCode] = runProgram[IO]

}
