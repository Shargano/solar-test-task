package rt.solar.config

import sttp.model.Uri

final case class SOFApiConfig(limit: Int, pagesize: Int, url: String, schema: String = "http") {
  val getUri: Uri = Uri(schema, url)
}
