package rt.solar.dto

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class SOFResponse(items: Seq[SOFResponseItem])

object SOFResponse {
  implicit val codec: Codec[SOFResponse] = deriveCodec
}
