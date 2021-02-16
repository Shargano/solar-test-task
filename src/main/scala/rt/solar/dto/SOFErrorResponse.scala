package rt.solar.dto

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class SOFErrorResponse(error_id: Int, error_message: String)

object SOFErrorResponse {
  implicit val codec: Codec[SOFErrorResponse] = deriveCodec
}
