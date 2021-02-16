package rt.solar.dto

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class ResponseItem(name: String, total: Int, answered: Int)

object ResponseItem {
  implicit val codec: Codec[ResponseItem] = deriveCodec
}
