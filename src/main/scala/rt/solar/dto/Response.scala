package rt.solar.dto

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class Response(tags: Seq[ResponseItem])

object Response {
  implicit val codec: Codec[Response] = deriveCodec
}
