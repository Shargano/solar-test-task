package rt.solar.config

import cats.Applicative
import pureconfig.ConfigSource
import pureconfig.generic.auto._

object ConfigKeeper {

  def getFull[F[_]: Applicative]: F[Config] =
    Applicative[F].pure(ConfigSource.defaultApplication.loadOrThrow[Config])

}
