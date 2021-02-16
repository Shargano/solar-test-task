package rt.solar.dto

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

final case class SOFResponseItem(tags: Seq[String], is_answered: Boolean)

object SOFResponseItem {
  implicit val codec: Codec[SOFResponseItem] = deriveCodec
}
